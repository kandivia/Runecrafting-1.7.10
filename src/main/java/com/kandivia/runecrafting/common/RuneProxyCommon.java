package com.kandivia.runecrafting.common;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class RuneProxyCommon implements IGuiHandler
{
	public void initialize ()
	{

	}

	@Override
	public Object getServerGuiElement (int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	@Override
	public Object getClientGuiElement (int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}


	public static void registerServerGuiHandler (int gui, IGuiHandler handler) {
		
	}

	public static void registerClientGuiHandler (int gui, IGuiHandler handler) {
		
	}
	
	 public ModelBiped getArmorModel(int id) {
		 return null; 
		}
}