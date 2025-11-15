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

        String unloc = event.itemStack.getUnlocalizedName().toLowerCase();

        boolean isPickaxe = unloc.contains("pickaxe");
        boolean isShovel  = unloc.contains("shovel");
        boolean isHoe     = unloc.contains("hoe");
        boolean isAxe     = unloc.contains("axe");
        boolean isSword   = unloc.contains("sword");

        // --- LEAVE SWORDS AND AXES ALONE ---
        if (isSword || isAxe) {
            return;
        }

        // --- CLEAN OUT VANILLA ATTACK DAMAGE LINES ---
        for (String line : original) {
            String raw = line.replaceAll("(?i)\u00A7[0-9A-FK-OR]", "").trim();
            String lower = raw.toLowerCase();

            // Strip vanilla attribute lines
            if (lower.contains("attack damage") ||
                lower.contains("attackdamage") ||
                raw.contains("attribute.name.generic.attackDamage")) {
                continue;
            }

            // Remove old stat line if added previously
            if (lower.contains("+0 attack damage")) {
                continue;
            }

            cleaned.add(line);
        }

        // --- APPLY CUSTOM STATS ONLY TO PICK/SHOVEL/HOE ---
        if (isPickaxe || isShovel || isHoe) {

            // Insert blank line after item name (index 1)
            if (cleaned.size() > 1)
                cleaned.add(1, "");
            else
                cleaned.add("");

            // Insert colored stat line just after the blank line
            cleaned.add(2, "\u00A79+0 Attack Damage");
        }

        // --- REPLACE TOOLTIP  ---
        event.toolTip.clear();
        event.toolTip.addAll(cleaned);
    }
}