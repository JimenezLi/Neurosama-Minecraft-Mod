package jimenezli.neurosama.client.renderer.entity;

import jimenezli.neurosama.NeurosamaMod;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.FoxRenderer;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.util.ResourceLocation;

public class DrawingFoxRenderer extends FoxRenderer {
    private static final ResourceLocation DRAWING_FOX = new ResourceLocation(NeurosamaMod.ID, "textures/entity/drawing_fox.png");
    private static final ResourceLocation DRAWING_FOX_SLEEP = new ResourceLocation(NeurosamaMod.ID, "textures/entity/drawing_fox_sleep.png");;

    public DrawingFoxRenderer(EntityRendererManager p_i50969_1_) {
        super(p_i50969_1_);
    }

    public ResourceLocation getTextureLocation(FoxEntity fox) {
        return fox.isSleeping() ? DRAWING_FOX_SLEEP : DRAWING_FOX;
    }
}
