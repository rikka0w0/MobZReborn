package net.mobz.client;

import net.minecraft.client.renderer.entity.ThrownItemRenderer;

import net.mobz.ILayerDefinitionRegistration;
import net.mobz.client.renderer.entity.*;
import net.mobz.client.renderer.model.TadpoleEntityModel;
import net.mobz.client.renderer.model.ToadEntityModel;
import net.mobz.init.MobZEntities;

public class EntityRenderers {
	public static void registerAll(IEntityRendererRegisterWrapper registry) {
		registry.register(MobZEntities.TANK_ZOMBIE.get(), EasyZombieRenderer::new);
		registry.register(MobZEntities.FAST_ZOMBIE.get(), EasyZombieRenderer::new);
		registry.register(MobZEntities.ARMORED_ZOMBIE.get(), EasyZombieRenderer::new);
		registry.register(MobZEntities.BOSS_ZOMBIE.get(), EasyZombieRenderer::new);
		registry.register(MobZEntities.FROST_CREEPER.get(), EasyCreeperRenderer::new);
		registry.register(MobZEntities.COOKIE_CREEPER.get(), EasyCreeperRenderer::new);
		registry.register(MobZEntities.ENDER.get(), EnderRenderer::new);
		registry.register(MobZEntities.ENDER_ZOMBIE.get(), EasyZombieRenderer::new);
		registry.register(MobZEntities.BLUE_SPIDER.get(), EasySpiderRenderer::new);
		registry.register(MobZEntities.PURPLE_SPIDER.get(), EasySpiderRenderer::new);
		registry.register(MobZEntities.PIGMAN.get(), PigmanRenderer::new);
		registry.register(MobZEntities.ICEGOLEM.get(), IceGolemRenderer::new);
		registry.register(MobZEntities.LAVAGOLEM.get(), EasyGolemRenderer::new);
		registry.register(MobZEntities.BOSS_SKELETON.get(), BossSkeletonRenderer::new);
		registry.register(MobZEntities.OVERGROWN_SKELETON.get(), EasySkeletonRender::new);
		registry.register(MobZEntities.NETHER_SKELETON.get(), EasySkeletonRender::new);
		registry.register(MobZEntities.BOWMAN.get(), EasyHumanoidRenderer::new);
		registry.register(MobZEntities.ARCHER.get(), EasyHumanoidRenderer::new);
		registry.register(MobZEntities.BIGBOSS.get(), BigBossRenderer::new);
		registry.register(MobZEntities.TEMPLAR.get(), EasyHumanoidRenderer::new);
		registry.register(MobZEntities.WARRIOR.get(), EasyHumanoidRenderer::new);
		registry.register(MobZEntities.SPIDER_MAGE.get(), EasyEvokerRenderer::new);
		registry.register(MobZEntities.ZOMBIE_MAGE.get(), EasyEvokerRenderer::new);
		registry.register(MobZEntities.SMALL_ZOMBIE.get(), SmallZombieRenderer::new);
		registry.register(MobZEntities.IRON_STEVE.get(), EasyHumanoidRenderer::new);
		registry.register(MobZEntities.FROST_BLAZE.get(), EasyBlazeRenderer::new);
        registry.register(MobZEntities.FROSTBALL.get(), (context) -> new ThrownItemRenderer<>(context, 0.75F, true));
		registry.register(MobZEntities.NETHER_WOLF.get(), NetherWolfRenderer::new);
		registry.register(MobZEntities.STONEGOLEM.get(), EasyGolemRenderer::new);
		registry.register(MobZEntities.ILLUSIONER.get(), EasyEvokerRenderer::new);
		registry.register(MobZEntities.DWARF.get(), DwarfEntityRenderer::new);
		registry.register(MobZEntities.SMALL_SPIDER.get(), SmallSpiderRenderer::new);
		registry.register(MobZEntities.BLACKBEAR.get(), EasyPandaRenderer::new);
		registry.register(MobZEntities.BROWNBEAR.get(), EasyPolarBearRenderer::new);
		registry.register(MobZEntities.GOLDEN_CHICKEN.get(), EasyChickenRenderer::new);
		registry.register(MobZEntities.WILD_BOAR.get(), EasyPigRenderer::new);
		registry.register(MobZEntities.BOAR.get(), EasyPigRenderer::new);
		registry.register(MobZEntities.DIRTY_BOAR.get(), EasyPigRenderer::new);
		registry.register(MobZEntities.KATHERINE.get(), EasyHumanoidRenderer::new);
		registry.register(MobZEntities.ENDER_KNIGHT.get(), EasyHumanoidRenderer::new);
		registry.register(MobZEntities.FIORA.get(), EasyHumanoidRenderer::new);
		registry.register(MobZEntities.LORD_OF_DARKNESS.get(), EasyHumanoidRenderer::new);
		registry.register(MobZEntities.WITHER_BLAZE.get(), EasyBlazeRenderer::new);
		registry.register(MobZEntities.LOST_SKELETON.get(), EasySkeletonRender::new);
		registry.register(MobZEntities.WITHENDER.get(), WithenderRenderer::new);
		registry.register(MobZEntities.HONEY_SLIME.get(), HoneySlimeRenderer::new);
		registry.register(MobZEntities.TINY_SPIDER.get(), TinySpiderRenderer::new);
		registry.register(MobZEntities.PILLAGER_BOSS.get(), PillagerBossRenderer::new);
		registry.register(MobZEntities.BABY_RAVAGER.get(), BabyRavagerRenderer::new);
		registry.register(MobZEntities.CHARLES.get(), EasyHumanoidRenderer::new);
		registry.register(MobZEntities.KNIGHT.get(), EasyHumanoidRenderer::new);
		registry.register(MobZEntities.WILLIAM.get(), EasyHumanoidRenderer::new);
		registry.register(MobZEntities.ANDRIU.get(), EasyHumanoidRenderer::new);
		registry.register(MobZEntities.SPIRIT_OF_DEATH.get(), SpiritOfDeathRenderer::new);
		registry.register(MobZEntities.METALGOLEM.get(), MetalGolemRenderer::new);
		registry.register(MobZEntities.SOUL_CREEPER.get(), EasyCreeperRenderer::new);

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
