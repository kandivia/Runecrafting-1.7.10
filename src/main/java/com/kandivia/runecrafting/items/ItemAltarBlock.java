package com.kandivia.runecrafting.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemAltarBlock extends ItemBlock {
	public static final String[] type = new String[] {"air", "mind", "water", "earth", "fire", "body", 
		"cosmic", "chaos", "nature", "law", "death", "blood"};
	
	public ItemAltarBlock(Block block) {
		super(block);
		this.setHasSubtypes(true);
	}
	
	public String getUnlocalizedName(ItemStack itemstack) {
		int i = itemstack.getItemDamage();
		if(i < 0 || i >= type.length) {
			i = 0;
		}
		return type[i] + "_" + super.getUnlocalizedName();
	}
	
	public int getMetadata(int meta) {
		return meta;
	}
}