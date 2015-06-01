package com.kandivia.runecrafting.main;

import com.kandivia.runecrafting.common.RuneProxyCommon;
import com.kandivia.runecrafting.event.MobDropEvent;
import com.kandivia.runecrafting.init.ConfigHandler;
import com.kandivia.runecrafting.init.CraftingRegister;
import com.kandivia.runecrafting.init.DictionaryRegister;
import com.kandivia.runecrafting.init.DungeonLootRegister;
import com.kandivia.runecrafting.init.RegisterBlocks;
import com.kandivia.runecrafting.init.RegisterItems;
import com.kandivia.runecrafting.player.PlayerEventHandler;
import com.kandivia.runecrafting.worldgen.RunecraftingWorldGen;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
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
	
	@SidedProxy(clientSide = "com.kandivia.runecrafting.client.RuneProxyClient", serverSide = "com.kandivia.runecrafting.common.RuneProxyCommon")
    public static RuneProxyCommon proxy;
	
	RunecraftingWorldGen eventWorldGen = new RunecraftingWorldGen();
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent preEvent) {
		ConfigHandler.init(preEvent.getSuggestedConfigurationFile());
		RegisterBlocks.init();
		RegisterItems.init();
		DictionaryRegister.init();
		CraftingRegister.init();
		DungeonLootRegister.init();
		if(Reference.enableSilverOreSpawn)
			GameRegistry.registerWorldGenerator(eventWorldGen, 0);
		MinecraftForge.EVENT_BUS.register(new MobDropEvent());
		MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());
	}
}
