package com.github.hummel.dirtequipment.init;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.event.RenderTooltipEvent;

import java.util.List;

public class TooltipRendererPatch {

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onTooltipRender(RenderTooltipEvent.PostText event) {
        List<String> tooltip = event.toolTip;
        if (tooltip == null || tooltip.isEmpty()) return;

        // Remove trailing empty lines
        while (!tooltip.isEmpty() && tooltip.get(tooltip.size() - 1).trim().isEmpty()) {
            tooltip.remove(tooltip.size() - 1);
        }
    }
}
