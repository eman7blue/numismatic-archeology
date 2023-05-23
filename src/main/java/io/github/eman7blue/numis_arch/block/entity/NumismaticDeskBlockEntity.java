package io.github.eman7blue.numis_arch.block.entity;

import io.github.eman7blue.numis_arch.item.CoinItem;
import io.github.eman7blue.numis_arch.recipe.NumismaticGradingRecipe;
import io.github.eman7blue.numis_arch.screen.NumismaticDeskScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class NumismaticDeskBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, Inventory {
    protected DefaultedList<ItemStack> inventory = DefaultedList.ofSize(13, ItemStack.EMPTY);
    protected static final int maxGradeTime = 20;
    protected int gradeTime;
    private final RecipeManager.MatchGetter<Inventory, NumismaticGradingRecipe> matchGetter;
    protected final PropertyDelegate propertyDelegate = new PropertyDelegate(){

        @Override
        public int get(int index) {
            if (index == 0) {
                return NumismaticDeskBlockEntity.this.gradeTime;
            }
            return 0;
        }

        @Override
        public void set(int index, int value) {
            if (index == 0) {
                NumismaticDeskBlockEntity.this.gradeTime = value;
            }
        }

        @Override
        public int size() {
            return 1;
        }
    };

    public NumismaticDeskBlockEntity(BlockPos pos, BlockState state) {
        super(NumisArchBlockEntityTypes.NUMISMATIC_DESK, pos, state);
        matchGetter = RecipeManager.createCachedMatchGetter(NumismaticGradingRecipe.Type.INSTANCE);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("container.numismatic_desk");
    }

    @Override
    public int size() {
        return inventory.size();
    }

    @Override
    public boolean isEmpty() {
        return inventory.isEmpty();
    }

    @Override
    public ItemStack getStack(int slot) {
        return inventory.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        return Inventories.splitStack(inventory, slot, amount);
    }

    @Override
    public ItemStack removeStack(int slot) {
        return Inventories.removeStack(inventory, slot);
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        inventory.set(slot, stack);
        if (stack.getCount() > stack.getMaxCount()) {
            stack.setCount(stack.getMaxCount());
        }
        if (slot == 0) {

        }
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return true;
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new NumismaticDeskScreenHandler(syncId, playerInventory, this, propertyDelegate);
    }

    @Override
    public boolean isValid(int slot, ItemStack stack) {
        if (stack.isIn(CoinItem.COIN_ITEM_TAG)) {
            if (slot == 0) {
                return CoinItem.isUnknown(stack);
            } else {
                return !CoinItem.isUnknown(stack);
            }
        } else {
            return false;
        }
    }

    @Override
    public void clear() {
        inventory.clear();
    }

    public static void tick(World world, BlockPos pos, BlockState state, NumismaticDeskBlockEntity blockEntity) {
        boolean dirty = false;
        boolean isNotEmpty = !blockEntity.inventory.get(0).isEmpty();
        if (isNotEmpty) {
            NumismaticGradingRecipe recipe = isNotEmpty ? blockEntity.matchGetter.getFirstMatch(blockEntity, world).orElse(null) : null;
            if (canGrade(recipe, blockEntity.inventory, world.getRegistryManager())) {
                blockEntity.gradeTime++;
                if (blockEntity.gradeTime == maxGradeTime) {
                    blockEntity.gradeTime = 0;
                    if (canGrade(recipe, blockEntity.inventory, world.getRegistryManager())) {
                        ItemStack itemStack = blockEntity.inventory.get(0);
                        ItemStack itemStackResult = recipe.getOutput(world.getRegistryManager());
                        if (NumismaticDeskBlockEntity.addToOutput(world, itemStackResult, recipe, blockEntity)) {
                            itemStack.decrement(1);
                        }
                    }
                }
                dirty = true;
            } else {
                blockEntity.gradeTime = 0;
            }
        }
        if (dirty) {
            NumismaticDeskBlockEntity.markDirty(world, pos, state);
        }
    }
    private static boolean canGrade(@Nullable Recipe<?> recipe, DefaultedList<ItemStack> inventory, DynamicRegistryManager registryManager) {
        if (inventory.get(0).isEmpty() || recipe == null) {
            return false;
        }
        ItemStack itemStack = recipe.getOutput(registryManager);
        if (itemStack.isEmpty()) {
            return false;
        }
        for (int i = 1; i < inventory.size(); i++) {
            if (inventory.get(i).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private static boolean addToOutput(World world, ItemStack itemStack, NumismaticGradingRecipe recipe, NumismaticDeskBlockEntity blockEntity) {
        NbtCompound nbt = itemStack.getOrCreateNbt();
        int max = recipe.getWeightSum();
        int rand = world.random.nextInt(max);
        if (rand < recipe.getSuperb()) {
            nbt.putInt("condition", CoinItem.Condition.SUPERB);
        } else if (rand < recipe.getSuperb() + recipe.getFine()) {
            nbt.putInt("condition", CoinItem.Condition.FINE);
        } else {
            nbt.putInt("condition", CoinItem.Condition.POOR);
        }
        for (int i = 1; i < 13; i++) {
            if (blockEntity.inventory.get(i).isEmpty()) {
                blockEntity.inventory.set(i, itemStack);
                return true;
            } else if (CoinItem.compareCondition(itemStack, blockEntity.inventory.get(i)) && ItemStack.canCombine(itemStack, blockEntity.inventory.get(i)) && blockEntity.inventory.get(i).getCount() < blockEntity.inventory.get(i).getMaxCount()) {
                blockEntity.inventory.get(i).increment(1);
                return true;
            }
        }
        return false;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
    }
}
