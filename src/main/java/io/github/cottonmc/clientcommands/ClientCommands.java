package io.github.cottonmc.clientcommands;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Lazy;

import java.util.List;

public final class ClientCommands {
    public static final String ENTRYPOINT_TYPE = "cotton-client-commands";
    private static final Lazy<List<ClientCommandPlugin>> PLUGINS = new Lazy<>(
            () -> FabricLoader.getInstance().getEntrypoints(ENTRYPOINT_TYPE, ClientCommandPlugin.class)
    );

    public static List<ClientCommandPlugin> getPlugins() {
        return PLUGINS.get();
    }
}
