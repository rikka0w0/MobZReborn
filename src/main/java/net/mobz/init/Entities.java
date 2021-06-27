package net.mobz.init;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.mobz.common.EntityDefinition;
import net.mobz.common.IRegistryWrapper;
import net.mobz.entity.BossEntity;

public class Entities {
    public static final EntityDefinition<BossEntity> BOSS = new EntityDefinition<>("boss", 
			EntityType.Builder.of(BossEntity::new, EntityClassification.MONSTER)
			.setTrackingRange(74).setUpdateInterval(2)
			.fireImmune()
			.sized(0.6F, 1.95F),
			BossEntity::createBossEntityAttributes,
			1181988, 3560490, Items.tab
			);

    public static void registerAll(IRegistryWrapper registry) {
    	registry.register(BOSS);
    }
}
