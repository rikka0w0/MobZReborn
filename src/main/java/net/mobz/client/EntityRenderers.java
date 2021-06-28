package net.mobz.client;

import net.mobz.client.renderer.entity.BossRenderer;
import net.mobz.init.Entities;

public class EntityRenderers {
	public static void registerAll(IEntityRendererRegisterWrapper registry) {
		registry.register(Entities.BOSS, BossRenderer::new);
	}
}
