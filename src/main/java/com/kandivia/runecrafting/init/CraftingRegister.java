package com.kandivia.runecrafting.init;

import com.kandivia.runecrafting.items.Tiaras;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class CraftingRegister {
		private static ItemStack silver_ingot = new  ItemStack(RegisterItems.silver_ingot);
		private static ItemStack tiara = new ItemStack(RegisterItems.tiara);
		
		public static void init() {
			addCraftingRecipies();
			addSmeltingRecipies();
		}
		
		public static void addCraftingRecipies() {
			//Tiara
			GameRegistry.addShapedRecipe(tiara, "xxx", "x x", 'x', silver_ingot);
			
			//Infused Tiaras
			for(int i = 0; i < Tiaras.type.length; i++){
				GameRegistry.addShapelessRecipe(new ItemStack(RegisterItems.tiaras, 1, i), new ItemStack(RegisterItems.talismans, 1, i), tiara);				
			}
		}
		
		public static void addSmeltingRecipies() {
			GameRegistry.addSmelting(RegisterBlocks.silver_ore, silver_ingot, 0.2F);
		}
}