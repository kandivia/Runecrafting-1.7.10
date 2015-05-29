package com.kandivia.runecrafting.blocks;

import java.util.Random;

import com.kandivia.runecrafting.main.MainRegistry;
import com.kandivia.runecrafting.main.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

public class SilverOre extends Block {
	public SilverOre() {
		super(Material.rock);
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setStepSound(soundTypeStone);
		this.setBlockName("silver_ore");
		this.setBlockTextureName("silver_ore");
		this.setCreativeTab(MainRegistry.tabRunecrafting);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.blockIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + this.getUnlocalizedName().substring(5));
	}
}