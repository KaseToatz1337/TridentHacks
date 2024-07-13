package com.kasetoatz.riptidehacks.mixin;

import com.kasetoatz.riptidehacks.RiptideHacks;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Inject(method="isUsingRiptide", at=@At("HEAD"), cancellable = true)
    private void isUsingRiptide(CallbackInfoReturnable<Boolean> cir)
    {
        LivingEntity entity = (LivingEntity)(Object)this;
        if (RiptideHacks.shouldAnimate() && entity == RiptideHacks.client.player)
        {
            cir.setReturnValue(true);
        }
    }
}
