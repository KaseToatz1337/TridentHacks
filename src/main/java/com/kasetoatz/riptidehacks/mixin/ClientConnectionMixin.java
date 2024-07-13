package com.kasetoatz.riptidehacks.mixin;

import com.kasetoatz.riptidehacks.RiptideHacks;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public abstract class ClientConnectionMixin {
    @Inject(method="send*", at=@At("HEAD"), cancellable = true)
    private void send(Packet<?> packet, CallbackInfo ci)
    {
        if (packet instanceof PlayerActionC2SPacket)
        {
            ClientPlayerEntity player = RiptideHacks.client.player;
            if (player != null && player.getActiveItem().getItem() == Items.TRIDENT)
            {
                ci.cancel();
            }
        }
    }
}
