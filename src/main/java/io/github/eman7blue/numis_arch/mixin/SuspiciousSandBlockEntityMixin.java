package io.github.eman7blue.numis_arch.mixin;

import io.github.eman7blue.numis_arch.block.NumisArchBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.SuspiciousSandBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SuspiciousSandBlockEntity.class)
public abstract class SuspiciousSandBlockEntityMixin {

    @Redirect(method = "brush", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/World;scheduleBlockTick(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/Block;I)V"))
    private void replacedScheduleBlockTickInBrush(World world, BlockPos blockPos, Block block, int i){
        world.scheduleBlockTick(blockPos, world.getBlockEntity(blockPos).getCachedState().getBlock(), i);
    }

    @Redirect(method = "scheduledTick", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/World;scheduleBlockTick(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/Block;I)V"))
    private void replacedScheduleBlockTickInScheduleTick(World world, BlockPos blockPos, Block block, int i){
        world.scheduleBlockTick(blockPos, world.getBlockEntity(blockPos).getCachedState().getBlock(), i);
    }

    @Redirect(method = "finishBrushing",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"))
    private boolean replacedBrushableBlock(World world, BlockPos pos, BlockState state, int flags) {
        BlockState currentBlock = world.getBlockState(pos);
        if(currentBlock.isOf(Blocks.SUSPICIOUS_SAND)) {
            return world.setBlockState(pos, Blocks.SAND.getDefaultState(), flags);
        } else if(currentBlock.isOf(NumisArchBlocks.SUSPICIOUS_RED_SAND)) {
            return world.setBlockState(pos, Blocks.RED_SAND.getDefaultState(), flags);
        } else if(currentBlock.isOf(NumisArchBlocks.SUSPICIOUS_GRAVEL)) {
            return world.setBlockState(pos, Blocks.GRAVEL.getDefaultState(), flags);
        } else if(currentBlock.isOf(NumisArchBlocks.SUSPICIOUS_SOUL_SAND)) {
            return world.setBlockState(pos, Blocks.SOUL_SAND.getDefaultState(), flags);
        } else if(currentBlock.isOf(NumisArchBlocks.SUSPICIOUS_END_STONE)) {
            return world.setBlockState(pos, Blocks.END_STONE.getDefaultState(), flags);
        }else {
            return false;
        }

    }
}
