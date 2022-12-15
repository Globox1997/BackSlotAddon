package net.backslotaddon.mixin.compat;

import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import net.coderbot.iris.shaderpack.materialmap.NamespacedId;
import net.coderbot.iris.uniforms.IdMapUniforms;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Hand;

@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
@Mixin(targets = "net/coderbot/iris/uniforms/IdMapUniforms$HeldItemSupplier")
public class IdMapUniformsMixin {

    // class net/coderbot/iris/uniforms/IdMapUniforms$HeldItemSupplier
    // {

    // // compiled from: IdMapUniforms.java
    // NESTHOST net/coderbot/iris/uniforms/IdMapUniforms
    // // access flags 0xA
    // private static INNERCLASS net/coderbot/iris/uniforms/IdMapUniforms$HeldItemSupplier net/coderbot/iris/uniforms/IdMapUniforms HeldItemSupplier

    // @Shadow
    // @Final
    // @Mutable
    // private Hand hand;

    // @Shadow
    // private int intID;
    // @Shadow
    // private int lightValue;
    // @Shadow
    // private Vector3f lightColor;

    // @Inject(method = "Lnet/coderbot/iris/uniforms/IdMapUniforms$HeldItemSupplier;update()V", at = @At(value = "INVOKE", target =
    // "Lnet/minecraft/registry/DefaultedRegistry;getId(L;)Lnet/minecraft/util/Identifier;"), cancellable = true)
    // private void updateMixin(CallbackInfo info) {
    // System.out.println("TEST");
    // // this.intID = this.itemIdMap.applyAsInt(new NamespacedId(var4.getNamespace(), var4.getPath()));
    // // this.lightValue = var5.getLightEmission(MinecraftClient.getInstance().player, var2);
    // // this.lightColor = var5.getLightColor(MinecraftClient.getInstance().player, var2);
    // }
}
