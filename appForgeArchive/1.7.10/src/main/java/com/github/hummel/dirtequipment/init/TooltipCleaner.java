package com.github.hummel.dirtequipment.init;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import java.util.List;

public class TooltipCleaner {

    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent event) {
        List<String> tooltip = event.toolTip;
        if (tooltip == null || tooltip.isEmpty()) return;

        // Remove leading/trailing empty lines no matter what
        while (!tooltip.isEmpty() && tooltip.get(0).trim().isEmpty()) {
            tooltip.remove(0);
        }
        while (!tooltip.isEmpty() && tooltip.get(tooltip.size() - 1).trim().isEmpty()) {
            tooltip.remove(tooltip.size() - 1);
        }
    }
}
