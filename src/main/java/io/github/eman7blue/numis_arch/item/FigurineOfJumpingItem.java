package io.github.eman7blue.numis_arch.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
public class FigurineOfJumpingItem extends Item {

    public FigurineOfJumpingItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        boolean bl = true;
        for (StatusEffectInstance status : user.getStatusEffects()) {
            if (status.getEffectType().equals(StatusEffects.JUMP_BOOST)) {
                bl = false;
                int duration = status.getDuration() + 1800;
                if (status.getDuration() <= 10800) {
                    duration = status.getDuration();
                }
                if (status.getAmplifier() == 0) {
                    status.upgrade(new StatusEffectInstance(StatusEffects.JUMP_BOOST, duration, 1));
                } else if (status.getAmplifier() == 1) {
                    status.upgrade(new StatusEffectInstance(StatusEffects.JUMP_BOOST, duration, 2));
                } else {
                    status.upgrade(new StatusEffectInstance(StatusEffects.JUMP_BOOST, duration, status.getAmplifier()));
                }
                user.getStackInHand(hand).damage(1, user, pl -> pl.sendToolBreakStatus(hand));
            }
        }
        if (bl) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 1800, 0));
            user.getStackInHand(hand).damage(1, user, pl -> pl.sendToolBreakStatus(hand));
        }
        return ItemUsage.consumeHeldItem(world, user, hand);
    }
}
