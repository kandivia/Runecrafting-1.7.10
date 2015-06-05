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
	
	public WizardArmor(String name, ArmorMaterial material, int renderIndex, int type) {
		super(material, renderIndex, type);
		this.material = material;
	    this.setUnlocalizedName(name);
	    this.setTextureName(Reference.MOD_ID + ":" + name);
		this.setCreativeTab(MainRegistry.tabRunecrafting);
	}
	
	@SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses() {
        return false;
    }
	
	public int getColor(ItemStack stack) {
        if (this.material != ItemArmor.ArmorMaterial.CLOTH) {
            return -1;
        }else {
            NBTTagCompound nbttagcompound = stack.getTagCompound();
            if (nbttagcompound == null) {
                return 16777215;
            }else {
                NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");
                return nbttagcompound1 == null ? 16777215 : (nbttagcompound1.hasKey("color", 3) ? nbttagcompound1.getInteger("color") : 16777215);
            }
        }
    }
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
	    return Reference.MOD_ID + ":textures/models/armor/wizard_layer_" + (this.armorType == 2 ? "2" : "1") + ".png";
	}
}
