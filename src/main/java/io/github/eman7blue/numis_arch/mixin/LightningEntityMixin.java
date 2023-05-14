package io.github.eman7blue.numis_arch.mixin;

import io.github.eman7blue.numis_arch.block.NumisArchBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LightningRodBlock;
import net.minecraft.entity.LightningEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightningEntity.class)
public abstract class LightningEntityMixin {

    @Shadow protected abstract BlockPos getAffectedBlockPos();

    @Inject(method = "tick",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LightningEntity;cleanOxidation(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)V"))
    private void spawnThunderstone(CallbackInfo info) {
        BlockPos pos = this.getAffectedBlockPos();
        World world = ((LightningEntity)(Object)this).getWorld();
        BlockState state = world.getBlockState(pos);
        if (state.isOf(Blocks.LIGHTNING_ROD)) {
            pos = pos.offset(state.get(LightningRodBlock.FACING).getOpposite());
            state = world.getBlockState(pos);
        }
        if (state.isOf(Blocks.STONE)) {
            world.setBlockState(pos, NumisArchBlocks.THUNDERSTONE_BLOCK.getDefaultState());
        }

        int i = world.random.nextInt(3) + 2;
        Iterable<BlockPos> iterable = BlockPos.iterateRandomly(world.random, i, pos, 1);
        for(BlockPos blockPos: iterable) {
            BlockState blockState = world.getBlockState(blockPos);
            if (blockState.isOf(Blocks.STONE))
                world.setBlockState(blockPos, NumisArchBlocks.THUNDERSTONE_BLOCK.getDefaultState());
        }
    }
}
