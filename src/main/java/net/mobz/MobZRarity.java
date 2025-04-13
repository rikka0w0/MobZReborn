package net.mobz;

import java.util.function.Consumer;

import net.minecraft.network.chat.Component;

public enum MobZRarity {
	UNCOMMON,
	COMMON,
	RARE,
	EPIC,
	LEGENDARY,
	;

	public final String localizationKey = "text.mobz.tier." + this.name().toLowerCase();

	public Component component() {
		return Component.translatable(this.localizationKey);
	}

	public void addToTooltip(Consumer<Component> tooltip) {
		tooltip.accept(component());
	}
}
