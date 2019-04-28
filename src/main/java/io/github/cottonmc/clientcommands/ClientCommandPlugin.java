package io.github.cottonmc.clientcommands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.CommandSource;

public interface ClientCommandPlugin {
	void registerCommands(CommandDispatcher<CommandSource> dispatcher);
}
