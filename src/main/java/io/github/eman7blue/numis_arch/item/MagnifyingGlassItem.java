package io.github.eman7blue.numis_arch.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BrushableBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class MagnifyingGlassItem extends Item {

    private static final int MAX_USE_TIME = 900;

    public MagnifyingGlassItem(Settings settings){
        super(settings);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BRUSH;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return MAX_USE_TIME;
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClient()) {
            BlockState oldState, newState;
            BlockHitResult blockHitResult = MagnifyingGlassItem.raycast(world, player, RaycastContext.FluidHandling.NONE);
            BlockPos pos = blockHitResult.getBlockPos();
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof BrushableBlockEntity brushableBlockEntity) {
                player.incrementStat(Stats.USED.getOrCreateStat(this));
                if (brushableBlockEntity.getItem().isEmpty()) {
                    oldState = world.getBlockState(pos);
                    brushableBlockEntity.generateItem(player);
                    newState = world.getBlockState(pos);
                    world.updateListeners(pos, oldState, newState, Block.NOTIFY_LISTENERS);
                }
            }
        }

        return ItemUsage.consumeHeldItem(world, player, hand);
    }
}
