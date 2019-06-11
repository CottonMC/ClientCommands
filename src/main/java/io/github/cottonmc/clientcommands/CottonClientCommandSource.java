package io.github.cottonmc.clientcommands;

import net.minecraft.network.chat.Component;
import net.minecraft.server.command.CommandSource;

/**
 * Alternative to {@code ServerCommandSource}.
 * Contains methods for sending messages to the player.
 *
 * (since 0.4.1) This interface is implemented on {@code ClientCommandSource} instances.
 */
public interface CottonClientCommandSource extends CommandSource {
    void sendFeedback(Component component);
    void sendError(Component component);
}
