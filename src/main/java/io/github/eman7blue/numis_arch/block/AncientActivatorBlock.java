package io.github.eman7blue.numis_arch.block;

import io.github.eman7blue.numis_arch.NumismaticArcheology;
import io.github.eman7blue.numis_arch.block.entity.AncientActivatorBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class AncientActivatorBlock extends BlockWithEntity {
    public static final BooleanProperty STRUCK_BY_LIGHTNING;

    public AncientActivatorBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(STRUCK_BY_LIGHTNING, false));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);

        if (world.getBlockEntity(pos) instanceof AncientActivatorBlockEntity blockEntity) {
            if (blockEntity.isEmpty() && itemStack.isIn(TagKey.of(RegistryKeys.ITEM, new Identifier("numis_arch", "can_be_lightning_activated")))) {
                if (!world.isClient()) {
                    blockEntity.insertStack(itemStack);
                    world.updateListeners(pos, state, state, Block.NOTIFY_LISTENERS);
                    if (!player.getAbilities().creativeMode)
                        itemStack.decrement(1);
                    player.incrementStat(Stats.USED.getOrCreateStat(itemStack.getItem()));
                }
            } else {
                ItemStack removedItem = blockEntity.removeStack();
                if (itemStack.isEmpty()) {
                    player.setStackInHand(hand, removedItem);
                } else if (!player.getInventory().insertStack(removedItem)) {
                    player.dropItem(removedItem, false);
                }
                BlockState updatedState = state.with(STRUCK_BY_LIGHTNING, false);
                world.setBlockState(pos, updatedState, 1);
                if (state.get(STRUCK_BY_LIGHTNING)) {
                    world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, updatedState));
                    world.playSound(null, pos, SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, SoundCategory.BLOCKS, 0.5F, 0.5F);
                }
            }
            world.updateComparators(pos, NumisArchBlocks.ANCIENT_ACTIVATOR);
            return ActionResult.success(world.isClient);
        }
        return ActionResult.PASS;
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AncientActivatorBlockEntity(pos, state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(STRUCK_BY_LIGHTNING);
    }

    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        AncientActivatorBlockEntity blockEntity = (AncientActivatorBlockEntity) world.getBlockEntity(pos);
        if (blockEntity == null || blockEntity.isEmpty()) {
            return 0;
        } else {
            return 1;
        }
    }

    static {
        STRUCK_BY_LIGHTNING = BooleanProperty.of("struck_by_lightning");
    }
}
