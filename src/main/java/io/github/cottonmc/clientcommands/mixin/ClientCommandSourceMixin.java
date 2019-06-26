package io.github.cottonmc.clientcommands.mixin;

import io.github.cottonmc.clientcommands.CottonClientCommandSource;
import net.minecraft.util.Formatting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientCommandSource;
import net.minecraft.text.Text;
import net.minecraft.text.LiteralText;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ClientCommandSource.class)
public abstract class ClientCommandSourceMixin implements CottonClientCommandSource {
    @Shadow @Final
    private MinecraftClient client;

    @Override
    public void sendFeedback(Text text) {
        client.player.addChatMessage(text, false);
    }

    @Override
    public void sendError(Text text) {
        client.player.addChatMessage(new LiteralText("").append(text).formatted(Formatting.RED), false);
    }
}
