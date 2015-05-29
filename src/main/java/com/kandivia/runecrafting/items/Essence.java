package com.kandivia.runecrafting.items;

import java.util.List;

import com.kandivia.runecrafting.main.MainRegistry;
import com.kandivia.runecrafting.main.Reference;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockLog;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.entity.player.BonemealEvent;

public class Essence extends Item {
	public static final String[] type = new String[] {"rune", "pure"};
	
	public Essence(){
		super();
		this.maxStackSize = Reference.essenceMaxStackSize;
		this.setUnlocalizedName("essence");
		this.setHasSubtypes(true);
		this.setCreativeTab(MainRegistry.tabRunecrafting);
	}
	
	@SideOnly(Side.CLIENT)
    private IIcon[] icon;
	
	@SideOnly(Side.CLIENT)
	 public void registerIcons(IIconRegister icon) {
		 this.icon = new IIcon[type.length];
	     for (int i = 0; i < type.length; ++i) {
	         this.icon[i] = icon.registerIcon(Reference.MOD_ID + ":" + type[i] + "_essence");
	     }
	 }
	
	@SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int x) {
        int j = MathHelper.clamp_int(x, 0, type.length);
        return this.icon[j];
    }
	
	 @Override
     public String getUnlocalizedName(ItemStack stack){
             return "item." + type[stack.getItemDamage()] + "_essence";
	 }
	 
	 @SideOnly(Side.CLIENT)
     public void getSubItems(Item item, CreativeTabs tabs, List list) {
		 for(int i = 0; i < type.length; ++i){
			 list.add(new ItemStack(item, 1, i));
		 }
     }
}
