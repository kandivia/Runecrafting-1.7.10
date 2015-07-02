package com.kandivia.runecrafting.blocks;

import java.util.List;

import com.kandivia.runecrafting.init.RegisterItems;
import com.kandivia.runecrafting.main.MainRegistry;
import com.kandivia.runecrafting.main.Reference;
import com.kandivia.runecrafting.player.ExtendedPlayer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S2FPacketSetSlot;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class AltarBlock extends Block{
	public static final String[] type = new String[] {"air", "mind", "water", "earth", "fire", "body", 
		"cosmic", "chaos", "nature", "law", "death", "blood"};
	
	@SideOnly(Side.CLIENT)
    private IIcon[] icon;	

	public AltarBlock() {
		super(Material.rock);
		this.setBlockName("altar");
		this.setHardness(-1.0F);
		this.setStepSound(soundTypeStone);
		this.setCreativeTab(MainRegistry.tabRunecrafting); //Temporary, Remove on Official Release
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int a, float b, float c, float d) {
		if(!player.isSneaking()) {
			int metaNum = this.getDamageValue(world, x, y, z);
			if((player.inventory.armorItemInSlot(3) != null && player.inventory.armorItemInSlot(3).isItemEqual(new ItemStack(RegisterItems.tiaras, 1, metaNum))) ||
					(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().isItemEqual(new ItemStack(RegisterItems.talismans, 1, metaNum)))) {
				int currentLevel = ExtendedPlayer.get(player).getRuneLevel();
				switch(metaNum){
				case 0:
					craftRunes(world, player, metaNum, 1, currentLevel, 0);												
					break;
				case 1:
					craftRunes(world, player, metaNum, 1, currentLevel, 0);
					break;
				case 2:
					craftRunes(world, player, metaNum, 2, currentLevel, 5);
					break;
				case 3:
					craftRunes(world, player, metaNum, 4, currentLevel, 9);
					break;
				case 4:
					craftRunes(world, player, metaNum, 8, currentLevel, 14);
					break;
				case 5:
					craftRunes(world, player, metaNum, 16, currentLevel, 20);
					break;
				case 6:
					craftRunes(world, player, metaNum, 32, currentLevel, 27);
					break;
				case 7:
					craftRunes(world, player, metaNum, 64, currentLevel, 35);
					break;
				case 8:
					craftRunes(world, player, metaNum, 128, currentLevel, 44);
					break;
				case 9:
					craftRunes(world, player, metaNum, 252, currentLevel, 54);
					break;
				case 10:
					craftRunes(world, player, metaNum, 504, currentLevel, 65);
					break;
				case 11:
					craftRunes(world, player, metaNum, 1008, currentLevel, 77);
					break;
				}				
			}
		}
        return false;
    }
	
	public static void craftRunes(World world, EntityPlayer player, int metaNum, int runeExp, int currentLevel, int reqLevel) {
		if(currentLevel >= reqLevel) {
			if(player.inventory.getFirstEmptyStack() == -1) {
				if(!world.isRemote)
					player.addChatComponentMessage(new ChatComponentText("You don't have any room in your inventory!"));
			}else {
				int essenceCount = 0;				
				for(int slotNum = 0; slotNum < player.inventory.getSizeInventory(); slotNum++) {
					ItemStack itemstack = player.inventory.getStackInSlot(slotNum);
					if(metaNum <= 5 && ((itemstack != null) && itemstack.isItemEqual(new ItemStack(RegisterItems.essence, 1, 0)))) {
						essenceCount++;
						player.inventory.setInventorySlotContents(slotNum, null);
					}
					if(itemstack != null && (itemstack.isItemEqual(new ItemStack(RegisterItems.essence, 1, 1)))) {
						essenceCount++;
						player.inventory.setInventorySlotContents(slotNum, null);	
					}					
				}
				player.inventory.addItemStackToInventory(new ItemStack(RegisterItems.runes, essenceCount, metaNum));
				giveRuneExp(world, player, (essenceCount * runeExp));
			}
		}else if(!world.isRemote) {
			player.addChatComponentMessage(new ChatComponentText("You need level "+ reqLevel + " Runecrafting for this altar."));
		}		
	}
	
	public static void giveRuneExp(World world, EntityPlayer player, int exp) {
		if(!world.isRemote) {
			ExtendedPlayer props = ExtendedPlayer.get(player);
			if(props.addRuneExp(exp)) {
				player.addChatComponentMessage(new ChatComponentText("Congratulations! You are now level " + props.getRuneLevel() + " Runecrafting!"));
			}		
		}
	}
	
	@Override
    public void registerBlockIcons(IIconRegister reg) {
		icon = new IIcon[type.length];
		for(int i = 0; i < type.length; i++){
			icon[i] = reg.registerIcon(Reference.MOD_ID + ":" + type[i] + "_altar");
		}
	}
	
	@Override
	public void getSubBlocks(Item block, CreativeTabs tab, List list) {
	    for(int i = 0; i < type.length; i++) {
	        list.add(new ItemStack(block, 1, i));
	    }
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return icon[meta];
	}
	
	@Override
	public int damageDropped(int meta) {
	    return meta;
	}	
}
