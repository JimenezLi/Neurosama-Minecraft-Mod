package jimenezli.neurosama.client.renderer.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.entity.LivingEntity;

public class NeurosamaModel<M extends LivingEntity> extends PlayerModel<M> {
    public NeurosamaModel(float p_i46304_1_) {
        super(p_i46304_1_, true);
    }
}
