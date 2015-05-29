package com.kandivia.runecrafting.blocks;

import java.util.Random;

import com.kandivia.runecrafting.init.RegisterItems;
import com.kandivia.runecrafting.main.MainRegistry;
import com.kandivia.runecrafting.main.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class EssenceOre extends Block {
	private boolean hasSilkTouch = false;
	private int itemDamage;

	public EssenceOre() {
		super(Material.rock);
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setStepSound(soundTypeStone);
		this.setBlockName("essence_ore");
		this.setBlockTextureName("essence_ore");
		this.setCreativeTab(MainRegistry.tabRunecrafting);
	}
	
	public int quantityDropped(Random random) {
		return 1;
	}
	
	private Random rand = new Random();
    @Override
    public int getExpDrop(IBlockAccess a, int x, int y) {
        if (this.getItemDropped(x, rand, y) != Item.getItemFromBlock(this)) {
            return MathHelper.getRandomIntegerInRange(rand, 2, 5);
        }
        return 0;
    }
    
    public void onBlockHarvested(World world, int x, int y, int z, int meta, EntityPlayer player) {
    	ItemStack item = player.inventory.getCurrentItem();
		hasSilkTouch = false;
		if (item != null) {
			if (item.stackTagCompound != null) {
				if (item.stackTagCompound.getTag("ench") != null) {
					NBTTagList enchants = (NBTTagList) item.stackTagCompound
							.getTag("ench");
					for (int i = 0; i < enchants.tagCount(); i++) {
						NBTTagCompound enchant = ((NBTTagList) enchants)
								.getCompoundTagAt(i);
						if (enchant.getInteger("id") == 33) {
							hasSilkTouch = true;
							break;
						}
					}
				}
			}
		}
	}

	public void onBlockDestroyedByPlayer(World world, int x, int y, int z,
			int meta) {
		if ((!world.isRemote) && (!hasSilkTouch)) {
			itemDamage = 0;
		}else if(!world.isRemote) {
			itemDamage = 1;
		}
		super.onBlockDestroyedByPlayer(world, x, y, z, 1);
	}

	public Item getItemDropped(int par1, Random par2, int par3) {
		return RegisterItems.essence;
	}
	
	public int damageDropped(int x) {
        return itemDamage;
    }
	
	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata) {
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.blockIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + this.getUnlocalizedName().substring(5));
	}
}
