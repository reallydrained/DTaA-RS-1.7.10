package com.github.hummel.dirtequipment.item;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import com.github.hummel.dirtequipment.init.Materials;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemPickaxe;

public class ItemDirtPickaxe extends ItemPickaxe {

    public ItemDirtPickaxe() {
        super(Materials.DIRT_TOOL);
        setCreativeTab(CreativeTabs.tabTools);
        setUnlocalizedName("dirtPickaxe");
        setTextureName("dirtequipment:dirt_pickaxe");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);

        tooltip.add("");
        tooltip.add(EnumChatFormatting.BLUE + "+0 Attack Damage" + EnumChatFormatting.RESET + "\u00A7r");

        while (!tooltip.isEmpty() && tooltip.get(tooltip.size() - 1).toString().trim().isEmpty()) {
            tooltip.remove(tooltip.size() - 1);
        }
    }
}