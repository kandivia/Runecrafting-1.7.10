package com.kandivia.runecrafting.items;

import com.kandivia.runecrafting.main.MainRegistry;
import com.kandivia.runecrafting.main.Reference;

import net.minecraft.item.Item;

public class Materials extends Item {
	public Materials(String name){
		super();
		this.setUnlocalizedName(name);
		this.setTextureName(Reference.MOD_ID + ":" + getUnlocalizedName().substring(5));
		this.setCreativeTab(MainRegistry.tabRunecrafting);
	}
}
