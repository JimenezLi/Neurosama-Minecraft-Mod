package jimenezli.neurosama.entity;

import jimenezli.neurosama.handler.EntityHandler;
import jimenezli.neurosama.handler.ItemHandler;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;

public class NeurosamaEntity extends AnimalEntity {
    public NeurosamaEntity(EntityType<? extends AnimalEntity> p_i48581_1_, World p_i48581_2_) {
        super(p_i48581_1_, p_i48581_2_);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new RandomWalkingGoal(this, 1.0D, 60));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookAtGoal(this, TurtleEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookAtGoal(this, FoxEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
    }

//    public boolean isFood(ItemStack itemStack) {
//        return itemStack.getItem() == ItemHandler.ITEM_IRONMILK;
//    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity p_241840_2_) {
        return EntityHandler.neurosama.create(world);
    }
}
