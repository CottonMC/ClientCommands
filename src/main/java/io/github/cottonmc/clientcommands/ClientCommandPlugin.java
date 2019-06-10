package io.github.cottonmc.clientcommands;

import com.mojang.brigadier.CommandDispatcher;

public interface ClientCommandPlugin {
	void registerCommands(CommandDispatcher<CottonClientCommandSource> dispatcher);
}
