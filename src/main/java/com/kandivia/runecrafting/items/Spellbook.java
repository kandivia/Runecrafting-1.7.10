package com.kandivia.runecrafting.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import com.kandivia.runecrafting.main.MainRegistry;
import com.kandivia.runecrafting.main.Reference;
import com.kandivia.runecrafting.player.ExtendedPlayer;
import com.kandivia.runecrafting.spells.StandardBook;

public class Spellbook extends Item {
	
	
	public Spellbook() {
		super();
		this.maxStackSize = 1;
		this.setUnlocalizedName("standard_spellbook");
		this.setCreativeTab(MainRegistry.tabRunecrafting);
		this.setTextureName(Reference.MOD_ID + ":" + getUnlocalizedName().substring(5));
	}
	
	public void onCreated(ItemStack itemstack, World world, EntityPlayer player) {
		itemstack.stackTagCompound = new NBTTagCompound();
		itemstack.stackTagCompound.setString("owner", player.getDisplayName());
		itemstack.stackTagCompound.setDouble("teleportX", player.posX);
		itemstack.stackTagCompound.setDouble("teleportY", player.posY);
		itemstack.stackTagCompound.setDouble("teleportZ", player.posZ);
	}
	
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4) {
    if (itemStack.stackTagCompound != null) {
            String owner = itemStack.stackTagCompound.getString("owner");
            int serverX = (int) itemStack.stackTagCompound.getDouble("teleportX");
            int serverY = (int) itemStack.stackTagCompound.getDouble("teleportY");
            int serverZ = (int) itemStack.stackTagCompound.getDouble("teleportZ");
            list.add("Owner: " + owner);
            list.add("Coordinates (" + serverX + ", " + serverY + ", " + serverZ+")");
    }
}
	
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
		if(!player.isSneaking()){
			StandardBook.bonesToApples(world, player);
		}
		/*if (!player.isSneaking()) {
			MainRegistry.proxy.handleTeleport(player, itemstack);
		}
		if (player.isSneaking()) {
			itemstack.stackTagCompound.setDouble("teleportX", player.posX);
			itemstack.stackTagCompound.setDouble("teleportY", player.posY);
			itemstack.stackTagCompound.setDouble("teleportZ", player.posZ);		}
		
		if (!world.isRemote) {
			ExtendedPlayer props = ExtendedPlayer.get(player);
			boolean level = props.addExp(2);
			if (level) {
				player.addChatComponentMessage(new ChatComponentText("Congratulations! You are now level " + props.getLevel()));
			}		
		}*/
		
        return itemstack;
    }
}
