package com.github.hummel.dirtequipment.init;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import java.util.ArrayList;
import java.util.List;

public class TooltipCleaner {

    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent event) {

        List<String> original = event.toolTip;
        List<String> cleaned = new ArrayList<>();

        for (String line : original) {
            String raw = line.replaceAll("(?i)\u00A7[0-9A-FK-OR]", "").trim();

            // Strip vanilla attack damage lines
            String lower = raw.toLowerCase();
            if (lower.contains("attack damage")
                || lower.contains("attackdamage")
                || raw.contains("attribute.name.generic.attackDamage")) {
                continue;
            }

            // Remove any stat lines generated previously
            if (lower.contains("+0 attack damage")) continue;

            cleaned.add(line);
        }

        // Identify dirt tools
        String name = event.itemStack.getUnlocalizedName().toLowerCase();
        boolean isShovel  = name.contains("shovel");
        boolean isPickaxe = name.contains("pickaxe");
        boolean isHoe     = name.contains("hoe");

        if (isShovel || isPickaxe || isHoe) {

            // Insert blank line
            int insertAt = Math.min(1, cleaned.size());
            cleaned.add(insertAt, "");

            // Insert stat line
            int statAt = Math.min(insertAt + 1, cleaned.size());
            cleaned.add(statAt, "\u00A79+0 Attack Damage");
        }

        // Replace tooltip
        event.toolTip.clear();
        event.toolTip.addAll(cleaned);
    }
}