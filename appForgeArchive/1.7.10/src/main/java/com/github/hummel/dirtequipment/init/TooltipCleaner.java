package com.github.hummel.dirtequipment.init;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraft.item.ItemStack;

import com.github.hummel.dirtequipment.item.ItemDirtPickaxe;
import com.github.hummel.dirtequipment.item.ItemDirtShovel;
import com.github.hummel.dirtequipment.item.ItemDirtHoe;

import java.util.List;
import java.util.Iterator;

public class TooltipCleaner {

    private static final String ATTACK_LINE = "\u00A79+0 Attack Damage";

    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.itemStack;
        if (stack == null || stack.getItem() == null) return;

        List tooltip = event.toolTip;
        if (tooltip == null || tooltip.isEmpty()) return;

        boolean isOurTool = stack.getItem() instanceof ItemDirtPickaxe
                         || stack.getItem() instanceof ItemDirtShovel
                         || stack.getItem() instanceof ItemDirtHoe;

        if (!isOurTool) return;

        boolean hasBlank = false;
        boolean hasAttack = false;
        for (Object o : tooltip) {
            String s = o.toString();
            if (s.trim().isEmpty()) hasBlank = true;
            if (s.equals(ATTACK_LINE)) hasAttack = true;
        }

        if (!hasBlank) tooltip.add(0, "");
        if (!hasAttack) tooltip.add(1, ATTACK_LINE);

        for (Iterator it = tooltip.iterator(); it.hasNext();) {
            String line = it.next().toString();
            if (line.trim().isEmpty()) it.remove();
        }

        boolean attackSeen = false;
        for (Iterator it = tooltip.iterator(); it.hasNext();) {
            String line = it.next().toString();
            if (line.equals(ATTACK_LINE)) {
                if (!attackSeen) {
                    attackSeen = true;
                } else {
                    it.remove();
                }
            }
        }

        while (!tooltip.isEmpty() && (tooltip.get(0).toString().trim().isEmpty() || tooltip.get(0).toString().equals(ATTACK_LINE))) {
            tooltip.remove(0);
        }

        tooltip.add(0, ATTACK_LINE);
        tooltip.add(0, "");
    }
}