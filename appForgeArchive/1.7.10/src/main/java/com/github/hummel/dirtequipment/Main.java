package com.github.hummel.dirtequipment;

import com.github.hummel.dirtequipment.init.Items;
import com.github.hummel.dirtequipment.init.Recipes;
import com.github.hummel.dirtequipment.init.TooltipCleaner;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = "dirtequipment", useMetadata = true)
public class Main {
	@SuppressWarnings("unused")
	public static final String DISABLE_CURSEFORGE_DUPLICATE_NOTICE = "111229022024";

	@Mod.EventHandler
	@SuppressWarnings("MethodMayBeStatic")
	public void preInit(FMLPreInitializationEvent event) {
		Items.preInit();
		Recipes.preInit();

		MinecraftForge.EVENT_BUS.register(new TooltipCleaner());
		FMLCommonHandler.instance().bus().register(new TooltipCleaner());
	}
}