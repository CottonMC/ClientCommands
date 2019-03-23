package io.github.cottonmc.clientcommands.test;

import io.github.cottonmc.clientcommands.ArgumentBuilders;
import io.github.cottonmc.clientcommands.ClientCommands;
import io.github.cottonmc.clientcommands.Feedback;
import net.fabricmc.api.ModInitializer;
import net.minecraft.text.StringTextComponent;

public class ExampleMod implements ModInitializer {
    @Override
    public void onInitialize() {
        ClientCommands.registerCommand(dispatcher -> {
            dispatcher.register(ArgumentBuilders.literal("client-commands").executes(
                source -> {
                    Feedback.sendFeedback(new StringTextComponent("Hello, world!"));
                    return 1;
                }
            ));
        });
    }
}
