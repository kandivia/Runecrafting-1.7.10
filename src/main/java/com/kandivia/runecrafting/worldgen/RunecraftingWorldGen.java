package com.kandivia.runecrafting.worldgen;

import java.util.Random;

import com.kandivia.runecrafting.init.RegisterBlocks;
import com.kandivia.runecrafting.main.Reference;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class RunecraftingWorldGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.dimensionId){
		case 0 :
			generateSurface(world, random, chunkX*16, chunkZ*16);
		case -1 :
		case 1 :
		}		
	}

	private void generateSurface(World world, Random random, int x, int z) {
		this.addOreSpawn(RegisterBlocks.silver_ore, world, random, x, z, 16, 16, 
				4+random.nextInt(6), Reference.silverSpawnChance, Reference.silverMinY, Reference.silverMaxY);
	}

	private void generateNether(World world, Random random, int x, int z) {
	}
	
	private void generateEnd(World world, Random random, int x, int z) {		
	}
	
	private void addOreSpawn(Block block, World world,
			Random random, int blockXPos, int blockZPos, int maxX, int maxZ, 
			int maxVeinSize, int chanceToSpawn, int minY, int maxY) {
		for(int i = 0; i < chanceToSpawn; i++) {
			int posX = blockXPos + random.nextInt(maxX);
			int posY = minY + random.nextInt(maxY - minY);
			int posZ = blockZPos + random.nextInt(maxZ);
			(new WorldGenMinable(block, maxVeinSize)).generate(world, random, posX, posY, posZ);
		}
	}
}
