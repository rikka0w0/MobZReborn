package net.mobz.item;

import net.minecraft.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.JukeboxSong;
import net.minecraft.world.item.Rarity;

public class DiscItem extends Item {
	private String descriptionId;

	public DiscItem(Rarity rarity, ResourceKey<JukeboxSong> song) {
		super(new Item.Properties().rarity(rarity).stacksTo(1).jukeboxPlayable(song));
	}

    @Override
	protected String getOrCreateDescriptionId() {
        if (this.descriptionId == null) {
            this.descriptionId = Util.makeDescriptionId("item", BuiltInRegistries.ITEM.getKey(this)) + ".desc";
        }

        return this.descriptionId;
    }
}
