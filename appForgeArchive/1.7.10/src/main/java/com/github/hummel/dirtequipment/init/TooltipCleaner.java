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
        System.out.println("Tooltip event fired for: " + event.itemStack.getUnlocalizedName());

        List<String> original = event.toolTip;
        List<String> cleaned = new ArrayList<>();

        // Detect dirt tools
        String unloc = event.itemStack.getUnlocalizedName().toLowerCase();
        System.out.println("Tooltip event fired for: " + unloc);

        boolean isDirtPickaxe = unloc.contains("dirtpickaxe");
        boolean isDirtShovel  = unloc.contains("dirtshovel");
        boolean isDirtHoe     = unloc.contains("dirthoe");
        boolean isDirtAxe     = unloc.contains("dirtaxe");
        boolean isDirtSword   = unloc.contains("dirtsword");

        if (!isDirtPickaxe && !isDirtShovel && !isDirtHoe && !isDirtAxe && !isDirtSword) {
            return;
        }

        // Ignore dirt sword + dirt axe
        if (isDirtSword || isDirtAxe) {
            return;
        }

        // Remove any attack-damage tooltip
        for (String line : original) {

            String stripped = line.replaceAll("(?i)\u00A7[0-9A-FK-OR]", "").trim();
            System.out.println("TOOLTIP LINE (raw): [" + stripped + "]"); // -- DEBUG CODE --
            String lower = stripped.toLowerCase();

            if (lower.matches(".*attack\\s*damage.*")) {
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
                cleaned.add(2, "\u00A79+0 Attack Damage");
            else
                cleaned.add("\u00A79+0 Attack Damage");
        }

        event.toolTip.clear();
        event.toolTip.addAll(cleaned);
    }
}
