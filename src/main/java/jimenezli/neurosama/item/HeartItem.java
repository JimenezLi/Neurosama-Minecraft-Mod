package jimenezli.neurosama.item;

import jimenezli.neurosama.handler.ItemHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class HeartItem extends Item {
    public static final Food HEART_FOOD = (new Food.Builder())
            .nutrition(1)
            .saturationMod(0.1F)
            .alwaysEat()
            .build();

    public HeartItem() {
        super(ItemHandler.defaultBuilder().food(HEART_FOOD));
    }

    /**
     * When the heart is consumed, it levels up player's current absorption effect (max level is 5).
     * Isn't it too overpowered?
     */
    public ItemStack finishUsingItem(ItemStack itemStack, World world, LivingEntity livingEntity) {
        ItemStack resultItemstack = super.finishUsingItem(itemStack, world, livingEntity);

        EffectInstance absorption = livingEntity.getEffect(Effects.ABSORPTION);
        if (absorption != null) {
            livingEntity.addEffect(new EffectInstance(Effects.ABSORPTION, 200, Math.min(absorption.getAmplifier() + 1, 4)));
        } else {
            livingEntity.addEffect(new EffectInstance(Effects.ABSORPTION, 200));
        }

        return resultItemstack;
    }
}
