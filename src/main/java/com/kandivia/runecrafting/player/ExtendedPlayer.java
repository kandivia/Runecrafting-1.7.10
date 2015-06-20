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
	private int magicExp, magicLevel, magicExpToLevel,
		runeExp, runeLevel, runeExpToLevel;

	public ExtendedPlayer() {}

	public ExtendedPlayer(EntityPlayer player) {
		this.player = new WeakReference<EntityPlayer>(player);
		magicExp = magicLevel = 0;
		magicExpToLevel = 2;
		runeExp = runeLevel = 0;
		runeExpToLevel = 2;
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
		properties.setInteger("magicExp", magicExp);
		properties.setInteger("magicLevel", magicLevel);
		properties.setInteger("magicExpToLevel", magicExpToLevel);
		properties.setInteger("runeExp", runeExp);
		properties.setInteger("runeLevel", runeLevel);
		properties.setInteger("runeExpToLevel", runeExpToLevel);

		compound.setTag(PROPERTIESNAME, properties);
		System.out.println("[Runecrafting]\tSaving NBT Data");
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(PROPERTIESNAME);

		this.magicExp = properties.getInteger("magicExp");
		this.magicLevel = properties.getInteger("magicLevel");
		this.magicExpToLevel = properties.getInteger("magicExpToLevel");
		this.runeExp = properties.getInteger("runeExp");
		this.runeLevel = properties.getInteger("runeLevel");
		this.runeExpToLevel = properties.getInteger("runeExpToLevel");

		System.out.println("[Runecrafting]\tLoading NBT Data");
	}

	@Override
	public void init(Entity entity, World world) {}

	public boolean addMagicExp(int amount) {
		boolean level = this.magicExp + amount >= this.magicExpToLevel;
		boolean hasLeveled = false;
		this.magicExp += amount;
		while(level) {
			hasLeveled = true;
			this.magicLevel++;
			this.magicExp -= magicExpToLevel;
			this.magicExpToLevel *= 2;
			level = this.magicExp + amount >= this.magicExpToLevel;
		}
		if(!hasLeveled)
			player.get().addChatComponentMessage(new ChatComponentText("EXP to level " + (this.magicExpToLevel-this.magicExp)));
		return hasLeveled;
	}
	
	public boolean addRuneExp(int amount) {
		boolean level = this.runeExp + amount >= this.runeExpToLevel;
		boolean hasLeveled = false;
		this.runeExp += amount;
		while(level) {
			hasLeveled = true;
			this.runeLevel++;
			this.runeExp -= runeExpToLevel;
			this.runeExpToLevel *= 2;
			level = this.runeExp + amount >= this.runeExpToLevel;
		}
		if(!hasLeveled)
			player.get().addChatComponentMessage(new ChatComponentText("EXP to level " + (this.runeExpToLevel-this.runeExp)));
		return hasLeveled;
	}

	public void printMagicExp() {
		System.out.println("Printing");
		player.get().addChatComponentMessage(new ChatComponentText("Current Magic Exp is " + this.magicExp));
	}
	
	public void printRuneExp() {
		System.out.println("Printing");
		player.get().addChatComponentMessage(new ChatComponentText("Current Runecrafting Exp is " + this.runeExp));
	}
	
	public int getMagicLevel() {
		return this.magicLevel;
	}
	
	public int getRuneLevel() {
		return this.runeLevel;
	}
}
