package net.backslotaddon.mixin;

import net.minecraft.entity.EquipmentSlot;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.backslot.client.feature.BeltSlotFeatureRenderer;
import net.backslotaddon.BackSlotAddonMain;
import net.backslotaddon.config.ConfigInit;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.math.RotationAxis;
import net.fabricmc.api.EnvType;
import net.backslot.BackSlotMain;

@Environment(EnvType.CLIENT)
@Mixin(BeltSlotFeatureRenderer.class)
public class BeltSlotFeatureRendererMixin extends HeldItemFeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {

    private final HeldItemRenderer heldItemRenderer;

    public BeltSlotFeatureRendererMixin(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> context, HeldItemRenderer heldItemRenderer) {
        super(context, heldItemRenderer);
        this.heldItemRenderer = heldItemRenderer;
    }

    @Inject(method = "render", at = @At("HEAD"), cancellable = true, remap = false)
    public void renderBeltSlotMixin(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, AbstractClientPlayerEntity livingEntity, float f, float g, float h, float j,
            float k, float l, CallbackInfo info) {
        PlayerEntity player = (PlayerEntity) livingEntity;
        ItemStack beltSlotStack = player.getInventory().getStack(42);
        ItemStack backSlotStack = player.getInventory().getStack(41);
        if (ConfigInit.CONFIG.double_back_sword) {
            if (livingEntity instanceof AbstractClientPlayerEntity && !backSlotStack.isEmpty() && !beltSlotStack.isEmpty()) {
                if (backSlotStack.getItem() instanceof SwordItem && beltSlotStack.getItem() instanceof SwordItem) {
                    matrixStack.push();
                    ModelPart modelPart = this.getContextModel().body;
                    modelPart.rotate(matrixStack);
                    matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(-270.0F));
                    matrixStack.translate(0.23D, -0.25D, 0.28D);
                    matrixStack.scale(BackSlotMain.CONFIG.backslot_scaling, BackSlotMain.CONFIG.backslot_scaling, BackSlotMain.CONFIG.backslot_scaling);
                    if (!livingEntity.hasStackEquipped(EquipmentSlot.CHEST))
                        matrixStack.translate(0.0F, 0.0F, -0.04F);
                    heldItemRenderer.renderItem(livingEntity, beltSlotStack, ModelTransformationMode.HEAD, false, matrixStack, vertexConsumerProvider, i);
                    matrixStack.pop();
                    info.cancel();
                } else if (backSlotStack.getItem() instanceof ShieldItem && beltSlotStack.getItem() instanceof SwordItem) {
                    matrixStack.push();
                    ModelPart modelPart = this.getContextModel().body;
                    modelPart.rotate(matrixStack);
                    matrixStack.translate(-0.07D, -0.05D, 0.23D);
                    matrixStack.scale(BackSlotMain.CONFIG.backslot_scaling, BackSlotMain.CONFIG.backslot_scaling, BackSlotMain.CONFIG.backslot_scaling);
                    if (livingEntity.hasStackEquipped(EquipmentSlot.CHEST))
                        matrixStack.translate(0.0F, 0.0F, 0.02F);
                    if (!ConfigInit.CONFIG.shield_clipping)
                        matrixStack.translate(0.0F, 0.0F, -0.06F);
                    heldItemRenderer.renderItem(livingEntity, beltSlotStack, ModelTransformationMode.HEAD, false, matrixStack, vertexConsumerProvider, i);
                    matrixStack.pop();
                    info.cancel();
                }
            }
        }
        if (ConfigInit.CONFIG.allow_lantern_on_belt) {
            if (livingEntity instanceof AbstractClientPlayerEntity && !beltSlotStack.isEmpty() && beltSlotStack.isIn(BackSlotAddonMain.LANTERN_TAG)) {
                matrixStack.push();
                ModelPart modelPart = this.getContextModel().body;
                modelPart.rotate(matrixStack);
                float positive_x = 20F;
                double translate_x = -0.1D;
                double translate_y = -1D;
                double translate_z = -0.28D;
                if (ConfigInit.CONFIG.lantern_on_back) {
                    positive_x = -32F;
                    translate_x = -0.15D;
                    translate_y = -0.95D;
                    translate_z = -0.5D;
                }
                matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(180F));
                matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(positive_x));
                matrixStack.translate(translate_x, translate_y, translate_z);
                float scale = 0.6F;
                matrixStack.scale(BackSlotMain.CONFIG.beltslot_scaling * scale, BackSlotMain.CONFIG.beltslot_scaling * scale, BackSlotMain.CONFIG.beltslot_scaling * scale);
                MinecraftClient.getInstance().getBlockRenderManager().renderBlockAsEntity(Block.getBlockFromItem(beltSlotStack.getItem()).getDefaultState(), matrixStack, vertexConsumerProvider, i,
                        OverlayTexture.DEFAULT_UV);
                matrixStack.pop();
                info.cancel();
            }
        }
    }

}
