package net.mobz.data;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.equipment.EquipmentModel;

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

    protected void generate(BiConsumer<ResourceLocation, EquipmentModel> writer) {
    	writer.accept(AmatArmorBase.EQUIPMENT_MODEL_AMAT, EquipmentModel.builder()
    			.addHumanoidLayers(AmatArmorBase.EQUIPMENT_MODEL_AMAT, false)
    			.build());

    	writer.accept(BossArmorBase.EQUIPMENT_MODEL_BOSS, EquipmentModel.builder()
    			.addHumanoidLayers(BossArmorBase.EQUIPMENT_MODEL_BOSS, false)
    			.build());

    	writer.accept(LifeArmorBase.EQUIPMENT_MODEL_LIFE, EquipmentModel.builder()
    			.addHumanoidLayers(LifeArmorBase.EQUIPMENT_MODEL_LIFE, false)
    			.build());

    	writer.accept(SpeedShoeBase.EQUIPMENT_MODEL_SPEED, EquipmentModel.builder()
    			.addHumanoidLayers(SpeedShoeBase.EQUIPMENT_MODEL_SPEED, false)
    			.build());

    	writer.accept(SpeedShoeBase.EQUIPMENT_MODEL_SPEED2, EquipmentModel.builder()
    			.addHumanoidLayers(SpeedShoeBase.EQUIPMENT_MODEL_SPEED2, false)
    			.build());
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        Map<ResourceLocation, EquipmentModel> generated = new HashMap<>();
        this.generate((resLoc, equipmentModel) -> {
            if (generated.putIfAbsent(resLoc, equipmentModel) != null) {
                throw new IllegalStateException("Tried to register equipment model twice for id: " + resLoc);
            }
        });
        return DataProvider.saveAll(cachedOutput, EquipmentModel.CODEC, this.pathProvider, generated);
    }

    @Override
    public String getName() {
        return "Equipment Model Definitions for " + MobZ.MODID;
    }
}
