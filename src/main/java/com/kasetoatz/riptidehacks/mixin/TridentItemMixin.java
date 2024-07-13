package com.kasetoatz.riptidehacks.mixin;

import com.kasetoatz.riptidehacks.RiptideHacks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
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
        if (user == RiptideHacks.client.player && user.getItemUseTime() > 10)
        {
            world.playSoundFromEntity(user, SoundEvents.ITEM_TRIDENT_RIPTIDE_3.value(), SoundCategory.AMBIENT, 1.f, 1.f);
            RiptideHacks.setLastTridentUse();
        }
    }
}
