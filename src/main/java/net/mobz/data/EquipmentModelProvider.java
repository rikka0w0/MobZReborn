package net.mobz.data;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;

import net.mobz.MobZ;
import net.mobz.item.armor.AmatArmorBase;
import net.mobz.item.armor.BossArmorBase;
import net.mobz.item.armor.LifeArmorBase;
import net.mobz.item.armor.SpeedShoeBase;

public class EquipmentModelProvider implements DataProvider {
    private final PackOutput.PathProvider pathProvider;

    public EquipmentModelProvider(PackOutput output) {
        this.pathProvider = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "models/equipment");
    }

    protected void generate(BiConsumer<ResourceKey<EquipmentAsset>, EquipmentClientInfo> writer) {
    	writer.accept(AmatArmorBase.EQUIPMENT_MODEL_AMAT, EquipmentClientInfo.builder()
    			.addHumanoidLayers(AmatArmorBase.EQUIPMENT_MODEL_AMAT.location(), false)
    			.build());

    	writer.accept(BossArmorBase.EQUIPMENT_MODEL_BOSS, EquipmentClientInfo.builder()
    			.addHumanoidLayers(BossArmorBase.EQUIPMENT_MODEL_BOSS.location(), false)
    			.build());

    	writer.accept(LifeArmorBase.EQUIPMENT_MODEL_LIFE, EquipmentClientInfo.builder()
    			.addHumanoidLayers(LifeArmorBase.EQUIPMENT_MODEL_LIFE.location(), false)
    			.build());

    	writer.accept(SpeedShoeBase.EQUIPMENT_MODEL_SPEED, EquipmentClientInfo.builder()
    			.addHumanoidLayers(SpeedShoeBase.EQUIPMENT_MODEL_SPEED.location(), false)
    			.build());

    	writer.accept(SpeedShoeBase.EQUIPMENT_MODEL_SPEED2, EquipmentClientInfo.builder()
    			.addHumanoidLayers(SpeedShoeBase.EQUIPMENT_MODEL_SPEED2.location(), false)
    			.build());
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
    	Map<ResourceKey<EquipmentAsset>, EquipmentClientInfo> generated = new HashMap<>();
        this.generate((resLoc, equipmentModel) -> {
            if (generated.putIfAbsent(resLoc, equipmentModel) != null) {
                throw new IllegalStateException("Tried to register equipment model twice for id: " + resLoc);
            }
        });
        return DataProvider.saveAll(cachedOutput, EquipmentClientInfo.CODEC, this.pathProvider::json, generated);
    }

    @Override
    public String getName() {
        return "Equipment Model Definitions for " + MobZ.MODID;
    }
}
