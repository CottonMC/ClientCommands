package io.github.cottonmc.clientcommands;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.command.CommandSource;
import net.minecraft.text.StringTextComponent;
import net.minecraft.text.TextComponent;
import net.minecraft.text.TextFormat;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public final class ClientCommands implements ModInitializer {
    private static final Set<Consumer<CommandDispatcher<CommandSource>>> commands = new HashSet<>();

    public static Collection<Consumer<CommandDispatcher<CommandSource>>> getCommands() {
        return Collections.unmodifiableSet(commands);
    }

    /**
     * Registers a function that adds client-side commands.
     * The provided {@link CommandDispatcher} will exist only on the client.
     *
     * @param command the function
     */
    public static void registerCommand(Consumer<CommandDispatcher<CommandSource>> command) {
        // TODO: (Maybe) error if a common/dedicated command with the same base is already registered
        if (FabricLoader.INSTANCE.getEnvironmentHandler().getEnvironmentType() == EnvType.CLIENT)
            commands.add(command);
    }

    // Alternatives for ServerCommandSource.send[Feedback, Error]

    /**
     * Sends a chat message to the client player. A replacement for ServerCommandSource.sendFeedback().
     *
     * @param textComponent the message
     */
    @Environment(EnvType.CLIENT)
    public static void sendFeedback(TextComponent textComponent) {
        MinecraftClient.getInstance().player.addChatMessage(textComponent, false);
    }

    /**
     * Sends a error chat message to the client player. A replacement for ServerCommandSource.sendError().
     *
     * @param textComponent the message
     */
    @Environment(EnvType.CLIENT)
    public static void sendError(TextComponent textComponent) {
        MinecraftClient.getInstance().player.addChatMessage(
            new StringTextComponent("").append(textComponent).applyFormat(TextFormat.RED), false
        );
    }

    @Override
    public void onInitialize() {

    }
}
