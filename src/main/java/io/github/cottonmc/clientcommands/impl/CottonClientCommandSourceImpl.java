package io.github.cottonmc.clientcommands.impl;

import io.github.cottonmc.clientcommands.CottonClientCommandSource;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.ChatFormat;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientCommandSource;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;

@Environment(EnvType.CLIENT)
public final class CottonClientCommandSourceImpl extends ClientCommandSource implements CottonClientCommandSource {
    private final MinecraftClient client;

    public CottonClientCommandSourceImpl(ClientPlayNetworkHandler networkHandler, MinecraftClient client) {
        super(networkHandler, client);
        this.client = client;
    }

    @Override
    public void sendFeedback(Component component) {
        client.player.addChatMessage(component, false);
    }

    @Override
    public void sendError(Component component) {
        client.player.addChatMessage(new TextComponent("").append(component).applyFormat(ChatFormat.RED), false);
    }
}
