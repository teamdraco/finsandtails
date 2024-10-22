package teamdraco.finsandstails.registry;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import teamdraco.finsandstails.FinsAndTails;

public class FTModelLayers {

    public static final ModelLayerLocation BANDED_REDBACK_SHRIMP = main("banded_redback_shrimp");
    public static final ModelLayerLocation GOLDEN_RIVER_RAY = main("golden_river_ray");
    public static final ModelLayerLocation MUDHORSE = main("mudhorse");
    public static final ModelLayerLocation ORNATE_BUGFISH = main("ornate_bugfish");
    public static final ModelLayerLocation PHANTOM_NUDIBRANCH = main("phantom_nudibranch");
    public static final ModelLayerLocation SWAMP_MUCKER = main("swamp_mucker");
    public static final ModelLayerLocation TEAL_ARROWFISH = main("teal_arrowfish");
    public static final ModelLayerLocation TEAL_ARROWFISH_ARROW = main("teal_arrowfish_arrow");

    private static ModelLayerLocation register(String id, String name) {
        return new ModelLayerLocation(new ResourceLocation(FinsAndTails.MOD_ID, id), name);
    }
    private static ModelLayerLocation main(String id) {
        return register(id, "main");
    }
}
