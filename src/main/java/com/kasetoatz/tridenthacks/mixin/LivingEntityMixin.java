package com.kasetoatz.tridenthacks.mixin;

import com.kasetoatz.tridenthacks.config.Config;
import com.kasetoatz.tridenthacks.TridentHacks;
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
        if (Config.toggleRiptide && TridentHacks.noNormalRiptide() && TridentHacks.lastTridentUse > System.currentTimeMillis() - 1000)
        {
             if (entity == TridentHacks.client.player)
             {
                 cir.setReturnValue(true);
             }
        }
    }
}