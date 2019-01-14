package io.github.cottonmc.clientcommands.mixin;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientCommandSource;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.command.CommandException;
import net.minecraft.text.StringTextComponent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class PlayerMixin {
    @Shadow @Final protected MinecraftClient client;

    @Inject(method = "sendChatMessage", at = @At("HEAD"), cancellable = true)
    private void onChatMessage(String msg, CallbackInfo info) {
        if (msg.length() < 2 || !msg.startsWith("/")) return;
        boolean cancel = false;
        try {
            // The game freezes when heavy commands.
            int result = client.getNetworkHandler().method_2886().execute(
                msg.substring(1), new ClientCommandSource(client.getNetworkHandler(), client)
            );
            if (result != 0)
                // Prevent sending the message
                cancel = true;
        } catch (CommandException e) {
            client.player.addChatMessage(e.getMessageComponent(), false);
            cancel = true;
        } catch (CommandSyntaxException e) {
            client.player.addChatMessage(new StringTextComponent(e.getRawMessage().getString()), false);
            cancel = true;
        }

        if (cancel)
            info.cancel();
    }
}
