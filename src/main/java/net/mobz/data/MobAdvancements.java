package net.mobz.data;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import javax.annotation.Nullable;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.KilledTrigger;
import net.minecraft.advancements.critereon.PlayerHurtEntityTrigger;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
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
		Item item = BuiltInRegistries.ITEM.getValue(resLoc);
		return item;
	}

	public static AdvancementHolder advancement(Consumer<AdvancementHolder> writer, EntityType<?> entityType,
			@Nullable String iconName,
			@Nullable AdvancementHolder parent,
			AdvancementType type, boolean showToast, boolean announceChat, boolean hidden,
			@Nullable AdvancementRewards.Builder rewardBuilder,
			BiConsumer<String, Advancement.Builder> criterionAdder) {
		String name = BuiltInRegistries.ENTITY_TYPE.getKey(entityType).getPath();

		if (iconName == null) {
			iconName = "head_" + name;
		}

		Advancement.Builder builder = Advancement.Builder.advancement().display(
			BuiltInRegistries.ITEM.getValue(ResourceLocation.tryBuild(MobZ.MODID, iconName)),
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

	public static AdvancementHolder killByPlayer(
			HolderLookup.Provider registries, Consumer<AdvancementHolder> writer,
			EntityType<?> entityType,
			@Nullable String iconName,
			@Nullable AdvancementHolder parent,
			AdvancementType type, boolean showToast, boolean announceChat, boolean hidden,
			@Nullable AdvancementRewards.Builder rewardBuilder) {
		HolderGetter<EntityType<?>> holderGetter = registries.lookupOrThrow(Registries.ENTITY_TYPE);
		return advancement(writer, entityType, iconName, parent, type, showToast, announceChat, hidden, rewardBuilder,
				(name, builder) -> builder.addCriterion("player_killed_" + name,
						KilledTrigger.TriggerInstance.playerKilledEntity(
							EntityPredicate.Builder.entity().of(holderGetter, entityType)
						)
					)
			);
	}

	public static AdvancementHolder hurtByPlayer(
			HolderLookup.Provider registries, Consumer<AdvancementHolder> writer,
			EntityType<?> entityType,
			@Nullable String iconName,
			@Nullable AdvancementHolder parent,
			AdvancementType type, boolean showToast, boolean announceChat, boolean hidden,
			@Nullable AdvancementRewards.Builder rewardBuilder) {
		HolderGetter<EntityType<?>> holderGetter = registries.lookupOrThrow(Registries.ENTITY_TYPE);
		return advancement(writer, entityType, iconName, parent, type, showToast, announceChat, hidden, rewardBuilder,
				(name, builder) -> builder.addCriterion("player_hurt_" + name,
						PlayerHurtEntityTrigger.TriggerInstance.playerHurtEntity(
							Optional.of(EntityPredicate.Builder.entity().of(holderGetter, entityType).build())
						)
					)
			);
	}

	@SuppressWarnings("unused")
	@Override
	public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> writer) {
		AdvancementHolder started = Advancement.Builder.advancement()
			.display(
				icon("head_start"),
				Component.translatable("mobz.advancements.start.title"),
				Component.translatable("mobz.advancements.start.description"),
				BACKGROUND, AdvancementType.TASK, true, false, false)
			.addCriterion("kill_anything", KilledTrigger.TriggerInstance.playerKilledEntity())
			.addCriterion("hurt_anything", PlayerHurtEntityTrigger.TriggerInstance.playerHurtEntity())
			.sendsTelemetryEvent()
			.save(writer, "mobz:mob/started");

		// frost_creeper
		AdvancementHolder frost_creeper = killByPlayer(registries, writer,  MobZEntities.FROST_CREEPER.get(), null, started,
			AdvancementType.GOAL, true, false, false, null);


		// baby_ravager
		AdvancementHolder baby_ravager = killByPlayer(registries, writer,  MobZEntities.BABY_RAVAGER.get(), null, started,
			AdvancementType.GOAL, true, false, true, null);


		// stone_golem
		AdvancementHolder stone_golem = killByPlayer(registries, writer,  MobZEntities.STONEGOLEM.get(), null, started,
			AdvancementType.GOAL, true, false, false, null);


		// frost_blaze
		AdvancementHolder frost_blaze = killByPlayer(registries, writer,  MobZEntities.FROST_BLAZE.get(), null, started,
			AdvancementType.GOAL, true, false, false, null);


		// ender_zombie
		AdvancementHolder ender_zombie = killByPlayer(registries, writer,  MobZEntities.ENDER_ZOMBIE.get(), null, started,
			AdvancementType.GOAL, true, false, false, null);


		// purple_spider
		AdvancementHolder purple_spider = killByPlayer(registries, writer,  MobZEntities.PURPLE_SPIDER.get(), null, started,
			AdvancementType.GOAL, true, false, false, null);


		// fast_zombie
		AdvancementHolder fast_zombie = killByPlayer(registries, writer,  MobZEntities.FAST_ZOMBIE.get(), null, started,
			AdvancementType.GOAL, true, false, false, null);


		// spider_mage
		AdvancementHolder spider_mage = killByPlayer(registries, writer,  MobZEntities.SPIDER_MAGE.get(), "head_zombie_mage", started,
			AdvancementType.GOAL, true, false, true, null);


		// lost_skeleton
		AdvancementHolder lost_skeleton = killByPlayer(registries, writer,  MobZEntities.LOST_SKELETON.get(), null, started,
			AdvancementType.GOAL, true, false, false, null);


		// warrior
		AdvancementHolder warrior = killByPlayer(registries, writer,  MobZEntities.WARRIOR.get(), null, started,
			AdvancementType.GOAL, true, false, false, null);


		// cookie_creeper
		AdvancementHolder cookie_creeper = killByPlayer(registries, writer,  MobZEntities.COOKIE_CREEPER.get(), null, frost_creeper,
			AdvancementType.GOAL, true, false, false, null);


		// iron_steve
		AdvancementHolder iron_steve = killByPlayer(registries, writer,  MobZEntities.IRON_STEVE.get(), null, warrior,
			AdvancementType.GOAL, true, false, false, null);


		// blue_spider
		AdvancementHolder blue_spider = killByPlayer(registries, writer,  MobZEntities.BLUE_SPIDER.get(), null, purple_spider,
			AdvancementType.GOAL, true, false, false, null);


		// bowman
		AdvancementHolder bowman = killByPlayer(registries, writer,  MobZEntities.BOWMAN.get(), null, warrior,
			AdvancementType.GOAL, true, false, false, null);


		// tank_zombie
		AdvancementHolder tank_zombie = killByPlayer(registries, writer,  MobZEntities.TANK_ZOMBIE.get(), "head_fast_zombie", fast_zombie,
			AdvancementType.GOAL, true, false, false, null);


		// william
		AdvancementHolder william = killByPlayer(registries, writer,  MobZEntities.WILLIAM.get(), null, baby_ravager,
			AdvancementType.GOAL, true, false, false, null);


		// ender
		AdvancementHolder ender = killByPlayer(registries, writer,  MobZEntities.ENDER.get(), null, ender_zombie,
			AdvancementType.GOAL, true, false, false, null);


		// zombie_mage
		AdvancementHolder zombie_mage = killByPlayer(registries, writer,  MobZEntities.ZOMBIE_MAGE.get(), null, spider_mage,
			AdvancementType.GOAL, true, false, false, null);


		// lava_golem
		AdvancementHolder lava_golem = killByPlayer(registries, writer,  MobZEntities.LAVAGOLEM.get(), null, stone_golem,
			AdvancementType.GOAL, true, false, false, null);


		// overgrown_skeleton
		AdvancementHolder overgrown_skeleton = killByPlayer(registries, writer,  MobZEntities.OVERGROWN_SKELETON.get(), null, lost_skeleton,
			AdvancementType.GOAL, true, false, false, null);


		// wither_blaze
		AdvancementHolder wither_blaze = killByPlayer(registries, writer,  MobZEntities.WITHER_BLAZE.get(), null, frost_blaze,
			AdvancementType.GOAL, true, false, false, null);


		// ice_golem
		AdvancementHolder ice_golem = killByPlayer(registries, writer,  MobZEntities.ICEGOLEM.get(), null, lava_golem,
			AdvancementType.GOAL, true, false, false, null);


		// nether_skeleton
		AdvancementHolder nether_skeleton = killByPlayer(registries, writer,  MobZEntities.NETHER_SKELETON.get(), null, overgrown_skeleton,
			AdvancementType.GOAL, true, false, false, null);


		// pillager_boss
		AdvancementHolder pillager_boss = killByPlayer(registries, writer,  MobZEntities.PILLAGER_BOSS.get(), null, zombie_mage,
			AdvancementType.CHALLENGE, true, false, true,
			AdvancementRewards.Builder.experience(20).addLootTable(RewardLoot.BIGBOSS));


		// dwarf
		AdvancementHolder dwarf = killByPlayer(registries, writer,  MobZEntities.DWARF.get(), null, iron_steve,
			AdvancementType.GOAL, true, false, false, null);


		// andriu
		AdvancementHolder andriu = killByPlayer(registries, writer,  MobZEntities.ANDRIU.get(), null, william,
			AdvancementType.GOAL, true, false, false, null);


		// armored_zombie
		AdvancementHolder armored_zombie = killByPlayer(registries, writer,  MobZEntities.ARMORED_ZOMBIE.get(), "head_fast_zombie", tank_zombie,
			AdvancementType.GOAL, true, false, false, null);


		// archer
		AdvancementHolder archer = killByPlayer(registries, writer,  MobZEntities.ARCHER.get(), null, bowman,
			AdvancementType.GOAL, true, false, false, null);


		// soul_creeper
		AdvancementHolder soul_creeper = killByPlayer(registries, writer,  MobZEntities.SOUL_CREEPER.get(), null, cookie_creeper,
			AdvancementType.GOAL, true, false, false, null);


		// katherine
		AdvancementHolder katherine = hurtByPlayer(registries, writer, MobZEntities.KATHERINE.get(), null, iron_steve,
			AdvancementType.GOAL, true, false, false, null);


		// fiora
		AdvancementHolder fiora = hurtByPlayer(registries, writer, MobZEntities.FIORA.get(), null, katherine,
			AdvancementType.GOAL, true, false, false, null);


		// charles
		AdvancementHolder charles = killByPlayer(registries, writer,  MobZEntities.CHARLES.get(), null, andriu,
			AdvancementType.CHALLENGE, true, true, false,
			AdvancementRewards.Builder.experience(40).addLootTable(RewardLoot.BIGBOSS));


		// templar
		AdvancementHolder templar = killByPlayer(registries, writer,  MobZEntities.TEMPLAR.get(), null, dwarf,
			AdvancementType.GOAL, true, false, false, null);


		// boss_skeleton
		AdvancementHolder boss_skeleton = killByPlayer(registries, writer,  MobZEntities.BOSS_SKELETON.get(), null, nether_skeleton,
			AdvancementType.CHALLENGE, true, false, false, null);


		// boss_zombie
		AdvancementHolder boss_zombie = killByPlayer(registries, writer,  MobZEntities.BOSS_ZOMBIE.get(), null, armored_zombie,
			AdvancementType.CHALLENGE, true, false, true,
			AdvancementRewards.Builder.experience(50).addLootTable(RewardLoot.BOSS_ZOMBIE));


		// ender_knight
		AdvancementHolder ender_knight = killByPlayer(registries, writer,  MobZEntities.ENDER_KNIGHT.get(), null, templar,
			AdvancementType.GOAL, true, false, false, null);


		// metal_golem
		AdvancementHolder metal_golem = killByPlayer(registries, writer,  MobZEntities.METALGOLEM.get(), null, fiora,
			AdvancementType.GOAL, true, false, true, null);


		// lord_of_darkness
		AdvancementHolder lord_of_darkness = killByPlayer(registries, writer,  MobZEntities.LORD_OF_DARKNESS.get(), null, ender_knight,
			AdvancementType.GOAL, true, false, false, null);


		// withender
		AdvancementHolder withender = killByPlayer(registries, writer,  MobZEntities.WITHENDER.get(), null, lord_of_darkness,
			AdvancementType.CHALLENGE, true, false, true, null);
	}

}
