package io.github.eman7blue.numis_arch.datagen;

import io.github.eman7blue.numis_arch.advancements.NumisArchAdvancements;
import io.github.eman7blue.numis_arch.block.NumisArchBlocks;
import io.github.eman7blue.numis_arch.item.NumisArchItems;
import io.github.eman7blue.numis_arch.loottable.NumisArchLootTables;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantRandomlyLootFunction;
import net.minecraft.loot.function.LimitCountLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.operator.BoundedIntUnaryOperator;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.poi.PointOfInterestType;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static io.github.eman7blue.numis_arch.NumismaticArcheology.id;

public class DataGeneration implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(NumisArchItemTagGenerator::new);
        pack.addProvider(NumisArchBlockTagGenerator::new);
        pack.addProvider(NumisArchAdvancementsProvider::new);
        pack.addProvider(NumisArchLootTableGenerator::new);
        pack.addProvider(NumisArchPOITagGenerator::new);
        pack.addProvider(NumisArchRecipeGenerator::new);
    }

    private static class NumisArchAdvancementsProvider extends FabricAdvancementProvider {
        protected NumisArchAdvancementsProvider(FabricDataOutput dataGen) {
            super(dataGen);
        }

        @Override
        public void generateAdvancement(Consumer<AdvancementEntry> consumer) {
            new NumisArchAdvancements().accept(consumer);
        }
    }


    private static class NumisArchLootTableGenerator extends SimpleFabricLootTableProvider {
        public NumisArchLootTableGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, LootContextTypes.CHEST);
        }


        @Override
        public void accept(BiConsumer<Identifier, LootTable.Builder> identifierBuilderBiConsumer) {
            identifierBuilderBiConsumer.accept(NumisArchLootTables.VILLAGE_ZOMBIE_DIG_SITE_ARCHEOLOGY, LootTable.builder().type(LootContextTypes.ARCHAEOLOGY)
                    .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F))
                            .with(ItemEntry.builder(Items.ROTTEN_FLESH).weight(18))
                            .with(ItemEntry.builder(Items.CHARCOAL).weight(9))
                            .with(ItemEntry.builder(Items.COBWEB).weight(9))
                            .with(ItemEntry.builder(Items.SKELETON_SKULL))
                            .with(ItemEntry.builder(Items.BONE).weight(45))
                    ));
            identifierBuilderBiConsumer.accept(NumisArchLootTables.VILLAGE_DIG_SITE_ARCHEOLOGY, LootTable.builder().type(LootContextTypes.ARCHAEOLOGY)
                    .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F))
                            .with(ItemEntry.builder(Items.BRUSH).apply(LimitCountLootFunction.builder(BoundedIntUnaryOperator.create(1))))
                            .with(ItemEntry.builder(Items.STICK).weight(3))
                            .with(ItemEntry.builder(Items.BOWL).weight(2))
                            .with(ItemEntry.builder(Items.CHARCOAL).weight(4))
                            .with(ItemEntry.builder(Items.FLOWER_POT).weight(2))
                            .with(ItemEntry.builder(Items.PAINTING))
                            .with(ItemEntry.builder(Items.PUMPKIN_SEEDS))
                            .with(ItemEntry.builder(Items.MELON_SEEDS))
                            .with(ItemEntry.builder(Items.BRICK))
                            .with(ItemEntry.builder(Items.BONE).weight(2))
                            .with(ItemEntry.builder(Items.EMERALD).weight(3))
                            .with(ItemEntry.builder(Items.CHAIN))
                            .with(ItemEntry.builder(NumisArchItems.VILLAGER_COIN))
                    ));
            identifierBuilderBiConsumer.accept(NumisArchLootTables.VILLAGE_ARCHEOLOGIST, LootTable.builder().type(LootContextTypes.CHEST)
                    .pool(LootPool.builder().rolls(UniformLootNumberProvider.create(9.0F, 14.0F))
                            .with(ItemEntry.builder(Items.IRON_SHOVEL).apply(LimitCountLootFunction.builder(BoundedIntUnaryOperator.create(1))))
                            .with(ItemEntry.builder(Items.BRUSH).apply(LimitCountLootFunction.builder(BoundedIntUnaryOperator.create(1))))
                            .with(ItemEntry.builder(Items.SPYGLASS).apply(LimitCountLootFunction.builder(BoundedIntUnaryOperator.create(1))))
                            .with(ItemEntry.builder(Items.COMPASS).apply(LimitCountLootFunction.builder(BoundedIntUnaryOperator.create(1))))
                            .with(ItemEntry.builder(Items.FLINT).weight(4))
                            .with(ItemEntry.builder(Items.STICK).weight(2))
                            .with(ItemEntry.builder(Items.GOLD_INGOT))
                            .with(ItemEntry.builder(Items.EMERALD).weight(3))
                    ));
            identifierBuilderBiConsumer.accept(NumisArchLootTables.END_TEMPLE_ARCHEOLOGY, LootTable.builder().type(LootContextTypes.ARCHAEOLOGY)
                    .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F))
                            .with(ItemEntry.builder(Items.DIAMOND))
                            .with(ItemEntry.builder(Items.DIAMOND_SHOVEL).apply(EnchantRandomlyLootFunction.builder()))
                            .with(ItemEntry.builder(Items.DIAMOND_PICKAXE).apply(EnchantRandomlyLootFunction.builder()))
                            .with(ItemEntry.builder(Items.ENDER_PEARL).weight(2))
                            .with(ItemEntry.builder(NumisArchItems.ENDER_COIN).weight(2))
                    ));
            identifierBuilderBiConsumer.accept(NumisArchLootTables.BURIED_HOARD_LOOT, LootTable.builder().type(LootContextTypes.CHEST)
                .pool(LootPool.builder().rolls(UniformLootNumberProvider.create(12.0F, 18.0F))
                        .with(ItemEntry.builder(Items.GOLD_NUGGET).weight(10).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(6.0F, 15.0F))))
                        .with(ItemEntry.builder(Items.GOLD_INGOT).weight(5).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 8.0F))))
                        .with(ItemEntry.builder(Items.DIAMOND).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
                        .with(ItemEntry.builder(Items.EMERALD).weight(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
                        .with(ItemEntry.builder(Items.SADDLE).weight(2))
                        .with(ItemEntry.builder(Items.CLOCK).weight(2))
                        .with(ItemEntry.builder(Items.IRON_AXE).apply(EnchantRandomlyLootFunction.builder()).apply(LimitCountLootFunction.builder(BoundedIntUnaryOperator.create(1))))
                        .with(ItemEntry.builder(Items.IRON_SHOVEL).apply(EnchantRandomlyLootFunction.builder()).apply(LimitCountLootFunction.builder(BoundedIntUnaryOperator.create(1))))
                        .with(ItemEntry.builder(Items.IRON_PICKAXE).apply(EnchantRandomlyLootFunction.builder()).apply(LimitCountLootFunction.builder(BoundedIntUnaryOperator.create(1))))
                        .with(ItemEntry.builder(Items.IRON_SWORD).apply(EnchantRandomlyLootFunction.builder()).apply(LimitCountLootFunction.builder(BoundedIntUnaryOperator.create(1))))
                        .with(ItemEntry.builder(Items.IRON_HOE).apply(EnchantRandomlyLootFunction.builder()).apply(LimitCountLootFunction.builder(BoundedIntUnaryOperator.create(1))))
                ));
            identifierBuilderBiConsumer.accept(NumisArchLootTables.BURIED_HOARD_DESERT_ARCHEOLOGY, LootTable.builder().type(LootContextTypes.ARCHAEOLOGY)
                .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F))
                        .with(ItemEntry.builder(Items.GOLD_NUGGET))
                        .with(ItemEntry.builder(Items.GOLD_INGOT))
                        .with(ItemEntry.builder(Items.DIAMOND))
                        .with(ItemEntry.builder(Items.EMERALD))
                        .with(ItemEntry.builder(Items.GUNPOWDER))
                        .with(ItemEntry.builder(Items.STICK))
                        .with(ItemEntry.builder(NumisArchItems.BEE_COIN).weight(3))
                ));
            identifierBuilderBiConsumer.accept(NumisArchLootTables.BURIED_HOARD_BADLANDS_ARCHEOLOGY, LootTable.builder().type(LootContextTypes.ARCHAEOLOGY)
                    .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F))
                            .with(ItemEntry.builder(Items.GOLD_INGOT).weight(2))
                            .with(ItemEntry.builder(Items.GOLDEN_AXE).apply(EnchantRandomlyLootFunction.builder()))
                            .with(ItemEntry.builder(Items.GOLDEN_SHOVEL).apply(EnchantRandomlyLootFunction.builder()))
                            .with(ItemEntry.builder(Items.GOLDEN_PICKAXE).apply(EnchantRandomlyLootFunction.builder()))
                            .with(ItemEntry.builder(Items.GOLDEN_SWORD).apply(EnchantRandomlyLootFunction.builder()))
                            .with(ItemEntry.builder(Items.GOLDEN_HOE).apply(EnchantRandomlyLootFunction.builder()))
                            .with(ItemEntry.builder(Items.EMERALD).weight(3))
                            .with(ItemEntry.builder(Items.STICK).weight(3))
                            .with(ItemEntry.builder(NumisArchItems.RABBIT_COIN).weight(5))
                    ));
            identifierBuilderBiConsumer.accept(NumisArchLootTables.BASTION_STABLE_ARCHEOLOGY, LootTable.builder().type(LootContextTypes.ARCHAEOLOGY)
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
            identifierBuilderBiConsumer.accept(NumisArchLootTables.BASTION_HOUSING_ARCHEOLOGY, LootTable.builder().type(LootContextTypes.ARCHAEOLOGY)
                    .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F))
                            .with(ItemEntry.builder(Items.GOLD_INGOT).weight(3))
                            .with(ItemEntry.builder(Items.GOLD_BLOCK))
                            .with(ItemEntry.builder(Items.GOLD_NUGGET).weight(2))
                            .with(ItemEntry.builder(Items.NETHER_WART).weight(2))
                            .with(ItemEntry.builder(Items.BLAZE_POWDER))
                            .with(ItemEntry.builder(NumisArchItems.PIGLIN_COIN).weight(2))
                    ));
            identifierBuilderBiConsumer.accept(NumisArchLootTables.BASTION_GARDEN_ARCHEOLOGY, LootTable.builder().type(LootContextTypes.ARCHAEOLOGY)
                    .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F))
                            .with(ItemEntry.builder(Items.GOLDEN_CARROT))
                            .with(ItemEntry.builder(Items.GOLDEN_APPLE))
                            .with(ItemEntry.builder(Items.NETHER_WART))
                            .with(ItemEntry.builder(Items.CRIMSON_FUNGUS))
                            .with(ItemEntry.builder(Items.WARPED_FUNGUS))
                            .with(ItemEntry.builder(Items.NETHER_BRICK))
                            .with(ItemEntry.builder(Items.GOLDEN_HOE).apply(EnchantRandomlyLootFunction.builder()).weight(2))
                    ));
            identifierBuilderBiConsumer.accept(NumisArchLootTables.BAMBOO_SHRINE_CHEST, LootTable.builder().type(LootContextTypes.CHEST)
                    .pool(LootPool.builder().rolls(UniformLootNumberProvider.create(9.0F, 12.0F))
                            .with(ItemEntry.builder(Items.GOLDEN_APPLE))
                            .with(ItemEntry.builder(Items.IRON_AXE).apply(LimitCountLootFunction.builder(BoundedIntUnaryOperator.create(1))))
                            .with(ItemEntry.builder(Items.IRON_SHOVEL).apply(LimitCountLootFunction.builder(BoundedIntUnaryOperator.create(1))))
                            .with(ItemEntry.builder(Items.ECHO_SHARD))
                            .with(ItemEntry.builder(Items.BAMBOO).weight(8).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 8.0F))))
                            .with(ItemEntry.builder(Items.IRON_INGOT).weight(4).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 6.0F))))
                            .with(ItemEntry.builder(Items.RED_CANDLE))
                            .with(ItemEntry.builder(Items.GREEN_CANDLE))
                            .with(ItemEntry.builder(Items.BLUE_CANDLE))
                            .with(ItemEntry.builder(Items.PINK_CANDLE))
                            .with(ItemEntry.builder(Items.CANDLE))
                            .with(ItemEntry.builder(Items.FEATHER).weight(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
                    ));
            identifierBuilderBiConsumer.accept(NumisArchLootTables.BAMBOO_SHRINE_CHERRY_CHEST, LootTable.builder().type(LootContextTypes.CHEST)
                    .pool(LootPool.builder().rolls(UniformLootNumberProvider.create(9.0F, 13.0F))
                            .with(ItemEntry.builder(Items.GOLDEN_APPLE))
                            .with(ItemEntry.builder(Items.ECHO_SHARD))
                            .with(ItemEntry.builder(Items.BAMBOO).weight(7).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 8.0F))))
                            .with(ItemEntry.builder(Items.PINK_CANDLE).weight(3))
                            .with(ItemEntry.builder(Items.PINK_STAINED_GLASS))
                            .with(ItemEntry.builder(Items.PINK_WOOL))
                            .with(ItemEntry.builder(Items.PINK_DYE).weight(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                            .with(ItemEntry.builder(Items.PINK_CONCRETE))
                            .with(ItemEntry.builder(Items.PINK_PETALS))
                            .with(ItemEntry.builder(Items.PINK_TERRACOTTA))
                            .with(ItemEntry.builder(Items.CHERRY_SAPLING))
                            .with(ItemEntry.builder(Items.FEATHER).weight(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
                    ));
            identifierBuilderBiConsumer.accept(NumisArchLootTables.BAMBOO_SHRINE_ARCHEOLOGY, LootTable.builder().type(LootContextTypes.ARCHAEOLOGY)
                    .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F))
                            .with(ItemEntry.builder(Items.IRON_PICKAXE))
                            .with(ItemEntry.builder(Items.IRON_SWORD))
                            .with(ItemEntry.builder(Items.FLINT_AND_STEEL))
                            .with(ItemEntry.builder(Items.PACKED_MUD))
                            .with(ItemEntry.builder(Items.ECHO_SHARD))
                            .with(ItemEntry.builder(Items.BAMBOO).weight(3))
                            .with(ItemEntry.builder(Items.IRON_INGOT).weight(2))
                            .with(ItemEntry.builder(Items.RED_CANDLE).weight(2))
                            .with(ItemEntry.builder(Items.GREEN_CANDLE))
                            .with(ItemEntry.builder(Items.BLUE_CANDLE))
                            .with(ItemEntry.builder(NumisArchItems.PARROT_COIN).weight(2))
                            .with(ItemEntry.builder(NumisArchItems.ODD_GREEN_FIGURINE))
                    ));
        }
    }

    private static class NumisArchItemTagGenerator extends FabricTagProvider.ItemTagProvider {
        public NumisArchItemTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup registries) {
            getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, id("coins")))
                    .add(NumisArchItems.RABBIT_COIN)
                    .add(NumisArchItems.BEE_COIN)
                    .add(NumisArchItems.ENDER_COIN)
                    .add(NumisArchItems.PARROT_COIN)
                    .add(NumisArchItems.PIGLIN_COIN)
                    .add(NumisArchItems.SNIFFER_COIN)
                    .add(NumisArchItems.TURTLE_COIN)
                    .add(NumisArchItems.VILLAGER_COIN);
            getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, id("can_be_lightning_activated")))
                    .add(NumisArchItems.ODD_GREEN_FIGURINE)
                    .add(Items.STONE)
                    .add(NumisArchItems.THUNDERSTONE);
        }
    }

    private static class NumisArchBlockTagGenerator extends FabricTagProvider.BlockTagProvider {
        public NumisArchBlockTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup registries) {
            getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, id("brushable")))
                    .add(Blocks.SUSPICIOUS_SAND)
                    .add(Blocks.GRAVEL)
                    .add(NumisArchBlocks.SUSPICIOUS_RED_SAND)
                    .add(NumisArchBlocks.SUSPICIOUS_SOUL_SAND)
                    .add(NumisArchBlocks.SUSPICIOUS_END_STONE);
            getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, id("nether_wart_plantable")))
                    .add(Blocks.SOUL_SAND)
                    .add(NumisArchBlocks.SUSPICIOUS_SOUL_SAND);
            getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, new Identifier("sand")))
                    .setReplace(false)
                    .add(NumisArchBlocks.SUSPICIOUS_RED_SAND);
            getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, new Identifier("needs_stone_tool")))
                    .setReplace(false)
                    .add(NumisArchBlocks.COIN_COLLECTOR_TROPHY);
            getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, new Identifier("mineable/axe")))
                    .setReplace(false)
                    .add(NumisArchBlocks.NUMISMATIC_DESK);
            getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, new Identifier("mineable/pickaxe")))
                    .setReplace(false)
                    .add(NumisArchBlocks.THUNDERSTONE_BLOCK)
                    .add(NumisArchBlocks.ANCIENT_ACTIVATOR)
                    .add(NumisArchBlocks.COIN_COLLECTOR_TROPHY)
                    .add(NumisArchBlocks.SUSPICIOUS_END_STONE);
            getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, new Identifier("mineable/shovel")))
                    .setReplace(false)
                    .add(NumisArchBlocks.SUSPICIOUS_SOUL_SAND)
                    .add(NumisArchBlocks.SUSPICIOUS_RED_SAND);
            getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, new Identifier("soul_fire_base_blocks")))
                    .setReplace(false)
                    .add(NumisArchBlocks.SUSPICIOUS_SOUL_SAND);
            getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, new Identifier("soul_speed_blocks")))
                    .setReplace(false)
                    .add(NumisArchBlocks.SUSPICIOUS_SOUL_SAND);

        }
    }

    private static class NumisArchPOITagGenerator extends FabricTagProvider<PointOfInterestType> {

        public NumisArchPOITagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, RegistryKeys.POINT_OF_INTEREST_TYPE, registriesFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup arg) {
            getOrCreateTagBuilder(TagKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, new Identifier("acquirable_job_site")))
                    .setReplace(false)
                    .add(id("archeologist"));
        }
    }

    private static class NumisArchRecipeGenerator extends FabricRecipeProvider {
        public NumisArchRecipeGenerator(FabricDataOutput output) {
            super(output);
        }

        @Override
        public void generate(RecipeExporter exporter) {
            ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, NumisArchItems.DIAMOND_BRUSH, 1)
                    .criterion("has_diamond", InventoryChangedCriterion.Conditions.items(Items.DIAMOND))
                    .input('I', Items.STICK)
                    .input('#', Items.DIAMOND)
                    .input('X', Items.FEATHER)
                    .pattern("X")
                    .pattern("#")
                    .pattern("I")
                    .offerTo(exporter);
            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, NumisArchItems.ANCIENT_ACTIVATOR, 1)
                    .criterion("has_thunderstone", InventoryChangedCriterion.Conditions.items(NumisArchItems.THUNDERSTONE))
                    .input('C', Items.COPPER_INGOT)
                    .input('E', Items.ECHO_SHARD)
                    .input('T', NumisArchItems.THUNDERSTONE)
                    .input('S', Items.STONE_BRICKS)
                    .pattern("CEC")
                    .pattern("CTC")
                    .pattern("SSS")
                    .offerTo(exporter);
            ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, NumisArchItems.MAGNIFYING_GLASS)
                    .criterion("has_gold", InventoryChangedCriterion.Conditions.items(Items.GOLD_INGOT))
                    .input('G', Items.GLASS)
                    .input('O', Items.GOLD_INGOT)
                    .input('S', Items.STICK)
                    .pattern("  G")
                    .pattern(" O ")
                    .pattern("S  ")
                    .offerTo(exporter);
            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, NumisArchItems.NUMISMATIC_DESK)
                    .criterion("has_magnifying_glass", InventoryChangedCriterion.Conditions.items(NumisArchItems.MAGNIFYING_GLASS))
                    .input('#', TagKey.of(RegistryKeys.ITEM, new Identifier("planks")))
                    .input('M', NumisArchItems.MAGNIFYING_GLASS)
                    .pattern("M")
                    .pattern("#")
                    .offerTo(exporter);

        }
    }
}

