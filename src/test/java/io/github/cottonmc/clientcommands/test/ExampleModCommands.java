package io.github.cottonmc.clientcommands.test;

import com.mojang.brigadier.CommandDispatcher;
import io.github.cottonmc.clientcommands.ArgumentBuilders;
import io.github.cottonmc.clientcommands.ClientCommandPlugin;
import io.github.cottonmc.clientcommands.CottonClientCommandSource;
import net.minecraft.network.chat.TextComponent;

public class ExampleModCommands implements ClientCommandPlugin {
    @Override
    public void registerCommands(CommandDispatcher<CottonClientCommandSource> dispatcher) {
        dispatcher.register(ArgumentBuilders.literal("client-commands").executes(
                source -> {
                    source.getSource().sendFeedback(new TextComponent("Hello, world!"));
                    return 1;
                }
        ));
    }
}
