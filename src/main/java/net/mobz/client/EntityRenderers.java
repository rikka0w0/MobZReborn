package net.mobz.client;

import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.mobz.ILayerDefinitionRegistration;
import net.mobz.client.renderer.entity.*;
import net.mobz.client.renderer.model.TadpoleEntityModel;
import net.mobz.client.renderer.model.ToadEntityModel;
import net.mobz.init.MobZEntities;

public class EntityRenderers {
	public static void registerAll(IEntityRendererRegisterWrapper registry) {
		registry.register(MobZEntities.TANK, TankRenderer::new);
		registry.register(MobZEntities.FAST, FastRenderer::new);
		registry.register(MobZEntities.TEST, TestRenderer::new);
		registry.register(MobZEntities.ARMORED, ArmoredRenderer::new);
		registry.register(MobZEntities.BOSS, BossRenderer::new);
		registry.register(MobZEntities.CREEP, CreepRenderer::new);
		registry.register(MobZEntities.CRIP, CripRenderer::new);
		registry.register(MobZEntities.ENDER, EnderRenderer::new);
		registry.register(MobZEntities.ENDERZOMBIE, EnderZombieRenderer::new);
		registry.register(MobZEntities.SPI, SpiRenderer::new);
		registry.register(MobZEntities.SPO, SpoRenderer::new);
		registry.register(MobZEntities.PIG, PigmanRenderer::new);
		registry.register(MobZEntities.ICEGOLEM, IceGolemRenderer::new);
		registry.register(MobZEntities.LAVAGOLEM, LavaGolemRenderer::new);
		registry.register(MobZEntities.SKELI1, skeli1renderer::new);
		registry.register(MobZEntities.SKELI2, skeli2renderer::new);
		registry.register(MobZEntities.SKELI3, skeli3renderer::new);
		registry.register(MobZEntities.ARCHERENTITY, ArcherRenderer::new);
		registry.register(MobZEntities.ARCHER2ENTITY, Archer2Renderer::new);
		registry.register(MobZEntities.BIGBOSSENTITY, BigBossRenderer::new);
		registry.register(MobZEntities.KNIGHTENTITY, KnightRenderer::new);
		registry.register(MobZEntities.KNIGHT2ENTITY, Knight2Renderer::new);
		registry.register(MobZEntities.MAGEENTITY, MageRenderer::new);
		registry.register(MobZEntities.MAGE2ENTITY, Mage2Renderer::new);
		registry.register(MobZEntities.SMALLZOMBIE, SmallZombieRenderer::new);
		registry.register(MobZEntities.FULLIRONENTITY, FullIronRenderer::new);
		registry.register(MobZEntities.FROSTENTITY, FrostRenderer::new);
        registry.register(MobZEntities.FROSTBALLENTITY,
                (context) -> new ThrownItemRenderer<>(context, 0.75F, true));
		registry.register(MobZEntities.DOG, DogRenderer::new);
		registry.register(MobZEntities.STONEGOLEM, StoneGolemRenderer::new);
		registry.register(MobZEntities.ILLUSIONER, IllusionerRenderer::new);
		registry.register(MobZEntities.DWARFENTITY, DwarfEntityRenderer::new);
		registry.register(MobZEntities.SPISMALL, SpiSmallRenderer::new);
		registry.register(MobZEntities.BLACKBEAR, BlackbearRenderer::new);
		registry.register(MobZEntities.BROWNBEAR, BrownbearRenderer::new);
		registry.register(MobZEntities.GCHICKEN, GChickenRenderer::new);
		registry.register(MobZEntities.BOAR, BoarRenderer::new);
		registry.register(MobZEntities.BOAR2, Boar2Renderer::new);
		registry.register(MobZEntities.BOAR3, Boar3Renderer::new);
		registry.register(MobZEntities.FRIEND, FriendRenderer::new);
		registry.register(MobZEntities.KNIGHT3ENTITY, Knight3Renderer::new);
		registry.register(MobZEntities.KNIGHT4ENTITY, Knight4Renderer::new);
		registry.register(MobZEntities.KNIGHT5ENTITY, Knight5Renderer::new);
		registry.register(MobZEntities.WITHENTITY, WithRenderer::new);
		registry.register(MobZEntities.SKELI4, skeli4renderer::new);
		registry.register(MobZEntities.WITHENDER, WithenderRenderer::new);
		registry.register(MobZEntities.SLIMO, SlimoRenderer::new);
		registry.register(MobZEntities.TSPIDER, TSpiderRenderer::new);
		registry.register(MobZEntities.PILLAGERBOSS, PillagerBossRenderer::new);
		registry.register(MobZEntities.BABYRAVAGERENTITY, BabyravagerRenderer::new);
		registry.register(MobZEntities.ISLANDKING, IslandKingRenderer::new);
		registry.register(MobZEntities.ISLANDKNIGHTNORMAL, IslandKnightNRenderer::new);
		registry.register(MobZEntities.ISLANDKNIGHTSPECIAL, IslandKnightSpecialRenderer::new);
		registry.register(MobZEntities.ISLANDKNIGHTSPECIAL2, IslandKnightSpecial2Renderer::new);
		registry.register(MobZEntities.ISLANDVEXENTITY, IslandVexEntityRenderer::new);
		registry.register(MobZEntities.METALGOLEM, MetalGolemRenderer::new);
		registry.register(MobZEntities.SCREEPER, SCreeperRenderer::new);

		registry.register(MobZEntities.TADPOLE, TadpoleRender::new);
		registry.register(MobZEntities.TOAD, ToadRender::new);
		registry.register(MobZEntities.TOAD_GIANT, ToadRender.Giant::new);

		registry.register(MobZEntities.WASP, WaspRender::new);
	}

	public static void registerLayerDefinitions(ILayerDefinitionRegistration registry) {
		registry.register(TadpoleEntityModel.modelResLoc, TadpoleEntityModel::createBodyLayer);
		registry.register(ToadEntityModel.modelResLoc, ToadEntityModel::createBodyLayer);
	}
}
