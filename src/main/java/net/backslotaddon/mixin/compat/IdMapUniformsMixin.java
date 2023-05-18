package net.backslotaddon.mixin.compat;

import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import it.unimi.dsi.fastutil.objects.Object2IntFunction;
import net.coderbot.iris.shaderpack.materialmap.NamespacedId;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.irisshaders.iris.api.v0.item.IrisItemLightProvider;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;

@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
@Mixin(targets = "net/coderbot/iris/uniforms/IdMapUniforms$HeldItemSupplier")
public class IdMapUniformsMixin {

    // @Shadow
    // @Mutable
    // @Final
    // private Object2IntFunction<NamespacedId> itemIdMap;
    // @Shadow
    // private int lightValue;
    // @Shadow
    // private Vector3f lightColor;
    // @Shadow
    // private int intID;

    // @Inject(method = "update", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getStackInHand(Lnet/minecraft/util/Hand;)Lnet/minecraft/item/ItemStack;"), locals = LocalCapture.CAPTURE_FAILSOFT)
    // private void updateMixin(CallbackInfo info, ClientPlayerEntity clientPlayerEntity) {
    //     ItemStack beltSlotStack = clientPlayerEntity.getInventory().getStack(42);
    //     if (beltSlotStack != null && !beltSlotStack.isEmpty()) {
    //         Identifier id = Registries.ITEM.getId(beltSlotStack.getItem());
    //         this.intID = this.itemIdMap.applyAsInt(new NamespacedId(id.getNamespace(), id.getPath()));
    //         IrisItemLightProvider irisItemLightProvider = (IrisItemLightProvider) beltSlotStack.getItem();
    //         this.lightValue = irisItemLightProvider.getLightEmission(clientPlayerEntity, beltSlotStack);
    //         this.lightColor = irisItemLightProvider.getLightColor(clientPlayerEntity, beltSlotStack);
    //     }
    // }

    @Shadow
    @Mutable
    @Final
    private  Hand hand;

    @ModifyVariable(method = "update", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getStackInHand(Lnet/minecraft/util/Hand;)Lnet/minecraft/item/ItemStack;"), ordinal = 0)
    private ItemStack updateMixin(ItemStack original) {
        ClientPlayerEntity clientPlayerEntity = MinecraftClient.getInstance().player;
        if (hand == Hand.OFF_HAND && clientPlayerEntity.getInventory().getStack(42) != null && !clientPlayerEntity.getInventory().getStack(42) .isEmpty()){
            return clientPlayerEntity.getInventory().getStack(42) ;
        }
        return original;
    }
}
