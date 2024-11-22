package net.mobz.init;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;

import java.util.function.Supplier;

import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.mobz.MobZ;
import net.mobz.MobZTabs;
import net.mobz.entity.Archer;
import net.mobz.entity.Bowman;
import net.mobz.entity.ArmoredZombie;
import net.mobz.entity.BabyRavager;
import net.mobz.entity.BigBoss;
import net.mobz.entity.Blackbear;
import net.mobz.entity.WildBoar;
import net.mobz.entity.Boar;
import net.mobz.entity.DirtyBoar;
import net.mobz.entity.BossZombie;
import net.mobz.entity.Brownbear;
import net.mobz.entity.FrostCreeper;
import net.mobz.entity.CookieCreeper;
import net.mobz.entity.NetherWolf;
import net.mobz.entity.Dwarf;
import net.mobz.entity.Ender;
import net.mobz.entity.EnderZombie;
import net.mobz.entity.FastZombie;
import net.mobz.entity.FriendEntity;
import net.mobz.entity.FrostBlaze;
import net.mobz.entity.IronSteve;
import net.mobz.entity.GoldenChicken;
import net.mobz.entity.GiantToad;
import net.mobz.entity.IceGolem;
import net.mobz.entity.Illusioner;
import net.mobz.entity.Charles;
import net.mobz.entity.Knight;
import net.mobz.entity.William;
import net.mobz.entity.Andriu;
import net.mobz.entity.SpiritOfDeath;
import net.mobz.entity.Warrior;
import net.mobz.entity.EnderKnight;
import net.mobz.entity.LordOfDarkness;
import net.mobz.entity.Templar;
import net.mobz.entity.LavaGolem;
import net.mobz.entity.ZombieMage;
import net.mobz.entity.SpiderMage;
import net.mobz.entity.MetalGolem;
import net.mobz.entity.Pigman;
import net.mobz.entity.PillagerBoss;
import net.mobz.entity.SoulCreeper;
import net.mobz.entity.HoneySlime;
import net.mobz.entity.SmallZombie;
import net.mobz.entity.BlueSpider;
import net.mobz.entity.SmallSpider;
import net.mobz.entity.PurpleSpider;
import net.mobz.entity.StoneGolem;
import net.mobz.entity.TinySpider;
import net.mobz.entity.TadpoleEntity;
import net.mobz.entity.TankZombie;
import net.mobz.entity.ToadEntity;
import net.mobz.entity.Wasp;
import net.mobz.entity.WitherBlaze;
import net.mobz.entity.Withender;
import net.mobz.entity.BossSkeleton;
import net.mobz.entity.OvergrownSkeleton;
import net.mobz.entity.NetherSkeleton;
import net.mobz.entity.LostSkeleton;
import net.mobz.entity.attack.FrostballEntity;

public class MobZEntities {
	public static final Supplier<EntityType<TankZombie>> TANK_ZOMBIE = register(EntityType.Builder
			.of(TankZombie::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.95F), "tank_zombie", TankZombie::createMobzAttributes, 5055902, 2507798);

	public static final Supplier<EntityType<FastZombie>> FAST_ZOMBIE = register(EntityType.Builder
			.of(FastZombie::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.95F), "fast_zombie", FastZombie::createMobzAttributes, 6109639, 2968097);
	public static final Supplier<EntityType<ArmoredZombie>> ARMORED_ZOMBIE = register(EntityType.Builder
			.of(ArmoredZombie::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.95F), "armored_zombie", ArmoredZombie::createMobzAttributes, 1397590, 3165729);
	public static final Supplier<EntityType<BossZombie>> BOSS_ZOMBIE = register(EntityType.Builder
			.of(BossZombie::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2).fireImmune()
			.sized(0.6F, 1.95F), "boss_zombie", BossZombie::createMobzAttributes, 1181988, 3560490);
	public static final Supplier<EntityType<FrostCreeper>> FROST_CREEPER = register(EntityType.Builder
			.of(FrostCreeper::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.7F), "frost_creeper", FrostCreeper::createMobzAttributes, 4897722, 0);
	public static final Supplier<EntityType<CookieCreeper>> COOKIE_CREEPER = register(EntityType.Builder
			.of(CookieCreeper::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.7F), "cookie_creeper", CookieCreeper::createMobzAttributes,	10250793, 0);
	public static final Supplier<EntityType<Ender>> ENDER = register(EntityType.Builder
			.of(Ender::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 2.9F), "ender", Ender::createMobzAttributes, 7884109, 2167558);
	public static final Supplier<EntityType<EnderZombie>> ENDER_ZOMBIE = register(EntityType.Builder
			.of(EnderZombie::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.95F), "ender_zombie", EnderZombie::createMobzAttributes, 656405, 3876927);
	public static final Supplier<EntityType<BlueSpider>> BLUE_SPIDER = register(EntityType.Builder.of(BlueSpider::new, MobCategory.MONSTER)
			.clientTrackingRange(74).updateInterval(2).sized(1.4F, 0.9F), "blue_spider"
			, BlueSpider::createMobzAttributes, 3291714, 960680);
	public static final Supplier<EntityType<PurpleSpider>> PURPLE_SPIDER = register(EntityType.Builder.of(PurpleSpider::new, MobCategory.MONSTER)
			.clientTrackingRange(74).updateInterval(2).sized(1.4F, 0.9F), "purple_spider"
			, PurpleSpider::createMobzAttributes, 4864065, 10817192);
	public static final Supplier<EntityType<Pigman>> PIGMAN = register(EntityType.Builder
			.of(Pigman::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2).fireImmune()
			.sized(0.75F, 2.2F), "pigman", Pigman::createMobzAttributes, 7026980, 15245428);
	public static final Supplier<EntityType<LavaGolem>> LAVAGOLEM = register(EntityType.Builder
			.of(LavaGolem::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2).fireImmune()
			.sized(1.35F, 2.6F), "lava_golem" , LavaGolem::createMobzAttributes, 6098704, 3039578);
	public static final Supplier<EntityType<IceGolem>> ICEGOLEM = register(EntityType.Builder
			.of(IceGolem::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2).sized(1.54F, 3F)
			, "ice_golem", IceGolem::createMobzAttributes, 7499112, 1572516);
	public static final Supplier<EntityType<BossSkeleton>> BOSS_SKELETON = register(EntityType.Builder.of(BossSkeleton::new, MobCategory.MONSTER)
			.clientTrackingRange(74).updateInterval(2).fireImmune().sized(0.6F, 1.99F), "boss_skeleton",
			BossSkeleton::createMobzAttributes, 2697513, 4934989);
	public static final Supplier<EntityType<OvergrownSkeleton>> OVERGROWN_SKELETON = register(EntityType.Builder.of(OvergrownSkeleton::new, MobCategory.MONSTER)
			.clientTrackingRange(74).updateInterval(2).fireImmune().sized(0.6F, 1.99F), "overgrown_skeleton",
			OvergrownSkeleton::createMobzAttributes, 5263682, 11534);
	public static final Supplier<EntityType<NetherSkeleton>> NETHER_SKELETON = register(EntityType.Builder.of(NetherSkeleton::new, MobCategory.MONSTER)
			.clientTrackingRange(74).updateInterval(2).sized(0.6F, 1.99F), "nether_skeleton",
			NetherSkeleton::createMobzAttributes, 4801614, 5121582);
	public static final Supplier<EntityType<Bowman>> BOWMAN = register(EntityType.Builder
			.of(Bowman::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.99F), "bowman",
			Bowman::createMobzAttributes, 3218704, 11711154);
	public static final Supplier<EntityType<Archer>> ARCHER = register(EntityType.Builder
			.of(Archer::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.99F), "archer",
			Archer::createMobzAttributes, 7691600, 4269851);
	public static final Supplier<EntityType<BigBoss>> BIGBOSS = register(EntityType.Builder
			.of(BigBoss::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(1.5F, 4.875F), "bigboss",
			BigBoss::createMobzAttributes, 667182, 984607);
	public static final Supplier<EntityType<Templar>> TEMPLAR = register(EntityType.Builder
			.of(Templar::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.99F), "templar",
			Templar::createMobzAttributes, 5914402, 2499613);
	public static final Supplier<EntityType<Warrior>> WARRIOR = register(EntityType.Builder
			.of(Warrior::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.99F), "warrior",
			Warrior::createMobzAttributes, 4464921, 2173756);
	public static final Supplier<EntityType<SpiderMage>> SPIDER_MAGE = register(EntityType.Builder
			.of(SpiderMage::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.95F), "spider_mage",
			SpiderMage::createMobzAttributes, 5128776, 12342593);
	public static final Supplier<EntityType<ZombieMage>> ZOMBIE_MAGE = register(EntityType.Builder
			.of(ZombieMage::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.95F), "zombie_mage",
			ZombieMage::createMobzAttributes, 4211261, 2375449);
	public static final Supplier<EntityType<SmallZombie>> SMALL_ZOMBIE = register(EntityType.Builder
			.of(SmallZombie::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.3F, 1.0F), "small_zombie",
			SmallZombie::createMobzAttributes, 3222535, 1116191);
	public static final Supplier<EntityType<IronSteve>> IRON_STEVE = register(EntityType.Builder
			.of(IronSteve::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.99F), "iron_steve",
			IronSteve::createMobzEntityAttributes, 888205, 4800672);
	public static final Supplier<EntityType<FrostBlaze>> FROST_BLAZE = register(EntityType.Builder
			.of(FrostBlaze::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.8F), "frost_blaze",
			FrostBlaze::createMobzAttributes, 8709375, 86111);
	public static final Supplier<EntityType<FrostballEntity>> FROSTBALL = register(EntityType.Builder
            .<FrostballEntity>of(FrostballEntity::new, MobCategory.MISC).sized(0.3125F, 0.3125F)
            .clientTrackingRange(4).updateInterval(10), "frost_ball");
	public static final Supplier<EntityType<NetherWolf>> NETHER_WOLF = register(EntityType.Builder.of(NetherWolf::new, MobCategory.MONSTER)
			.clientTrackingRange(74).updateInterval(2).fireImmune().sized(0.6F, 0.85F), "nether_wolf",
			NetherWolf::createMobzAttributes, 4785691, 6700094);
	public static final Supplier<EntityType<StoneGolem>> STONEGOLEM = register(EntityType.Builder
			.of(StoneGolem::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(1.4F, 2.7F), "stone_golem",
			StoneGolem::createMobzAttributes, 10197915, 6654258);
	public static final Supplier<EntityType<Illusioner>> ILLUSIONER = register(EntityType.Builder
			.of(Illusioner::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.95F), "illusioner",
			Illusioner::createMobzAttributes, 2763306, 2507353);
	public static final Supplier<EntityType<Dwarf>> DWARF = register(EntityType.Builder
			.of(Dwarf::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.5F), "dwarf",
			Dwarf::createMobzAttributes, 5392946, 11882545);
	public static final Supplier<EntityType<SmallSpider>> SMALL_SPIDER = register(EntityType.Builder
			.of(SmallSpider::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2).sized(0.7F, 0.5F)
			, "small_spider", SmallSpider::createSpiSmallAttributes, 3806513, 146458);
	public static final Supplier<EntityType<Blackbear>> BLACKBEAR = register(EntityType.Builder
			.of(Blackbear::new, MobCategory.CREATURE).clientTrackingRange(74).updateInterval(2)
			.sized(1.25F, 1.3F), "black_bear",
			Blackbear::createMobzAttributes, 657934, 2960685);
	public static final Supplier<EntityType<Brownbear>> BROWNBEAR = register(EntityType.Builder
			.of(Brownbear::new, MobCategory.CREATURE).clientTrackingRange(74).updateInterval(2)
			.sized(1.3F, 1.4F), "brown_bear",
			Brownbear::createMobzAttributes, 2169097, 4403731);
	public static final Supplier<EntityType<GoldenChicken>> GOLDEN_CHICKEN = register(EntityType.Builder
			.of(GoldenChicken::new, MobCategory.CREATURE).clientTrackingRange(74).updateInterval(2)
			.eyeHeight(0.644F)
			.sized(0.4F, 0.7F), "golden_chicken",
			GoldenChicken::createMobzAttributes, 13027014, 15315221);
	public static final Supplier<EntityType<WildBoar>> WILD_BOAR = register(EntityType.Builder.of(WildBoar::new, MobCategory.CREATURE)
			.clientTrackingRange(74).updateInterval(2).sized(0.9F, 0.9F), "wild_boar",
			WildBoar::createMobzAttributes, 3211264, 9984303);
	public static final Supplier<EntityType<Boar>> BOAR = register(EntityType.Builder.of(Boar::new, MobCategory.CREATURE)
			.clientTrackingRange(74).updateInterval(2).sized(0.9F, 0.9F), "boar",
			Boar::createMobzAttributes, 14601929, 2962756);
	public static final Supplier<EntityType<DirtyBoar>> DIRTY_BOAR = register(EntityType.Builder.of(DirtyBoar::new, MobCategory.CREATURE)
			.clientTrackingRange(74).updateInterval(2).sized(0.9F, 0.9F), "dirty_boar",
			DirtyBoar::createMobzAttributes, 13284514, 2890508);
	public static final Supplier<EntityType<FriendEntity.KatherineEntity>> KATHERINE = register(EntityType.Builder
			.of(FriendEntity.KatherineEntity::new, MobCategory.CREATURE).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.99F), "katherine",
			FriendEntity.KatherineEntity::createMobzAttributes, 5132380, 7164237);
	public static final Supplier<EntityType<EnderKnight>> ENDER_KNIGHT = register(EntityType.Builder
			.of(EnderKnight::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.99F), "ender_knight",
			EnderKnight::createMobzAttributes, 1447190, 4917648);
	public static final Supplier<EntityType<FriendEntity.FioraEntity>> FIORA = register(EntityType.Builder
			.of(FriendEntity.FioraEntity::new, MobCategory.CREATURE).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.99F), "fiora",
			FriendEntity.FioraEntity::createMobzAttributes, 5308416, 16039829);
	public static final Supplier<EntityType<LordOfDarkness>> LORD_OF_DARKNESS = register(EntityType.Builder
			.of(LordOfDarkness::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.99F), "lord_of_darkness",
			LordOfDarkness::createMobzAttributes, 1118487, 5000017);
	public static final Supplier<EntityType<WitherBlaze>> WITHER_BLAZE = register(EntityType.Builder
			.of(WitherBlaze::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2).fireImmune()
			.sized(0.6F, 1.8F), "wither_blaze",
			WitherBlaze::createMobzAttributes, 1841947, 8157561);
	public static final Supplier<EntityType<LostSkeleton>> LOST_SKELETON = register(EntityType.Builder.of(LostSkeleton::new, MobCategory.MONSTER)
			.clientTrackingRange(74).updateInterval(2).sized(0.6F, 1.99F), "lost_skeleton",
			LostSkeleton::createMobzAttributes, 4079166, 11776947);
	public static final Supplier<EntityType<HoneySlime>> HONEY_SLIME = register(EntityType.Builder.of(HoneySlime::new, MobCategory.MONSTER)
			.clientTrackingRange(74).updateInterval(2).sized(0.51F, 0.51F), "honey_slime",
			HoneySlime::createMobzAttributes, 16752702, 16564078);
	public static final Supplier<EntityType<Withender>> WITHENDER = register(EntityType.Builder
			.of(Withender::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2).fireImmune()
			.sized(0.9F, 3.5F), "withender",
			Withender::createMobzAttributes, 1452605, 1982799);
	public static final Supplier<EntityType<TinySpider>> TINY_SPIDER = register(EntityType.Builder.of(TinySpider::new, MobCategory.AMBIENT)
			.clientTrackingRange(74).updateInterval(2).sized(0.2F, 0.1F), "tiny_spider",
			TinySpider::createMobzAttributes, 0, 0);
	public static final Supplier<EntityType<PillagerBoss>> PILLAGER_BOSS = register(EntityType.Builder
			.of(PillagerBoss::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.78F, 2.675F), "pillager_boss",
			PillagerBoss::createMobzAttributes, 4984603, 1453610);
	public static final Supplier<EntityType<BabyRavager>> BABY_RAVAGER = register(EntityType.Builder
			.of(BabyRavager::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.78F, 0.88F), "baby_ravager",
			BabyRavager::createMobzAttributes, 6315866, 4538432);
	public static final Supplier<EntityType<Charles>> CHARLES = register(EntityType.Builder
			.of(Charles::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.99F), "charles",
			Charles::createMobzAttributes, 8222839, 12891527);
	public static final Supplier<EntityType<Knight>> KNIGHT = register(EntityType.Builder
			.of(Knight::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.99F), "knight",
			Knight::createMobzAttributes, 3815735, 723723);
	public static final Supplier<EntityType<William>> WILLIAM = register(EntityType.Builder
			.of(William::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.99F), "william",
			William::createMobzAttributes, 2434859, 3481123);
	public static final Supplier<EntityType<Andriu>> ANDRIU = register(EntityType.Builder
			.of(Andriu::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.99F), "andriu",
			Andriu::createMobzAttributes, 3882305, 3161413);
	public static final Supplier<EntityType<SpiritOfDeath>> SPIRIT_OF_DEATH = register(EntityType.Builder
			.of(SpiritOfDeath::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.4F, 0.8F), "spirit_of_death",
			SpiritOfDeath::createMobzAttributes, 2039583, 9014412);
	public static final Supplier<EntityType<MetalGolem>> METALGOLEM = register(EntityType.Builder
			.of(MetalGolem::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(1.59F, 3F), "metal_golem",
			MetalGolem::createMobzAttributes, 2930848, 5197390);
	public static final Supplier<EntityType<SoulCreeper>> SOUL_CREEPER = register(EntityType.Builder
			.of(SoulCreeper::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.7F), "soul_creeper", SoulCreeper::createMobzAttributes, 4798252, 107176);


	public static final Supplier<EntityType<ToadEntity>> TOAD = register(EntityType.Builder
			.of(ToadEntity::new, MobCategory.CREATURE)
			.sized(0.8F, 0.6F).clientTrackingRange(12), "toad", ToadEntity::createEntityAttributes, 0x4b8252, 0x614d33);
	public static final Supplier<EntityType<TadpoleEntity>> TADPOLE = register(EntityType.Builder
			.of(TadpoleEntity::new, MobCategory.WATER_CREATURE)
			.sized(0.5F, 0.3F).clientTrackingRange(12), "tadpode", TadpoleEntity::createEntityAttributes, 0x67824b, 0x614d33);
	public static final Supplier<EntityType<GiantToad>> TOAD_GIANT = register(EntityType.Builder
			.of(GiantToad::new, MobCategory.CREATURE)
			.sized(3.2F, 2.4F).clientTrackingRange(12), "toad_giant", ToadEntity::createEntityAttributes, 0x4b8252, 0x614d33);

	public static final Supplier<EntityType<Wasp>> WASP = register(EntityType.Builder
			.of(Wasp::new, MobCategory.CREATURE)
			.sized(0.7F, 0.6F).clientTrackingRange(12), "wasp", Wasp::createAttributes, 0x4b8252, 0x614d33);

	private static <T extends Entity> Supplier<EntityType<T>> register(EntityType.Builder<T> entityTypeBuilder, String name) {
		ResourceKey<EntityType<?>> resKey = MobZ.resKey(Registries.ENTITY_TYPE, name);
		return MobZ.platform.registerEntityType(name, ()->entityTypeBuilder.build(resKey), null, null);
	}

	/**
	 * Register a Mob (also a LivingEntity) with its spawn egg
	 * @param <T> must be a super class of Mob
	 * @param name registration name, e.g. if set to "boss", name of the entity type will be "boss_entity",
	 * name of the spawn egg will be "spawn_boss".
	 * @param entityTypeBuilder
	 * @param attribModifierSupplier set to null to skip custom attrib modifier injection
	 * @param eggColor1
	 * @param eggColor2
	 */
	private static <T extends Mob> Supplier<EntityType<T>> register(EntityType.Builder<T> entityTypeBuilder, String name,
			Supplier<AttributeSupplier.Builder> attribModifierSupplier,
			int eggColor1, int eggColor2) {
		ResourceKey<EntityType<?>> resKey = MobZ.resKey(Registries.ENTITY_TYPE, name);
		String spawnEggLocalizationKey = Util.makeDescriptionId("entity", resKey.location());
		Supplier<EntityType<T>> entityTypeSupplier =
				MobZ.platform.registerEntityType(name, () -> entityTypeBuilder.build(resKey), attribModifierSupplier, null);

		MobZ.platform.registerItem("spawn_" + name, MobZTabs.EGGS,
				(props) -> MobZ.platform.spawnEggOf(entityTypeSupplier, eggColor1, eggColor2, props.overrideDescription(spawnEggLocalizationKey)).get(),
				null);

		return entityTypeSupplier;
	}
}
