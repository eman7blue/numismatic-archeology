package io.github.eman7blue.numis_arch.advancements;

import io.github.eman7blue.numis_arch.NumismaticArcheology;
import io.github.eman7blue.numis_arch.item.NumisArchItems;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class Advancements implements Consumer<Consumer<Advancement>>{

    @Override
    public void accept(Consumer<Advancement> consumer) {
        String [][] coinRequirements = {{"animal_coin", "bee_coin", "ender_coin", "parrot_coin", "piglin_coin", "sniffer_coin", "turtle_coin", "villager_coin"}};
        Advancement rootAdvancement = Advancement.Builder.create()
                .display(
                        NumisArchItems.BEE_COIN,
                        Text.translatable("advancements.numis_arch.numismatic_archeology.title"),
                        Text.translatable("advancements.numis_arch.numismatic_archeology.description"),
                        new Identifier("textures/block/suspicious_sand_0.png"),
                        AdvancementFrame.TASK,
                        false,
                        false,
                        false
                )
                .criterion("got_brush", InventoryChangedCriterion.Conditions.items(Items.BRUSH))
                .build(consumer, "numis_arch" + "/root");
        Advancement keepTheChangeAdvancement = Advancement.Builder.create().parent(rootAdvancement)
                .display(
                        NumisArchItems.ENDER_COIN,
                        Text.translatable("advancements.numis_arch.keep_the_change.title"),
                        Text.translatable("advancements.numis_arch.keep_the_change.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("animal_coin", InventoryChangedCriterion.Conditions.items(NumisArchItems.ANIMAL_COIN))
                .criterion("bee_coin", InventoryChangedCriterion.Conditions.items(NumisArchItems.BEE_COIN))
                .criterion("ender_coin", InventoryChangedCriterion.Conditions.items(NumisArchItems.ENDER_COIN))
                .criterion("parrot_coin", InventoryChangedCriterion.Conditions.items(NumisArchItems.PARROT_COIN))
                .criterion("piglin_coin", InventoryChangedCriterion.Conditions.items(NumisArchItems.PIGLIN_COIN))
                .criterion("sniffer_coin", InventoryChangedCriterion.Conditions.items(NumisArchItems.SNIFFER_COIN))
                .criterion("turtle_coin", InventoryChangedCriterion.Conditions.items(NumisArchItems.TURTLE_COIN))
                .criterion("villager_coin", InventoryChangedCriterion.Conditions.items(NumisArchItems.VILLAGER_COIN))
                .requirements(coinRequirements)
                .build(consumer, NumismaticArcheology.MOD_ID + "/keep_the_change");

        Advancement collectCoinsAdvancement = Advancement.Builder.create().parent(keepTheChangeAdvancement)
                .display(
                        NumisArchItems.COIN_COLLECTOR_TROPHY,
                        Text.translatable("advancements.numis_arch.collect_coins.title"),
                        Text.translatable("advancements.numis_arch.collect_coins.description"),
                        null,
                        AdvancementFrame.CHALLENGE,
                        true,
                        true,
                        false
                )
                .rewards(AdvancementRewards.Builder.experience(500).addRecipe(new Identifier("numis_arch:coin_collector_trophy")))
                .criterion("animal_coin", InventoryChangedCriterion.Conditions.items(NumisArchItems.ANIMAL_COIN))
                .criterion("bee_coin", InventoryChangedCriterion.Conditions.items(NumisArchItems.BEE_COIN))
                .criterion("ender_coin", InventoryChangedCriterion.Conditions.items(NumisArchItems.ENDER_COIN))
                .criterion("parrot_coin", InventoryChangedCriterion.Conditions.items(NumisArchItems.PARROT_COIN))
                .criterion("piglin_coin", InventoryChangedCriterion.Conditions.items(NumisArchItems.PIGLIN_COIN))
                .criterion("sniffer_coin", InventoryChangedCriterion.Conditions.items(NumisArchItems.SNIFFER_COIN))
                .criterion("turtle_coin", InventoryChangedCriterion.Conditions.items(NumisArchItems.TURTLE_COIN))
                .criterion("villager_coin", InventoryChangedCriterion.Conditions.items(NumisArchItems.VILLAGER_COIN))
                .build(consumer, NumismaticArcheology.MOD_ID + "/collect_coins");


    }


}
