package net.mobz.data;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import javax.annotation.Nullable;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.KilledTrigger;
import net.minecraft.advancements.critereon.PlayerHurtEntityTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

import net.mobz.MobZ;
import net.mobz.init.MobZEntities;

public class MobAdvancements implements AdvancementSubProvider {
	public final static ResourceLocation BACKGROUND =
			ResourceLocation.tryBuild("minecraft", "textures/gui/advancements/backgrounds/adventure.png");

	public static Item icon(String name) {
		ResourceLocation resLoc = ResourceLocation.tryBuild(MobZ.MODID, name);
		Item item = BuiltInRegistries.ITEM.get(resLoc);
		return item;
	}

	public static Advancement advancement(Consumer<Advancement> writer, EntityType<?> entityType,
			@Nullable String iconName,
			@Nullable Advancement parent,
			FrameType type, boolean showToast, boolean announceChat, boolean hidden,
			@Nullable AdvancementRewards.Builder rewardBuilder,
			BiConsumer<String, Advancement.Builder> criterionAdder) {
		String name = BuiltInRegistries.ENTITY_TYPE.getKey(entityType).getPath();

		if (iconName == null) {
			iconName = "head_" + name;
		}

		Advancement.Builder builder = Advancement.Builder.advancement().display(
			BuiltInRegistries.ITEM.get(ResourceLocation.tryBuild(MobZ.MODID, iconName)),
			Component.translatable("entity.mobz." + name),
			Component.translatable("mobz.advancements." + name + ".description"),
			null, type, showToast, announceChat, hidden);

		criterionAdder.accept(name, builder);

		if (parent != null) {
			builder.parent(parent);
		}

		if (rewardBuilder != null) {
			builder.rewards(rewardBuilder);
		}

		return builder.save(writer, "mobz:mob/" + name);
	}

	public static Advancement killByPlayer(Consumer<Advancement> writer, EntityType<?> entityType,
			@Nullable String iconName,
			@Nullable Advancement parent,
			FrameType type, boolean showToast, boolean announceChat, boolean hidden,
			@Nullable AdvancementRewards.Builder rewardBuilder) {
		return advancement(writer, entityType, iconName, parent, type, showToast, announceChat, hidden, rewardBuilder,
				(name, builder) -> builder.addCriterion("player_killed_" + name,
						KilledTrigger.TriggerInstance.playerKilledEntity(
							EntityPredicate.Builder.entity().of(entityType)
						)
					)
			);
	}

	public static Advancement hurtByPlayer(Consumer<Advancement> writer, EntityType<?> entityType,
			@Nullable String iconName,
			@Nullable Advancement parent,
			FrameType type, boolean showToast, boolean announceChat, boolean hidden,
			@Nullable AdvancementRewards.Builder rewardBuilder) {
		return advancement(writer, entityType, iconName, parent, type, showToast, announceChat, hidden, rewardBuilder,
				(name, builder) -> builder.addCriterion("player_hurt_" + name,
						PlayerHurtEntityTrigger.TriggerInstance.playerHurtEntity(
							EntityPredicate.Builder.entity().of(entityType).build()
						)
					)
			);
	}

	@SuppressWarnings("unused")
	@Override
	public void generate(HolderLookup.Provider registries, Consumer<Advancement> writer) {
		Advancement started = Advancement.Builder.advancement()
			.display(
				icon("head_start"),
				Component.translatable("mobz.advancements.start.title"),
				Component.translatable("mobz.advancements.start.description"),
				BACKGROUND, FrameType.TASK, true, false, false)
			.addCriterion("kill_anything", KilledTrigger.TriggerInstance.playerKilledEntity())
			.addCriterion("hurt_anything", PlayerHurtEntityTrigger.TriggerInstance.playerHurtEntity())
//			.sendsTelemetryEvent() TODO: back port to 1.20.1, no matching for this?
			.save(writer, "mobz:mob/started");

		// frost_creeper
		Advancement frost_creeper = killByPlayer(writer, MobZEntities.FROST_CREEPER.get(), null, started,
			FrameType.GOAL, true, false, false, null);


		// baby_ravager
		Advancement baby_ravager = killByPlayer(writer, MobZEntities.BABY_RAVAGER.get(), null, started,
			FrameType.GOAL, true, false, true, null);


		// stone_golem
		Advancement stone_golem = killByPlayer(writer, MobZEntities.STONEGOLEM.get(), null, started,
			FrameType.GOAL, true, false, false, null);


		// frost_blaze
		Advancement frost_blaze = killByPlayer(writer, MobZEntities.FROST_BLAZE.get(), null, started,
			FrameType.GOAL, true, false, false, null);


		// ender_zombie
		Advancement ender_zombie = killByPlayer(writer, MobZEntities.ENDER_ZOMBIE.get(), null, started,
			FrameType.GOAL, true, false, false, null);


		// purple_spider
		Advancement purple_spider = killByPlayer(writer, MobZEntities.PURPLE_SPIDER.get(), null, started,
			FrameType.GOAL, true, false, false, null);


		// fast_zombie
		Advancement fast_zombie = killByPlayer(writer, MobZEntities.FAST_ZOMBIE.get(), null, started,
			FrameType.GOAL, true, false, false, null);


		// spider_mage
		Advancement spider_mage = killByPlayer(writer, MobZEntities.SPIDER_MAGE.get(), "head_zombie_mage", started,
			FrameType.GOAL, true, false, true, null);


		// lost_skeleton
		Advancement lost_skeleton = killByPlayer(writer, MobZEntities.LOST_SKELETON.get(), null, started,
			FrameType.GOAL, true, false, false, null);


		// warrior
		Advancement warrior = killByPlayer(writer, MobZEntities.WARRIOR.get(), null, started,
			FrameType.GOAL, true, false, false, null);


		// cookie_creeper
		Advancement cookie_creeper = killByPlayer(writer, MobZEntities.COOKIE_CREEPER.get(), null, frost_creeper,
			FrameType.GOAL, true, false, false, null);


		// iron_steve
		Advancement iron_steve = killByPlayer(writer, MobZEntities.IRON_STEVE.get(), null, warrior,
			FrameType.GOAL, true, false, false, null);


		// blue_spider
		Advancement blue_spider = killByPlayer(writer, MobZEntities.BLUE_SPIDER.get(), null, purple_spider,
			FrameType.GOAL, true, false, false, null);


		// bowman
		Advancement bowman = killByPlayer(writer, MobZEntities.BOWMAN.get(), null, warrior,
			FrameType.GOAL, true, false, false, null);


		// tank_zombie
		Advancement tank_zombie = killByPlayer(writer, MobZEntities.TANK_ZOMBIE.get(), "head_fast_zombie", fast_zombie,
			FrameType.GOAL, true, false, false, null);


		// william
		Advancement william = killByPlayer(writer, MobZEntities.WILLIAM.get(), null, baby_ravager,
			FrameType.GOAL, true, false, false, null);


		// ender
		Advancement ender = killByPlayer(writer, MobZEntities.ENDER.get(), null, ender_zombie,
			FrameType.GOAL, true, false, false, null);


		// zombie_mage
		Advancement zombie_mage = killByPlayer(writer, MobZEntities.ZOMBIE_MAGE.get(), null, spider_mage,
			FrameType.GOAL, true, false, false, null);


		// lava_golem
		Advancement lava_golem = killByPlayer(writer, MobZEntities.LAVAGOLEM.get(), null, stone_golem,
			FrameType.GOAL, true, false, false, null);


		// overgrown_skeleton
		Advancement overgrown_skeleton = killByPlayer(writer, MobZEntities.OVERGROWN_SKELETON.get(), null, lost_skeleton,
			FrameType.GOAL, true, false, false, null);


		// wither_blaze
		Advancement wither_blaze = killByPlayer(writer, MobZEntities.WITHER_BLAZE.get(), null, frost_blaze,
			FrameType.GOAL, true, false, false, null);


		// ice_golem
		Advancement ice_golem = killByPlayer(writer, MobZEntities.ICEGOLEM.get(), null, lava_golem,
			FrameType.GOAL, true, false, false, null);


		// nether_skeleton
		Advancement nether_skeleton = killByPlayer(writer, MobZEntities.NETHER_SKELETON.get(), null, overgrown_skeleton,
			FrameType.GOAL, true, false, false, null);


		// pillager_boss
		Advancement pillager_boss = killByPlayer(writer, MobZEntities.PILLAGER_BOSS.get(), null, zombie_mage,
			FrameType.CHALLENGE, true, false, true,
			AdvancementRewards.Builder.experience(20).addLootTable(RewardLoot.BIGBOSS));


		// dwarf
		Advancement dwarf = killByPlayer(writer, MobZEntities.DWARF.get(), null, iron_steve,
			FrameType.GOAL, true, false, false, null);


		// andriu
		Advancement andriu = killByPlayer(writer, MobZEntities.ANDRIU.get(), null, william,
			FrameType.GOAL, true, false, false, null);


		// armored_zombie
		Advancement armored_zombie = killByPlayer(writer, MobZEntities.ARMORED_ZOMBIE.get(), "head_fast_zombie", tank_zombie,
			FrameType.GOAL, true, false, false, null);


		// archer
		Advancement archer = killByPlayer(writer, MobZEntities.ARCHER.get(), null, bowman,
			FrameType.GOAL, true, false, false, null);


		// soul_creeper
		Advancement soul_creeper = killByPlayer(writer, MobZEntities.SOUL_CREEPER.get(), null, cookie_creeper,
			FrameType.GOAL, true, false, false, null);


		// katherine
		Advancement katherine = hurtByPlayer(writer, MobZEntities.KATHERINE.get(), null, iron_steve,
			FrameType.GOAL, true, false, false, null);


		// fiora
		Advancement fiora = hurtByPlayer(writer, MobZEntities.FIORA.get(), null, katherine,
			FrameType.GOAL, true, false, false, null);


		// charles
		Advancement charles = killByPlayer(writer, MobZEntities.CHARLES.get(), null, andriu,
			FrameType.CHALLENGE, true, true, false,
			AdvancementRewards.Builder.experience(40).addLootTable(RewardLoot.BIGBOSS));


		// templar
		Advancement templar = killByPlayer(writer, MobZEntities.TEMPLAR.get(), null, dwarf,
			FrameType.GOAL, true, false, false, null);


		// boss_skeleton
		Advancement boss_skeleton = killByPlayer(writer, MobZEntities.BOSS_SKELETON.get(), null, nether_skeleton,
			FrameType.CHALLENGE, true, false, false, null);


		// boss_zombie
		Advancement boss_zombie = killByPlayer(writer, MobZEntities.BOSS_ZOMBIE.get(), null, armored_zombie,
			FrameType.CHALLENGE, true, false, true,
			AdvancementRewards.Builder.experience(50).addLootTable(RewardLoot.BOSS_ZOMBIE));


		// ender_knight
		Advancement ender_knight = killByPlayer(writer, MobZEntities.ENDER_KNIGHT.get(), null, templar,
			FrameType.GOAL, true, false, false, null);


		// metal_golem
		Advancement metal_golem = killByPlayer(writer, MobZEntities.METALGOLEM.get(), null, fiora,
			FrameType.GOAL, true, false, true, null);


		// lord_of_darkness
		Advancement lord_of_darkness = killByPlayer(writer, MobZEntities.LORD_OF_DARKNESS.get(), null, ender_knight,
			FrameType.GOAL, true, false, false, null);


		// withender
		Advancement withender = killByPlayer(writer, MobZEntities.WITHENDER.get(), null, lord_of_darkness,
			FrameType.CHALLENGE, true, false, true, null);
	}

}