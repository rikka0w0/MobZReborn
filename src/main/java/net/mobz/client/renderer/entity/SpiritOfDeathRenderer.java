package net.mobz.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.VexRenderer;
import net.minecraft.client.renderer.entity.state.VexRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

public class SpiritOfDeathRenderer extends VexRenderer {
	private final Identifier texture;
	private final Identifier charging_texture;

	public SpiritOfDeathRenderer(EntityRendererProvider.Context context, Identifier texture) {
		super(context);
		this.texture = texture;
		String eyePath = texture.getPath().replace(".png", "_charging.png");
		this.charging_texture = Identifier.fromNamespaceAndPath(texture.getNamespace(), eyePath);
	}

	@Override
	public Identifier getTextureLocation(VexRenderState renderState) {
		return renderState.isCharging ? this.charging_texture : this.texture;
	}

	@Override
	protected void scale(VexRenderState renderState, PoseStack matrixStack) {
		matrixStack.scale(0.4F, 0.4F, 0.4F);
	}
}
