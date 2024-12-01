package net.backslotaddon.mixin.compat;

import net.backslotaddon.config.ConfigInit;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Environment(EnvType.CLIENT)
@Mixin(targets = "net/irisshaders/iris/uniforms/IdMapUniforms$HeldItemSupplier")
public class IdMapUniformsMixin {

    @Shadow
    @Mutable
    @Final
    private Hand hand;

    @ModifyVariable(method = "update", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getStackInHand(Lnet/minecraft/util/Hand;)Lnet/minecraft/item/ItemStack;"), index = 2)
    private ItemStack updateMixin(ItemStack original) {
        if (hand == Hand.OFF_HAND && ConfigInit.CONFIG.allow_lantern_on_belt) {
            ClientPlayerEntity clientPlayerEntity = MinecraftClient.getInstance().player;
            if (clientPlayerEntity.getInventory().getStack(42) != null && !clientPlayerEntity.getInventory().getStack(42).isEmpty()) {
                return clientPlayerEntity.getInventory().getStack(42);
            }
        }
        return original;
    }
}
