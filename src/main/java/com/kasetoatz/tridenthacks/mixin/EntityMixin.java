package com.kasetoatz.tridenthacks.mixin;

import com.kasetoatz.tridenthacks.config.Config;
import com.kasetoatz.tridenthacks.TridentHacks;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Inject(method="isTouchingWaterOrRain", at=@At("RETURN"), cancellable = true)
    private void isTouchingWaterOrRain(CallbackInfoReturnable<Boolean> cir)
    {
        if (Config.toggleRiptide)
        {
            if (((Entity)(Object)this) == TridentHacks.client.player)
            {
                TridentHacks.riptideConditions = cir.getReturnValue();
                cir.setReturnValue(true);
            }
        }
    }
}