package com.kandivia.runecrafting.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.kandivia.runecrafting.main.MainRegistry;
import com.kandivia.runecrafting.main.Reference;
import com.kandivia.runecrafting.player.ExtendedPlayer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Talismans extends Item{
public static final String[] type = new String[] {"air", "mind", "water", "earth", "fire", "body", 
	"cosmic", "chaos", "nature", "law", "death", "blood"};
	
	public Talismans(){
		super();
		this.maxStackSize = 1;
		this.setUnlocalizedName("talismans");
		this.setHasSubtypes(true);
		this.setCreativeTab(MainRegistry.tabRunecrafting);
	}
	
	@SideOnly(Side.CLIENT)
    private IIcon[] icon;
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister icon) {
		this.icon = new IIcon[type.length];
		for (int i = 0; i < type.length; ++i) {
			this.icon[i] = icon.registerIcon(Reference.MOD_ID + ":" + type[i] + "_talisman");
	    }
	}
	
	@SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int x) {
		int j = MathHelper.clamp_int(x, 0, type.length);
        return this.icon[j];
    }
	
	@Override
    public String getUnlocalizedName(ItemStack stack){
		return "item." + type[stack.getItemDamage()] + "_talisman";
	}
	 
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tabs, List list) {
		for(int i = 0; i < type.length; ++i){
			list.add(new ItemStack(item, 1, i));
		}
    }
	
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
    {
		if (!world.isRemote) {
			ExtendedPlayer props = ExtendedPlayer.get(player);
			
			props.printExp();
			
			System.out.println("[Runecrafting] on Item Click Talisman");
		}
		
        return itemstack;
    }
}