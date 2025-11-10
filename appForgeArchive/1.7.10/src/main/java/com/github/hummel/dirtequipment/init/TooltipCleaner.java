package com.github.hummel.dirtequipment.init;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import java.util.List;

public class TooltipCleaner {

    @SubscribeEvent
    public void onItemTooltip(ItemTooltipEvent event) {
        List<String> tooltip = event.toolTip;
        // Remove trailing blank lines (caused by addInformation or vanilla rendering)
        while (!tooltip.isEmpty() && tooltip.get(tooltip.size() - 1).trim().isEmpty()) {
            tooltip.remove(tooltip.size() - 1);
        }
    }
}