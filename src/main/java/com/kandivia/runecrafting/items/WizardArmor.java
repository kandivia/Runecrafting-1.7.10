package com.kandivia.runecrafting.items;

import com.kandivia.runecrafting.init.RegisterItems;
import com.kandivia.runecrafting.main.MainRegistry;
import com.kandivia.runecrafting.main.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class WizardArmor extends ItemArmor {
	
	public WizardArmor(ArmorMaterial material, int renderIndex, int armorType) {
		super(material, renderIndex, armorType);
		this.setCreativeTab(MainRegistry.tabRunecrafting);
	}
	
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        if(stack.getItem() == RegisterItems.wizard_hat || stack.getItem() == RegisterItems.wizard_robe || stack.getItem() == RegisterItems.wizard_boots) {
        	return Reference.MOD_ID + ":textures/models/armor/wizardArmorLayer1.png";
        }else if(stack.getItem() == RegisterItems.wizard_skirt) {
        	return Reference.MOD_ID + ":textures/models/armor/wizardArmorLayer2.png";
        }else {
        	return null;
        }
    }
}
