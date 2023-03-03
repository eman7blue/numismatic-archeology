package io.github.eman7blue.numis_arch.worldgen.feature;

import com.mojang.serialization.Codec;
import io.github.eman7blue.numis_arch.loottable.NumisArchLootTables;
import net.minecraft.block.BarrelBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.predicate.block.BlockStatePredicate;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class BuriedHoardFeature extends Feature<BuriedHoardFeatureConfig> {

    public BuriedHoardFeature(Codec<BuriedHoardFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<BuriedHoardFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();
        Random random = context.getRandom();
        BlockPos testPos = new BlockPos(origin);

        for(testPos = testPos.up(); world.isAir(testPos) && testPos.getY() > world.getBottomY() + 2; testPos = testPos.down()) {}
        if (BlockStatePredicate.forBlock(Blocks.SAND).test(world.getBlockState(testPos))) {
            BlockPos finalTestPos = testPos;
            Iterable<BlockPos> iterable = BlockPos.iterateRandomly(random, 15, testPos.down(), 3);
            for(BlockPos pos: iterable ) {
                if(!pos.isWithinDistance(testPos.down().toCenterPos(), 2.0)) {
                    if(BlockStatePredicate.forBlock(Blocks.SAND).test(world.getBlockState(pos.up()))
                            && !BlockStatePredicate.forBlock(Blocks.AIR).test(world.getBlockState(pos.down()))) {
                        world.setBlockState(pos, Blocks.BARREL.getDefaultState().with(BarrelBlock.FACING, Direction.UP), 2);
                        world.getBlockEntity(pos, BlockEntityType.BARREL).ifPresent((barrelBlockEntity ->
                                barrelBlockEntity.setLootTable(NumisArchLootTables.BURIED_HOARD_LOOT, pos.asLong())));
                        world.setBlockState(testPos.up(), Blocks.DEAD_BUSH.getDefaultState(), 2);
                        if (world.getEnabledFeatures().contains(FeatureFlags.UPDATE_1_20)) {
                            world.setBlockState(testPos, Blocks.SUSPICIOUS_SAND.getDefaultState(), 2);
                            world.getBlockEntity(testPos, BlockEntityType.SUSPICIOUS_SAND).ifPresent((blockEntity) ->
                                    blockEntity.setLootTable(NumisArchLootTables.BURIED_HOARD_ARCHEOLOGY, finalTestPos.asLong()));
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }


}
