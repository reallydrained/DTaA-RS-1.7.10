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
	}

  @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add("\u00A79+1 Attack Damage");  // §9 (\u00A79) for blue text
    }
}