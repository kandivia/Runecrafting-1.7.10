package com.kandivia.runecrafting.event;

import java.util.Random;

import com.kandivia.runecrafting.helper.Reference;
import com.kandivia.runecrafting.init.RegisterItems;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class MobDropEvent {
	
	Random rand = new Random();
	private double rareTalismanChance = 20.0;
	private double rareRuneChance = 20.0;
	
	@SubscribeEvent
	public void onEntityDrop(LivingDropsEvent event) {
		if(event.source.getDamageType().equals("player")) {
			if(event.entity instanceof EntityZombie) {
				if(willDropTalisman(event.lootingLevel)){
					event.entityLiving.entityDropItem(new ItemStack(RegisterItems.talismans, 1, getRandomType(new int[]{1,3})), 0.0F);
				}
				if(willDropRunes()){
					event.entityLiving.entityDropItem(new ItemStack(RegisterItems.runes, rand.nextInt(3 + event.lootingLevel) + 1, getRandomType(new int[]{1,3})), 0.0F);
				}
			}else if(event.entity instanceof EntitySkeleton){
				if(((EntitySkeleton) event.entity).getSkeletonType() == 1){
					if(willDropTalisman(event.lootingLevel)){
						if(rand.nextDouble() * 100 < rareTalismanChance)
							event.entityLiving.entityDropItem(new ItemStack(RegisterItems.talismans, 1, 11), 0.0F);
						else
							event.entityLiving.entityDropItem(new ItemStack(RegisterItems.talismans, 1, getRandomType(new int[]{0,5})), 0.0F);
					}
					if(willDropRunes()){
						if(rand.nextDouble() * 100 < rareRuneChance)
							event.entityLiving.entityDropItem(new ItemStack(RegisterItems.runes, rand.nextInt(2 + event.lootingLevel) + 1, 11), 0.0F);
						else
							event.entityLiving.entityDropItem(new ItemStack(RegisterItems.runes, rand.nextInt(3 + event.lootingLevel) + 1, getRandomType(new int[]{0,5})), 0.0F);
					}
				}else{
					if(willDropTalisman(event.lootingLevel)){
						event.entityLiving.entityDropItem(new ItemStack(RegisterItems.talismans, 1, getRandomType(new int[]{0,5})), 0.0F);
					}
					if(willDropRunes()){
						event.entityLiving.entityDropItem(new ItemStack(RegisterItems.runes, rand.nextInt(3 + event.lootingLevel) + 1, getRandomType(new int[]{0,5})), 0.0F);
					}
				}
			}else if(event.entity instanceof EntityCreeper){
				if(willDropTalisman(event.lootingLevel)){
					if(rand.nextDouble() * 100 < rareTalismanChance)
						event.entityLiving.entityDropItem(new ItemStack(RegisterItems.talismans, 1, 8), 0.0F);
					else
						event.entityLiving.entityDropItem(new ItemStack(RegisterItems.talismans, 1, 4), 0.0F);
				}
				if(willDropRunes()){
					if(rand.nextDouble() * 100 < rareRuneChance)
						event.entityLiving.entityDropItem(new ItemStack(RegisterItems.runes, rand.nextInt(2 + event.lootingLevel) + 1, 8), 0.0F);
					else
						event.entityLiving.entityDropItem(new ItemStack(RegisterItems.runes, rand.nextInt(3 + event.lootingLevel) + 1, 4), 0.0F);
				}
			}else if(event.entity instanceof EntitySpider){
				if(willDropTalisman(event.lootingLevel)){
					event.entityLiving.entityDropItem(new ItemStack(RegisterItems.talismans, 1, 3), 0.0F);
				}
				if(willDropRunes()){
					event.entityLiving.entityDropItem(new ItemStack(RegisterItems.runes, rand.nextInt(3 + event.lootingLevel) + 1, 3), 0.0F);
				}
			}
			//add remaining mobs here
		}
	}
	
	private int getRandomType(int[] i) {
		return i[rand.nextInt(i.length)];
	}

	private boolean willDropRunes() {
		if((rand.nextDouble() * 100) < (Reference.runeDropChance))
			return true;
		else
			return false;
	}

	private boolean willDropTalisman(int looting){
		if((rand.nextDouble() * 100) < (Reference.talismanDropChance + looting))
			return true;
		else
			return false;
	}
}