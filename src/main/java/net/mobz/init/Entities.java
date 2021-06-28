package net.mobz.init;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.mobz.common.IRegistryWrapper;
import net.mobz.entity.BossEntity;

public class Entities {
    public static final EntityType<BossEntity> BOSS = 
    		EntityType.Builder.of(BossEntity::new, EntityClassification.MONSTER)
			.setTrackingRange(74).setUpdateInterval(2)
			.fireImmune()
			.sized(0.6F, 1.95F)
			.build("boss_entity");

    public static void registerAll(IRegistryWrapper registry) {
    	registry.register("boss", BOSS, BossEntity::createBossEntityAttributes,
    			1181988, 3560490, Items.tab);
    }
}
