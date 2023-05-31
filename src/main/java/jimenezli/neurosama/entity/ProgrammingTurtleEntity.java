package jimenezli.neurosama.entity;

import jimenezli.neurosama.handler.EntityHandler;
import jimenezli.neurosama.handler.ItemHandler;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class ProgrammingTurtleEntity extends TurtleEntity {
    public ProgrammingTurtleEntity(EntityType<? extends TurtleEntity> p_i50241_1_, World p_i50241_2_) {
        super(p_i50241_1_, p_i50241_2_);
    }

    public void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D, DrawingFoxEntity.class));
    }

    /**
     * Vedal won't drop any experience in case someone kills him on purpose.
     */
    protected boolean shouldDropExperience() {
        return false;
    }

    /**
     * Vedal won't drop any loot in case someone kills him on purpose.
     */
    protected boolean shouldDropLoot() {
        return false;
    }

    public boolean isFood(ItemStack itemStack) {
        return super.isFood(itemStack) || itemStack.getItem() == ItemHandler.HEART.get();
    }

    public boolean canMate(AnimalEntity animal) {
        if (animal.getClass() != DrawingFoxEntity.class) {
            return false;
        } else {
            return this.isInLove() && animal.isInLove();
        }
    }

    public NeurosamaEntity getBreedOffspring(ServerWorld world, AgeableEntity ageable) {
        return EntityHandler.neurosama.create(world);
    }
}
