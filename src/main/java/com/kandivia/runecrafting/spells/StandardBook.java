package com.kandivia.runecrafting.spells;

import com.kandivia.runecrafting.init.RegisterItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;

public class StandardBook {
	public static ItemStack air = new ItemStack(RegisterItems.runes, 1, 0);
	public static ItemStack mind = new ItemStack(RegisterItems.runes, 1, 1);
	public static ItemStack water = new ItemStack(RegisterItems.runes, 1, 2);
	public static ItemStack earth = new ItemStack(RegisterItems.runes, 1, 3);
	public static ItemStack fire = new ItemStack(RegisterItems.runes, 1, 4);
	public static ItemStack body = new ItemStack(RegisterItems.runes, 1, 5);
	public static ItemStack cosmic = new ItemStack(RegisterItems.runes, 1, 6);
	public static ItemStack chaos = new ItemStack(RegisterItems.runes, 1, 7);
	public static ItemStack nature = new ItemStack(RegisterItems.runes, 1, 8);
	public static ItemStack law = new ItemStack(RegisterItems.runes, 1, 9);
	public static ItemStack death = new ItemStack(RegisterItems.runes, 1, 10);
	
	public static void bonesToApples(EntityPlayer player){
		int count = 0;
		if(checkItems(player.inventory, nature, 1) && checkItems(player.inventory, earth, 2) && checkItems(player.inventory, water, 2)){
			if(player.inventory.hasItem(Items.bone)){
				if(consumeItems(player.inventory, nature, 1) &&	consumeItems(player.inventory, earth, 2) &&	
						consumeItems(player.inventory, water, 2)){
					for(int i = 0; i < 8; i++){				
						if(player.inventory.consumeInventoryItem(Items.bone)){
							count++;
						}else {
							break;					
						}
					}
				}				
			}else{
				player.addChatComponentMessage(new ChatComponentText("You don't have any bones to cast this spell on!"));
			}
			
		}else{
			player.addChatComponentMessage(new ChatComponentText("You don't have the nessecary Runes to cast this spell!"));
		}		
		player.inventory.addItemStackToInventory(new ItemStack(Items.apple, count, 0));
	}
	
	public static boolean checkItems(IInventory inventory, ItemStack stack, int count) {
		boolean hasItems = false;
		for (int slot = 0, remain = count; slot < inventory.getSizeInventory(); ++slot) {
			ItemStack itemstack = inventory.getStackInSlot(slot);
			if (itemstack != null && itemstack.isItemEqual(stack)) {
				if ((remain -= itemstack.stackSize) <= 0) {
					hasItems = true;
					break;
				}
			}
		}
		return hasItems;
	}
	
	public static boolean consumeItems(IInventory inventory, ItemStack stack, int count) {
	    boolean flag = false;
	    for(int slot = 0, remain = count; slot < inventory.getSizeInventory(); ++slot) {
		    ItemStack itemstack = inventory.getStackInSlot(slot);
		    if (itemstack != null && itemstack.isItemEqual(stack)) {
			    if ((remain -= itemstack.stackSize) <= 0) {
				    flag = true;
				    break;
			    }
		    }
	    }
	    if(flag) {
		    for (int slot = 0; count > 0 && slot < inventory.getSizeInventory(); ++slot) {
			    ItemStack itemstack = inventory.getStackInSlot(slot);
			    if (itemstack != null && itemstack.isItemEqual(stack)) {
				    if ((count -= itemstack.stackSize) >= 0) {
					    inventory.setInventorySlotContents(slot, (ItemStack)null);
				    } else {
					    itemstack.stackSize = -count;
				    }
			    }
		    }
	    }
	    return flag;
	}
}
