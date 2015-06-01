package com.kandivia.runecrafting.items;

import com.kandivia.runecrafting.init.RegisterItems;
import com.kandivia.runecrafting.main.MainRegistry;
import com.kandivia.runecrafting.main.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;

public class WizardArmor extends ItemArmor {
	
	private final ItemArmor.ArmorMaterial material;
	private String textureName;
	
	public WizardArmor(String unlocalizedName, ArmorMaterial material, String textureName, int type) {
		super(material, 0, type);
		this.material = material;
		this.textureName = textureName;
	    this.setUnlocalizedName(unlocalizedName);
	    this.setTextureName(Reference.MOD_ID + ":" + unlocalizedName);
		this.setCreativeTab(MainRegistry.tabRunecrafting);
	}
	
	@SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses() {
        return false;
    }
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
	    return Reference.MOD_ID + ":textures/models/armor/wizard_layer_" + (this.armorType == 2 ? "2" : "1") + ".png";
	}
}
