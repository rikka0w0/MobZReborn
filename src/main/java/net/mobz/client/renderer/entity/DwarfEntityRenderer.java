package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.resources.ResourceLocation;
import net.mobz.entity.DwarfEntity;

public class DwarfEntityRenderer extends HumanoidMobRenderer<DwarfEntity, PlayerModel<DwarfEntity>> {

    public DwarfEntityRenderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher, new PlayerModel<>(0.0F, false), 0.5F);
        this.addLayer(new HumanoidArmorLayer<>(this, new PlayerModel<>(0.5F, true),
                new PlayerModel<>(1.0F, true)));
    }

    @Override
    protected void scale(DwarfEntity dwarf, PoseStack matrixStack, float f) {
        matrixStack.scale(1F, 0.75F, 1F);
    }

    @Override
    public ResourceLocation getTextureLocation(DwarfEntity dwarfentitye) {
        return new ResourceLocation("mobz:textures/entity/dwarf.png");
    }
}