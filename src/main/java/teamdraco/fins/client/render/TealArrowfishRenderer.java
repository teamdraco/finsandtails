package teamdraco.fins.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import teamdraco.fins.FinsAndTails;
import teamdraco.fins.client.model.TealArrowfishModel;
import teamdraco.fins.common.entities.TealArrowfishEntity;

public class TealArrowfishRenderer extends MobRenderer<TealArrowfishEntity, TealArrowfishModel<TealArrowfishEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/teal_arrowfish.png");

    public TealArrowfishRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new TealArrowfishModel<>(), 0.3F);
    }

    public ResourceLocation getTextureLocation(TealArrowfishEntity entity) {
        return TEXTURE;
    }

    protected void setupRotations(TealArrowfishEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        float f = 4.3F * MathHelper.sin(0.6F * ageInTicks);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(f));
        if (!entityLiving.isInWater()) {
            matrixStackIn.translate((double)0.1F, (double)0.1F, (double)-0.1F);
            matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
        }
    }
}