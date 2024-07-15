package com.kasetoatz.tridenthacks.mixin;

import com.kasetoatz.tridenthacks.TridentHacks;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerInventory.class)
public abstract class PlayerInventoryMixin {
    @Inject(method="setStack", at=@At("HEAD"))
    public void setStack(int slot, ItemStack stack, CallbackInfo ci)
    {
        int tridentSlot = TridentHacks.tridentSlot;
        if (tridentSlot != -1)
        {
            ClientPlayerEntity player = TridentHacks.client.player;
            ClientPlayerInteractionManager interactionManager = TridentHacks.client.interactionManager;
            if (!stack.isEmpty() && stack.getItem() == Items.TRIDENT && player != null && interactionManager != null)
            {
                if (player.getInventory().getStack(TridentHacks.tridentSlot).isEmpty())
                {
                    interactionManager.clickSlot(player.playerScreenHandler.syncId, this.getSlotID(slot), 0, SlotActionType.PICKUP, player);
                    interactionManager.clickSlot(player.playerScreenHandler.syncId, this.getSlotID(TridentHacks.tridentSlot), 0, SlotActionType.PICKUP, player);
                    if (TridentHacks.tridentSlot == tridentSlot)
                    {
                        TridentHacks.tridentSlot = -1;
                    }
                }
            }
        }
    }

    @Unique
    private int getSlotID(int index)
    {
        return (index == PlayerInventory.OFF_HAND_SLOT) ? 45 : (index < PlayerInventory.getHotbarSize()) ? index + PlayerInventory.MAIN_SIZE : index;
    }
}