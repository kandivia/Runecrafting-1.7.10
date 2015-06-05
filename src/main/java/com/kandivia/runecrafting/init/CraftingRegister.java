package com.kandivia.runecrafting.init;

import com.kandivia.runecrafting.items.Tiaras;
import com.kandivia.runecrafting.main.Reference;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class CraftingRegister {
		private static ItemStack silver_ingot = new  ItemStack(RegisterItems.silver_ingot);
		private static ItemStack tiara = new ItemStack(RegisterItems.tiara);
		private static ItemStack writable_book = new ItemStack(Items.writable_book);
		private static ItemStack ball_of_wool = new ItemStack(RegisterItems.ball_of_wool);
		
		public static void init() {
			addCraftingRecipies();
			addSmeltingRecipies();
		}
		
		public static void addCraftingRecipies() {
			//Tiara
			GameRegistry.addRecipe(new ShapedOreRecipe(tiara, "xxx", "x x", 'x', "ingotSilver"));
			
			//Infused Tiaras
			for(int i = 0; i < Tiaras.type.length; i++) {
				GameRegistry.addShapelessRecipe(new ItemStack(RegisterItems.tiaras, 1, i), new ItemStack(RegisterItems.talismans, 1, i), tiara);				
			}
			
			//Wizard Armor
			GameRegistry.addShapedRecipe(new ItemStack(RegisterItems.wizard_hat), "xxx", "x x", 'x', ball_of_wool);
			GameRegistry.addShapedRecipe(new ItemStack(RegisterItems.wizard_robe), "x x", "xxx", "xxx", 'x', ball_of_wool);
			GameRegistry.addShapedRecipe(new ItemStack(RegisterItems.wizard_skirt), "xxx", "x x", "x x", 'x', ball_of_wool);
			GameRegistry.addShapedRecipe(new ItemStack(RegisterItems.wizard_boots), "x x", "x x", 'x', ball_of_wool);
			
			//Placeholder Spellbook
			GameRegistry.addRecipe(new ShapelessOreRecipe(RegisterItems.spellbook, "runeRC", writable_book));
		}
		
		public static void addSmeltingRecipies() {
			if(Reference.enableSilverOreSpawn)
				GameRegistry.addSmelting(RegisterBlocks.silver_ore, silver_ingot, 0.2F);
		}
}
