package com.kasetoatz.riptidehacks.mixin;

import com.kasetoatz.riptidehacks.RiptideHacks;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Inject(method="isTouchingWaterOrRain", at=@At("HEAD"), cancellable = true)
    private void isTouchingWaterOrRain(CallbackInfoReturnable<Boolean> cir)
    {
        if (((Entity)(Object)this) == RiptideHacks.client.player)
        {
            cir.setReturnValue(true);
        }
    }
}