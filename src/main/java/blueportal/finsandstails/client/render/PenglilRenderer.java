
package blueportal.finsandstails.client.render;

import blueportal.finsandstails.client.render.layer.PenglilCarryingItemLayer;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import blueportal.finsandstails.FinsAndTails;
import blueportal.finsandstails.client.model.PenglilModel;
import blueportal.finsandstails.common.entities.PenglilEntity;
import blueportal.finsandstails.client.FTModelLayers;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class PenglilRenderer extends MobRenderer<PenglilEntity, PenglilModel<PenglilEntity>> {
    public static final Map<Integer, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (hashMap) -> {
        hashMap.put(0, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/penglil/penglil_1.png"));
        hashMap.put(1, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/penglil/penglil_2.png"));
        hashMap.put(2, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/penglil/penglil_3.png"));
        hashMap.put(3, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/penglil/penglil_4.png"));
        hashMap.put(4, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/penglil/penglil_5.png"));
        hashMap.put(5, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/penglil/penglil_6.png"));
        hashMap.put(6, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/penglil/penglil_7.png"));
        hashMap.put(7, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/penglil/penglil_8.png"));
        hashMap.put(8, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/penglil/penglil_lord.png"));
        hashMap.put(9, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/penglil/penglil_pomegranits.png"));
        hashMap.put(10, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/penglil/penglil_sus.png"));
    });

    public PenglilRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new PenglilModel<>(ctx.bakeLayer(FTModelLayers.PENGLIL)), 0.2F);
        addLayer(new PenglilCarryingItemLayer(this, ctx.getItemInHandRenderer()));
    }

    @Override
    public void render(PenglilEntity penglil, float p_115456_, float p_115457_, PoseStack p_115458_, MultiBufferSource p_115459_, int p_115460_) {
        Minecraft mc = Minecraft.getInstance();

        if (penglil.isPassenger() && penglil.getVehicle() instanceof Player player) {
            if (player.is(mc.player) && mc.options.getCameraType().isFirstPerson()) {
                return;
            }
        }

        super.render(penglil, p_115456_, p_115457_, p_115458_, p_115459_, p_115460_);
    }

    @Override
    public ResourceLocation getTextureLocation(PenglilEntity entity) {
        String s = entity.getName().getString();

        return switch (s) {
            case "Lord", "Lord Penglil", "Lord_Penglil" -> TEXTURES.get(8);
            case "Pomegranits" -> TEXTURES.get(9);
            case "Sus", "Amogus", "Impostor", "Among Us" -> TEXTURES.get(10);
            default -> TEXTURES.getOrDefault(entity.getVariant(), TEXTURES.get(0));
        };
    }
}
