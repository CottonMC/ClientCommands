package io.github.cottonmc.clientcommands.mixin;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.CommandDispatcher;
import io.github.cottonmc.clientcommands.ClientCommands;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.packet.CommandTreeClientPacket;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.command.CommandSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class NetworkHandlerMixin {
    @Shadow private CommandDispatcher<CommandSource> commandDispatcher;

    @Inject(method = "onCommandTree", at = @At("RETURN"))
    private void onCommandTree(CommandTreeClientPacket packet, CallbackInfo info) {
        addCommands();
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onConstruct(MinecraftClient mc, Screen screen, ClientConnection cc, GameProfile gp, CallbackInfo info) {
        addCommands();
    }

    private void addCommands() {
        ClientCommands.getCommands().forEach(c -> c.accept(commandDispatcher));
    }
}
