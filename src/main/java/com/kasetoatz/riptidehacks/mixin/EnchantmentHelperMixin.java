package com.kasetoatz.riptidehacks.mixin;

import com.kasetoatz.riptidehacks.RiptideHacks;
import net.minecraft.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentHelper.class)
public abstract class EnchantmentHelperMixin {
    @Inject(method="getTridentSpinAttackStrength", at=@At("HEAD"), cancellable = true)
    private static void getTridentSpinAttackStrength(CallbackInfoReturnable<Float> cir)
    {
        if (RiptideHacks.toggled)
        {
            cir.setReturnValue(3.f);
        }
    }
}