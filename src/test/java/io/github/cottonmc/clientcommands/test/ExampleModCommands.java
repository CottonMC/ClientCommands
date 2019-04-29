package io.github.cottonmc.clientcommands.test;

import com.mojang.brigadier.CommandDispatcher;
import io.github.cottonmc.clientcommands.ArgumentBuilders;
import io.github.cottonmc.clientcommands.ClientCommandPlugin;
import io.github.cottonmc.clientcommands.Feedback;
import net.minecraft.server.command.CommandSource;
import net.minecraft.text.StringTextComponent;

public class ExampleModCommands implements ClientCommandPlugin {
    @Override
    public void registerCommands(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(ArgumentBuilders.literal("client-commands").executes(
                source -> {
                    Feedback.sendFeedback(new StringTextComponent("Hello, world!"));
                    return 1;
                }
        ));
    }
}
