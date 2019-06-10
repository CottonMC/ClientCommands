package io.github.cottonmc.clientcommands;

import net.minecraft.network.chat.Component;
import net.minecraft.server.command.CommandSource;

/**
 * Alternative to {@code ServerCommandSource}.
 * Contains methods for sending messages to the player.
 */
public interface CottonClientCommandSource extends CommandSource {
    void sendFeedback(Component component);
    void sendError(Component component);
}
