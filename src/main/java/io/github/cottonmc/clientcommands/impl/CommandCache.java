package io.github.cottonmc.clientcommands.impl;

import com.mojang.brigadier.CommandDispatcher;
import io.github.cottonmc.clientcommands.ClientCommands;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.server.command.CommandSource;

import java.util.Collections;

@Environment(EnvType.CLIENT)
public final class CommandCache {
	private CommandCache() {}

	private static final CommandDispatcher<CommandSource> DISPATCHER = new CommandDispatcher<>();

	public static void build() {
		ClientCommands.getPlugins().forEach(provider -> provider.registerCommands(DISPATCHER));
	}

	public static boolean hasCommand(String name) {
		return DISPATCHER.findNode(Collections.singleton(name)) != null;
	}
}
