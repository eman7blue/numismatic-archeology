package io.github.eman7blue.numis_arch.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SuspiciousSandBlock;
import net.minecraft.block.entity.SuspiciousSandBlockEntity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class SuspiciousEndStoneBlock extends SuspiciousSandBlock {


    public SuspiciousEndStoneBlock(Block block, Settings settings, SoundEvent soundEvent, SoundEvent soundEvent2) {
        super(block, settings, soundEvent, soundEvent2);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getBlockEntity(pos) instanceof SuspiciousSandBlockEntity blockEntity)
            blockEntity.scheduledTick();
    }

    @Override
    public void onDestroyedOnLanding(World world, BlockPos pos, FallingBlockEntity fallingBlockEntity) {}

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {}
}
