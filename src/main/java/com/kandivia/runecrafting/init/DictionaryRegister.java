package com.kandivia.runecrafting.init;

import net.minecraftforge.oredict.OreDictionary;

public class DictionaryRegister {
	
	public static void init(){
		OreDictionary.registerOre("oreSilver", RegisterBlocks.silver_ore);
		OreDictionary.registerOre("ingotSilver", RegisterItems.silver_ingot);
	}

}
