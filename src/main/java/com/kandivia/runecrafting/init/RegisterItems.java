package com.kandivia.runecrafting.init;

import net.minecraft.item.Item;

import com.kandivia.runecrafting.helper.Reference;
import com.kandivia.runecrafting.items.Essence;
import com.kandivia.runecrafting.items.Materials;
import com.kandivia.runecrafting.items.Runes;
import com.kandivia.runecrafting.items.Talismans;
import com.kandivia.runecrafting.items.Tiaras;

import cpw.mods.fml.common.registry.GameRegistry;

public class RegisterItems {
	public static Item essence, runes, talismans, tiaras, tiara, silver_ingot;
	
	public static void init() {
		initItem();
		registerItem();
	}
	
	private static void initItem() {
		essence = new Essence();
		runes = new Runes();
		talismans = new Talismans();
		tiaras = new Tiaras();
		
		tiara = new Materials().setUnlocalizedName("tiara").setMaxStackSize(1);
		silver_ingot = new Materials().setUnlocalizedName("silver_ingot");
		
	}
	
	private static void registerItem() {
		registerItem(essence);
		registerItem(runes);
		registerItem(talismans);
		registerItem(tiaras);
		
		registerItem(tiara);
		registerItem(silver_ingot);
	}
	
	private static void registerItem(Item item) {
		GameRegistry.registerItem(item, Reference.MOD_ID + " " + item.getUnlocalizedName().substring(5));
	}
}
