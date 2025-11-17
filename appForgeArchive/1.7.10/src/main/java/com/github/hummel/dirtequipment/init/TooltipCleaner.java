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

        String unloc = event.itemStack.getUnlocalizedName().toLowerCase();

        boolean isDirtPickaxe = unloc.contains("dirt_pickaxe");
        boolean isDirtShovel  = unloc.contains("dirt_shovel");
        boolean isDirtHoe     = unloc.contains("dirt_hoe");

        // ONLY target the three tools
        if (!isDirtPickaxe && !isDirtShovel && !isDirtHoe)
            return;

        // === EXPERIMENT: Build EXACT tooltip manually ===
        List<String> finalTip = new ArrayList<>();

        // line 0
        finalTip.add(event.itemStack.getDisplayName());

        // line 1 (blank)
        finalTip.add("");

        // line 2 (+0)
        finalTip.add("\u00A79+0 Attack Damage");

        // line 3 (durability IF ANY)
        if (event.itemStack.isItemDamaged()) {
            int dmg = event.itemStack.getMaxDamage() - event.itemStack.getItemDamage();
            finalTip.add("\u00A77" + dmg + " Durability");
        }

        event.toolTip.clear();
        event.toolTip.addAll(finalTip);
    }
}
