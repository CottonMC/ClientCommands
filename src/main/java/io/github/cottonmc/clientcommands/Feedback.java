package io.github.cottonmc.clientcommands;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Formatting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.text.LiteralText;

/**
 * Client-side replacements for ServerCommandSource.send[Feedback, Error]
 *
 * @deprecated Use {@link CottonClientCommandSource} instead.
 */
@Environment(EnvType.CLIENT)
@Deprecated
public final class Feedback {
    private Feedback() {}

    /**
     * Sends a chat message to the client player. A replacement for ServerCommandSource.sendFeedback().
     *
     * @param text the message
     */
    public static void sendFeedback(Text text) {
        MinecraftClient.getInstance().player.addChatMessage(text, false);
    }

    /**
     * Sends a error chat message to the client player. A replacement for ServerCommandSource.sendError().
     *
     * @param text the message
     */
    public static void sendError(Text text) {
        MinecraftClient.getInstance().player.addChatMessage(
                new LiteralText("").append(text).formatted(Formatting.RED), false
        );
    }
}
