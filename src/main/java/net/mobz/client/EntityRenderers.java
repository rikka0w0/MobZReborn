package net.mobz.client;

import net.minecraft.client.renderer.entity.ThrownItemRenderer;

import net.mobz.ILayerDefinitionRegistration;
import net.mobz.client.renderer.entity.*;
import net.mobz.client.renderer.model.TadpoleEntityModel;
import net.mobz.client.renderer.model.ToadEntityModel;
import net.mobz.init.MobZEntities;

public class EntityRenderers {
	public static void registerAll(IEntityRendererRegisterWrapper registry) {
		registry.register(MobZEntities.TANK.get(), EasyZombieRenderer::new);
		registry.register(MobZEntities.FAST.get(), EasyZombieRenderer::new);
		registry.register(MobZEntities.ARMORED.get(), EasyZombieRenderer::new);
		registry.register(MobZEntities.BOSS.get(), EasyZombieRenderer::new);
		registry.register(MobZEntities.CREEP.get(), EasyCreeperRenderer::new);
		registry.register(MobZEntities.CRIP.get(), EasyCreeperRenderer::new);
		registry.register(MobZEntities.ENDER.get(), EnderRenderer::new);
		registry.register(MobZEntities.ENDERZOMBIE.get(), EasyZombieRenderer::new);
		registry.register(MobZEntities.SPI.get(), EasySpiderRenderer::new);
		registry.register(MobZEntities.SPO.get(), EasySpiderRenderer::new);
		registry.register(MobZEntities.PIG.get(), PigmanRenderer::new);
		registry.register(MobZEntities.ICEGOLEM.get(), IceGolemRenderer::new);
		registry.register(MobZEntities.LAVAGOLEM.get(), EasyGolemRenderer::new);
		registry.register(MobZEntities.SKELI1.get(), skeli1renderer::new);
		registry.register(MobZEntities.SKELI2.get(), EasySkeletonRender::new);
		registry.register(MobZEntities.SKELI3.get(), EasySkeletonRender::new);
		registry.register(MobZEntities.BOWMAN.get(), EasyHumanoidRenderer::new);
		registry.register(MobZEntities.ARCHER.get(), EasyHumanoidRenderer::new);
		registry.register(MobZEntities.BIGBOSSENTITY.get(), BigBossRenderer::new);
		registry.register(MobZEntities.KNIGHTENTITY.get(), EasyHumanoidRenderer::new);
		registry.register(MobZEntities.KNIGHT2ENTITY.get(), EasyHumanoidRenderer::new);
		registry.register(MobZEntities.MAGEENTITY.get(), EasyEvokerRenderer::new);
		registry.register(MobZEntities.MAGE2ENTITY.get(), EasyEvokerRenderer::new);
		registry.register(MobZEntities.SMALLZOMBIE.get(), SmallZombieRenderer::new);
		registry.register(MobZEntities.FULLIRONENTITY.get(), EasyHumanoidRenderer::new);
		registry.register(MobZEntities.FROSTENTITY.get(), EasyBlazeRenderer::new);
        registry.register(MobZEntities.FROSTBALLENTITY.get(), (context) -> new ThrownItemRenderer<>(context, 0.75F, true));
		registry.register(MobZEntities.DOG.get(), DogRenderer::new);
		registry.register(MobZEntities.STONEGOLEM.get(), EasyGolemRenderer::new);
		registry.register(MobZEntities.ILLUSIONER.get(), EasyEvokerRenderer::new);
		registry.register(MobZEntities.DWARFENTITY.get(), DwarfEntityRenderer::new);
		registry.register(MobZEntities.SPISMALL.get(), SpiSmallRenderer::new);
		registry.register(MobZEntities.BLACKBEAR.get(), EasyPandaRenderer::new);
		registry.register(MobZEntities.BROWNBEAR.get(), EasyPolarBearRenderer::new);
		registry.register(MobZEntities.GCHICKEN.get(), EasyChickenRenderer::new);
		registry.register(MobZEntities.BOAR.get(), EasyPigRenderer::new);
		registry.register(MobZEntities.BOAR2.get(), EasyPigRenderer::new);
		registry.register(MobZEntities.BOAR3.get(), EasyPigRenderer::new);
		registry.register(MobZEntities.KATHERINE.get(), EasyHumanoidRenderer::new);
		registry.register(MobZEntities.KNIGHT3ENTITY.get(), EasyHumanoidRenderer::new);
		registry.register(MobZEntities.FIORA.get(), EasyHumanoidRenderer::new);
		registry.register(MobZEntities.KNIGHT5ENTITY.get(), EasyHumanoidRenderer::new);
		registry.register(MobZEntities.WITHENTITY.get(), EasyBlazeRenderer::new);
		registry.register(MobZEntities.SKELI4.get(), EasySkeletonRender::new);
		registry.register(MobZEntities.WITHENDER.get(), WithenderRenderer::new);
		registry.register(MobZEntities.SLIMO.get(), SlimoRenderer::new);
		registry.register(MobZEntities.TSPIDER.get(), TSpiderRenderer::new);
		registry.register(MobZEntities.PILLAGERBOSS.get(), PillagerBossRenderer::new);
		registry.register(MobZEntities.BABYRAVAGERENTITY.get(), BabyravagerRenderer::new);
		registry.register(MobZEntities.ISLANDKING.get(), EasyHumanoidRenderer::new);
		registry.register(MobZEntities.ISLANDKNIGHTNORMAL.get(), EasyHumanoidRenderer::new);
		registry.register(MobZEntities.ISLANDKNIGHTSPECIAL.get(), EasyHumanoidRenderer::new);
		registry.register(MobZEntities.ISLANDKNIGHTSPECIAL2.get(), EasyHumanoidRenderer::new);
		registry.register(MobZEntities.ISLANDVEXENTITY.get(), IslandVexEntityRenderer::new);
		registry.register(MobZEntities.METALGOLEM.get(), MetalGolemRenderer::new);
		registry.register(MobZEntities.SCREEPER.get(), EasyCreeperRenderer::new);

		registry.register(MobZEntities.TADPOLE.get(), TadpoleRender::new);
		registry.register(MobZEntities.TOAD.get(), ToadRender.Normal::new);
		registry.register(MobZEntities.TOAD_GIANT.get(), ToadRender.Giant::new);

		registry.register(MobZEntities.WASP.get(), WaspRender::new);
	}

	public static void registerLayerDefinitions(ILayerDefinitionRegistration registry) {
		registry.register(TadpoleEntityModel.modelResLoc, TadpoleEntityModel::createBodyLayer);
		registry.register(ToadEntityModel.modelResLoc, ToadEntityModel::createBodyLayer);
	}
}
