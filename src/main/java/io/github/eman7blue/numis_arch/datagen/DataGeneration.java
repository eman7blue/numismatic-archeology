package io.github.eman7blue.numis_arch.datagen;

import io.github.eman7blue.numis_arch.block.NumisArchBlocks;
import io.github.eman7blue.numis_arch.item.NumisArchItems;
import io.github.eman7blue.numis_arch.loottable.NumisArchLootTables;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantRandomlyLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class DataGeneration implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(NumisArchItemTagGenerator::new);
        pack.addProvider(NumisArchBlockTagGenerator::new);
        pack.addProvider(AdvancementsProvider::new);
        pack.addProvider(NumisArchLootTableGenerator::new);
    }

    private static class NumisArchLootTableGenerator extends SimpleFabricLootTableProvider {
        public NumisArchLootTableGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, LootContextTypes.CHEST);
        }


        @Override
        public void accept(BiConsumer<Identifier, LootTable.Builder> identifierBuilderBiConsumer) {
            identifierBuilderBiConsumer.accept(NumisArchLootTables.BURIED_HOARD_LOOT, LootTable.builder()
                .pool(LootPool.builder().rolls(UniformLootNumberProvider.create(6.0F, 12.0F))
                        .with(ItemEntry.builder(Items.GOLD_NUGGET).weight(10))
                        .with(ItemEntry.builder(Items.GOLD_INGOT).weight(5))
                        .with(ItemEntry.builder(Items.DIAMOND))
                        .with(ItemEntry.builder(Items.EMERALD).weight(3))
                        .with(ItemEntry.builder(Items.SADDLE).weight(2))
                        .with(ItemEntry.builder(Items.CLOCK).weight(2))
                        .with(ItemEntry.builder(Items.IRON_AXE).apply(EnchantRandomlyLootFunction.builder()))
                        .with(ItemEntry.builder(Items.IRON_SHOVEL).apply(EnchantRandomlyLootFunction.builder()))
                        .with(ItemEntry.builder(Items.IRON_PICKAXE).apply(EnchantRandomlyLootFunction.builder()))
                        .with(ItemEntry.builder(Items.IRON_SWORD).apply(EnchantRandomlyLootFunction.builder()))
                        .with(ItemEntry.builder(Items.IRON_HOE).apply(EnchantRandomlyLootFunction.builder()))
                ));
            identifierBuilderBiConsumer.accept(NumisArchLootTables.BURIED_HOARD_DESERT_ARCHEOLOGY, LootTable.builder()
                .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F))
                        .with(ItemEntry.builder(Items.GOLD_NUGGET))
                        .with(ItemEntry.builder(Items.GOLD_INGOT))
                        .with(ItemEntry.builder(Items.DIAMOND))
                        .with(ItemEntry.builder(Items.EMERALD))
                        .with(ItemEntry.builder(Items.GUNPOWDER))
                        .with(ItemEntry.builder(Items.STICK))
                        .with(ItemEntry.builder(NumisArchItems.BEE_COIN).weight(3))
                ));
            identifierBuilderBiConsumer.accept(NumisArchLootTables.BURIED_HOARD_BADLANDS_ARCHEOLOGY, LootTable.builder()
                    .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F))
                            .with(ItemEntry.builder(Items.GOLD_INGOT).weight(2))
                            .with(ItemEntry.builder(Items.GOLDEN_AXE).apply(EnchantRandomlyLootFunction.builder()))
                            .with(ItemEntry.builder(Items.GOLDEN_SHOVEL).apply(EnchantRandomlyLootFunction.builder()))
                            .with(ItemEntry.builder(Items.GOLDEN_PICKAXE).apply(EnchantRandomlyLootFunction.builder()))
                            .with(ItemEntry.builder(Items.GOLDEN_SWORD).apply(EnchantRandomlyLootFunction.builder()))
                            .with(ItemEntry.builder(Items.GOLDEN_HOE).apply(EnchantRandomlyLootFunction.builder()))
                            .with(ItemEntry.builder(Items.EMERALD).weight(3))
                            .with(ItemEntry.builder(Items.STICK).weight(3))
                            .with(ItemEntry.builder(NumisArchItems.ANIMAL_COIN).weight(5))
                    ));
            identifierBuilderBiConsumer.accept(NumisArchLootTables.BASTION_STABLE_ARCHEOLOGY, LootTable.builder()
                    .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F))
                            .with(ItemEntry.builder(Items.GOLD_INGOT))
                            .with(ItemEntry.builder(Items.GOLDEN_AXE).apply(EnchantRandomlyLootFunction.builder()))
                            .with(ItemEntry.builder(Items.GOLDEN_SHOVEL).apply(EnchantRandomlyLootFunction.builder()))
                            .with(ItemEntry.builder(Items.GOLDEN_HOE).apply(EnchantRandomlyLootFunction.builder()))
                            .with(ItemEntry.builder(Items.GOLD_BLOCK))
                            .with(ItemEntry.builder(Items.NETHER_WART).weight(3))
                            .with(ItemEntry.builder(Items.NETHER_BRICK))
                            .with(ItemEntry.builder(Items.CRIMSON_FUNGUS))
                    ));
            identifierBuilderBiConsumer.accept(NumisArchLootTables.BASTION_HOUSING_ARCHEOLOGY, LootTable.builder()
                    .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F))
                            .with(ItemEntry.builder(Items.GOLD_INGOT).weight(3))
                            .with(ItemEntry.builder(Items.GOLD_BLOCK))
                            .with(ItemEntry.builder(Items.GOLD_NUGGET).weight(2))
                            .with(ItemEntry.builder(Items.NETHER_WART).weight(2))
                            .with(ItemEntry.builder(Items.BLAZE_POWDER))
                            .with(ItemEntry.builder(NumisArchItems.PIGLIN_COIN).weight(2))
                    ));
            identifierBuilderBiConsumer.accept(NumisArchLootTables.BASTION_GARDEN_ARCHEOLOGY, LootTable.builder()
                    .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F))
                            .with(ItemEntry.builder(Items.GOLDEN_CARROT))
                            .with(ItemEntry.builder(Items.GOLDEN_APPLE))
                            .with(ItemEntry.builder(Items.NETHER_WART))
                            .with(ItemEntry.builder(Items.CRIMSON_FUNGUS))
                            .with(ItemEntry.builder(Items.WARPED_FUNGUS))
                            .with(ItemEntry.builder(Items.NETHER_BRICK))
                            .with(ItemEntry.builder(Items.GOLDEN_HOE).apply(EnchantRandomlyLootFunction.builder()).weight(2))
                    ));
        }
    }

    private static class NumisArchItemTagGenerator extends FabricTagProvider<Item> {
        public NumisArchItemTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, RegistryKeys.ITEM, registriesFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup registries) {
            getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("numis_arch", "coins")))
                    .add(NumisArchItems.ANIMAL_COIN)
                    .add(NumisArchItems.BEE_COIN)
                    .add(NumisArchItems.ENDER_COIN)
                    .add(NumisArchItems.PARROT_COIN)
                    .add(NumisArchItems.PIGLIN_COIN)
                    .add(NumisArchItems.SNIFFER_COIN)
                    .add(NumisArchItems.TURTLE_COIN)
                    .add(NumisArchItems.VILLAGER_COIN);
        }
    }

    private static class NumisArchBlockTagGenerator extends FabricTagProvider<Block> {
        public NumisArchBlockTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, RegistryKeys.BLOCK, registriesFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup registries) {
            getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, new Identifier("numis_arch", "brushable")))
                    .add(Blocks.SUSPICIOUS_SAND)
                    .add(Blocks.GRAVEL)
                    .add(NumisArchBlocks.SUSPICIOUS_RED_SAND)
                    .add(NumisArchBlocks.SUSPICIOUS_SOUL_SAND)
                    .add(NumisArchBlocks.SUSPICIOUS_END_STONE);
            getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, new Identifier("numis_arch", "nether_wart_plantable")))
                    .add(Blocks.SOUL_SAND)
                    .add(NumisArchBlocks.SUSPICIOUS_SOUL_SAND);
        }
    }
}

