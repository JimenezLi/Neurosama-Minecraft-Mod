package jimenezli.neurosama.client.renderer.entity;

import jimenezli.neurosama.NeurosamaMod;
import jimenezli.neurosama.client.renderer.model.NeurosamaModel;
import jimenezli.neurosama.entity.NeurosamaEntity;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NeurosamaRenderer extends BipedRenderer<NeurosamaEntity, NeurosamaModel<NeurosamaEntity>> {
    private static final ResourceLocation NEUROSAMA = new ResourceLocation(NeurosamaMod.ID, "textures/entity/neurosama.png");
    public NeurosamaRenderer(EntityRendererManager p_i46102_1_) {
        super(p_i46102_1_, new NeurosamaModel<>(0.0F), 0.5F);
        this.addLayer(new BipedArmorLayer<>(this, new NeurosamaModel<>(0.5F), new NeurosamaModel<>(1.0F)));
    }

    @Override
    public ResourceLocation getTextureLocation(NeurosamaEntity neurosama) {
        return NEUROSAMA;
    }
}
