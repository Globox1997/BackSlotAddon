package net.backslotaddon.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.At;

import net.backslot.network.SwitchPacket;
import net.backslotaddon.BackSlotAddonMain;
import net.backslotaddon.config.ConfigInit;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;

@Mixin(SwitchPacket.class)
public class SwitchPacketMixin {

    @Inject(method = "isItemAllowed", at = @At("HEAD"), cancellable = true, remap = false)
    private static void isItemAllowed(ItemStack stack, int slot, CallbackInfoReturnable<Boolean> info) {
        if (ConfigInit.CONFIG.allow_shield_on_back && slot == 41 && stack.getItem() instanceof ShieldItem) {
            info.setReturnValue(true);
        }
        if (ConfigInit.CONFIG.allow_lantern_on_belt && slot == 42 && stack.isIn(BackSlotAddonMain.LANTERN_TAG)) {
            info.setReturnValue(true);
        }
    }
}
