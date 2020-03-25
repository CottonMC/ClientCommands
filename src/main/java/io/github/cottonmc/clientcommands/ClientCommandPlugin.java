package io.github.cottonmc.clientcommands;

import com.mojang.brigadier.CommandDispatcher;

/**
 * An initializer that registers client-sided commands.
 */
public interface ClientCommandPlugin {
	/**
	 * Implementations can use the {@code dispatcher} to register their commands.
	 *
	 * @param dispatcher a client-side command dispatcher
	 */
	void registerCommands(CommandDispatcher<CottonClientCommandSource> dispatcher);
}
