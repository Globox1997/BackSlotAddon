package net.backslotaddon;

import grondag.frex.api.event.HeldItemLightListener;
import grondag.frex.api.light.ItemLight;
import net.backslotaddon.config.ConfigInit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class BackSlotAddonClient implements ClientModInitializer {
    public static final Tag<Item> LANTERN_TAG = TagRegistry.item(new Identifier("backslotaddon", "belt_lantern_items"));

    @Override
    public void onInitializeClient() {
        if (ConfigInit.CONFIG.allow_lantern_on_belt && FabricLoader.getInstance().isModLoaded("canvas")) {
            HeldItemLightListener.EVENT.register((holdingEntity, heldStack, defaultResult, currentResult) -> {
                if (holdingEntity instanceof PlayerEntity && !((PlayerEntity) holdingEntity).getInventory().getStack(42).isEmpty()
                        && ((PlayerEntity) holdingEntity).getInventory().getStack(42).isIn(LANTERN_TAG)) {
                    return ItemLight.get(((PlayerEntity) holdingEntity).getInventory().getStack(42));
                }
                return defaultResult;
            });
        }
    }

}
