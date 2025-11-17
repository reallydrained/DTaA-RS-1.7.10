package com.github.hummel.dirtequipment.init;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class TooltipCleaner {
    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent event) {
        List<String> original = event.toolTip;
        List<String> cleaned = new ArrayList<>();

        String unloc = event.itemStack.getUnlocalizedName().toLowerCase();

        boolean isPickaxe = unloc.contains("dirt_pickaxe");
        boolean isShovel  = unloc.contains("dirt_shovel");
        boolean isHoe     = unloc.contains("dirt_hoe");
        boolean isAxe     = unloc.contains("dirt_axe");
        boolean isSword   = unloc.contains("dirt_sword");

        if (!isPickaxe && !isShovel && !isHoe && !isAxe && !isSword) return;
        if (isSword || isAxe) return;

        for (String line : original) {
            String stripped = line.replaceAll("(?i)\u00A7[0-9A-FK-OR]", "").trim();
            if (stripped.toLowerCase().matches(".*attack\\s*damage.*")) continue;
            if (!stripped.isEmpty()) cleaned.add(line);
        }

        if (cleaned.isEmpty()) {
            cleaned.add(event.itemStack.getDisplayName());
        }

        if (cleaned.size() > 1) {
            cleaned.add(1, "");
        }

        cleaned.add("\u00A79+0 Attack Damage");

        event.toolTip.clear();
        event.toolTip.addAll(cleaned);
    }
}
