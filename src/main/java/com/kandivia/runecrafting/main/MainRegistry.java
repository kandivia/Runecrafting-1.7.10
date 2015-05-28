package com.kandivia.runecrafting.main;

import com.kandivia.runecrafting.event.MobDropEvent;
import com.kandivia.runecrafting.helper.ConfigHandler;
import com.kandivia.runecrafting.helper.Reference;
import com.kandivia.runecrafting.init.CraftingRegister;
import com.kandivia.runecrafting.init.RegisterBlocks;
import com.kandivia.runecrafting.init.RegisterItems;
import com.kandivia.runecrafting.worldgen.RunecraftingWorldGen;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = Reference.MOD_ID, version = Reference.VERSION, name = Reference.MOD_NAME)
public class MainRegistry {
	
	public static CreativeTabs tabRunecrafting = new CreativeTabs("Runecrafting") {
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return RegisterItems.essence;
		}
	};
	
	RunecraftingWorldGen eventWorldGen = new RunecraftingWorldGen();
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent preEvent) {
		ConfigHandler.init(preEvent.getSuggestedConfigurationFile());
		RegisterBlocks.init();
		RegisterItems.init();
		CraftingRegister.init();
		if(Reference.enableSilverOreSpawn)
			GameRegistry.registerWorldGenerator(eventWorldGen, 0);
		MinecraftForge.EVENT_BUS.register(new MobDropEvent());
	}
}