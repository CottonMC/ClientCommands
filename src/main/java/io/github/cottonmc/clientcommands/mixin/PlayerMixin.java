package io.github.cottonmc.clientcommands.mixin;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.github.cottonmc.clientcommands.impl.CommandCache;
import net.minecraft.ChatFormat;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientCommandSource;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.command.CommandException;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public abstract class PlayerMixin {
    @Shadow @Final protected MinecraftClient client;

    @Shadow public abstract void addChatMessage(Component textComponent_1, boolean boolean_1);

    @Inject(method = "sendChatMessage", at = @At("HEAD"), cancellable = true)
    private void onChatMessage(String msg, CallbackInfo info) {
        if (msg.length() < 2 || !msg.startsWith("/")) return;
        if (!CommandCache.hasCommand(msg.substring(1).split(" ")[0])) return;
        boolean cancel = false;
        try {
            // The game freezes when using heavy commands. Run your heavy code somewhere else pls
            int result = client.getNetworkHandler().getCommandDispatcher().execute(
                msg.substring(1), new ClientCommandSource(client.getNetworkHandler(), client)
            );
            if (result != 0)
                // Prevent sending the message
                cancel = true;
        } catch (CommandException e) {
            addChatMessage(e.getMessageComponent().applyFormat(ChatFormat.RED), false);
            cancel = true;
        } catch (CommandSyntaxException e) {
            addChatMessage(new TextComponent(e.getMessage()).applyFormat(ChatFormat.RED), false);
            cancel = true;
        } catch (Exception e) {
            addChatMessage(new TranslatableComponent("command.failed").applyFormat(ChatFormat.RED), false);
            cancel = true;
        }

        if (cancel)
            info.cancel();
    }
}
