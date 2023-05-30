package jimenezli.neurosama.entity;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;

public class NeurosamaEntity extends AgeableEntity {
    public NeurosamaEntity(EntityType<? extends AgeableEntity> p_i48581_1_, World p_i48581_2_) {
        super(p_i48581_1_, p_i48581_2_);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(7, new RandomWalkingGoal(this, 1.0D, 60));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookAtGoal(this, TurtleEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookAtGoal(this, FoxEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        return null;
    }
}
