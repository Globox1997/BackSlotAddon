package net.backslotaddon;

import net.backslotaddon.config.ConfigInit;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class BackSlotAddonMain implements ModInitializer {

    public static final TagKey<Item> LANTERN_TAG = TagKey.of(RegistryKeys.ITEM, new Identifier("backslotaddon", "belt_lantern_items"));

    @Override
    public void onInitialize() {
        ConfigInit.init();
    }
}

// You are LOVED!!!
// Jesus loves you unconditional!