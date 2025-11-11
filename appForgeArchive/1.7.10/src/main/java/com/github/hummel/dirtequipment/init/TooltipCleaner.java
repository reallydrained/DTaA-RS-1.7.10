package com.github.hummel.dirtequipment.init;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.RenderTooltipEvent;

import java.util.List;

public class TooltipCleaner {

    @SubscribeEvent
    public void onRenderTooltip(RenderTooltipEvent.Pre event) {
        List<String> lines = event.toolTip;
        if (lines == null || lines.isEmpty()) return;

        // Remove leading/trailing empty lines no matter what added them
        while (!lines.isEmpty() && lines.get(0).trim().isEmpty()) {
            lines.remove(0);
        }
        while (!lines.isEmpty() && lines.get(lines.size() - 1).trim().isEmpty()) {
            lines.remove(lines.size() - 1);
        }
    }
}