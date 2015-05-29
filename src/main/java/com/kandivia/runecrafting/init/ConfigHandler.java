package com.kandivia.runecrafting.init;

import java.io.File;

import com.kandivia.runecrafting.main.Reference;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class ConfigHandler {
	
	public static void init(File configFile){
		Configuration config = new Configuration(configFile);		
		try{
			config.load();
			
		//General Config			
			Property essStackSizeProp = config.get("General", "essenceMaxStackSize", 1);
			essStackSizeProp.comment = "Sets the Max Stack Size for Rune Essence.";
			Reference.essenceMaxStackSize = essStackSizeProp.getInt(1);
			
			Property talismanDropProp = config.get("General", "talismanDropChance", 5.0);
			talismanDropProp.comment = "Set the chance for Mobs to drop talismans. 5.0 = 5%";
			Reference.talismanDropChance = talismanDropProp.getDouble(5.0);
			
			Property runeDropProp = config.get("General", "runeDropChance", 20.0);
			runeDropProp.comment = "Set the chance for Mobs to drop runes. 20.0 = 20%";
			Reference.runeDropChance = runeDropProp.getDouble(20.0);
			
		//World Generation Config
			Property enableSilverProp = config.get("World Generation", "enableSilverOreSpawn", true);
			enableSilverProp.comment = "Set this to true to enable Silver Ore generation.";
			Reference.enableSilverOreSpawn = enableSilverProp.getBoolean(true);
			
			Property silverSpawnChanceProp = config.get("World Generation", "silverSpawnChance", 10);
			silverSpawnChanceProp.comment = "Sets the chance/rarity of Silver Ore to spawn.";
			Reference.silverSpawnChance = silverSpawnChanceProp.getInt(10);
			
			Property silverMinYProp = config.get("World Generation", "silverMinY", 10);
			silverMinYProp.comment = "Minimum Y-Level for Silver Ore to spawn.";
			Reference.silverMinY = silverMinYProp.getInt(10);
			
			Property silverMaxYProp = config.get("World Generation", "silverMaxY", 25);
			silverMaxYProp.comment = "Maximum Y-Level for Silver Ore to spawn.";
			Reference.silverMaxY = silverMaxYProp.getInt(25);
			
		} catch (Exception e){
			System.out.println("Runecrafting: Failed to Load Config File!");			
		}finally{
			config.save();
		}				
	}
}
