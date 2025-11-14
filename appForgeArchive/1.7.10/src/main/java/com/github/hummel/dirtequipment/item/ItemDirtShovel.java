package com.github.hummel.dirtequipment.item;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
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

}