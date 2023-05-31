package jimenezli.neurosama.entity;

import jimenezli.neurosama.handler.ItemHandler;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class DrawingFoxEntity extends FoxEntity {
    public DrawingFoxEntity(EntityType<? extends FoxEntity> p_i50271_1_, World p_i50271_2_) {
        super(p_i50271_1_, p_i50271_2_);
    }

    public void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D, ProgrammingTurtleEntity.class));
    }

    public boolean isFood(ItemStack itemStack) {
        return super.isFood(itemStack) || itemStack.getItem() == ItemHandler.HEART.get();
    }

    public boolean canMate(AnimalEntity animal) {
        if (animal.getClass() != ProgrammingTurtleEntity.class) {
            return false;
        } else {
            return this.isInLove() && animal.isInLove();
        }
    }

    public FoxEntity getBreedOffspring(ServerWorld world, AgeableEntity ageable) {
        return null;
    }
}
