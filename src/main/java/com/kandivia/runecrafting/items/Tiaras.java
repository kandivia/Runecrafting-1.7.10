package com.kandivia.runecrafting.items;

import java.util.List;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

import com.kandivia.runecrafting.main.MainRegistry;
import com.kandivia.runecrafting.main.Reference;
import com.kandivia.runecrafting.models.Model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Tiaras extends ItemArmor {

	public static final String[] type = new String[] {"air", "mind", "water", "earth", "fire", "body", 
		"cosmic", "chaos", "nature", "law", "death", "blood"};
	public String textureName;
	
	public Tiaras(){
		super(ArmorMaterial.IRON, 3 , 0);
		this.maxStackSize = 1;
		this.setUnlocalizedName("tiaras");
		this.setHasSubtypes(true);
		this.setCreativeTab(MainRegistry.tabRunecrafting);		
	}

	@SideOnly(Side.CLIENT)
	private IIcon[] icon;

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister icon) {
		this.icon = new IIcon[type.length];
		for (int i = 0; i < type.length; ++i) {
			//Why a variable for textureName? Are you referencing it elsewhere?
			this.textureName = Reference.MOD_ID + ":" + type[i] + "_tiara";
			this.icon[i] = icon.registerIcon(this.textureName);
		}
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int x) {
		int j = MathHelper.clamp_int(x, 0, type.length);
		return this.icon[j];
	}

	@Override
	public String getUnlocalizedName(ItemStack stack){
		return "item." + type[stack.getItemDamage()] + "_tiara";
	}

	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tabs, List list) {
		for(int i = 0; i < type.length; ++i){
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel (EntityLivingBase entityLiving, ItemStack itemstack, int armorSlot){
		ModelBiped armorModel = new Model(1.0F);
		return armorModel;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer){

		String name = "_tiara";
		name = type[stack.getItemDamage()] + name + "_1";
		System.out.println(name);

		return String.format("%s:textures/models/armor/%s.png", Reference.MOD_ID, name);
	}

}