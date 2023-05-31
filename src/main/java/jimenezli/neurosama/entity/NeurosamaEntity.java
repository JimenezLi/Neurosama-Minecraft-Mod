package jimenezli.neurosama.entity;

import jimenezli.neurosama.handler.EntityHandler;
import jimenezli.neurosama.handler.ItemHandler;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.passive.StriderEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;

public class NeurosamaEntity extends AnimalEntity {
    public NeurosamaEntity(EntityType<? extends AnimalEntity> p_i48581_1_, World p_i48581_2_) {
        super(p_i48581_1_, p_i48581_2_);
    }

    /**
     * Neurosama always walk into lava when playing Minecraft, so she is called neuroLava.
     * That is why MoveToLavaGoal is put in first place.
     */
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new MoveToLavaGoal(this, 1.5D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new RandomWalkingGoal(this, 1.0D, 60));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookAtGoal(this, TurtleEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookAtGoal(this, FoxEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
    }

    public boolean isFood(ItemStack itemStack) {
        return itemStack.getItem() == ItemHandler.IRONMILK.get();
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity p_241840_2_) {
        return EntityHandler.neurosama.create(world);
    }

    /**
     * This MoveToLavaGoal is copied from strider.
     */
    static class MoveToLavaGoal extends MoveToBlockGoal {
        private final NeurosamaEntity neurosama;

        private MoveToLavaGoal(NeurosamaEntity p_i241913_1_, double p_i241913_2_) {
            super(p_i241913_1_, p_i241913_2_, 8, 2);
            this.neurosama = p_i241913_1_;
        }

        public BlockPos getMoveToTarget() {
            return this.blockPos;
        }

        public boolean canContinueToUse() {
            return !this.neurosama.isInLava() && this.isValidTarget(this.neurosama.level, this.blockPos);
        }

        public boolean canUse() {
            return !this.neurosama.isInLava() && super.canUse();
        }

        public boolean shouldRecalculatePath() {
            return this.tryTicks % 20 == 0;
        }

        protected boolean isValidTarget(IWorldReader p_179488_1_, BlockPos p_179488_2_) {
            return p_179488_1_.getBlockState(p_179488_2_).is(Blocks.LAVA) && p_179488_1_.getBlockState(p_179488_2_.above()).isPathfindable(p_179488_1_, p_179488_2_, PathType.LAND);
        }
    }
}
