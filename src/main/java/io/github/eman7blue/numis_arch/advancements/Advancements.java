package io.github.eman7blue.numis_arch.advancements;

import io.github.eman7blue.numis_arch.item.CoinItems;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class Advancements implements Consumer<Consumer<Advancement>>{

    @Override
    public void accept(Consumer<Advancement> consumer) {
        String [][] rootRequirements = {{"animal_coin", "bee_coin", "ender_coin", "parrot_coin", "piglin_coin", "sniffer_coin", "turtle_coin", "villager_coin"}};
        Advancement rootAdvancement = Advancement.Builder.create()
                .display(
                        CoinItems.BEE_COIN,
                        Text.translatable("advancements.numis_arch.numismatic_archeology.title"),
                        Text.translatable("advancements.numis_arch.numismatic_archeology.description"),
                        new Identifier("textures/block/suspicious_sand_0.png"),
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("animal_coin", InventoryChangedCriterion.Conditions.items(CoinItems.ANIMAL_COIN))
                .criterion("bee_coin", InventoryChangedCriterion.Conditions.items(CoinItems.BEE_COIN))
                .criterion("ender_coin", InventoryChangedCriterion.Conditions.items(CoinItems.ENDER_COIN))
                .criterion("parrot_coin", InventoryChangedCriterion.Conditions.items(CoinItems.PARROT_COIN))
                .criterion("piglin_coin", InventoryChangedCriterion.Conditions.items(CoinItems.PIGLIN_COIN))
                .criterion("sniffer_coin", InventoryChangedCriterion.Conditions.items(CoinItems.SNIFFER_COIN))
                .criterion("turtle_coin", InventoryChangedCriterion.Conditions.items(CoinItems.TURTLE_COIN))
                .criterion("villager_coin", InventoryChangedCriterion.Conditions.items(CoinItems.VILLAGER_COIN))
                .requirements(rootRequirements)
                .build(consumer, "numis_arch" + "/root");

        Advancement collectCoinsAdvancement = Advancement.Builder.create().parent(rootAdvancement)
                .display(
                        CoinItems.ENDER_COIN,
                        Text.translatable("advancements.numis_arch.collect_coins.title"),
                        Text.translatable("advancements.numis_arch.collect_coins.description"),
                        null,
                        AdvancementFrame.CHALLENGE,
                        true,
                        true,
                        false
                )
                .rewards(AdvancementRewards.Builder.experience(500).addRecipe(new Identifier("numis_arch:coin_collector_trophy")))
                .criterion("animal_coin", InventoryChangedCriterion.Conditions.items(CoinItems.ANIMAL_COIN))
                .criterion("bee_coin", InventoryChangedCriterion.Conditions.items(CoinItems.BEE_COIN))
                .criterion("ender_coin", InventoryChangedCriterion.Conditions.items(CoinItems.ENDER_COIN))
                .criterion("parrot_coin", InventoryChangedCriterion.Conditions.items(CoinItems.PARROT_COIN))
                .criterion("piglin_coin", InventoryChangedCriterion.Conditions.items(CoinItems.PIGLIN_COIN))
                .criterion("sniffer_coin", InventoryChangedCriterion.Conditions.items(CoinItems.SNIFFER_COIN))
                .criterion("turtle_coin", InventoryChangedCriterion.Conditions.items(CoinItems.TURTLE_COIN))
                .criterion("villager_coin", InventoryChangedCriterion.Conditions.items(CoinItems.VILLAGER_COIN))
                .build(consumer, "numis_arch");


    }


}
