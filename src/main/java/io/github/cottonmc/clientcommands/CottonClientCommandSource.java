package io.github.cottonmc.clientcommands;

import net.minecraft.text.Text;
import net.minecraft.server.command.CommandSource;

/**
 * Contains methods for sending messages to the player.
 * Alternative to {@code ServerCommandSource}.
 *
 * <p>(since 0.4.1) This interface is implemented on {@code ClientCommandSource} instances.
 */
public interface CottonClientCommandSource extends CommandSource {
    /**
     * Sends a message to the player (mirrors {@link net.minecraft.server.command.ServerCommandSource#sendFeedback}).
     * Equivalent of calling {@code sendFeedback(message, false)}.
     *
     * @param message the message
     */
    void sendFeedback(Text message);

    /**
     * Sends a message to the player (mirrors {@link net.minecraft.server.command.ServerCommandSource#sendFeedback}).
     *
     * @param message the message
     * @param actionBar true, if the message is displayed on the action bar.
     * @since 1.0.0
     */
    void sendFeedback(Text message, boolean actionBar);

    /**
     * Sends an error message to the player (mirrors {@link net.minecraft.server.command.ServerCommandSource#sendError}).
     *
     * @param text the message
     */
    void sendError(Text text);
}
