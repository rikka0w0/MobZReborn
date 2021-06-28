package net.mobz.client;

import net.mobz.client.renderer.entity.BossRenderer;
import net.mobz.init.MobZEntities;

public class EntityRenderers {
	public static void registerAll(IEntityRendererRegisterWrapper registry) {
		registry.register(MobZEntities.BOSS, BossRenderer::new);
	}
}
