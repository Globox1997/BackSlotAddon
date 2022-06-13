package net.backslotaddon.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "backslotaddon")
@Config.Gui.Background("minecraft:textures/block/stone.png")
public class BackSlotAddonConfig implements ConfigData {
    public double test0 = 0.0D;
    public double test1 = 0.0D;
    public double test2 = 0.0D;
    public float test4 = 0.0F;
    public boolean double_back_sword = true;
    @ConfigEntry.Gui.RequiresRestart
    public boolean allow_shield_on_back = true;
    public boolean shield_clipping = true;
    @ConfigEntry.Gui.RequiresRestart
    public boolean allow_lantern_on_belt = true;
    public boolean lantern_on_back = false;
}