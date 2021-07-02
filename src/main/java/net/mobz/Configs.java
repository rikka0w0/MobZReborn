package net.mobz;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;

@SuppressWarnings("unused")
@Config(name = "mobz")
@Config.Gui.Background("minecraft:textures/block/oak_planks.png")
public class Configs implements ConfigData {
	// To be assigned by either ForgeConfigManager or AutoConfig
	public static Configs instance = null;

    @ConfigEntry.Category("spawnsetting")
    @ConfigEntry.Gui.PrefixText
    public boolean AlexSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean ArcherSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean ArmoredZombieSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean BigBossSpawn = false;
    @ConfigEntry.Category("spawnsetting")
    public boolean BlackBearSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean BlueSpiderSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean BoarSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean BossSkeletonSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean BossZombieSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean BowmanSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean BrownBearSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean CookieCreeperSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean DirtyBoarSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean DwarfSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean DwarfSpawn_UndergroundOnly = false;
    @ConfigEntry.Category("spawnsetting")
    public boolean EnderKnightSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean EndermanSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean EnderzombieSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean FioraSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean FrostBlazeSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean FrostCreeperSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean GoldenChickenSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean GrassSlimeSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean IceGolemSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean IllusionerSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean LavaGolemSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean LordofDarknessSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean LostSkeletonSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean MetalGolemSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean NetherSkeletonSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean NetherWolfSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean OvergrownSkeletonSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean PigmanSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean PillagerBossSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean PurpleSpiderSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean SoulCreeperSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean SpeedyZombieSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean SpiderMageSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean SteveSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean StoneGolemSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean TankSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean TemplarSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean TinySpiderSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean WarriorSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean WildBoarSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean WithenderSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean WitherBlazeSpawn = true;
    @ConfigEntry.Category("spawnsetting")
    public boolean ZombieMageSpawn = true;

    @ConfigEntry.Category("lifesetting")
    @ConfigEntry.Gui.PrefixText
    public double AlexLife = 30D;
    @ConfigEntry.Category("lifesetting")
    public double ArcherLife = 32D;
    @ConfigEntry.Category("lifesetting")
    public double ArmoredZombieLife = 20D;
    @ConfigEntry.Category("lifesetting")
    public double BigBossLife = 400D;
    @ConfigEntry.Category("lifesetting")
    public double BlueSpiderLife = 24D;
    @ConfigEntry.Category("lifesetting")
    public double BossSkeletonLife = 60D;
    @ConfigEntry.Category("lifesetting")
    public double BossZombieLife = 400D;
    @ConfigEntry.Category("lifesetting")
    public double BowmanLife = 32D;
    @ConfigEntry.Category("lifesetting")
    public double CookieCreeperLife = 24D;
    @ConfigEntry.Category("lifesetting")
    public double DwarfLife = 70D;
    @ConfigEntry.Category("lifesetting")
    public double EnderKnightLife = 48D;
    @ConfigEntry.Category("lifesetting")
    public double EndermanLife = 46D;
    @ConfigEntry.Category("lifesetting")
    public double EnderzombieLife = 20D;
    @ConfigEntry.Category("lifesetting")
    public double FioraLife = 30D;
    @ConfigEntry.Category("lifesetting")
    public double FrostBlazeLife = 26D;
    @ConfigEntry.Category("lifesetting")
    public double FrostCreeperLife = 18D;
    @ConfigEntry.Category("lifesetting")
    public double IceGolemLife = 52D;
    @ConfigEntry.Category("lifesetting")
    public double IllusionerLife = 16D;
    @ConfigEntry.Category("lifesetting")
    public double LavaGolemLife = 48D;
    @ConfigEntry.Category("lifesetting")
    public double LordofDarknessLife = 72D;
    @ConfigEntry.Category("lifesetting")
    public double LostSkeletonLife = 20D;
    @ConfigEntry.Category("lifesetting")
    public double MetalGolemLife = 160D;
    @ConfigEntry.Category("lifesetting")
    public double NetherSkeletonLife = 28D;
    @ConfigEntry.Category("lifesetting")
    public double NetherWolfLife = 20D;
    @ConfigEntry.Category("lifesetting")
    public double OvergrownSkeletonLife = 26D;
    @ConfigEntry.Category("lifesetting")
    public double PigmanLife = 24D;
    @ConfigEntry.Category("lifesetting")
    public double PillagerBossLife = 300D;
    @ConfigEntry.Category("lifesetting")
    public double PurpleSpiderLife = 26D;
    @ConfigEntry.Category("lifesetting")
    public double SoulCreeperLife = 25D;
    @ConfigEntry.Category("lifesetting")
    public double SpeedyZombieLife = 15D;
    @ConfigEntry.Category("lifesetting")
    public double SpiderMageLife = 32D;
    @ConfigEntry.Category("lifesetting")
    public double SteveLife = 32D;
    @ConfigEntry.Category("lifesetting")
    public double StoneGolemLife = 64D;
    @ConfigEntry.Category("lifesetting")
    public double TankLife = 56D;
    @ConfigEntry.Category("lifesetting")
    public double TemplarLife = 34D;
    @ConfigEntry.Category("lifesetting")
    public double TinySpiderLife = 1D;
    @ConfigEntry.Category("lifesetting")
    public double WarriorLife = 48D;
    @ConfigEntry.Category("lifesetting")
    public double WithenderLife = 400D;
    @ConfigEntry.Category("lifesetting")
    public double WitherBlazeLife = 26D;
    @ConfigEntry.Category("lifesetting")
    public double ZombieMageLife = 28D;
    @ConfigEntry.Category("lifesetting")
    public double KingCharlesLife = 120D;
    @ConfigEntry.Category("lifesetting")
    public double WilliamLife = 60D;
    @ConfigEntry.Category("lifesetting")
    public double AndriuLife = 60D;
    @ConfigEntry.Category("lifesetting")
    public double IslandKnightLife = 26D;
    @ConfigEntry.Category("lifesetting")
    public double DeathSpiritLife = 16D;
    @ConfigEntry.Category("lifesetting")
    public double BabyRavagerLife = 40D;
    @ConfigEntry.Category("lifesetting")
    @Comment("Be carefull with this setting! Multiplicator must be > 0!")
    public double LifeMultiplicatorMob = 1.0D;

    @ConfigEntry.Category("damagesetting")
    @ConfigEntry.Gui.PrefixText
    public double AlexAttack = 5D;
    @ConfigEntry.Category("damagesetting")
    public double ArcherAttack = 6D;
    @ConfigEntry.Category("damagesetting")
    public double ArmoredZombieAttack = 7D;
    @ConfigEntry.Category("damagesetting")
    public double BigBossAttack = 9D;
    @ConfigEntry.Category("damagesetting")
    public double BlueSpiderAttack = 6D;
    @ConfigEntry.Category("damagesetting")
    public double BossZombieAttack = 10D;
    @ConfigEntry.Category("damagesetting")
    public double BowmanAttack = 8D;
    @ConfigEntry.Category("damagesetting")
    public double DwarfAttack = 7D;
    @ConfigEntry.Category("damagesetting")
    public double EnderKnightAttack = 8D;
    @ConfigEntry.Category("damagesetting")
    public double EndermanAttack = 9D;
    @ConfigEntry.Category("damagesetting")
    public double EnderzombieAttack = 5D;
    @ConfigEntry.Category("damagesetting")
    public double FioraAttack = 5D;
    @ConfigEntry.Category("damagesetting")
    public double FrostBlazeAttack = 7D;
    @ConfigEntry.Category("damagesetting")
    public double IceGolemAttack = 14D;
    @ConfigEntry.Category("damagesetting")
    public double IllusionerAttack = 7D;
    @ConfigEntry.Category("damagesetting")
    public double LavaGolemAttack = 14D;
    @ConfigEntry.Category("damagesetting")
    public double LordofDarknessAttack = 7D;
    @ConfigEntry.Category("damagesetting")
    public double LostSkeletonAttack = 5D;
    @ConfigEntry.Category("damagesetting")
    public double MetalGolemAttack = 16D;
    @ConfigEntry.Category("damagesetting")
    public double NetherWolfAttack = 6D;
    @ConfigEntry.Category("damagesetting")
    public double PigmanAttack = 7D;
    @ConfigEntry.Category("damagesetting")
    public double PillagerBossAttack = 12D;
    @ConfigEntry.Category("damagesetting")
    public double PurpleSpiderAttack = 5D;
    @ConfigEntry.Category("damagesetting")
    public double SpeedyZombieAttack = 4D;
    @ConfigEntry.Category("damagesetting")
    public double SpiderMageAttack = 7D;
    @ConfigEntry.Category("damagesetting")
    public double SteveAttack = 9D;
    @ConfigEntry.Category("damagesetting")
    public double StoneGolemAttack = 14D;
    @ConfigEntry.Category("damagesetting")
    public double TankAttack = 9D;
    @ConfigEntry.Category("damagesetting")
    public double TemplarAttack = 7D;
    @ConfigEntry.Category("damagesetting")
    public double TinySpiderAttack = 0D;
    @ConfigEntry.Category("damagesetting")
    public double WarriorAttack = 6D;
    @ConfigEntry.Category("damagesetting")
    public double WitherBlazeAttack = 8D;
    @ConfigEntry.Category("damagesetting")
    public double ZombieMageAttack = 7D;
    @ConfigEntry.Category("damagesetting")
    public double KingCharlesAttack = 10D;
    @ConfigEntry.Category("damagesetting")
    public double WilliamAttack = 8D;
    @ConfigEntry.Category("damagesetting")
    public double AndriuAttack = 8D;
    @ConfigEntry.Category("damagesetting")
    public double IslandKnightAttack = 6D;
    @ConfigEntry.Category("damagesetting")
    public double DeathSpiritAttack = 5D;
    @ConfigEntry.Category("damagesetting")
    public double BabyRavagerAttack = 10D;
    @ConfigEntry.Category("damagesetting")
    @Comment("Be carefull with this setting! Multiplicator must be > 0!")
    public double DamageMultiplicatorMob = 1.0D;

    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int AlexSpawnRate = 2;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int ArcherSpawnRate = 2;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int ArmoredZombieSpawnRate = 15;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int BigBossSpawnRate = 1;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int BlackBearSpawnRate = 5;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int BlueSpiderSpawnRate = 20;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int BoarSpawnRate = 5;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int BossSkeletonSpawnRate = 3;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int BossZombieSpawnRate = 1;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int BowmanSpawnRate = 5;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int BrownBearSpawnRate = 5;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int CookieCreeperSpawnRate = 10;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int DirtyBoarSpawnRate = 5;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int DwarfSpawnRate = 3;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int EnderKnightSpawnRate = 3;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int EndermanSpawnRate = 8;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int EnderzombieSpawnRate = 8;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int FioraSpawnRate = 2;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int FrostBlazeSpawnRate = 10;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int FrostCreeperSpawnRate = 10;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int GoldenChickenSpawnRate = 3;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int GrassSlimeSpawnRate = 3;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int IceGolemSpawnRate = 5;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int IllusionerSpawnRate = 2;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int LavaGolemSpawnRate = 10;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int LordofDarknessSpawnRate = 3;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int LostSkeletonSpawnRate = 10;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int NetherSkeletonSpawnRate = 6;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int NetherWolfSpawnRate = 5;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int OvergrownSkeletonSpawnRate = 15;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int PigmanSpawnRate = 10;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int PurpleSpiderSpawnRate = 15;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int SoulCreeperSpawnRate = 5;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int SpeedyZombieSpawnRate = 30;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int SpiderMageSpawnRate = 6;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int SteveSpawnRate = 2;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int StoneGolemSpawnRate = 2;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int TankSpawnRate = 30;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int TemplarSpawnRate = 2;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int TinySpiderSpawnRate = 5;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int WarriorSpawnRate = 3;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int WildBoarSpawnRate = 5;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int WitherBlazeSpawnRate = 6;
    @ConfigEntry.Category("spawnratesetting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int ZombieMageSpawnRate = 5;

    @ConfigEntry.Category("miscsetting")
    @ConfigEntry.Gui.PrefixText
    public int SpawnCountBabyRavagerBlock = 12;

}
