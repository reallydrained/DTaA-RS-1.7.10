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
        List<String> cleaned = new ArrayList<String>();

        String unloc = event.itemStack.getUnlocalizedName().toLowerCase();

        boolean isPick = unloc.contains("dirt_pickaxe");
        boolean isShovel = unloc.contains("dirt_shovel");
        boolean isHoe = unloc.contains("dirt_hoe");
        boolean isAxe = unloc.contains("dirt_axe");
        boolean isSword = unloc.contains("dirt_sword");

        if (!isPick && !isShovel && !isHoe && !isAxe && !isSword) {
            return;
        }

        if (isAxe || isSword) {
            return;
        }

        String name = original.get(0);
        cleaned.add(name);

        boolean durabilityFound = false;
        String durabilityLine = null;

        for (int i = 1; i < original.size(); i++) {
            String line = original.get(i);
            String stripped = line.replaceAll("(?i)\u00A7[0-9A-FK-OR]", "").trim().toLowerCase();

            if (stripped.contains("attack damage")) {
                continue;
            }

            if (stripped.startsWith("durability")) {
                durabilityFound = true;
                durabilityLine = line;
                continue;
            }

            cleaned.add(line);
        }

        cleaned.add("");

        cleaned.add("\u00A79+0 Attack Damage");

        if (durabilityFound && durabilityLine != null) {
            cleaned.add(durabilityLine);
        }

        event.toolTip.clear();
        event.toolTip.addAll(cleaned);
    }
}
