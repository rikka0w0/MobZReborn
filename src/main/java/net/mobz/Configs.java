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
		public int spawn_rate;
		public double life;

		public MobNoAttack(boolean spawn, int spawn_rate, double life) {
			this.spawn = spawn;
			this.spawn_rate = spawn_rate;
			this.life = life;
		}
	}

	public static class Mob {
		public boolean spawn;
	    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
		public int spawn_rate;
		public double life;
		public double attack;

		public Mob(boolean spawn, int spawn_rate, double life, double attack) {
			this.spawn = spawn;
			this.spawn_rate = spawn_rate;
			this.life = life;
			this.attack = attack;
		}
	}

	public static class Dwarf {
		public boolean spawn = true;
	    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
		public int spawn_rate = 3;
		public double life = 70D;
		public double attack = 7D;
		public boolean underground_only = false;
	}

	public static class Charles {
		public double life = 120D;
		public double attack = 10D;
		@ConfigEntry.BoundedDiscrete(min = 0, max = 200)
		public int slowdown_attack_cooldown = 180;
		@ConfigEntry.BoundedDiscrete(min = 0, max = 200)
		public int vex_summon_cooldown = 200;
	}

	public static class PillagerBoss {
		public boolean enabled = true;
		public double life = 300D;
		public double attack = 12D;

		@ConfigEntry.Gui.PrefixText
	    public int wither_attack_cooldown = 60;
	    public int wither_attack_cooldown_hard = 45;
	}

	public static class BabyRavager {
		public double life = 40D;
		public double attack = 10D;

	    // Not sure what is this
		public int spawn_count = 12;
	}

    @ConfigEntry.Gui.CollapsibleObject
    public Mob katherine = new Mob(true, 2, 20D, 5D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob archer = new Mob(true, 2, 32D, 6D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob armored_zombie = new Mob(true, 15, 20D, 7D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob bigboss = new Mob(false, 1, 400D, 9D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob black_bear = new Mob(true, 5, 20D, 6D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob blue_spider = new Mob(true, 20, 24D, 6D);
    @ConfigEntry.Gui.CollapsibleObject
    public MobNoAttack boar = new MobNoAttack(true, 5, 16D);
    @ConfigEntry.Gui.CollapsibleObject
    public MobNoAttack boss_skeleton = new MobNoAttack(true, 3, 60D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob boss_zombie = new Mob(true, 1, 400D, 10D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob bowman = new Mob(true, 5, 32D, 8D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob brown_bear = new Mob(true, 5, 30.0D, 6.0D);
    @ConfigEntry.Gui.CollapsibleObject
    public MobNoAttack cookie_creeper = new MobNoAttack(true, 10, 24D);
    @ConfigEntry.Gui.CollapsibleObject
    public MobNoAttack dirty_boar = new MobNoAttack(true, 5, 10.0D);
    @ConfigEntry.Gui.CollapsibleObject
    public Dwarf dwarf = new Dwarf();
    @ConfigEntry.Gui.CollapsibleObject
    public Mob ender_knight = new Mob(true, 3, 48D, 8D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob ender = new Mob(true, 8, 46D, 9D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob ender_zombie = new Mob(true, 8, 20D, 5D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob fiora = new Mob(true, 2, 30D, 5D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob frost_blaze = new Mob(true, 10, 26D, 7D);
    @ConfigEntry.Gui.CollapsibleObject
    public MobNoAttack frost_creeper = new MobNoAttack(true, 10, 18D);
    @ConfigEntry.Gui.CollapsibleObject
    public MobNoAttack golden_chicken = new MobNoAttack(true, 3, 4D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob honey_slime = new Mob(true, 3, 4D, 1D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob ice_golem = new Mob(true, 5, 52D, 14D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob illusioner = new Mob(true, 2, 16D, 7D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob lava_golem = new Mob(true, 10, 48D, 14D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob lord_of_darkness = new Mob(true, 3, 72D, 7D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob lost_skeleton = new Mob(true, 10, 20D, 5D);
    @ConfigEntry.Gui.CollapsibleObject
    public MobSummonable metal_golem = new MobSummonable(true, 160D, 16D);
    @ConfigEntry.Gui.CollapsibleObject
    public MobNoAttack nether_skeleton = new MobNoAttack(true, 6, 28D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob nether_wolf = new Mob(true, 5, 20D, 6D);
    @ConfigEntry.Gui.CollapsibleObject
    public MobNoAttack overgrown_skeleton = new MobNoAttack(true, 15, 26D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob pigman = new Mob(true, 10, 24D, 7D);
    @ConfigEntry.Gui.CollapsibleObject
    public PillagerBoss pillager_boss = new PillagerBoss();
    @ConfigEntry.Gui.CollapsibleObject
    public Mob purple_spider = new Mob(true, 15, 26D, 5D);
    @ConfigEntry.Gui.CollapsibleObject
    public MobNoAttack soul_creeper = new MobNoAttack(true, 5, 25D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob fast_zombie = new Mob(true, 30, 15D, 4D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob spider_mage = new Mob(true, 6, 32D, 7D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob iron_steve = new Mob(true, 2, 32D, 9D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob stone_golem = new Mob(true, 2, 64D, 14D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob tank_zombie = new Mob(true, 30, 56D, 9D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob templar = new Mob(true, 2, 34D, 7D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob tiny_spider = new Mob(true, 5, 1D, 0D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob warrior = new Mob(true, 3, 48D, 6D);
    @ConfigEntry.Gui.CollapsibleObject
    public MobNoAttack wild_boar = new MobNoAttack(true, 5, 12.0D);
    @ConfigEntry.Gui.CollapsibleObject
    public MobSummonableNoAttack withender = new MobSummonableNoAttack(true, 400D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob wither_blaze = new Mob(true, 6, 26D, 8D);
    @ConfigEntry.Gui.CollapsibleObject
    public Mob zombie_mage = new Mob(true, 5, 28D, 7D);
    @ConfigEntry.Gui.CollapsibleObject
    public Charles charles = new Charles();
    @ConfigEntry.Gui.CollapsibleObject
    public MobSummonableAlwaysEnable william = new MobSummonableAlwaysEnable(60D, 8D);
    @ConfigEntry.Gui.CollapsibleObject
    public MobSummonableAlwaysEnable andriu = new MobSummonableAlwaysEnable(60D, 8D);
    @ConfigEntry.Gui.CollapsibleObject
    public MobSummonableAlwaysEnable knight = new MobSummonableAlwaysEnable(26D, 6D);
    @ConfigEntry.Gui.CollapsibleObject
    public MobSummonableAlwaysEnable spirit_of_death = new MobSummonableAlwaysEnable(16D, 5D);
    @ConfigEntry.Gui.CollapsibleObject
    public BabyRavager baby_ravager = new BabyRavager();
    @ConfigEntry.Gui.CollapsibleObject
    public MobNoAttack toad = new MobNoAttack(false, 8, 10.0D);

    @Comment("Be carefull with this setting! Multiplicator must be > 0!")
    public double life_multiplier = 1.0D;
    @Comment("Be carefull with this setting! Multiplicator must be > 0!")
    public double damage_multiplier = 1.0D;
}
