package io.github.cottonmc.clientcommands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.CommandSource;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public final class ClientCommands {
    private static final Set<Consumer<CommandDispatcher<CommandSource>>> commands = new HashSet<>();

    public static Collection<Consumer<CommandDispatcher<CommandSource>>> getCommands() {
        return Collections.unmodifiableSet(commands);
    }

    /**
     * Registers a function that adds client-side commands.
     * The provided {@link CommandDispatcher} will exist only on the client.
     *
     * @param command the function
     */
    public static void registerCommand(Consumer<CommandDispatcher<CommandSource>> command) {
        // TODO: (Maybe) error if a common/dedicated command with the same base is already registered
        commands.add(command);
    }
}
