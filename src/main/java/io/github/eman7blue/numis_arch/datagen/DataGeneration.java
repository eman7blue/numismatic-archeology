package io.github.eman7blue.numis_arch.datagen;

import io.github.eman7blue.numis_arch.item.CoinItems;
import io.github.eman7blue.numis_arch.loottable.NumisArchLootTables;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
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
        pack.addProvider(CoinTagGenerator::new);
        pack.addProvider(AdvancementsProvider::new);
        pack.addProvider(BuriedHoardLootTables::new);

    }

    private static class BuriedHoardLootTables extends SimpleFabricLootTableProvider {
        public BuriedHoardLootTables(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, LootContextTypes.CHEST);
        }


        @Override
        public void accept(BiConsumer<Identifier, LootTable.Builder> identifierBuilderBiConsumer) {
            identifierBuilderBiConsumer.accept(NumisArchLootTables.BURIED_HOARD_LOOT, LootTable.builder()
                    .pool(LootPool.builder().rolls(UniformLootNumberProvider.create(4.0F, 8.0F))
                            .with(ItemEntry.builder(Items.GOLD_NUGGET).weight(6))
                            .with(ItemEntry.builder(Items.GOLD_INGOT).weight(3))
                            .with(ItemEntry.builder(Items.DIAMOND))
                            .with(ItemEntry.builder(Items.EMERALD))
                            .with(ItemEntry.builder(Items.SADDLE))
                            .with(ItemEntry.builder(Items.CLOCK))
                    ));
                identifierBuilderBiConsumer.accept(NumisArchLootTables.BURIED_HOARD_ARCHEOLOGY, LootTable.builder()
                    .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F))
                            .with(ItemEntry.builder(Items.GOLD_NUGGET))
                            .with(ItemEntry.builder(Items.GOLD_INGOT))
                            .with(ItemEntry.builder(Items.DIAMOND))
                            .with(ItemEntry.builder(Items.EMERALD))
                            .with(ItemEntry.builder(Items.GUNPOWDER))
                            .with(ItemEntry.builder(Items.STICK))
                            .with(ItemEntry.builder(CoinItems.BEE_COIN).weight(3))
                    ));
        }
    }

    private static class CoinTagGenerator extends FabricTagProvider<Item> {
        public CoinTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, RegistryKeys.ITEM, registriesFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup registries) {
            getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("numis_arch", "coins")))
                    .add(CoinItems.ANIMAL_COIN)
                    .add(CoinItems.BEE_COIN)
                    .add(CoinItems.ENDER_COIN)
                    .add(CoinItems.PARROT_COIN)
                    .add(CoinItems.PIGLIN_COIN)
                    .add(CoinItems.SNIFFER_COIN)
                    .add(CoinItems.TURTLE_COIN)
                    .add(CoinItems.VILLAGER_COIN);
        }
    }


}

