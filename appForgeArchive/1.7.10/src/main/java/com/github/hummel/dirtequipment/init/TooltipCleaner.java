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

        // Detect dirt tools
        String unloc = event.itemStack.getUnlocalizedName().toLowerCase();

        boolean isDirtPickaxe = unloc.contains("dirtpickaxe");
        boolean isDirtShovel  = unloc.contains("dirtshovel");
        boolean isDirtHoe     = unloc.contains("dirthoe");
        boolean isDirtAxe     = unloc.contains("dirtaxe");
        boolean isDirtSword   = unloc.contains("dirtsword");

        // Ignore vanilla tools
        if (!isDirtPickaxe && !isDirtShovel && !isDirtHoe && !isDirtAxe && !isDirtSword) {
            return;
        }

        // Ignore sword and axe
        if (isDirtSword || isDirtAxe) {
            return;
        }

        // Remove vanilla tooltips
        for (String line : original) {

            String stripped = line.replaceAll("(?i)\u00A7[0-9A-FK-OR]", "").trim();
            String lower = stripped.toLowerCase();

            if (lower.contains("attack damage") ||
                lower.contains("attackdamage") ||
                stripped.contains("attribute.name.generic.attackDamage")) {
                continue;
            }

            if (lower.contains("+0 attack damage")) {
                continue;
            }

            cleaned.add(line);
        }

        // Add tooltip to dirt tools
        if (isDirtPickaxe || isDirtShovel || isDirtHoe) {

            if (cleaned.isEmpty()) {
                cleaned.add(event.itemStack.getDisplayName());
            }

            if (cleaned.size() > 1)
                cleaned.add(1, "");
            else
                cleaned.add("");

            cleaned.add(2, "\u00A79+0 Attack Damage");
        }

        event.toolTip.clear();
        event.toolTip.addAll(cleaned);
    }
}