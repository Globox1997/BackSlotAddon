package net.backslotaddon.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;

public class ConfigInit {
    public static BackSlotAddonConfig CONFIG = new BackSlotAddonConfig();

    public static void init() {
        AutoConfig.register(BackSlotAddonConfig.class, JanksonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(BackSlotAddonConfig.class).getConfig();
    }

}
