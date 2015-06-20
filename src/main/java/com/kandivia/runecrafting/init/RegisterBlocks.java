package com.kandivia.runecrafting.init;

import com.kandivia.runecrafting.blocks.AltarBlock;
import com.kandivia.runecrafting.blocks.EssenceOre;
import com.kandivia.runecrafting.blocks.SilverOre;
import com.kandivia.runecrafting.items.ItemAltarBlock;
import com.kandivia.runecrafting.main.Reference;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class RegisterBlocks {
	public static Block silver_ore, essence_ore, atlar;
	
	public static void init() {
		initBlock();
		registerBlock();
	}
	
	private static void initBlock() {
		silver_ore = new SilverOre();
		essence_ore = new EssenceOre();
		atlar = new AltarBlock();
	}
	
	private static void registerBlock() {
		registerBlock(silver_ore);
		registerBlock(essence_ore);
		GameRegistry.registerBlock(atlar, ItemAltarBlock.class, Reference.MOD_ID + " " + atlar.getUnlocalizedName().substring(5));
	}
	
	public static void registerBlock(Block block) {
		GameRegistry.registerBlock(block, Reference.MOD_ID + " " + block.getUnlocalizedName().substring(5));
	}
}
