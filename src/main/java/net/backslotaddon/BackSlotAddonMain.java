package net.backslotaddon;

import net.backslotaddon.config.ConfigInit;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BackSlotAddonMain implements ModInitializer {

    public static final TagKey<Item> LANTERN_TAG = TagKey.of(Registry.ITEM_KEY, new Identifier("backslotaddon", "belt_lantern_items"));

    @Override
    public void onInitialize() {
        ConfigInit.init();
    }
}

// You are LOVED!!!
// Jesus loves you unconditional!