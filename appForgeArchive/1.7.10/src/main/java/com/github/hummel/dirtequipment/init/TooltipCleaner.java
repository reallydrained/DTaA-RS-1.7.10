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

        // Only modify dirt tools
        String unloc = event.itemStack.getUnlocalizedName().toLowerCase();
        boolean isDirtItem = unloc.startsWith("item.dirtequipment:") 
                             || unloc.contains("dirtequipment");

        if (!isDirtItem) {
            // COMPLETELY ignore vanilla items
            return;
        }

        // Detect which dirt tool it is
        boolean isPickaxe = unloc.contains("dirtpickaxe");
        boolean isShovel  = unloc.contains("dirtshovel");
        boolean isHoe     = unloc.contains("dirthoe");
        boolean isAxe     = unloc.contains("dirtaxe");
        boolean isSword   = unloc.contains("dirtsword");

        // Dirt sword/axe should remain untouched
        if (isSword || isAxe) {
            return;
        }

        // Copy lines while removing vanilla attack dmg
        for (String line : original) {

            String stripped = line.replaceAll("(?i)\u00A7[0-9A-FK-OR]", "").trim();
            String lower = stripped.toLowerCase();

            // remove vanilla attack damage
            if (lower.contains("attack damage") ||
                lower.contains("attackdamage") ||
                stripped.contains("attribute.name.generic.attackDamage")) {
                continue;
            }

            // Remove old custom line
            if (lower.contains("+0 attack damage")) {
                continue;
            }

            cleaned.add(line);
        }

        // Add custom stat to dirt pick/shovel/hoe/axe
        if (isPickaxe || isShovel || isHoe) {

            // Ensure at least 1 line exists
            if (cleaned.isEmpty()) {
                cleaned.add(event.itemStack.getDisplayName());
            }

            // Insert blank line after name (index 1)
            if (cleaned.size() > 1)
                cleaned.add(1, "");
            else
                cleaned.add("");

            // Insert stat line after blank
            cleaned.add(2, "\u00A79+0 Attack Damage");
        }

        // Replace tooltip
        event.toolTip.clear();
        event.toolTip.addAll(cleaned);
    }
}