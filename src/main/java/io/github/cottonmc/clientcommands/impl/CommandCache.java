package io.github.cottonmc.clientcommands.impl;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.github.cottonmc.clientcommands.ClientCommands;
import io.github.cottonmc.clientcommands.CottonClientCommandSource;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.util.Collections;

@Environment(EnvType.CLIENT)
public final class CommandCache {
	private CommandCache() {}

	private static final CommandDispatcher<CottonClientCommandSource> DISPATCHER = new CommandDispatcher<>();

	public static void build() {
		ClientCommands.getPlugins().forEach(provider -> provider.registerCommands(DISPATCHER));
	}

	public static int execute(String input, CottonClientCommandSource source) throws CommandSyntaxException {
		return DISPATCHER.execute(input, source);
	}

	public static boolean hasCommand(String name) {
		return DISPATCHER.findNode(Collections.singleton(name)) != null;
	}
}
