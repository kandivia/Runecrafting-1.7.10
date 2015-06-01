package com.kandivia.runecrafting.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderItem;

import com.kandivia.runecrafting.common.RuneProxyCommon;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class RuneProxyClient extends RuneProxyCommon {

    public static Minecraft mc;
    public static RenderItem itemRenderer = new RenderItem();

    public void initialize() {
        registerRenderer();
    }

    /* Registers any rendering code. */
    public void registerRenderer() {
        Minecraft mc = Minecraft.getMinecraft();
    }
    public ModelBiped getArmorModel(int id) {
    	return null;
    }
}