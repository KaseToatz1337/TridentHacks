package com.kasetoatz.tridenthacks.mixin;

import com.kasetoatz.tridenthacks.config.Config;
import com.kasetoatz.tridenthacks.TridentHacks;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public abstract class ClientConnectionMixin {
    @Inject(method="send*", at=@At("HEAD"), cancellable = true)
    private void send(Packet<?> packet, CallbackInfo ci)
    {
        if (Config.toggleRiptide)
        {
            ClientPlayerEntity player = TridentHacks.client.player;
            if (player != null)
            {
                if (packet instanceof PlayerActionC2SPacket && TridentHacks.noNormalRiptide() && ((PlayerActionC2SPacket)packet).getAction() == PlayerActionC2SPacket.Action.RELEASE_USE_ITEM)
                {
                    ci.cancel();
                }
                else if (Config.noFallDamage && packet instanceof PlayerMoveC2SPacket && !TridentHacks.onGround && !player.isFallFlying())
                {
                    ((PlayerMoveC2SPacketMixin)packet).setOnGround(true);
                }
            }
        }
    }
}