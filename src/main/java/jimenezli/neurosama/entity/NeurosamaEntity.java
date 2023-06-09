package jimenezli.neurosama.entity;

import jimenezli.neurosama.entity.ai.goal.FamilyHurtByTargetGoal;
import jimenezli.neurosama.entity.ai.goal.NeurosamaFamilyHurtByTargetGoal;
import jimenezli.neurosama.handler.EntityHandler;
import jimenezli.neurosama.handler.ItemHandler;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.PathType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;

public class NeurosamaEntity extends AnimalEntity {
    private int heartTime = this.random.nextInt(6000) + 6000;

    public NeurosamaEntity(EntityType<? extends AnimalEntity> p_i48581_1_, World p_i48581_2_) {
        super(p_i48581_1_, p_i48581_2_);
    }

    /**
     * Neurosama always walk into lava when playing Minecraft, so she is called neuroLava.
     * That is why MoveToLavaGoal is put in first place.
     */
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new MoveToLavaGoal(this, 1.5D));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
        this.goalSelector.addGoal(7, new RandomWalkingGoal(this, 1.0D, 60));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookAtGoal(this, TurtleEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookAtGoal(this, FoxEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new NeurosamaFamilyHurtByTargetGoal(this));
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.ATTACK_DAMAGE, 1.0D);
    }

    /**
     * Neurosama produces heart like chicken produces egg.
     */
    public void aiStep() {
        super.aiStep();

        if (!this.level.isClientSide && this.isAlive() && !this.isBaby() && --this.heartTime <= 0) {
            this.spawnAtLocation(ItemHandler.HEART.get());
            this.heartTime = this.random.nextInt(6000) + 6000;
        }
    }

    public void readAdditionalSaveData(CompoundNBT p_70037_1_) {
        super.readAdditionalSaveData(p_70037_1_);
        if (p_70037_1_.contains("HeartProductionTime")) {
            this.heartTime = p_70037_1_.getInt("HeartProductionTime");
        }
    }

    public void addAdditionalSaveData(CompoundNBT p_213281_1_) {
        super.addAdditionalSaveData(p_213281_1_);
        p_213281_1_.putInt("HeartProductionTime", this.heartTime);
    }

    /**
     * Neurosama won't drop any experience in case someone kills her on purpose.
     */
    protected boolean shouldDropExperience() {
        return false;
    }

    /**
     * Neurosama won't drop any loot in case someone kills her on purpose.
     */
    protected boolean shouldDropLoot() {
        return false;
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
