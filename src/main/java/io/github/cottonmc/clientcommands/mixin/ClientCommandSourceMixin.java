package io.github.cottonmc.clientcommands.mixin;

import io.github.cottonmc.clientcommands.CottonClientCommandSource;
import net.minecraft.ChatFormat;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientCommandSource;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ClientCommandSource.class)
public abstract class ClientCommandSourceMixin implements CottonClientCommandSource {
    @Shadow @Final
    private MinecraftClient client;

    @Override
    public void sendFeedback(Component component) {
        client.player.addChatMessage(component, false);
    }

    @Override
    public void sendError(Component component) {
        client.player.addChatMessage(new TextComponent("").append(component).applyFormat(ChatFormat.RED), false);
    }
}
