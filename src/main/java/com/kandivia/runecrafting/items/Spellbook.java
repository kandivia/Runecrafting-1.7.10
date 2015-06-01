package com.kandivia.runecrafting.items;

import com.kandivia.runecrafting.main.MainRegistry;
import com.kandivia.runecrafting.main.Reference;
import com.kandivia.runecrafting.player.ExtendedPlayer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class Spellbook extends Item {
	public Spellbook(){
		super();
		this.maxStackSize = 1;
		this.setUnlocalizedName("standard_spellbook");
		this.setCreativeTab(MainRegistry.tabRunecrafting);
		this.setTextureName(Reference.MOD_ID + ":" + getUnlocalizedName().substring(5));
	}
	
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
    {
		if (!world.isRemote) {
			ExtendedPlayer props = ExtendedPlayer.get(player);
			boolean level = props.addExp(2);
			if (level) {
				player.addChatComponentMessage(new ChatComponentText("Congratulations! You are now level " + props.getLevel()));
			}
			System.out.println("[Runecrafting] on Item Click Spellbook");
			
		}
		
        return itemstack;
    }
}
