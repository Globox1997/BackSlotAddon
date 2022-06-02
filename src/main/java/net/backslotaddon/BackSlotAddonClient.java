package net.backslotaddon;

import io.vram.frex.api.light.HeldItemLightListener;
import io.vram.frex.api.light.ItemLight;
import net.backslotaddon.config.ConfigInit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.player.PlayerEntity;

public class BackSlotAddonClient implements ClientModInitializer {

    public static final boolean isCanvasLoaded = FabricLoader.getInstance().isModLoaded("canvas");

    @Override
    public void onInitializeClient() {
        if (ConfigInit.CONFIG.allow_lantern_on_belt && isCanvasLoaded) {
            HeldItemLightListener.register((holdingEntity, heldStack, defaultResult, currentResult) -> {
                if (holdingEntity instanceof PlayerEntity && !((PlayerEntity) holdingEntity).getInventory().getStack(42).isEmpty()
                        && ((PlayerEntity) holdingEntity).getInventory().getStack(42).isIn(BackSlotAddonMain.LANTERN_TAG)) {
                    return ItemLight.get(((PlayerEntity) holdingEntity).getInventory().getStack(42));
                }
                return defaultResult;
            });
        }
    }

}
