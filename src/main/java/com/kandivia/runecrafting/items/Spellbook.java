package com.kandivia.runecrafting.items;

import com.kandivia.runecrafting.helper.Reference;
import com.kandivia.runecrafting.main.MainRegistry;

import net.minecraft.item.Item;

public class Spellbook extends Item {
	public Spellbook(){
		super();
		this.maxStackSize = 1;
		this.setUnlocalizedName("standard_spellbook");
		this.setCreativeTab(MainRegistry.tabRunecrafting);
		this.setTextureName(Reference.MOD_ID + ":" + getUnlocalizedName().substring(5));
	}
}
