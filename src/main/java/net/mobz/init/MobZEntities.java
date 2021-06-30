package net.mobz.init;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.mobz.IRegistryWrapper;
import net.mobz.MobZ;
import net.mobz.entity.*;

public class MobZEntities {
    public static final EntityType<BossEntity> BOSS = 
    		EntityType.Builder.of(BossEntity::new, EntityClassification.MONSTER)
			.setTrackingRange(74).setUpdateInterval(2)
			.fireImmune()
			.sized(0.6F, 1.95F)
			.build("boss_entity");
    
    public static final EntityType<TankEntity> TANK = 
    		EntityType.Builder.of(TankEntity::new, EntityClassification.MONSTER)
    		.setTrackingRange(74).setUpdateInterval(2)
            .sized(0.6F, 1.95F)
            .build("");
    
public static final EntityType<FastEntity> FAST = 
            EntityType.Builder.of(FastEntity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
            .sized(0.6F, 1.95F)
            .build("");
public static final EntityType<TestEntity> TEST = 
            EntityType.Builder.of(TestEntity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2).fireImmune()
            .sized(0.6F, 1.95F)
            .build("");
public static final EntityType<ArmoredEntity> ARMORED = 
            EntityType.Builder.of(ArmoredEntity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
            .sized(0.6F, 1.95F).build("");

public static final EntityType<CreepEntity> CREEP = 
            EntityType.Builder.of(CreepEntity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
            .sized(0.6F, 1.7F).build("");
public static final EntityType<CripEntity> CRIP = 
            EntityType.Builder.of(CripEntity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
            .sized(0.6F, 1.7F).build("");
public static final EntityType<EnderEntity> ENDER = 
            EntityType.Builder.of(EnderEntity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
            .sized(0.6F, 2.9F).build("");
public static final EntityType<EnderZombieEntity> ENDERZOMBIE = 
            EntityType.Builder.of(EnderZombieEntity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
            .sized(0.6F, 1.95F).build("");
public static final EntityType<SpiEntity> SPI = 
            EntityType.Builder.of(SpiEntity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
            .sized(1.4F, 0.9F).build("");
public static final EntityType<SpoEntity> SPO = 
            EntityType.Builder.of(SpoEntity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
            .sized(1.4F, 0.9F).build("");
public static final EntityType<PigmanEntity> PIG = 
            EntityType.Builder.of(PigmanEntity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2).fireImmune()
            .sized(0.5F, 0.9F).build("");
public static final EntityType<LavaGolem> LAVAGOLEM = 
            EntityType.Builder.of(LavaGolem::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2).fireImmune()
            .sized(1.35F, 2.6F).build("");
public static final EntityType<IceGolem> ICEGOLEM = 
            EntityType.Builder.of(IceGolem::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
            .sized(1.54F, 3F).build("");
public static final EntityType<skeli1> SKELI1 = EntityType.Builder.of(skeli1::new, EntityClassification.MONSTER)
            .setTrackingRange(74).setUpdateInterval(2).fireImmune().sized(0.6F, 1.99F).build("");
public static final EntityType<skeli2> SKELI2 = EntityType.Builder.of(skeli2::new, EntityClassification.MONSTER)
            .setTrackingRange(74).setUpdateInterval(2).fireImmune().sized(0.6F, 1.99F).build("");
public static final EntityType<skeli3> SKELI3 = EntityType.Builder.of(skeli3::new, EntityClassification.MONSTER)
            .setTrackingRange(74).setUpdateInterval(2).sized(0.6F, 1.99F).build("");
public static final EntityType<ArcherEntity> ARCHERENTITY = 
            EntityType.Builder.of(ArcherEntity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
            .sized(0.6F, 1.99F).build("");
public static final EntityType<Archer2Entity> ARCHER2ENTITY = 
            EntityType.Builder.of(Archer2Entity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
            .sized(0.6F, 1.99F).build("");
public static final EntityType<BigBossEntity> BIGBOSSENTITY = 
            EntityType.Builder.of(BigBossEntity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
            .sized(1.5F, 4.875F).build("");
public static final EntityType<KnightEntity> KNIGHTENTITY = 
            EntityType.Builder.of(KnightEntity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
            .sized(0.6F, 1.99F).build("");
public static final EntityType<Knight2Entity> KNIGHT2ENTITY = 
            EntityType.Builder.of(Knight2Entity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
            .sized(0.6F, 1.99F).build("");
public static final EntityType<MageEntity> MAGEENTITY = 
            EntityType.Builder.of(MageEntity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
            .sized(0.6F, 1.95F).build("");
public static final EntityType<Mage2Entity> MAGE2ENTITY = 
            EntityType.Builder.of(Mage2Entity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
            .sized(0.6F, 1.95F).build("");
public static final EntityType<SmallZombie> SMALLZOMBIE = 
            EntityType.Builder.of(SmallZombie::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
            .sized(0.3F, 1.0F).build("");
public static final EntityType<FullIronEntity> FULLIRONENTITY = 
            EntityType.Builder.of(FullIronEntity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
            .sized(0.6F, 1.99F).build("");
public static final EntityType<FrostEntity> FROSTENTITY = 
            EntityType.Builder.of(FrostEntity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
            .sized(0.6F, 1.8F).build("");
public static final EntityType<Dog> DOG = EntityType.Builder.of(Dog::new, EntityClassification.MONSTER)
            .setTrackingRange(74).setUpdateInterval(2).fireImmune().sized(0.6F, 0.85F).build("");
public static final EntityType<StoneGolem> STONEGOLEM = 
            EntityType.Builder.of(StoneGolem::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
            .sized(1.4F, 2.7F).build("");
public static final EntityType<Illusioner> ILLUSIONER = 
            EntityType.Builder.of(Illusioner::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
            .sized(0.6F, 1.95F).build("");
public static final EntityType<DwarfEntity> DWARFENTITY = 
            EntityType.Builder.of(DwarfEntity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
            .sized(0.6F, 1.5F).build("");
public static final EntityType<SpiSmall> SPISMALL = 
            EntityType.Builder.of(SpiSmall::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
            .sized(0.7F, 0.5F).build("");
public static final EntityType<Blackbear> BLACKBEAR = 
            EntityType.Builder.of(Blackbear::new, EntityClassification.CREATURE).setTrackingRange(74).setUpdateInterval(2)
            .sized(1.25F, 1.3F).build("");
public static final EntityType<Brownbear> BROWNBEAR = 
            EntityType.Builder.of(Brownbear::new, EntityClassification.CREATURE).setTrackingRange(74).setUpdateInterval(2)
            .sized(1.3F, 1.4F).build("");
public static final EntityType<GChicken> GCHICKEN = 
            EntityType.Builder.of(GChicken::new, EntityClassification.CREATURE).setTrackingRange(74).setUpdateInterval(2)
            .sized(0.4F, 0.7F).build("");
public static final EntityType<Boar> BOAR = EntityType.Builder.of(Boar::new, EntityClassification.CREATURE)
            .setTrackingRange(74).setUpdateInterval(2).sized(0.9F, 0.9F).build("");
public static final EntityType<Boar2> BOAR2 = EntityType.Builder.of(Boar2::new, EntityClassification.CREATURE)
            .setTrackingRange(74).setUpdateInterval(2).sized(0.9F, 0.9F).build("");
public static final EntityType<Boar3> BOAR3 = EntityType.Builder.of(Boar3::new, EntityClassification.CREATURE)
            .setTrackingRange(74).setUpdateInterval(2).sized(0.9F, 0.9F).build("");
public static final EntityType<FriendEntity> FRIEND = 
            EntityType.Builder.of(FriendEntity::new, EntityClassification.CREATURE).setTrackingRange(74).setUpdateInterval(2)
            .sized(0.6F, 1.99F).build("");
public static final EntityType<Knight3Entity> KNIGHT3ENTITY = 
            EntityType.Builder.of(Knight3Entity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
            .sized(0.6F, 1.99F).build("");
public static final EntityType<Knight4Entity> KNIGHT4ENTITY = 
            EntityType.Builder.of(Knight4Entity::new, EntityClassification.CREATURE).setTrackingRange(74).setUpdateInterval(2)
            .sized(0.6F, 1.99F).build("");
public static final EntityType<Knight5Entity> KNIGHT5ENTITY = 
            EntityType.Builder.of(Knight5Entity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
            .sized(0.6F, 1.99F).build("");
public static final EntityType<WithEntity> WITHENTITY = 
            EntityType.Builder.of(WithEntity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2).fireImmune()
            .sized(0.6F, 1.8F).build("");
public static final EntityType<skeli4> SKELI4 = EntityType.Builder.of(skeli4::new, EntityClassification.MONSTER)
            .setTrackingRange(74).setUpdateInterval(2).sized(0.6F, 1.99F).build("");
public static final EntityType<Withender> WITHENDER = 
            EntityType.Builder.of(Withender::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2).fireImmune()
            .sized(0.6F, 1.95F).build("");
public static final EntityType<Slimo> SLIMO = EntityType.Builder.of(Slimo::new, EntityClassification.MONSTER)
            .setTrackingRange(74).setUpdateInterval(2).sized(0.51F, 0.51F).build("");
public static final EntityType<TSpider> TSPIDER = 
            EntityType.Builder.of(TSpider::new, EntityClassification.AMBIENT).setTrackingRange(74).setUpdateInterval(2)
            .sized(0.2F, 0.1F).build("");
public static final EntityType<PillagerBoss> PILLAGERBOSS = 
            EntityType.Builder.of(PillagerBoss::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
            .sized(0.78F, 2.675F).build("");
public static final EntityType<BabyravagerEntity> BABYRAVAGERENTITY = 
            EntityType.Builder.of(BabyravagerEntity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
            .sized(0.78F, 0.88F).build("");
public static final EntityType<IslandKing> ISLANDKING = 
            EntityType.Builder.of(IslandKing::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
            .sized(0.6F, 1.99F).build("");
public static final EntityType<IslandKnightNormal> ISLANDKNIGHTNORMAL = 
            EntityType.Builder.of(IslandKnightNormal::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
            .sized(0.6F, 1.99F).build("");
public static final EntityType<IslandKnightSpecial> ISLANDKNIGHTSPECIAL = 
            EntityType.Builder.of(IslandKnightSpecial::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
            .sized(0.6F, 1.99F).build("");
public static final EntityType<IslandKnightSpecial2> ISLANDKNIGHTSPECIAL2 = 
            EntityType.Builder.of(IslandKnightSpecial2::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
            .sized(0.6F, 1.99F).build("");
public static final EntityType<IslandVexEntity> ISLANDVEXENTITY = 
            EntityType.Builder.of(IslandVexEntity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
            .sized(0.4F, 0.8F).build("");
public static final EntityType<MetalGolem> METALGOLEM = 
            EntityType.Builder.of(MetalGolem::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
            .sized(1.59F, 3F).build("");
public static final EntityType<SCreeperEntity> SCREEPER = 
            EntityType.Builder.of(SCreeperEntity::new, EntityClassification.MONSTER).setTrackingRange(74).setUpdateInterval(2)
            .sized(0.6F, 1.7F).build("");

    public static void registerAll(IRegistryWrapper registry) {
    	registry.register("boss", BOSS, BossEntity::createBossEntityAttributes,
    			1181988, 3560490, MobZ.eggs);
    }
}
