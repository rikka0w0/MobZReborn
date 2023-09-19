package net.mobz.init;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

import java.util.function.Supplier;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.mobz.MobZ;
import net.mobz.MobZTabs;
import net.mobz.entity.Archer2Entity;
import net.mobz.entity.ArcherEntity;
import net.mobz.entity.ArmoredEntity;
import net.mobz.entity.BabyravagerEntity;
import net.mobz.entity.BigBossEntity;
import net.mobz.entity.Blackbear;
import net.mobz.entity.Boar;
import net.mobz.entity.Boar2;
import net.mobz.entity.Boar3;
import net.mobz.entity.BossEntity;
import net.mobz.entity.Brownbear;
import net.mobz.entity.CreepEntity;
import net.mobz.entity.CripEntity;
import net.mobz.entity.Dog;
import net.mobz.entity.DwarfEntity;
import net.mobz.entity.EnderEntity;
import net.mobz.entity.EnderZombieEntity;
import net.mobz.entity.FastEntity;
import net.mobz.entity.FriendEntity;
import net.mobz.entity.FrostEntity;
import net.mobz.entity.FullIronEntity;
import net.mobz.entity.GChicken;
import net.mobz.entity.GiantToad;
import net.mobz.entity.IceGolem;
import net.mobz.entity.Illusioner;
import net.mobz.entity.IslandKing;
import net.mobz.entity.IslandKnightNormal;
import net.mobz.entity.IslandKnightSpecial;
import net.mobz.entity.IslandKnightSpecial2;
import net.mobz.entity.IslandVexEntity;
import net.mobz.entity.Knight2Entity;
import net.mobz.entity.Knight3Entity;
import net.mobz.entity.Knight5Entity;
import net.mobz.entity.KnightEntity;
import net.mobz.entity.LavaGolem;
import net.mobz.entity.Mage2Entity;
import net.mobz.entity.MageEntity;
import net.mobz.entity.MetalGolem;
import net.mobz.entity.PigmanEntity;
import net.mobz.entity.PillagerBoss;
import net.mobz.entity.SCreeperEntity;
import net.mobz.entity.Slimo;
import net.mobz.entity.SmallZombie;
import net.mobz.entity.SpiEntity;
import net.mobz.entity.SpiSmall;
import net.mobz.entity.SpoEntity;
import net.mobz.entity.StoneGolem;
import net.mobz.entity.TSpider;
import net.mobz.entity.TadpoleEntity;
import net.mobz.entity.TankEntity;
import net.mobz.entity.ToadEntity;
import net.mobz.entity.Wasp;
import net.mobz.entity.WithEntity;
import net.mobz.entity.Withender;
import net.mobz.entity.skeli1;
import net.mobz.entity.skeli2;
import net.mobz.entity.skeli3;
import net.mobz.entity.skeli4;
import net.mobz.entity.attack.FrostballEntity;

public class MobZEntities {
	public static final Supplier<EntityType<TankEntity>> TANK = register(EntityType.Builder
			.of(TankEntity::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.95F), "tank", TankEntity::createTankEntityAttributes, 5055902, 2507798);

	public static final Supplier<EntityType<FastEntity>> FAST = register(EntityType.Builder
			.of(FastEntity::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.95F), "fast", FastEntity::createFastEntityAttributes, 6109639, 2968097);
	public static final Supplier<EntityType<ArmoredEntity>> ARMORED = register(EntityType.Builder
			.of(ArmoredEntity::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.95F), "armored", ArmoredEntity::createArmoredEntityAttributes, 1397590, 3165729);
	public static final Supplier<EntityType<BossEntity>> BOSS = register(EntityType.Builder
			.of(BossEntity::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2).fireImmune()
			.sized(0.6F, 1.95F), "boss", BossEntity::createBossEntityAttributes, 1181988, 3560490);
	public static final Supplier<EntityType<CreepEntity>> CREEP = register(EntityType.Builder
			.of(CreepEntity::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.7F), "creep", CreepEntity::createCreepEntityAttributes, 4897722, 0);
	public static final Supplier<EntityType<CripEntity>> CRIP = register(EntityType.Builder
			.of(CripEntity::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.7F), "crip", CripEntity::createCripEntityAttributes,	10250793, 0);
	public static final Supplier<EntityType<EnderEntity>> ENDER = register(EntityType.Builder
			.of(EnderEntity::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 2.9F), "ender", EnderEntity::createEnderEntityAttributes, 7884109, 2167558);
	public static final Supplier<EntityType<EnderZombieEntity>> ENDERZOMBIE = register(EntityType.Builder
			.of(EnderZombieEntity::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.95F), "enderzombie", EnderZombieEntity::createEnderZombieEntityAttributes, 656405, 3876927);
	public static final Supplier<EntityType<SpiEntity>> SPI = register(EntityType.Builder.of(SpiEntity::new, MobCategory.MONSTER)
			.clientTrackingRange(74).updateInterval(2).sized(1.4F, 0.9F), "spi"
			, SpiEntity::createSpiEntityAttributes, 3291714, 960680);
	public static final Supplier<EntityType<SpoEntity>> SPO = register(EntityType.Builder.of(SpoEntity::new, MobCategory.MONSTER)
			.clientTrackingRange(74).updateInterval(2).sized(1.4F, 0.9F), "spo"
			, SpoEntity::createSpoEntityAttributes, 4864065, 10817192);
	public static final Supplier<EntityType<PigmanEntity>> PIG = register(EntityType.Builder
			.of(PigmanEntity::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2).fireImmune()
			.sized(0.75F, 2.2F), "pigman", PigmanEntity::createPigmanEntityAttributes, 7026980, 15245428);
	public static final Supplier<EntityType<LavaGolem>> LAVAGOLEM = register(EntityType.Builder
			.of(LavaGolem::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2).fireImmune()
			.sized(1.35F, 2.6F), "lavagolem" , LavaGolem::createLavaGolemAttributes, 6098704, 3039578);
	public static final Supplier<EntityType<IceGolem>> ICEGOLEM = register(EntityType.Builder
			.of(IceGolem::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2).sized(1.54F, 3F)
			, "icegolem", IceGolem::createIceGolemAttributes, 7499112, 1572516);
	public static final Supplier<EntityType<skeli1>> SKELI1 = register(EntityType.Builder.of(skeli1::new, MobCategory.MONSTER)
			.clientTrackingRange(74).updateInterval(2).fireImmune().sized(0.6F, 1.99F), "skeli1",
			skeli1::createskeli1Attributes, 2697513, 4934989);
	public static final Supplier<EntityType<skeli2>> SKELI2 = register(EntityType.Builder.of(skeli2::new, MobCategory.MONSTER)
			.clientTrackingRange(74).updateInterval(2).fireImmune().sized(0.6F, 1.99F), "skeli2",
			skeli2::createskeli2Attributes, 5263682, 11534);
	public static final Supplier<EntityType<skeli3>> SKELI3 = register(EntityType.Builder.of(skeli3::new, MobCategory.MONSTER)
			.clientTrackingRange(74).updateInterval(2).sized(0.6F, 1.99F), "skeli3",
			skeli3::createskeli3Attributes, 4801614, 5121582);
	public static final Supplier<EntityType<ArcherEntity>> ARCHERENTITY = register(EntityType.Builder
			.of(ArcherEntity::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.99F), "archer",
			ArcherEntity::createArcherEntityAttributes, 3218704, 11711154);
	public static final Supplier<EntityType<Archer2Entity>> ARCHER2ENTITY = register(EntityType.Builder
			.of(Archer2Entity::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.99F), "archer2",
			Archer2Entity::createArcher2EntityAttributes, 7691600, 4269851);
	public static final Supplier<EntityType<BigBossEntity>> BIGBOSSENTITY = register(EntityType.Builder
			.of(BigBossEntity::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(1.5F, 4.875F), "bigboss",
			BigBossEntity::createBigBossEntityAttributes, 667182, 984607);
	public static final Supplier<EntityType<KnightEntity>> KNIGHTENTITY = register(EntityType.Builder
			.of(KnightEntity::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.99F), "knight",
			KnightEntity::createKnightEntityAttributes, 5914402, 2499613);
	public static final Supplier<EntityType<Knight2Entity>> KNIGHT2ENTITY = register(EntityType.Builder
			.of(Knight2Entity::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.99F), "knight2",
			Knight2Entity::createKnight2EntityAttributes, 4464921, 2173756);
	public static final Supplier<EntityType<MageEntity>> MAGEENTITY = register(EntityType.Builder
			.of(MageEntity::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.95F), "mage",
			MageEntity::createMageEntityAttributes, 5128776, 12342593);
	public static final Supplier<EntityType<Mage2Entity>> MAGE2ENTITY = register(EntityType.Builder
			.of(Mage2Entity::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.95F), "mage2",
			Mage2Entity::createMage2EntityAttributes, 4211261, 2375449);
	public static final Supplier<EntityType<SmallZombie>> SMALLZOMBIE = register(EntityType.Builder
			.of(SmallZombie::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.3F, 1.0F), "smallzombie",
			SmallZombie::createSmallZombieAttributes, 3222535, 1116191);
	public static final Supplier<EntityType<FullIronEntity>> FULLIRONENTITY = register(EntityType.Builder
			.of(FullIronEntity::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.99F), "fulliron",
			FullIronEntity::createFullIronEntityAttributes, 888205, 4800672);
	public static final Supplier<EntityType<FrostEntity>> FROSTENTITY = register(EntityType.Builder
			.of(FrostEntity::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.8F), "frost",
			FrostEntity::createFrostEntityAttributes, 8709375, 86111);
	public static final Supplier<EntityType<FrostballEntity>> FROSTBALLENTITY = register(EntityType.Builder
            .<FrostballEntity>of(FrostballEntity::new, MobCategory.MISC).sized(0.3125F, 0.3125F)
            .clientTrackingRange(4).updateInterval(10), "frostball");
	public static final Supplier<EntityType<Dog>> DOG = register(EntityType.Builder.of(Dog::new, MobCategory.MONSTER)
			.clientTrackingRange(74).updateInterval(2).fireImmune().sized(0.6F, 0.85F), "dog",
			Dog::createDogAttributes, 4785691, 6700094);
	public static final Supplier<EntityType<StoneGolem>> STONEGOLEM = register(EntityType.Builder
			.of(StoneGolem::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(1.4F, 2.7F), "stonegolem",
			StoneGolem::createStoneGolemAttributes, 10197915, 6654258);
	public static final Supplier<EntityType<Illusioner>> ILLUSIONER = register(EntityType.Builder
			.of(Illusioner::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.95F), "illusioner",
			Illusioner::createAttributes, 2763306, 2507353);
	public static final Supplier<EntityType<DwarfEntity>> DWARFENTITY = register(EntityType.Builder
			.of(DwarfEntity::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.5F), "dwarf",
			DwarfEntity::createDwarfEntityAttributes, 5392946, 11882545);
	public static final Supplier<EntityType<SpiSmall>> SPISMALL = register(EntityType.Builder
			.of(SpiSmall::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2).sized(0.7F, 0.5F)
			, "spismall", SpiSmall::createSpiSmallAttributes, 3806513, 146458);
	public static final Supplier<EntityType<Blackbear>> BLACKBEAR = register(EntityType.Builder
			.of(Blackbear::new, MobCategory.CREATURE).clientTrackingRange(74).updateInterval(2)
			.sized(1.25F, 1.3F), "blackbear",
			Blackbear::createBlackbearAttributes, 657934, 2960685);
	public static final Supplier<EntityType<Brownbear>> BROWNBEAR = register(EntityType.Builder
			.of(Brownbear::new, MobCategory.CREATURE).clientTrackingRange(74).updateInterval(2)
			.sized(1.3F, 1.4F), "brownbear",
			Brownbear::createBrownbearAttributes, 2169097, 4403731);
	public static final Supplier<EntityType<GChicken>> GCHICKEN = register(EntityType.Builder
			.of(GChicken::new, MobCategory.CREATURE).clientTrackingRange(74).updateInterval(2)
			.sized(0.4F, 0.7F), "gchicken",
			GChicken::createGChickenAttributes, 13027014, 15315221);
	public static final Supplier<EntityType<Boar>> BOAR = register(EntityType.Builder.of(Boar::new, MobCategory.CREATURE)
			.clientTrackingRange(74).updateInterval(2).sized(0.9F, 0.9F), "boar",
			Boar::createBoarAttributes, 3211264, 9984303);
	public static final Supplier<EntityType<Boar2>> BOAR2 = register(EntityType.Builder.of(Boar2::new, MobCategory.CREATURE)
			.clientTrackingRange(74).updateInterval(2).sized(0.9F, 0.9F), "boar2",
			Boar2::createBoar2Attributes, 14601929, 2962756);
	public static final Supplier<EntityType<Boar3>> BOAR3 = register(EntityType.Builder.of(Boar3::new, MobCategory.CREATURE)
			.clientTrackingRange(74).updateInterval(2).sized(0.9F, 0.9F), "boar3",
			Boar3::createBoar3Attributes, 13284514, 2890508);
	public static final Supplier<EntityType<FriendEntity.KatherineEntity>> KATHERINE = register(EntityType.Builder
			.of(FriendEntity.KatherineEntity::new, MobCategory.CREATURE).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.99F), "katherine",
			FriendEntity.KatherineEntity::createFriendEntityAttributes, 5132380, 7164237);
	public static final Supplier<EntityType<Knight3Entity>> KNIGHT3ENTITY = register(EntityType.Builder
			.of(Knight3Entity::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.99F), "knight3",
			Knight3Entity::createKnight3EntityAttributes, 1447190, 4917648);
	public static final Supplier<EntityType<FriendEntity.FioraEntity>> FIORA = register(EntityType.Builder
			.of(FriendEntity.FioraEntity::new, MobCategory.CREATURE).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.99F), "fiora",
			FriendEntity.FioraEntity::createKnight4EntityAttributes, 5308416, 16039829);
	public static final Supplier<EntityType<Knight5Entity>> KNIGHT5ENTITY = register(EntityType.Builder
			.of(Knight5Entity::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.99F), "knight5",
			Knight5Entity::createKnight5EntityAttributes, 1118487, 5000017);
	public static final Supplier<EntityType<WithEntity>> WITHENTITY = register(EntityType.Builder
			.of(WithEntity::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2).fireImmune()
			.sized(0.6F, 1.8F), "with",
			WithEntity::createWithEntityAttributes, 1841947, 8157561);
	public static final Supplier<EntityType<skeli4>> SKELI4 = register(EntityType.Builder.of(skeli4::new, MobCategory.MONSTER)
			.clientTrackingRange(74).updateInterval(2).sized(0.6F, 1.99F), "skeli4",
			skeli4::createskeli4Attributes, 4079166, 11776947);
	public static final Supplier<EntityType<Slimo>> SLIMO = register(EntityType.Builder.of(Slimo::new, MobCategory.MONSTER)
			.clientTrackingRange(74).updateInterval(2).sized(0.51F, 0.51F), "slimo",
			Slimo::createSlimoAttributes, 16752702, 16564078);
	public static final Supplier<EntityType<Withender>> WITHENDER = register(EntityType.Builder
			.of(Withender::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2).fireImmune()
			.sized(0.9F, 3.5F), "withender",
			Withender::createWithenderAttributes, 1452605, 1982799);
	public static final Supplier<EntityType<TSpider>> TSPIDER = register(EntityType.Builder.of(TSpider::new, MobCategory.AMBIENT)
			.clientTrackingRange(74).updateInterval(2).sized(0.2F, 0.1F), "tspider",
			TSpider::createTSpiderAttributes, 0, 0);
	public static final Supplier<EntityType<PillagerBoss>> PILLAGERBOSS = register(EntityType.Builder
			.of(PillagerBoss::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.78F, 2.675F), "pillagerboss",
			PillagerBoss::createPillagerBossAttributes, 4984603, 1453610);
	public static final Supplier<EntityType<BabyravagerEntity>> BABYRAVAGERENTITY = register(EntityType.Builder
			.of(BabyravagerEntity::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.78F, 0.88F), "babyravager",
			BabyravagerEntity::createBabyravagerEntityAttributes, 6315866, 4538432);
	public static final Supplier<EntityType<IslandKing>> ISLANDKING = register(EntityType.Builder
			.of(IslandKing::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.99F), "islandking",
			IslandKing::createIslandKingAttributes, 8222839, 12891527);
	public static final Supplier<EntityType<IslandKnightNormal>> ISLANDKNIGHTNORMAL = register(EntityType.Builder
			.of(IslandKnightNormal::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.99F), "islandknightnormal",
			IslandKnightNormal::createIslandKnightNormalAttributes, 3815735, 723723);
	public static final Supplier<EntityType<IslandKnightSpecial>> ISLANDKNIGHTSPECIAL = register(EntityType.Builder
			.of(IslandKnightSpecial::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.99F), "islandknightspecial",
			IslandKnightSpecial::createIslandKnightSpecialAttributes, 2434859, 3481123);
	public static final Supplier<EntityType<IslandKnightSpecial2>> ISLANDKNIGHTSPECIAL2 = register(EntityType.Builder
			.of(IslandKnightSpecial2::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.99F), "islandknightspecial2",
			IslandKnightSpecial2::createIslandKnightSpecial2Attributes, 3882305, 3161413);
	public static final Supplier<EntityType<IslandVexEntity>> ISLANDVEXENTITY = register(EntityType.Builder
			.of(IslandVexEntity::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.4F, 0.8F), "islandvex",
			IslandVexEntity::createIslandVexEntityAttributes, 2039583, 9014412);
	public static final Supplier<EntityType<MetalGolem>> METALGOLEM = register(EntityType.Builder
			.of(MetalGolem::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(1.59F, 3F), "metalgolem",
			MetalGolem::createMetalGolemAttributes, 2930848, 5197390);
	public static final Supplier<EntityType<SCreeperEntity>> SCREEPER = register(EntityType.Builder
			.of(SCreeperEntity::new, MobCategory.MONSTER).clientTrackingRange(74).updateInterval(2)
			.sized(0.6F, 1.7F), "screeper", SCreeperEntity::createSCreeperEntityAttributes, 4798252, 107176);


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
		return MobZ.platform.registerEntityType(name, ()->entityTypeBuilder.build(name), null, null);
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
		Supplier<EntityType<T>> entityTypeSupplier = MobZ.platform.registerEntityType(name, ()->entityTypeBuilder.build(name), attribModifierSupplier, null);
		Supplier<SpawnEggItem> spawnEgg = MobZ.platform.spawnEggOf(entityTypeSupplier, eggColor1, eggColor2, new Item.Properties());
		MobZ.platform.registerItem("spawn_" + name, MobZTabs.eggs, spawnEgg, null);

		return entityTypeSupplier;
	}
}
