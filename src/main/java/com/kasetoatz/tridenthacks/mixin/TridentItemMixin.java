package com.kasetoatz.tridenthacks.mixin;

import com.kasetoatz.tridenthacks.config.Config;
import com.kasetoatz.tridenthacks.TridentHacks;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.TridentItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TridentItem.class)
public class TridentItemMixin {
    @Inject(method="onStoppedUsing", at=@At("HEAD"))
    private void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks, CallbackInfo ci)
    {
        if (Config.toggleRiptide)
        {
            PlayerEntity player = TridentHacks.client.player;
            if (TridentHacks.noNormalRiptide() && user == player && user.getItemUseTime() > 10)
            {
                world.playSoundFromEntity(player, SoundEvents.ITEM_TRIDENT_RIPTIDE_3.value(), SoundCategory.PLAYERS, 1.F, 1.F);
            }
            TridentHacks.lastTridentUse = System.currentTimeMillis();
            TridentHacks.onGround = false;
        }
        else if (Config.returnToSameSlot && !TridentHacks.hasRiptide)
        {
            PlayerEntity player = TridentHacks.client.player;
            if (player != null)
            {
                TridentHacks.tridentSlot = (user.getOffHandStack() == stack) ? PlayerInventory.OFF_HAND_SLOT : player.getInventory().selectedSlot;
            }
        }
    }
}