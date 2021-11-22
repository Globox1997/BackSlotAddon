package net.backslotaddon;

import net.backslotaddon.config.ConfigInit;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class BackSlotAddonMain implements ModInitializer {

    public static final Tag<Item> LANTERN_TAG = TagFactory.ITEM.create(new Identifier("backslotaddon", "belt_lantern_items"));

    @Override
    public void onInitialize() {
        ConfigInit.init();
    }
}

// You are LOVED!!!
// Jesus loves you unconditional!