package net.mobz;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = MobZ.MODID)
@Config.Gui.Background("minecraft:textures/block/oak_planks.png")
public class Configs implements ConfigData {
	/**
	 * A mob that does not spawn naturally
	 */
	public static class MobSummonable {
		public boolean enabled;
		public double life;
		public double attack;

		public MobSummonable(boolean enabled, double life, double attack) {
			this.enabled = enabled;
			this.life = life;
			this.attack = attack;
		}
	}

	/**
	 * A mob that does not spawn naturally
	 */
	public static class MobSummonableAlwaysEnable {
		public double life;
		public double attack;

		public MobSummonableAlwaysEnable(double life, double attack) {
			this.life = life;
			this.attack = attack;
		}
	}

	/**
	 * A mob that does not spawn naturally that doesnt use melee attack, e.g. pig, creeper, and skeleton
	 */
	public static class MobSummonableNoAttack {
		public boolean enabled;
		public double life;

		public MobSummonableNoAttack(boolean enabled, double life) {
			this.enabled = enabled;
			this.life = life;
		}
	}

	/**
	 * A passive mob or a mob that doesnt use melee attack, e.g. pig, creeper, and skeleton
	 */
	public static class MobNoAttack {
		public boolean spawn;
	    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
		public int spawnRate;
		public double life;

		public MobNoAttack(boolean spawn, int spawnRate, double life) {
			this.spawn = spawn;
			this.spawnRate = spawnRate;
			this.life = life;
		}
	}

	public static class Mob {
		public boolean spawn;
	    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
		public int spawnRate;
		public double life;
		public double attack;

		public Mob(boolean spawn, int spawnRate, double life, double attack) {
			this.spawn = spawn;
			this.spawnRate = spawnRate;
			this.life = life;
			this.attack = attack;
		}
	}

	public static class Dwarf {
		public boolean spawn = true;
	    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
		public int spawnRate = 3;
		public double life = 70D;
		public double attack = 7D;
		public boolean undergroundOnly = false;
	}

	public static class PillagerBoss {
		public boolean enabled = true;
		public double life = 300D;
		public double attack = 12D;
	    public int attackCooldown = 60;
	    public int attackCooldownHard = 45;
	}

	public static class BabyRavager {
		public double life = 40D;
		public double attack = 10D;

	    // Not sure what is this
		public int spawnCount = 12;
	}

    @ConfigEntry.Gui.CollapsibleObject
    public Mob Alex = new Mob(true, 2, 20D, 5D);// Katherine
    @ConfigEntry.Gui.CollapsibleObject
    public Mob Archer = new Mob(true, 2, 32D, 6D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob ArmoredZombie = new Mob(true, 15, 20D, 7D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob BigBoss = new Mob(false, 1, 400D, 9D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob BlackBear = new Mob(true, 5, 20D, 6D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob BlueSpider = new Mob(true, 20, 24D, 6D);
    @ConfigEntry.Gui.CollapsibleObject
    public MobNoAttack Boar = new MobNoAttack(true, 5, 16D);
    @ConfigEntry.Gui.CollapsibleObject
    public MobNoAttack BossSkeleton = new MobNoAttack(true, 3, 60D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob BossZombie = new Mob(true, 1, 400D, 10D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob Bowman = new Mob(true, 5, 32D, 8D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob BrownBear = new Mob(true, 5, 30.0D, 6.0D);
    @ConfigEntry.Gui.CollapsibleObject
    public MobNoAttack CookieCreeper = new MobNoAttack(true, 10, 24D);
    @ConfigEntry.Gui.CollapsibleObject
    public MobNoAttack DirtyBoar = new MobNoAttack(true, 5, 10.0D);
    @ConfigEntry.Gui.CollapsibleObject
    public Dwarf Dwarf = new Dwarf();
    @ConfigEntry.Gui.CollapsibleObject
    public Mob EnderKnight = new Mob(true, 3, 48D, 8D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob Enderman = new Mob(true, 8, 46D, 9D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob Enderzombie = new Mob(true, 8, 20D, 5D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob Fiora = new Mob(true, 2, 30D, 5D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob FrostBlaze = new Mob(true, 10, 26D, 7D);
    @ConfigEntry.Gui.CollapsibleObject
    public MobNoAttack FrostCreeper = new MobNoAttack(true, 10, 18D);
    @ConfigEntry.Gui.CollapsibleObject
    public MobNoAttack GoldenChicken = new MobNoAttack(true, 3, 4D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob HoneySlime = new Mob(true, 3, 4D, 1D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob IceGolem = new Mob(true, 5, 52D, 14D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob Illusioner = new Mob(true, 2, 16D, 7D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob LavaGolem = new Mob(true, 10, 48D, 14D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob LordofDarkness = new Mob(true, 3, 72D, 7D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob LostSkeleton = new Mob(true, 10, 20D, 5D);
    @ConfigEntry.Gui.CollapsibleObject
    public MobSummonable MetalGolem = new MobSummonable(true, 160D, 16D);
    @ConfigEntry.Gui.CollapsibleObject
    public MobNoAttack NetherSkeleton = new MobNoAttack(true, 6, 28D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob NetherWolf = new Mob(true, 5, 20D, 6D);
    @ConfigEntry.Gui.CollapsibleObject
    public MobNoAttack OvergrownSkeleton = new MobNoAttack(true, 15, 26D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob Pigman = new Mob(true, 10, 24D, 7D);
    @ConfigEntry.Gui.CollapsibleObject
    public PillagerBoss PillagerBoss = new PillagerBoss();
    @ConfigEntry.Gui.CollapsibleObject
    public Mob PurpleSpider = new Mob(true, 15, 26D, 5D);
    @ConfigEntry.Gui.CollapsibleObject
    public MobNoAttack SoulCreeper = new MobNoAttack(true, 5, 25D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob SpeedyZombie = new Mob(true, 30, 15D, 4D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob SpiderMage = new Mob(true, 6, 32D, 7D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob Steve = new Mob(true, 2, 32D, 9D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob StoneGolem = new Mob(true, 2, 64D, 14D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob Tank = new Mob(true, 30, 56D, 9D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob Templar = new Mob(true, 2, 34D, 7D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob TinySpider = new Mob(true, 5, 1D, 0D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob Warrior = new Mob(true, 3, 48D, 6D);
    @ConfigEntry.Gui.CollapsibleObject
    public MobNoAttack WildBoar = new MobNoAttack(true, 5, 12.0D);
    @ConfigEntry.Gui.CollapsibleObject
    public MobSummonableNoAttack Withender = new MobSummonableNoAttack(true, 400D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob WitherBlaze = new Mob(true, 6, 26D, 8D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob ZombieMage = new Mob(true, 5, 28D, 7D);
    @ConfigEntry.Gui.CollapsibleObject
    public MobSummonableAlwaysEnable KingCharles = new MobSummonableAlwaysEnable(120D, 10D);
    @ConfigEntry.Gui.CollapsibleObject
    public MobSummonableAlwaysEnable William = new MobSummonableAlwaysEnable(60D, 8D);
    @ConfigEntry.Gui.CollapsibleObject
    public MobSummonableAlwaysEnable Andriu = new MobSummonableAlwaysEnable(60D, 8D);
    @ConfigEntry.Gui.CollapsibleObject
    public MobSummonableAlwaysEnable IslandKnight = new MobSummonableAlwaysEnable(26D, 6D);
    @ConfigEntry.Gui.CollapsibleObject
    public MobSummonableAlwaysEnable DeathSpirit = new MobSummonableAlwaysEnable(16D, 5D);
    @ConfigEntry.Gui.CollapsibleObject
    public BabyRavager BabyRavager = new BabyRavager();
    @ConfigEntry.Gui.CollapsibleObject
    public MobNoAttack Toad = new MobNoAttack(false, 8, 10.0D);

    @Comment("Be carefull with this setting! Multiplicator must be > 0!")
    public double LifeMultiplicatorMob = 1.0D;
    @Comment("Be carefull with this setting! Multiplicator must be > 0!")
    public double DamageMultiplicatorMob = 1.0D;
}
