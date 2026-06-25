package net.mobz.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.WitherBossRenderer;
import net.minecraft.client.renderer.entity.layers.WitherArmorLayer;
import net.minecraft.client.renderer.entity.state.WitherRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.level.LightLayer;

public class WithenderRenderer extends WitherBossRenderer {
	private final Identifier texture;

	public WithenderRenderer(EntityRendererProvider.Context context, Identifier texture) {
		super(context);
		this.texture = texture;
		this.layers.removeIf(layer -> layer instanceof WitherArmorLayer);
	}

	@Override
	protected int getBlockLightLevel(WitherBoss entity, BlockPos blockPos) {
		return entity.isOnFire() ? 15 : entity.level().getBrightness(LightLayer.BLOCK, blockPos);
	}

	@Override
	public Identifier getTextureLocation(WitherRenderState renderState) {
		return this.texture;
	}
}
