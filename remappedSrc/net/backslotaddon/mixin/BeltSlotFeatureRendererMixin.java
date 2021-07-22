package net.backslotaddon.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.backslot.client.feature.BeltSlotFeatureRenderer;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.fabricmc.api.EnvType;
import net.backslot.BackSlotMain;

@Environment(EnvType.CLIENT)
@Mixin(BeltSlotFeatureRenderer.class)
public class BeltSlotFeatureRendererMixin
    extends HeldItemFeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {

  public BeltSlotFeatureRendererMixin(
      FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> featureRendererContext) {
    super(featureRendererContext);
  }

  @Inject(method = "Lnet/backslot/client/feature/BeltSlotFeatureRenderer;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/client/network/AbstractClientPlayerEntity;FFFFFF)V", at = @At("HEAD"), cancellable = true)
  public void renderBeltSlotMixin(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i,
      AbstractClientPlayerEntity livingEntity, float f, float g, float h, float j, float k, float l,
      CallbackInfo info) {
    PlayerEntity player = (PlayerEntity) livingEntity;
    ItemStack beltSlotStack = player.inventory.getStack(42);
    ItemStack backSlotStack = player.inventory.getStack(41);
    if (livingEntity instanceof AbstractClientPlayerEntity && !backSlotStack.isEmpty() && !beltSlotStack.isEmpty()) {
      if (backSlotStack.getItem() instanceof SwordItem && backSlotStack.getItem() instanceof SwordItem) {
        matrixStack.push();
        ModelPart modelPart = this.getContextModel().torso;
        modelPart.rotate(matrixStack);
        matrixStack.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(-270.0F));
        matrixStack.translate(0.23D, -0.25D, 0.28D);
        matrixStack.scale(BackSlotMain.CONFIG.backslot_scale, BackSlotMain.CONFIG.backslot_scale,
            BackSlotMain.CONFIG.backslot_scale);

        MinecraftClient.getInstance().getHeldItemRenderer().renderItem(livingEntity, beltSlotStack,
            ModelTransformation.Mode.GROUND, false, matrixStack, vertexConsumerProvider, i);
        matrixStack.pop();
        info.cancel();
      }
    }
  }

}
