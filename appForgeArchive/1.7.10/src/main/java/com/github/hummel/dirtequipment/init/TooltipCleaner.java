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

        boolean isDirtPickaxe = unloc.contains("dirt_pickaxe");
        boolean isDirtShovel  = unloc.contains("dirt_shovel");
        boolean isDirtHoe     = unloc.contains("dirt_hoe");
        boolean isDirtAxe     = unloc.contains("dirt_axe");
        boolean isDirtSword   = unloc.contains("dirt_sword");

        if (!isDirtPickaxe && !isDirtShovel && !isDirtHoe && !isDirtAxe && !isDirtSword) {
            return;
        }

        if (isDirtSword || isDirtAxe) {
            return;
        }

        for (String line : original) {
            String stripped = line.replaceAll("(?i)\u00A7[0-9A-FK-OR]", "").trim();
            String lower = stripped.toLowerCase();

            if (lower.matches(".*attack\\s*damage.*")) {
                continue;
            }

            cleaned.add(line);
        }

        if (isDirtPickaxe || isDirtShovel || isDirtHoe) {

            int insertIndex = 1;
            if (cleaned.size() > 1) {
                insertIndex = 1;
            }

            cleaned.add(insertIndex, "");
            cleaned.add(insertIndex + 1, "\u00A79+0 Attack Damage");

        }

        event.toolTip.clear();
        event.toolTip.addAll(cleaned);
    }
}
