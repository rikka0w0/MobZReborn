package net.mobz.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;

public class MobSpawnHelper {
	public static boolean isPossibleToRespawnInThis(LevelReader view, BlockPos posentity) {
		BlockState blockState = view.getBlockState(posentity);
		return blockState.getBlock().isPossibleToRespawnInThis(blockState);
	}

	public static boolean checkSpawnObstruction(Entity thisEntity, LevelReader view) {
		BlockPos blockunderentity = thisEntity.blockPosition().below();
		BlockPos posentity = thisEntity.blockPosition();

		return view.isUnobstructed(thisEntity) && !view.containsAnyLiquid(thisEntity.getBoundingBox())
				&& isPossibleToRespawnInThis(view, posentity)
				&& view.getBlockState(blockunderentity).isValidSpawn(view, blockunderentity, thisEntity.getType());
	}
}
