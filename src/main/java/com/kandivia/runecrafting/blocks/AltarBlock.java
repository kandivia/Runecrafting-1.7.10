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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
				int essenceCount = 0;				
				for(int slot = 0; slot < player.inventory.getSizeInventory(); ++slot) {
					ItemStack itemstack = player.inventory.getStackInSlot(slot);
					if(metaNum > 5){
						if(itemstack != null && itemstack.isItemEqual(new ItemStack(RegisterItems.essence, 1, 1))) {
							essenceCount++;
							player.inventory.setInventorySlotContents(slot, null);
							player.inventory.addItemStackToInventory(new ItemStack(RegisterItems.runes, 1, metaNum));
						}
					}else {
						if(itemstack != null && (itemstack.isItemEqual(new ItemStack(RegisterItems.essence, 1, 0)) || 
								itemstack.isItemEqual(new ItemStack(RegisterItems.essence, 1, 1)))) {
							essenceCount++;
							player.inventory.setInventorySlotContents(slot, null);
							player.inventory.addItemStackToInventory(new ItemStack(RegisterItems.runes, 1, metaNum));							
						}
					}					
				}
				giveRuneExp(world, player, essenceCount);
			}
		}
        return false;
    }
	
	public static void giveRuneExp(World world, EntityPlayer player, int exp){
		if (!world.isRemote) {
			ExtendedPlayer props = ExtendedPlayer.get(player);
			if (props.addRuneExp(exp)) {
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
