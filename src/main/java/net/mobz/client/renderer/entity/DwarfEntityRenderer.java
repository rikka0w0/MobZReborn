package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.util.ResourceLocation;
import net.mobz.entity.DwarfEntity;

public class DwarfEntityRenderer extends BipedRenderer<DwarfEntity, PlayerModel<DwarfEntity>> {

    public DwarfEntityRenderer(EntityRendererManager dispatcher) {
        super(dispatcher, new PlayerModel<>(0.0F, false), 0.5F);
        this.addLayer(new BipedArmorLayer<>(this, new PlayerModel<>(0.5F, true),
                new PlayerModel<>(1.0F, true)));
    }

    @Override
    protected void scale(DwarfEntity dwarf, MatrixStack matrixStack, float f) {
        matrixStack.scale(1F, 0.75F, 1F);
    }

    @Override
    public ResourceLocation getTextureLocation(DwarfEntity dwarfentitye) {
        return new ResourceLocation("mobz:textures/entity/dwarf.png");
    }
}