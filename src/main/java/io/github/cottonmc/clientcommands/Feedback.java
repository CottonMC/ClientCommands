package io.github.cottonmc.clientcommands;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.ChatFormat;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;

/**
 * Client-side replacements for ServerCommandSource.send[Feedback, Error]
 */
@Environment(EnvType.CLIENT)
public final class Feedback {
    private Feedback() {}

    /**
     * Sends a chat message to the client player. A replacement for ServerCommandSource.sendFeedback().
     *
     * @param textComponent the message
     */
    public static void sendFeedback(Component textComponent) {
        MinecraftClient.getInstance().player.addChatMessage(textComponent, false);
    }

    /**
     * Sends a error chat message to the client player. A replacement for ServerCommandSource.sendError().
     *
     * @param textComponent the message
     */
    public static void sendError(Component textComponent) {
        MinecraftClient.getInstance().player.addChatMessage(
                new TextComponent("").append(textComponent).applyFormat(ChatFormat.RED), false
        );
    }
}
