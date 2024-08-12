package net.mobz.item.armor;

import java.util.UUID;

import com.google.common.collect.Multimap;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class ArmorUtils {
	public static UUID getModifierUUID(Multimap<Attribute, AttributeModifier> existing) {
		return existing.values().stream().findFirst().map(modifier -> modifier.getId()).orElse(null);
	}
}
