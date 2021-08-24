package net.mobz.client.renderer.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import com.mojang.math.Vector4f;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.mobz.entity.ToadEntity;
import net.mobz.MathUtils;
import net.mobz.MobZ;

public class ToadEntityModel extends ListModel<ToadEntity> implements HeadedModel
{
	private final ModelPart body;
	private final ModelPart backlege;
	private final ModelPart backlegw;
	private final ModelPart eyeballw;
	private final ModelPart eyeballe;
	private final ModelPart lips;
	private final ModelPart lipBottom;
	private final ModelPart lipTop;
	private final ModelPart frontlegw;
	private final ModelPart frontlege;
	private final ModelPart tongue;

	static float tongueDistance;
	static ToadEntity entity;

	public final static ModelLayerLocation modelResLoc = new ModelLayerLocation(
			new ResourceLocation(MobZ.MODID, "toad_model"), "main");

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition root = meshdefinition.getRoot();

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder
				.create().texOffs(0, 0)
				.addBox(-4.0F, -8.0F, -2.0F, 8.0F, 8.0F, 8.0F).texOffs(10, 24)
				.addBox(1.0F, -10.0F, -1.0F, 2.0F, 2.0F, 3.0F).texOffs(0, 24)
				.addBox(-3.0F, -10.0F, -1.0F, 2.0F, 2.0F, 3.0F),
				PartPose.offset(0.0F, 24.0F, -0.2727F));

		body.addOrReplaceChild("backlege", CubeListBuilder.create()
				.texOffs(8, 16).addBox(-2.0F, 2.0F, -4.0F, 2.0F, 1.0F, 2.0F)
				.texOffs(12, 16).addBox(-2.0F, -1.0F, -2.0F, 2.0F, 4.0F, 4.0F),
				PartPose.offset(-4.0F, -3.0F, 5.0F));

		body.addOrReplaceChild("backlegw", CubeListBuilder.create()
				.texOffs(0, 16).addBox(0.0F, -1.0F, -2.0F, 2.0F, 4.0F, 4.0F)
				.texOffs(24, 16).addBox(0.0F, 2.0F, -4.0F, 2.0F, 1.0F, 2.0F),
				PartPose.offset(4.0F, -3.0F, 5.0F));

		body.addOrReplaceChild("eyeballw", CubeListBuilder.create()
				.texOffs(18, 28).addBox(0.0F, 0.0F, -1.0F, 1.0F, 2.0F, 2.0F),
				PartPose.offset(2.5F, -9.5F, 0.5F));

		body.addOrReplaceChild("eyeballe", CubeListBuilder.create()
				.texOffs(24, 3).addBox(-1.0F, 0.0F, -1.0F, 1.0F, 2.0F, 2.0F),
				PartPose.offset(-2.5F, -9.5F, 0.5F));

		PartDefinition lips = body.addOrReplaceChild("lips", CubeListBuilder.create()
				, PartPose.offset(0.0F, -4.0F, -2.0F));

		lips.addOrReplaceChild("lip_bottom", CubeListBuilder.create()
				.texOffs(20, 18).addBox(-3.0F, 1.0F, -1.0F, 6.0F, 1.0F, 1.0F), PartPose.offset(0.0F, 0.0F, 0.0F));

		lips.addOrReplaceChild("lip_top", CubeListBuilder.create()
				.texOffs(20, 16).addBox(-3.0F, 0.0F, -1.0F, 6.0F, 1.0F, 1.0F), PartPose.offset(0.0F, 0.0F, 0.0F));


		PartDefinition frontlegw = body.addOrReplaceChild("frontlegw", CubeListBuilder.create()
				, PartPose.offset(4.0F, -5.0F, -1.0F));

		frontlegw.addOrReplaceChild("cube_r1", CubeListBuilder.create()
				.texOffs(22, 22).addBox(0.0F, 0.684F, -1.3794F, 2.0F, 6.0F, 2.0F)
				, PartPose.offsetAndRotation(0.0F, -1.0F, 1.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition frontlege = body.addOrReplaceChild("frontlege", CubeListBuilder.create()
				, PartPose.offset(-4.0F, -5.0F, -1.0F));

		frontlegw.addOrReplaceChild("cube_r2", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-2.0F, -6.0F, 0.0F, 2.0F, 6.0F, 2.0F)
				, PartPose.offsetAndRotation(0.0F, 5.0F, -2.0F, -0.3491F, 0.0F, 0.0F));

		body.addOrReplaceChild("tongue", CubeListBuilder.create()
				.texOffs(0, 30).addBox(-1.0F, -1.5F, -0.8F, 2.0F, 1.0F, 1.0F)
				, PartPose.offset(0.1F, 0.0F, -4.1273F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	public ToadEntityModel(ModelPart modelPart) {
		this.body = modelPart.getChild("body");
		this.backlege = body.getChild("backlege");
		this.backlegw = body.getChild("backlegw");
		this.eyeballw = body.getChild("eyeballw");
		this.eyeballe = body.getChild("eyeballe");
		this.lips = body.getChild("lips");
		this.lipBottom = lips.getChild("lip_bottom");
		this.lipTop = lips.getChild("lip_top");
		this.frontlegw = body.getChild("frontlegw");
		this.frontlege = body.getChild("frontlege");
		this.tongue = body.getChild("tongue");
	}

	@Override
	public void setupAnim(ToadEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch)
	{
		ToadEntityModel.entity = entity;
		float pi = (float) Math.PI;
		float legAmount = 1.4F;

		this.frontlege.xRot = Mth.cos(limbAngle * 1 + pi) * 1.4F * limbDistance;
		this.backlege.xRot = Mth.cos(limbAngle * 1) * legAmount * limbDistance;

		this.frontlegw.xRot = Mth.cos(limbAngle * 1) * 1.4F * limbDistance;
		this.backlegw.xRot = Mth.cos(limbAngle * 1 + pi) * legAmount * limbDistance;

		if(!entity.isOnGround())
		{
			this.backlegw.xRot = 2F;
			this.backlege.xRot = 2F;
		}

		if(entity.hasTongueEntity())
		{
			entity.mouthDistance = MathUtils.approachValue(entity.mouthDistance, 1, 0.5F);
			Entity e = entity.level.getEntity(entity.getTongueEntityID());
			if(e != null && entity.isTongueReady())
			{
				tongueDistance = (entity.distanceTo(e) * 16) - ((float) (e.getBoundingBox().maxX - e.getBoundingBox().minX) * 16F);
			}else tongueDistance = 0;
		}else
		{
			entity.mouthDistance = MathUtils.approachValue(entity.mouthDistance, 0, 0.10F);
		}
		lipTop.y = -entity.mouthDistance;
		lipBottom.y = entity.mouthDistance;

		tongue.xRot = -0.2618F + (headPitch * 0.0175F);
		tongue.yRot = headYaw * 0.0175F;
	}

	@Override
	public Iterable<ModelPart> parts()
	{
		return ImmutableList.of(body);
	}

	public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z)
	{
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}

	@Override
	public ModelPart getHead()
	{
		return tongue;
	}

	/*private static class TonguePart extends ModelPart
	{
		TonguePart(Model model)
		{
			super(model);
		}

		@Override
		public void render(PoseStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha)
		{
			ModelPart.Cube cube = getRandomCuboid(RandomUtil.RANDOM);
			matrices.pushPose();
			if(this.roll != 0.0F)
			{
				matrices.mulPose(Vector3f.ZP.rotation(this.roll));
			}

			if(this.yaw != 0.0F)
			{
				matrices.mulPose(Vector3f.YP.rotation(this.yaw));
			}

			if(this.pitch != 0.0F)
			{
				matrices.mulPose(Vector3f.XP.rotation(this.pitch));
			}


			drawBox(matrices, vertices, cube.minX, cube.minY, cube.minZ, cube.maxX, cube.maxY, cube.minZ - entity.tongueDistance, light, overlay);

			matrices.popPose();
			super.render(matrices, vertices, light, overlay, red, green, blue, alpha);
		}

		public static void drawBox(PoseStack matrices, VertexConsumer vertexConsumer, float x1, float y1, float z1, float x2, float y2, float z2, int light, int overlay)
		{
			Cuboid cuboid = new Cuboid(0, 30, x1, y1, z1, x2 - x1, y2 - y1, z2 - z1, 0, 0, 0, false, 64, 54);

			PoseStack.Pose me = matrices.last();
			Matrix4f matrix4f = me.pose();
			Matrix3f matrix3f = me.normal();

			ModelPart.Polygon[] var13 = ((CuboidAccessor) cuboid).bm_getSides();
			int var14 = var13.length;

			for(int var15 = 0; var15 < var14; ++var15)
			{
				ModelPart.Polygon quad = var13[var15];
				Vector3f vector3f = quad.normal.copy();
				vector3f.transform(matrix3f);

				for(int i = 0; i < 4; ++i)
				{
					ModelPart.Vertex vertex = quad.vertices[i];
					float j = vertex.pos.x() / 16.0F;
					float k = vertex.pos.y() / 16.0F;
					float l = vertex.pos.z() / 16.0F;
					Vector4f vector4f = new Vector4f(j, k, l, 1.0F);
					vector4f.transform(matrix4f);
					vertexConsumer.vertex(vector4f.x(), vector4f.y(), vector4f.z(), 1, 0, 0, 1, 0.1F, 0.1F, overlay, light, 1, 1, 1);
				}
			}
		}
	}*/
}
