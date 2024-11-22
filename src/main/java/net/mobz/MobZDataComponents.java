package net.mobz;

import java.util.function.Supplier;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.util.ExtraCodecs;

public class MobZDataComponents {
	public static final Supplier<DataComponentType<Integer>> DRYING_NUMBER =
			MobZ.platform.registerDataComponentType("drying_number",
			builder -> builder.persistent(ExtraCodecs.NON_NEGATIVE_INT).networkSynchronized(ByteBufCodecs.VAR_INT),
			null);
}
