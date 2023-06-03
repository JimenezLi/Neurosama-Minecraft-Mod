package jimenezli.neurosama.entity.ai.goal;

import jimenezli.neurosama.entity.DrawingFoxEntity;
import jimenezli.neurosama.entity.NeurosamaEntity;
import jimenezli.neurosama.entity.ProgrammingTurtleEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This extends HurtByTargetGoal to alert entities of a series of classes.
 */
public class FamilyHurtByTargetGoal extends HurtByTargetGoal {
    protected List<Class<? extends MobEntity>> toAlert = new ArrayList<>();

    /**
     * If you attack any entity in toAlert list, you will be attacked by all in toAlert list.
     */
    public FamilyHurtByTargetGoal(CreatureEntity p_i50317_1_, Class<?>... p_i50317_2_) {
        super(p_i50317_1_, p_i50317_2_);
        this.setAlertOthers();
    }

    /**
     * Alert all classes of entities in toAlert list.
     */
    protected void alertOthers(){
        double d0 = this.getFollowDistance();
        AxisAlignedBB axisalignedbb = AxisAlignedBB.unitCubeFromLowerCorner(this.mob.position()).inflate(d0, 10.0D, d0);
        List<MobEntity> list = new ArrayList<>();
        for (Class<? extends MobEntity> oclass: this.toAlert) {
            list.addAll(this.mob.level.getLoadedEntitiesOfClass(oclass, axisalignedbb));
        }
        Iterator iterator = list.iterator();

        while(true) {
            MobEntity mobentity;
            do {
                if (!iterator.hasNext()) {
                    return;
                }

                mobentity = (MobEntity) iterator.next();
            } while (this.mob == mobentity || mobentity.getTarget() != null || (this.mob instanceof TameableEntity && ((TameableEntity) this.mob).getOwner() != ((TameableEntity) mobentity).getOwner()) || mobentity.isAlliedTo(this.mob.getLastHurtByMob()));

            this.alertOther(mobentity, this.mob.getLastHurtByMob());
        }
    }
}
