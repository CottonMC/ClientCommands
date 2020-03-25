package io.github.cottonmc.clientcommands.impl;

import com.google.common.collect.ImmutableList;
import io.github.cottonmc.clientcommands.ClientCommandPlugin;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Lazy;

public final class ClientCommands {
    public static final String ENTRYPOINT_TYPE = "cotton-client-commands";
    private static final Lazy<ImmutableList<ClientCommandPlugin>> PLUGINS = new Lazy<>(
            () -> ImmutableList.copyOf(
                    FabricLoader.getInstance().getEntrypoints(ENTRYPOINT_TYPE, ClientCommandPlugin.class)
            )
    );

    public static ImmutableList<ClientCommandPlugin> getPlugins() {
        return PLUGINS.get();
    }
}
