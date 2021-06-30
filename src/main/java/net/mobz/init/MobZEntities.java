package net.mobz.init;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.mobz.IRegistryWrapper;
import net.mobz.MobZ;
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
import net.mobz.entity.IceGolem;
import net.mobz.entity.Illusioner;
import net.mobz.entity.IslandKing;
import net.mobz.entity.IslandKnightNormal;
import net.mobz.entity.IslandKnightSpecial;
import net.mobz.entity.IslandKnightSpecial2;
import net.mobz.entity.IslandVexEntity;
import net.mobz.entity.Knight2Entity;
import net.mobz.entity.Knight3Entity;
import net.mobz.entity.Knight4Entity;
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
import net.mobz.entity.TankEntity;
import net.mobz.entity.TestEntity;
import net.mobz.entity.WithEntity;
import net.mobz.entity.Withender;
import net.mobz.entity.skeli1;
import net.mobz.entity.skeli2;
import net.mobz.entity.skeli3;
import net.mobz.entity.skeli4;

public class MobZEntities {
	public static final EntityType<BossEntity> BOSS = EntityType.Builder
			.of(BossEntity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2).fireImmune()
			.sized(0.6F, 1.95F).build("boss");

	public static final EntityType<TankEntity> TANK = EntityType.Builder
			.of(TankEntity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
			.sized(0.6F, 1.95F).build("tank");

	public static final EntityType<FastEntity> FAST = EntityType.Builder
			.of(FastEntity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
			.sized(0.6F, 1.95F).build("fast");
	public static final EntityType<TestEntity> TEST = EntityType.Builder
			.of(TestEntity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2).fireImmune()
			.sized(0.6F, 1.95F).build("test");
	public static final EntityType<ArmoredEntity> ARMORED = EntityType.Builder
			.of(ArmoredEntity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
			.sized(0.6F, 1.95F).build("armored");

	public static final EntityType<CreepEntity> CREEP = EntityType.Builder
			.of(CreepEntity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
			.sized(0.6F, 1.7F).build("creep");
	public static final EntityType<CripEntity> CRIP = EntityType.Builder
			.of(CripEntity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
			.sized(0.6F, 1.7F).build("crip");
	public static final EntityType<EnderEntity> ENDER = EntityType.Builder
			.of(EnderEntity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
			.sized(0.6F, 2.9F).build("ender");
	public static final EntityType<EnderZombieEntity> ENDERZOMBIE = EntityType.Builder
			.of(EnderZombieEntity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
			.sized(0.6F, 1.95F).build("enderzombie");
	public static final EntityType<SpiEntity> SPI = EntityType.Builder.of(SpiEntity::new, EntityClassification.MONSTER)
			.setTrackingRange(74).setUpdateInterval(2).sized(1.4F, 0.9F).build("spi");
	public static final EntityType<SpoEntity> SPO = EntityType.Builder.of(SpoEntity::new, EntityClassification.MONSTER)
			.setTrackingRange(74).setUpdateInterval(2).sized(1.4F, 0.9F).build("spo");
	public static final EntityType<PigmanEntity> PIG = EntityType.Builder
			.of(PigmanEntity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2).fireImmune()
			.sized(0.5F, 0.9F).build("pigman");
	public static final EntityType<LavaGolem> LAVAGOLEM = EntityType.Builder
			.of(LavaGolem::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2).fireImmune()
			.sized(1.35F, 2.6F).build("lavagolem");
	public static final EntityType<IceGolem> ICEGOLEM = EntityType.Builder
			.of(IceGolem::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2).sized(1.54F, 3F)
			.build("icegolem");
	public static final EntityType<skeli1> SKELI1 = EntityType.Builder.of(skeli1::new, EntityClassification.MONSTER)
			.setTrackingRange(74).setUpdateInterval(2).fireImmune().sized(0.6F, 1.99F).build("skeli1");
	public static final EntityType<skeli2> SKELI2 = EntityType.Builder.of(skeli2::new, EntityClassification.MONSTER)
			.setTrackingRange(74).setUpdateInterval(2).fireImmune().sized(0.6F, 1.99F).build("skeli2");
	public static final EntityType<skeli3> SKELI3 = EntityType.Builder.of(skeli3::new, EntityClassification.MONSTER)
			.setTrackingRange(74).setUpdateInterval(2).sized(0.6F, 1.99F).build("skeli3");
	public static final EntityType<ArcherEntity> ARCHERENTITY = EntityType.Builder
			.of(ArcherEntity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
			.sized(0.6F, 1.99F).build("archer");
	public static final EntityType<Archer2Entity> ARCHER2ENTITY = EntityType.Builder
			.of(Archer2Entity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
			.sized(0.6F, 1.99F).build("archer2");
	public static final EntityType<BigBossEntity> BIGBOSSENTITY = EntityType.Builder
			.of(BigBossEntity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
			.sized(1.5F, 4.875F).build("");
	public static final EntityType<KnightEntity> KNIGHTENTITY = EntityType.Builder
			.of(KnightEntity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
			.sized(0.6F, 1.99F).build("knight");
	public static final EntityType<Knight2Entity> KNIGHT2ENTITY = EntityType.Builder
			.of(Knight2Entity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
			.sized(0.6F, 1.99F).build("knight2");
	public static final EntityType<MageEntity> MAGEENTITY = EntityType.Builder
			.of(MageEntity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
			.sized(0.6F, 1.95F).build("mage");
	public static final EntityType<Mage2Entity> MAGE2ENTITY = EntityType.Builder
			.of(Mage2Entity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
			.sized(0.6F, 1.95F).build("mage2");
	public static final EntityType<SmallZombie> SMALLZOMBIE = EntityType.Builder
			.of(SmallZombie::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
			.sized(0.3F, 1.0F).build("smallzombie");
	public static final EntityType<FullIronEntity> FULLIRONENTITY = EntityType.Builder
			.of(FullIronEntity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
			.sized(0.6F, 1.99F).build("fulliron");
	public static final EntityType<FrostEntity> FROSTENTITY = EntityType.Builder
			.of(FrostEntity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
			.sized(0.6F, 1.8F).build("frost");
	public static final EntityType<Dog> DOG = EntityType.Builder.of(Dog::new, EntityClassification.MONSTER)
			.setTrackingRange(74).setUpdateInterval(2).fireImmune().sized(0.6F, 0.85F).build("dog");
	public static final EntityType<StoneGolem> STONEGOLEM = EntityType.Builder
			.of(StoneGolem::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
			.sized(1.4F, 2.7F).build("stonegolem");
	public static final EntityType<Illusioner> ILLUSIONER = EntityType.Builder
			.of(Illusioner::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
			.sized(0.6F, 1.95F).build("illusioner");
	public static final EntityType<DwarfEntity> DWARFENTITY = EntityType.Builder
			.of(DwarfEntity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
			.sized(0.6F, 1.5F).build("dwarfentity");
	public static final EntityType<SpiSmall> SPISMALL = EntityType.Builder
			.of(SpiSmall::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2).sized(0.7F, 0.5F)
			.build("spismall");
	public static final EntityType<Blackbear> BLACKBEAR = EntityType.Builder
			.of(Blackbear::new, EntityClassification.CREATURE).setTrackingRange(74).setUpdateInterval(2)
			.sized(1.25F, 1.3F).build("blackbear");
	public static final EntityType<Brownbear> BROWNBEAR = EntityType.Builder
			.of(Brownbear::new, EntityClassification.CREATURE).setTrackingRange(74).setUpdateInterval(2)
			.sized(1.3F, 1.4F).build("brownbear");
	public static final EntityType<GChicken> GCHICKEN = EntityType.Builder
			.of(GChicken::new, EntityClassification.CREATURE).setTrackingRange(74).setUpdateInterval(2)
			.sized(0.4F, 0.7F).build("gchicken");
	public static final EntityType<Boar> BOAR = EntityType.Builder.of(Boar::new, EntityClassification.CREATURE)
			.setTrackingRange(74).setUpdateInterval(2).sized(0.9F, 0.9F).build("boar");
	public static final EntityType<Boar2> BOAR2 = EntityType.Builder.of(Boar2::new, EntityClassification.CREATURE)
			.setTrackingRange(74).setUpdateInterval(2).sized(0.9F, 0.9F).build("boar2");
	public static final EntityType<Boar3> BOAR3 = EntityType.Builder.of(Boar3::new, EntityClassification.CREATURE)
			.setTrackingRange(74).setUpdateInterval(2).sized(0.9F, 0.9F).build("boar3");
	public static final EntityType<FriendEntity> FRIEND = EntityType.Builder
			.of(FriendEntity::new, EntityClassification.CREATURE).setTrackingRange(74).setUpdateInterval(2)
			.sized(0.6F, 1.99F).build("friend");
	public static final EntityType<Knight3Entity> KNIGHT3ENTITY = EntityType.Builder
			.of(Knight3Entity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
			.sized(0.6F, 1.99F).build("knight3");
	public static final EntityType<Knight4Entity> KNIGHT4ENTITY = EntityType.Builder
			.of(Knight4Entity::new, EntityClassification.CREATURE).setTrackingRange(74).setUpdateInterval(2)
			.sized(0.6F, 1.99F).build("knight4");
	public static final EntityType<Knight5Entity> KNIGHT5ENTITY = EntityType.Builder
			.of(Knight5Entity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
			.sized(0.6F, 1.99F).build("knight5");
	public static final EntityType<WithEntity> WITHENTITY = EntityType.Builder
			.of(WithEntity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2).fireImmune()
			.sized(0.6F, 1.8F).build("with");
	public static final EntityType<skeli4> SKELI4 = EntityType.Builder.of(skeli4::new, EntityClassification.MONSTER)
			.setTrackingRange(74).setUpdateInterval(2).sized(0.6F, 1.99F).build("skeli4");
	public static final EntityType<Withender> WITHENDER = EntityType.Builder
			.of(Withender::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2).fireImmune()
			.sized(0.6F, 1.95F).build("withender");
	public static final EntityType<Slimo> SLIMO = EntityType.Builder.of(Slimo::new, EntityClassification.MONSTER)
			.setTrackingRange(74).setUpdateInterval(2).sized(0.51F, 0.51F).build("slimo");
	public static final EntityType<TSpider> TSPIDER = EntityType.Builder.of(TSpider::new, EntityClassification.AMBIENT)
			.setTrackingRange(74).setUpdateInterval(2).sized(0.2F, 0.1F).build("tspider");
	public static final EntityType<PillagerBoss> PILLAGERBOSS = EntityType.Builder
			.of(PillagerBoss::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
			.sized(0.78F, 2.675F).build("pillagerboss");
	public static final EntityType<BabyravagerEntity> BABYRAVAGERENTITY = EntityType.Builder
			.of(BabyravagerEntity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
			.sized(0.78F, 0.88F).build("babyravager");
	public static final EntityType<IslandKing> ISLANDKING = EntityType.Builder
			.of(IslandKing::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
			.sized(0.6F, 1.99F).build("islandking");
	public static final EntityType<IslandKnightNormal> ISLANDKNIGHTNORMAL = EntityType.Builder
			.of(IslandKnightNormal::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
			.sized(0.6F, 1.99F).build("islandkingnormal");
	public static final EntityType<IslandKnightSpecial> ISLANDKNIGHTSPECIAL = EntityType.Builder
			.of(IslandKnightSpecial::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
			.sized(0.6F, 1.99F).build("islandkingspecial");
	public static final EntityType<IslandKnightSpecial2> ISLANDKNIGHTSPECIAL2 = EntityType.Builder
			.of(IslandKnightSpecial2::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
			.sized(0.6F, 1.99F).build("islandkingspecial2");
	public static final EntityType<IslandVexEntity> ISLANDVEXENTITY = EntityType.Builder
			.of(IslandVexEntity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
			.sized(0.4F, 0.8F).build("islandvex");
	public static final EntityType<MetalGolem> METALGOLEM = EntityType.Builder
			.of(MetalGolem::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
			.sized(1.59F, 3F).build("metalgolem");
	public static final EntityType<SCreeperEntity> SCREEPER = EntityType.Builder
			.of(SCreeperEntity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
			.sized(0.6F, 1.7F).build("screeper");

	public static void registerAll(IRegistryWrapper registry) {
		registry.register("tank", TANK, TankEntity::createTankEntityAttributes,	5055902, 2507798, MobZ.eggs);
        registry.register("fast", FAST, FastEntity::createFastEntityAttributes, 6109639, 2968097, MobZ.eggs);
        registry.register("test", TEST, TestEntity::createTestEntityAttributes, 2039583, 9109643, MobZ.eggs);
        registry.register("armored", ARMORED, ArmoredEntity::createArmoredEntityAttributes, 1397590, 3165729, MobZ.eggs);
        registry.register("boss", BOSS, BossEntity::createBossEntityAttributes, 1181988, 3560490, MobZ.eggs);
        registry.register("creep", CREEP, CreepEntity::createCreepEntityAttributes,	4897722, 0, MobZ.eggs);
        registry.register("crip", CRIP, CripEntity::createCripEntityAttributes,	10250793, 0, MobZ.eggs);
        registry.register("ender", ENDER, EnderEntity::createEnderEntityAttributes,	7884109, 2167558, MobZ.eggs);
        registry.register("enderzombie", ENDERZOMBIE, EnderZombieEntity::createEnderZombieEntityAttributes,	656405, 3876927, MobZ.eggs);
        registry.register("spi", SPI, SpiEntity::createSpiEntityAttributes, 3291714, 960680, MobZ.eggs);
        registry.register("spo", SPO, SpoEntity::createSpoEntityAttributes, 4864065, 10817192, MobZ.eggs);
        registry.register("pigman", PIG, PigmanEntity::createPigmanEntityAttributes, 7026980, 15245428, MobZ.eggs);
        registry.register("lavagolem", LAVAGOLEM, LavaGolem::createLavaGolemAttributes, 6098704, 3039578, MobZ.eggs);
        registry.register("icegolem", ICEGOLEM, IceGolem::createIceGolemAttributes, 7499112, 1572516, MobZ.eggs);
        registry.register("skeli1", SKELI1, skeli1::createskeli1Attributes, 2697513, 4934989, MobZ.eggs);
        registry.register("skeli2", SKELI2, skeli2::createskeli2Attributes, 5263682, 11534, MobZ.eggs);
        registry.register("skeli3", SKELI3, skeli3::createskeli3Attributes, 4801614, 5121582, MobZ.eggs);
        registry.register("archer", ARCHERENTITY, ArcherEntity::createArcherEntityAttributes, 3218704, 11711154, MobZ.eggs);
        registry.register("archer2", ARCHER2ENTITY, Archer2Entity::createArcher2EntityAttributes, 7691600, 4269851, MobZ.eggs);
        registry.register("bigboss", BIGBOSSENTITY, BigBossEntity::createBigBossEntityAttributes, 667182, 984607, MobZ.eggs);
        registry.register("knight", KNIGHTENTITY, KnightEntity::createKnightEntityAttributes, 5914402, 2499613, MobZ.eggs);
        registry.register("knight2", KNIGHT2ENTITY, Knight2Entity::createKnight2EntityAttributes, 4464921, 2173756, MobZ.eggs);
        registry.register("mage", MAGEENTITY, MageEntity::createMageEntityAttributes, 5128776, 12342593, MobZ.eggs);
        registry.register("mage2", MAGE2ENTITY, Mage2Entity::createMage2EntityAttributes, 4211261, 2375449, MobZ.eggs);
        registry.register("smallzombie", SMALLZOMBIE, SmallZombie::createSmallZombieAttributes, 3222535, 1116191, MobZ.eggs);
        registry.register("fulliron", FULLIRONENTITY, FullIronEntity::createFullIronEntityAttributes, 888205, 4800672, MobZ.eggs);
        registry.register("frost", FROSTENTITY, FrostEntity::createFrostEntityAttributes, 8709375, 86111, MobZ.eggs);
        registry.register("dog", DOG, Dog::createDogAttributes, 4785691, 6700094, MobZ.eggs);
        registry.register("stonegolem", STONEGOLEM, StoneGolem::createStoneGolemAttributes, 10197915, 6654258, MobZ.eggs);
        registry.register("illusioner", ILLUSIONER, Illusioner::createAttributes, 2763306, 2507353, MobZ.eggs);
        registry.register("dwarf", DWARFENTITY, DwarfEntity::createDwarfEntityAttributes, 5392946, 11882545, MobZ.eggs);
        registry.register("spismall", SPISMALL, SpiSmall::createSpiSmallAttributes, 3806513, 146458, MobZ.eggs);
        registry.register("blackbear", BLACKBEAR, Blackbear::createBlackbearAttributes, 657934, 2960685, MobZ.eggs);
        registry.register("brownbear", BROWNBEAR, Brownbear::createBrownbearAttributes, 2169097, 4403731, MobZ.eggs);
        registry.register("gchicken", GCHICKEN, GChicken::createGChickenAttributes, 13027014, 15315221, MobZ.eggs);
        registry.register("boar", BOAR, Boar::createBoarAttributes, 3211264, 9984303, MobZ.eggs);
        registry.register("boar2", BOAR2, Boar2::createBoar2Attributes, 14601929, 2962756, MobZ.eggs);
        registry.register("boar3", BOAR3, Boar3::createBoar3Attributes, 13284514, 2890508, MobZ.eggs);
        registry.register("friend", FRIEND, FriendEntity::createFriendEntityAttributes, 5132380, 7164237, MobZ.eggs);
        registry.register("knight3", KNIGHT3ENTITY, Knight3Entity::createKnight3EntityAttributes, 1447190, 4917648, MobZ.eggs);
        registry.register("knight4", KNIGHT4ENTITY, Knight4Entity::createKnight4EntityAttributes, 5308416, 16039829, MobZ.eggs);
        registry.register("knight5", KNIGHT5ENTITY, Knight5Entity::createKnight5EntityAttributes, 1118487, 5000017, MobZ.eggs);
        registry.register("with", WITHENTITY, WithEntity::createWithEntityAttributes, 1841947, 8157561, MobZ.eggs);
        registry.register("skeli4", SKELI4, skeli4::createskeli4Attributes, 4079166, 11776947, MobZ.eggs);
        registry.register("slimo", SLIMO, Slimo::createSlimoAttributes, 16752702, 16564078, MobZ.eggs);
        registry.register("withender", WITHENDER, Withender::createWithenderAttributes, 1452605, 1982799, MobZ.eggs);
        registry.register("tspider", TSPIDER, TSpider::createTSpiderAttributes, 0, 0, MobZ.eggs);
        registry.register("pillagerboss", PILLAGERBOSS, PillagerBoss::createPillagerBossAttributes, 4984603, 1453610, MobZ.eggs);
        registry.register("babyravager", BABYRAVAGERENTITY, BabyravagerEntity::createBabyravagerEntityAttributes, 6315866, 4538432, MobZ.eggs);
        registry.register("islandking", ISLANDKING, IslandKing::createIslandKingAttributes, 8222839, 12891527, MobZ.eggs);
        registry.register("islandknightnormal", ISLANDKNIGHTNORMAL, IslandKnightNormal::createIslandKnightNormalAttributes, 3815735, 723723 , MobZ.eggs);
        registry.register("islandknightspecial", ISLANDKNIGHTSPECIAL, IslandKnightSpecial::createIslandKnightSpecialAttributes, 2434859, 3481123, MobZ.eggs);
        registry.register("islandknightspecial2", ISLANDKNIGHTSPECIAL2, IslandKnightSpecial2::createIslandKnightSpecial2Attributes, 3882305, 3161413, MobZ.eggs);
        registry.register("islandvex", ISLANDVEXENTITY, IslandVexEntity::createIslandVexEntityAttributes, 2039583, 9014412, MobZ.eggs);
        registry.register("metalgolem", METALGOLEM, MetalGolem::createMetalGolemAttributes, 2930848, 5197390, MobZ.eggs);
        registry.register("screeper", SCREEPER, SCreeperEntity::createSCreeperEntityAttributes, 4798252, 107176, MobZ.eggs);
	}
}
