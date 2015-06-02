package com.kandivia.runecrafting.init;

import com.kandivia.runecrafting.items.Runes;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class DictionaryRegister {
	
	public static void init(){
		OreDictionary.registerOre("oreSilver", RegisterBlocks.silver_ore);
		OreDictionary.registerOre("ingotSilver", RegisterItems.silver_ingot);
		for(int i = 0; i < Runes.type.length; i++){
			OreDictionary.registerOre("runeRC", new ItemStack(RegisterItems.runes, 1, i));
		}
	}
}
