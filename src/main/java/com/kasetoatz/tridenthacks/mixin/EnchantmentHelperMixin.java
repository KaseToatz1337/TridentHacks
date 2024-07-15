package com.kasetoatz.tridenthacks.mixin;

import com.kasetoatz.tridenthacks.config.Config;
import com.kasetoatz.tridenthacks.TridentHacks;
import net.minecraft.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentHelper.class)
public abstract class EnchantmentHelperMixin {
    @Inject(method="getTridentSpinAttackStrength", at=@At("RETURN"), cancellable = true)
    private static void getTridentSpinAttackStrength(CallbackInfoReturnable<Float> cir)
    {
        if (Config.toggleRiptide)
        {
            TridentHacks.hasRiptide = cir.getReturnValue() > 0.0f;
            cir.setReturnValue(3.f);
        }
    }
}