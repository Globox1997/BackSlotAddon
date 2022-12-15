package net.backslotaddon.mixin.compat;

import org.spongepowered.asm.mixin.Mixin;

import net.coderbot.iris.uniforms.CommonUniforms;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
@Mixin(CommonUniforms.class)
public class CommonUniformsMixin {
    // IrisItemLightProvider
}
