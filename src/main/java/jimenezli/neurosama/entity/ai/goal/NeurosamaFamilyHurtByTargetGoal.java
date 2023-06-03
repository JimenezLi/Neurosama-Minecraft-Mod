package jimenezli.neurosama.entity.ai.goal;

import jimenezli.neurosama.entity.DrawingFoxEntity;
import jimenezli.neurosama.entity.NeurosamaEntity;
import jimenezli.neurosama.entity.ProgrammingTurtleEntity;
import net.minecraft.entity.CreatureEntity;

public class NeurosamaFamilyHurtByTargetGoal extends FamilyHurtByTargetGoal{
    /**
     * If you attack Neurosama or her parents, you will be attacked by the whole family.
     */
    public NeurosamaFamilyHurtByTargetGoal(CreatureEntity p_i50317_1_, Class<?>... p_i50317_2_) {
        super(p_i50317_1_, p_i50317_2_);
        this.toAlert.add(NeurosamaEntity.class);
        this.toAlert.add(ProgrammingTurtleEntity.class);
        this.toAlert.add(DrawingFoxEntity.class);
    }
}
