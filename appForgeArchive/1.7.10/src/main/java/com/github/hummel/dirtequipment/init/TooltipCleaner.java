package com.github.hummel.dirtequipment.init;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import java.util.List;

public class TooltipCleaner {

    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent event) {
        List tooltip = event.toolTip;
        if (tooltip == null || tooltip.isEmpty()) return;

        // Remove leading empty lines
        while (!tooltip.isEmpty() && tooltip.get(0).toString().trim().isEmpty()) {
            tooltip.remove(0);
        }

        // Remove trailing empty lines
        while (!tooltip.isEmpty() && tooltip.get(tooltip.size() - 1).toString().trim().isEmpty()) {
            tooltip.remove(tooltip.size() - 1);
        }

        // Re-run this pass just in case tooltip updates after item damage
        while (!tooltip.isEmpty() && tooltip.get(tooltip.size() - 1).toString().trim().isEmpty()) {
            tooltip.remove(tooltip.size() - 1);
        }
    }
}