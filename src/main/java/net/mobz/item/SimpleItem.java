package net.mobz.item;

import java.util.function.Consumer;
import java.util.function.Function;

import javax.annotation.Nullable;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.mobz.MobZRarity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class SimpleItem extends Item {
	@Nullable
	public final MobZRarity mobZTier;

	@Nullable
	private MutableComponent tooltip;

	/**
	 * @param name        Naming rules: lower case letters + numbers only,
	 *                    '_' as delimiter, e.g. "cooked_beef"
	 */
	public SimpleItem(Item.Properties properties) {
		this(properties, null, false);
	}

	public SimpleItem(Item.Properties properties, @Nullable MobZRarity mobZTier) {
		this(properties, mobZTier, false);
	}

	public SimpleItem(Item.Properties properties, @Nullable MobZRarity mobZTier, boolean hasTooltip) {
		super(properties);
		this.mobZTier = mobZTier;
		this.tooltip = hasTooltip ? Component.translatable(this.getDescriptionId() + ".tooltip") : null;
	}

	@Override
	public void appendHoverText(
		ItemStack itemStack,
		Item.TooltipContext tooltipContext,
		TooltipDisplay display,
		Consumer<Component> tooltip,
		TooltipFlag flag) {

		if (this.tooltip != null) {
			tooltip.accept(this.tooltip);
		}
		if (this.mobZTier != null) {
			this.mobZTier.addToTooltip(tooltip);
		}
	}

	public static Function<Item.Properties, SimpleItem> ofTier(@Nullable MobZRarity mobZTier) {
		return (props) -> new SimpleItem(props, mobZTier);
	}

	/**
	 * To be used in {@link Item#inventoryTick} to get the slot argument. Only applicable if entity
	 * is a player.
	 * @param itemStack the {@link ItemStack} argument of {@link Item#inventoryTick}
	 * @param entity the {@link Entity} argument of {@link Item#inventoryTick}
	 * @return the slot id of the given {@link ItemStack} in the player's inventory. Return -1 if entity
	 * is not an instance of {@link Player} or the ItemStack is not in the inventory.
	 */
	public static int inventoryTickGetPlayerSlot(ItemStack itemStack, Entity entity) {
		if (entity instanceof Player player) {
			Inventory inventory = player.getInventory();
			int size = inventory.getContainerSize();
			for (int i = 0; i<size; i++) {
				if (itemStack == inventory.getItem(i)) {
					return i;
				}
			}
		}

		return -1;
	}

	public static boolean inventoryTickIsSlotQuickAccess(ItemStack itemStack, Entity entity) {
		int slot = inventoryTickGetPlayerSlot(itemStack, entity);
		return slot >= 0 && slot < 9;
	}
}
