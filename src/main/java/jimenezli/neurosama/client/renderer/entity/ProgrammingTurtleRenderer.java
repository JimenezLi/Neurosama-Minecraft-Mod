package jimenezli.neurosama.client.renderer.entity;

import jimenezli.neurosama.NeurosamaMod;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.TurtleRenderer;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.util.ResourceLocation;

public class ProgrammingTurtleRenderer extends TurtleRenderer {
    private static final ResourceLocation PROGRAMMING_TURTLE = new ResourceLocation(NeurosamaMod.ID, "textures/entity/programming_turtle.png");

    public ProgrammingTurtleRenderer(EntityRendererManager p_i48827_1_) {
        super(p_i48827_1_);
    }

    @Override
    public ResourceLocation getTextureLocation(TurtleEntity turtle) {
        return PROGRAMMING_TURTLE;
    }
}
