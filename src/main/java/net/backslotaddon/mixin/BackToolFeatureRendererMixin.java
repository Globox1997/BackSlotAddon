package net.backslotaddon.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.backslot.client.feature.BackToolFeatureRenderer;
import net.backslotaddon.config.ConfigInit;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.fabricmc.api.EnvType;
import net.backslot.BackSlotMain;

@Environment(EnvType.CLIENT)
@Mixin(BackToolFeatureRenderer.class)
public class BackToolFeatureRendererMixin extends HeldItemFeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {

    private final HeldItemRenderer heldItemRenderer;

    public BackToolFeatureRendererMixin(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> context, HeldItemRenderer heldItemRenderer) {
        super(context, heldItemRenderer);
        this.heldItemRenderer = heldItemRenderer;
    }

    @Inject(method = "render", at = @At("HEAD"), cancellable = true, remap = false)
    public void renderBackToolMixin(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, AbstractClientPlayerEntity livingEntity, float f, float g, float h, float j,
            float k, float l, CallbackInfo info) {
        if (ConfigInit.CONFIG.allow_shield_on_back) {
            PlayerEntity player = livingEntity;
            ItemStack backSlotStack = player.getInventory().getStack(41);
            if(!backSlotStack.isEmpty() && backSlotStack.getItem() instanceof ShieldItem) {
                matrixStack.push();
                ModelPart modelPart = this.getContextModel().body;
                modelPart.rotate(matrixStack);
                matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(45F));
                double chestTranslate = 0.145D;
                if (!player.getEquippedStack(EquipmentSlot.CHEST).isEmpty()) {
                    chestTranslate = 0.18D;
                }
                if (ConfigInit.CONFIG.shield_clipping)
                    chestTranslate = chestTranslate + 0.125D;
                matrixStack.translate(0.71D, 0.72D, chestTranslate + 0.42D);
                matrixStack.scale(BackSlotMain.CONFIG.backslotScaling, BackSlotMain.CONFIG.backslotScaling, BackSlotMain.CONFIG.backslotScaling);
                heldItemRenderer.renderItem(livingEntity, backSlotStack, ModelTransformationMode.HEAD, false, matrixStack, vertexConsumerProvider, i);
                matrixStack.pop();
                info.cancel();
            }
        }
    }

}
