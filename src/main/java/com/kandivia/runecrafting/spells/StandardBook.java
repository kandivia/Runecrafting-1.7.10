package com.kandivia.runecrafting.spells;

import java.util.Random;

import com.kandivia.runecrafting.init.RegisterBlocks;
import com.kandivia.runecrafting.init.RegisterItems;
import com.kandivia.runecrafting.player.ExtendedPlayer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

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
	
	private static Random rand = new Random();
	
	public static void bonesToApples(World world, EntityPlayer player) {
		int count = 0;		
		if(checkItems(player.inventory, nature, 1) && checkItems(player.inventory, earth, 2) && checkItems(player.inventory, water, 2)) {
			if(player.inventory.hasItem(Items.bone)) {
				if(player.inventory.getFirstEmptyStack() == -1) {
					if(!world.isRemote){
						player.addChatComponentMessage(new ChatComponentText("You don't have any room in your inventory!"));
					}
				}else if(consumeItems(player.inventory, nature, 1) && consumeItems(player.inventory, earth, 2) &&	
						consumeItems(player.inventory, water, 2)) {
					for(int i = 0; i < 8; i++) {				
						if(player.inventory.consumeInventoryItem(Items.bone)) {
							count++;
						}else {
							break;					
						}
					}
				}				
			}else if(!world.isRemote) {
				player.addChatComponentMessage(new ChatComponentText("You don't have any bones to cast this spell on!"));
			}
			
		}else if(!world.isRemote) {
			player.addChatComponentMessage(new ChatComponentText("You don't have the nessecary Runes to cast this spell!"));			
		}
		if(count > 0) {
			player.inventory.addItemStackToInventory(new ItemStack(Items.apple, count, 0));
			giveMagicExp(world, player, 2);
		}		
	}
	
	public static void superheatItem(World world, EntityPlayer player) {
		ItemStack[] smeltList = new ItemStack[]{new ItemStack(Blocks.iron_ore), new ItemStack(Blocks.gold_ore), new ItemStack(RegisterBlocks.silver_ore),
				new ItemStack(Blocks.sand), new ItemStack(Blocks.cobblestone), new ItemStack(Items.clay_ball),
				new ItemStack(Blocks.netherrack), new ItemStack(Blocks.clay)};
		
		if(checkItems(player.inventory, nature, 1) && checkItems(player.inventory, fire, 5)) {			
			boolean smeltableItems = false;
			for(int slot = 0; slot < player.inventory.getSizeInventory(); ++slot) {
				ItemStack itemstack = player.inventory.getStackInSlot(slot);
				for(int i = 0; i < smeltList.length; i++){
					if(itemstack != null && itemstack.isItemEqual(smeltList[i])) {
						smeltableItems = true;
						break;
					}
				}
				if(smeltableItems)
					break;
			}
			
			if(smeltableItems) {
				if(player.inventory.getFirstEmptyStack() == -1) {
					if(!world.isRemote){
						player.addChatComponentMessage(new ChatComponentText("You don't have any room in your inventory!"));
					}
				}else if(consumeItems(player.inventory, nature, 1) && consumeItems(player.inventory, fire, 5)) {
					int count = 0;
					boolean finished = false;
					for(int j = 0; j < smeltList.length && !finished; j++) {
						while(player.inventory.consumeInventoryItem(smeltList[j].getItem())) {
							if(j == 0)
								player.inventory.addItemStackToInventory(new ItemStack(Items.iron_ingot, 1));
							else if(j == 1)
								player.inventory.addItemStackToInventory(new ItemStack(Items.gold_ingot, 1));
							else if(j == 2)
								player.inventory.addItemStackToInventory(new ItemStack(RegisterItems.silver_ingot, 1));
							else if(j == 3)
								player.inventory.addItemStackToInventory(new ItemStack(Blocks.glass, 1));
							else if(j == 4)
								player.inventory.addItemStackToInventory(new ItemStack(Blocks.stone, 1));
							else if(j == 5)
								player.inventory.addItemStackToInventory(new ItemStack(Items.brick, 1));
							else if(j == 6)
								player.inventory.addItemStackToInventory(new ItemStack(Items.netherbrick, 1));
							else if(j == 7)
								player.inventory.addItemStackToInventory(new ItemStack(Blocks.hardened_clay, 1));
							count++;
							if(count >= 8) {
								finished = true;
								break;
							}
						}	
					}
					giveMagicExp(world, player, 2);
					world.playSoundAtEntity(player, "random.orb", 0.1F, 0.5F * ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.8F));
				}
			}else if(!world.isRemote) {
				player.addChatComponentMessage(new ChatComponentText("You don't have anything to cast this spell on!"));
			}
			
		}else if(!world.isRemote) {
			player.addChatComponentMessage(new ChatComponentText("You don't have the nessecary Runes to cast this spell!"));			
		}		
	}
	
	public static void giveMagicExp(World world, EntityPlayer player, int exp) {
		if(!world.isRemote) {
			ExtendedPlayer props = ExtendedPlayer.get(player);
			if (props.addMagicExp(exp)) {
				player.addChatComponentMessage(new ChatComponentText("Congratulations! You are now level " + props.getMagicLevel() + " Magic!"));
			}		
		}
	}
	
	public static boolean checkItems(IInventory inventory, ItemStack stack, int count) {
		boolean hasItems = false;
		for(int slot = 0, remain = count; slot < inventory.getSizeInventory(); ++slot) {
			ItemStack itemstack = inventory.getStackInSlot(slot);
			if(itemstack != null && itemstack.isItemEqual(stack)) {
				if((remain -= itemstack.stackSize) <= 0) {
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
		    if(itemstack != null && itemstack.isItemEqual(stack)) {
			    if((remain -= itemstack.stackSize) <= 0) {
				    flag = true;
				    break;
			    }
		    }
	    }
	    if(flag) {
		    for(int slot = 0; count > 0 && slot < inventory.getSizeInventory(); ++slot) {
			    ItemStack itemstack = inventory.getStackInSlot(slot);
			    if(itemstack != null && itemstack.isItemEqual(stack)) {
				    if((count -= itemstack.stackSize) >= 0) {
					    inventory.setInventorySlotContents(slot, (ItemStack)null);
				    }else {
					    itemstack.stackSize = -count;
				    }
			    }
		    }
	    }
	    return flag;
	}
}