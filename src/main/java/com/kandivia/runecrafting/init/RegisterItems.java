package com.kandivia.runecrafting.init;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;

import com.kandivia.runecrafting.items.Essence;
import com.kandivia.runecrafting.items.Materials;
import com.kandivia.runecrafting.items.Runes;
import com.kandivia.runecrafting.items.Spellbook;
import com.kandivia.runecrafting.items.Talismans;
import com.kandivia.runecrafting.items.Tiaras;
import com.kandivia.runecrafting.items.WizardArmor;
import com.kandivia.runecrafting.main.MainRegistry;
import com.kandivia.runecrafting.main.Reference;

import cpw.mods.fml.common.registry.GameRegistry;

public class RegisterItems {
	public static Item essence, runes, talismans, tiaras, tiara, silver_ingot, spellbook, 
		wizard_hat, wizard_robe, wizard_skirt, wizard_boots;
	public static Item[] wizardItem = {wizard_hat, wizard_robe, wizard_skirt, wizard_boots};
	public static String[] wizardString = {"wizard_hat", "wizard_robe", "wizard_skirt", "wizard_boots"};
	
	public static void init() {
		initItem();
		registerItem();
	}
	
	private static void initItem() {
		essence = new Essence();
		runes = new Runes();
		talismans = new Talismans();
		tiaras = new Tiaras();
		spellbook = new Spellbook();
		tiara = new Materials("tiara").setMaxStackSize(1);
		silver_ingot = new Materials("silver_ingot");
		
		for(int i = 0; i < wizardString.length; i++){
			wizardItem[i] = new WizardArmor(wizardString[i], ArmorMaterial.CLOTH , wizardString[i], i);
		}		
	}
	
	private static void registerItem() {
		registerItem(essence);
		registerItem(runes);
		registerItem(talismans);
		registerItem(tiaras);
		registerItem(spellbook);
		registerItem(tiara);
		registerItem(silver_ingot);
		
		for(int i = 0; i < wizardString.length; i++){
			registerItem(wizardItem[i]);
		}
	}
	
	private static void registerItem(Item item) {
		GameRegistry.registerItem(item, Reference.MOD_ID + " " + item.getUnlocalizedName().substring(5));
	}
}
