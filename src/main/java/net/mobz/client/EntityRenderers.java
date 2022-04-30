package net.mobz.client;

import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.mobz.ILayerDefinitionRegistration;
import net.mobz.client.renderer.entity.*;
import net.mobz.client.renderer.model.TadpoleEntityModel;
import net.mobz.client.renderer.model.ToadEntityModel;
import net.mobz.init.MobZEntities;

public class EntityRenderers {
	public static void registerAll(IEntityRendererRegisterWrapper registry) {
		registry.register(MobZEntities.TANK.get(), TankRenderer::new);
		registry.register(MobZEntities.FAST.get(), FastRenderer::new);
		registry.register(MobZEntities.ARMORED.get(), ArmoredRenderer::new);
		registry.register(MobZEntities.BOSS.get(), BossRenderer::new);
		registry.register(MobZEntities.CREEP.get(), CreepRenderer::new);
		registry.register(MobZEntities.CRIP.get(), CripRenderer::new);
		registry.register(MobZEntities.ENDER.get(), EnderRenderer::new);
		registry.register(MobZEntities.ENDERZOMBIE.get(), EnderZombieRenderer::new);
		registry.register(MobZEntities.SPI.get(), SpiRenderer::new);
		registry.register(MobZEntities.SPO.get(), SpoRenderer::new);
		registry.register(MobZEntities.PIG.get(), PigmanRenderer::new);
		registry.register(MobZEntities.ICEGOLEM.get(), IceGolemRenderer::new);
		registry.register(MobZEntities.LAVAGOLEM.get(), LavaGolemRenderer::new);
		registry.register(MobZEntities.SKELI1.get(), skeli1renderer::new);
		registry.register(MobZEntities.SKELI2.get(), skeli2renderer::new);
		registry.register(MobZEntities.SKELI3.get(), skeli3renderer::new);
		registry.register(MobZEntities.ARCHERENTITY.get(), ArcherRenderer::new);
		registry.register(MobZEntities.ARCHER2ENTITY.get(), Archer2Renderer::new);
		registry.register(MobZEntities.BIGBOSSENTITY.get(), BigBossRenderer::new);
		registry.register(MobZEntities.KNIGHTENTITY.get(), KnightRenderer::new);
		registry.register(MobZEntities.KNIGHT2ENTITY.get(), Knight2Renderer::new);
		registry.register(MobZEntities.MAGEENTITY.get(), MageRenderer::new);
		registry.register(MobZEntities.MAGE2ENTITY.get(), Mage2Renderer::new);
		registry.register(MobZEntities.SMALLZOMBIE.get(), SmallZombieRenderer::new);
		registry.register(MobZEntities.FULLIRONENTITY.get(), FullIronRenderer::new);
		registry.register(MobZEntities.FROSTENTITY.get(), FrostRenderer::new);
        registry.register(MobZEntities.FROSTBALLENTITY.get(), (context) -> new ThrownItemRenderer<>(context, 0.75F, true));
		registry.register(MobZEntities.DOG.get(), DogRenderer::new);
		registry.register(MobZEntities.STONEGOLEM.get(), StoneGolemRenderer::new);
		registry.register(MobZEntities.ILLUSIONER.get(), IllusionerRenderer::new);
		registry.register(MobZEntities.DWARFENTITY.get(), DwarfEntityRenderer::new);
		registry.register(MobZEntities.SPISMALL.get(), SpiSmallRenderer::new);
		registry.register(MobZEntities.BLACKBEAR.get(), BlackbearRenderer::new);
		registry.register(MobZEntities.BROWNBEAR.get(), BrownbearRenderer::new);
		registry.register(MobZEntities.GCHICKEN.get(), GChickenRenderer::new);
		registry.register(MobZEntities.BOAR.get(), BoarRenderer::new);
		registry.register(MobZEntities.BOAR2.get(), Boar2Renderer::new);
		registry.register(MobZEntities.BOAR3.get(), Boar3Renderer::new);
		registry.register(MobZEntities.KATHERINE.get(), FriendRenderer.KatherineRenderer::new);
		registry.register(MobZEntities.KNIGHT3ENTITY.get(), Knight3Renderer::new);
		registry.register(MobZEntities.FIORA.get(), FriendRenderer.FioraRenderer::new);
		registry.register(MobZEntities.KNIGHT5ENTITY.get(), Knight5Renderer::new);
		registry.register(MobZEntities.WITHENTITY.get(), WithRenderer::new);
		registry.register(MobZEntities.SKELI4.get(), skeli4renderer::new);
		registry.register(MobZEntities.WITHENDER.get(), WithenderRenderer::new);
		registry.register(MobZEntities.SLIMO.get(), SlimoRenderer::new);
		registry.register(MobZEntities.TSPIDER.get(), TSpiderRenderer::new);
		registry.register(MobZEntities.PILLAGERBOSS.get(), PillagerBossRenderer::new);
		registry.register(MobZEntities.BABYRAVAGERENTITY.get(), BabyravagerRenderer::new);
		registry.register(MobZEntities.ISLANDKING.get(), IslandKingRenderer::new);
		registry.register(MobZEntities.ISLANDKNIGHTNORMAL.get(), IslandKnightNRenderer::new);
		registry.register(MobZEntities.ISLANDKNIGHTSPECIAL.get(), IslandKnightSpecialRenderer::new);
		registry.register(MobZEntities.ISLANDKNIGHTSPECIAL2.get(), IslandKnightSpecial2Renderer::new);
		registry.register(MobZEntities.ISLANDVEXENTITY.get(), IslandVexEntityRenderer::new);
		registry.register(MobZEntities.METALGOLEM.get(), MetalGolemRenderer::new);
		registry.register(MobZEntities.SCREEPER.get(), SCreeperRenderer::new);

		registry.register(MobZEntities.TADPOLE.get(), TadpoleRender::new);
		registry.register(MobZEntities.TOAD.get(), ToadRender::new);
		registry.register(MobZEntities.TOAD_GIANT.get(), ToadRender.Giant::new);

		registry.register(MobZEntities.WASP.get(), WaspRender::new);
	}

	public static void registerLayerDefinitions(ILayerDefinitionRegistration registry) {
		registry.register(TadpoleEntityModel.modelResLoc, TadpoleEntityModel::createBodyLayer);
		registry.register(ToadEntityModel.modelResLoc, ToadEntityModel::createBodyLayer);
	}
}
