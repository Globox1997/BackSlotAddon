package net.backslotaddon.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "backslotaddon")
@Config.Gui.Background("minecraft:textures/block/stone.png")
public class BackSlotAddonConfig implements ConfigData {
    public boolean double_back_sword = true;
    public boolean allow_shield_on_back = true;
    public boolean shield_no_clipping = false;
    @ConfigEntry.Gui.RequiresRestart
    public boolean allow_lantern_on_belt = true;
    public boolean lantern_on_back = false;
}