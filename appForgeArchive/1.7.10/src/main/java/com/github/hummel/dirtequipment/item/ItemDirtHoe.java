package com.github.hummel.dirtequipment.item;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import com.github.hummel.dirtequipment.init.Materials;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemHoe;

public class ItemDirtHoe extends ItemHoe {
	public ItemDirtHoe() {
		super(Materials.DIRT_TOOL);
		setCreativeTab(CreativeTabs.tabTools);
        	setUnlocalizedName("dirtHoe");
        	setTextureName("dirtequipment:dirt_hoe");
	}

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
        tooltip.add("");
        tooltip.add("\u00A79+0 Attack Damage");
    }
}