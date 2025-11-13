package com.github.hummel.dirtequipment.init;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import java.util.List;

public class TooltipCleaner {

    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent event) {
        List tooltip = event.toolTip;
        if (tooltip == null || tooltip.isEmpty()) return;

        boolean removed;
        do {
            removed = false;

            // Remove top empty lines
            while (!tooltip.isEmpty() && tooltip.get(0).toString().trim().isEmpty()) {
                tooltip.remove(0);
                removed = true;
            }

            // Remove bottom empty lines
            while (!tooltip.isEmpty() && tooltip.get(tooltip.size() - 1).toString().trim().isEmpty()) {
                tooltip.remove(tooltip.size() - 1);
                removed = true;
            }

        } while (removed);
    }
}