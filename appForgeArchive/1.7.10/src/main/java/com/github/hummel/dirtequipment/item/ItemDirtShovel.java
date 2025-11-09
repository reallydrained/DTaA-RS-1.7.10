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
import net.minecraft.item.ItemSpade;

public class ItemDirtShovel extends ItemSpade {
	public ItemDirtShovel() {
		super(Materials.DIRT_TOOL2);
		setCreativeTab(CreativeTabs.tabTools);
        	setUnlocalizedName("dirtShovel");
        	setTextureName("dirtequipment:dirt_shovel");
		}

  @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);

// Remove trailing blank lines, if any
    while (!tooltip.isEmpty() && tooltip.get(tooltip.size() - 1).toString().trim().isEmpty()) {
        tooltip.remove(tooltip.size() - 1);
    }

        tooltip.add("\u00A79+0 Attack Damage");  // §9 (\u00A79) for blue text
    }
}
