package com.kandivia.runecrafting.init;

import com.kandivia.runecrafting.items.Runes;
import com.kandivia.runecrafting.items.Talismans;

import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

public class DungeonLootRegister {
	public static String[] dungeonList = {"mineshaftCorridor", "pyramidDesertyChest", "pyramidJungleChest", 
			"strongholdCorridor", "strongholdLibrary", "strongholdCrossing", "villageBlacksmith", "dungeonChest"};
	
	public static void init(){
		for(int i = 0; i < Talismans.type.length; i++){
			for(int j = 0; j < dungeonList.length; j++){
				ChestGenHooks.addItem(dungeonList[j], new WeightedRandomChestContent(new ItemStack(RegisterItems.talismans, 1, i), 1, 1, 1));
			}			
		}
		
		for(int i = 0; i < Runes.type.length; i++){
			for(int j = 0; j < dungeonList.length; j++){
				ChestGenHooks.addItem(dungeonList[j], new WeightedRandomChestContent(new ItemStack(RegisterItems.runes, 1, i), 1, 10, 1));
			}			
		}
		
		for(int i = 0; i < dungeonList.length; i++){
			ChestGenHooks.addItem(dungeonList[i], new WeightedRandomChestContent(new ItemStack(RegisterItems.spellbook), 1, 1, 2));
		}
	}
}
