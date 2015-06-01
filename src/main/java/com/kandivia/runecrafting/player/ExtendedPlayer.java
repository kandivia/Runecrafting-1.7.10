package com.kandivia.runecrafting.player;

import java.lang.ref.WeakReference;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

import com.kandivia.runecrafting.main.Reference;

public class ExtendedPlayer implements IExtendedEntityProperties {
	
	public static final String PROPERTIESNAME = Reference.MOD_ID;
	public WeakReference<EntityPlayer> player;
	private int currentExp, currentLevel, expToLevel;

	public ExtendedPlayer() {

	}

	public ExtendedPlayer(EntityPlayer player) {
		this.player = new WeakReference<EntityPlayer>(player);
		currentExp = currentLevel = 0;
		expToLevel = 2;
	}

	public final static void register(EntityPlayer player) {
		player.registerExtendedProperties(PROPERTIESNAME, new ExtendedPlayer(player));
	}

	public final static ExtendedPlayer get(EntityPlayer player) {
		return (ExtendedPlayer) player.getExtendedProperties(PROPERTIESNAME);
	}


	@Override
	public void saveNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = new NBTTagCompound();
		properties.setInteger("currentExp", currentExp);
		properties.setInteger("currentLevel", currentLevel);
		properties.setInteger("expToLevel", expToLevel);

		compound.setTag(PROPERTIESNAME, properties);
		System.out.println("[Runecrafting]\tSaving NBT Data");

	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(PROPERTIESNAME);

		this.currentExp = properties.getInteger("currentExp");
		this.currentLevel = properties.getInteger("currentLevel");
		this.expToLevel = properties.getInteger("expToLevel");

		System.out.println("[Runecrafting]\tLoading NBT Data");
	}

	@Override
	public void init(Entity entity, World world) {

	}

	public boolean addExp(int amount) {
		boolean level = this.currentExp + amount >= this.expToLevel;
		this.currentExp += amount;
		if (level) {
			this.currentLevel++;
			this.currentExp -= expToLevel;
			this.expToLevel *= 2;
			return true;
		}
		player.get().addChatComponentMessage(new ChatComponentText("EXP to level " + (this.expToLevel-this.currentExp)));
		return false;
	}

	public void printExp() {
		System.out.println("Printing");
		player.get().addChatComponentMessage(new ChatComponentText("Current EXP is " + this.currentExp));

	}
	
	public int getLevel() {
		return this.currentLevel;
	}
}
