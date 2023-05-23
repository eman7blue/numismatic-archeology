package io.github.eman7blue.numis_arch.village;

import io.github.eman7blue.numis_arch.item.CoinItem;
import io.github.eman7blue.numis_arch.item.NumisArchItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffers;

public class NumisArchTradeOffers {

    public static void registerTrades() {
        TradeOfferHelper.registerVillagerOffers(NumisArchProfession.ARCHEOLOGIST, 1, factories -> {
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(Items.CHARCOAL, 10, 16, 2));
            factories.add(new TradeOffers.SellItemFactory(Items.LEAD, 4, 1, 12, 2));
            factories.add(new TradeOffers.SellItemFactory(Items.CAMPFIRE, 3, 1, 12,2));
        });

        TradeOfferHelper.registerVillagerOffers(NumisArchProfession.ARCHEOLOGIST, 2, factories -> {
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(Items.TERRACOTTA, 32, 12, 5));
            factories.add(new TradeOffers.SellItemFactory(Items.BRUSH, 18, 1, 16,10));
            factories.add(new TradeOffers.SellItemFactory(Items.COMPASS, 7, 1, 12, 5));
            factories.add(new TradeOffers.SellItemFactory(Items.CANDLE, 12, 4, 16, 5));
        });

        TradeOfferHelper.registerVillagerOffers(NumisArchProfession.ARCHEOLOGIST, 3, factories -> {
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(Items.ANGLER_POTTERY_SHERD, 1, 12, 20));
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(Items.ARCHER_POTTERY_SHERD, 1, 12, 20));
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(Items.ARMS_UP_POTTERY_SHERD, 1, 12, 20));
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(Items.BLADE_POTTERY_SHERD, 1, 12, 20));
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(Items.BREWER_POTTERY_SHERD, 1, 12, 20));
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(Items.BURN_POTTERY_SHERD, 1, 12, 20));
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(Items.DANGER_POTTERY_SHERD, 1, 12, 20));
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(Items.EXPLORER_POTTERY_SHERD, 1, 12, 20));
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(Items.FRIEND_POTTERY_SHERD, 1, 12, 20));
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(Items.HEART_POTTERY_SHERD, 1, 12, 20));
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(Items.HEARTBREAK_POTTERY_SHERD, 1, 12, 20));
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(Items.HOWL_POTTERY_SHERD, 1, 12, 20));
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(Items.MINER_POTTERY_SHERD, 1, 12, 20));
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(Items.MOURNER_POTTERY_SHERD, 1, 12, 20));
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(Items.PLENTY_POTTERY_SHERD, 1, 12, 20));
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(Items.PRIZE_POTTERY_SHERD, 1, 12, 20));
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(Items.SKULL_POTTERY_SHERD, 1, 12, 20));
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(Items.SNORT_POTTERY_SHERD, 1, 12, 20));
        });

        TradeOfferHelper.registerVillagerOffers(NumisArchProfession.ARCHEOLOGIST, 4, factories -> {
            factories.add(new TradeOffers.SellItemFactory(Items.ANGLER_POTTERY_SHERD, 19, 1, 3, 15));
            factories.add(new TradeOffers.SellItemFactory(Items.ARCHER_POTTERY_SHERD, 19, 1, 3, 15));
            factories.add(new TradeOffers.SellItemFactory(Items.ARMS_UP_POTTERY_SHERD, 19, 1, 3, 15));
            factories.add(new TradeOffers.SellItemFactory(Items.BLADE_POTTERY_SHERD, 19, 1, 3, 15));
            factories.add(new TradeOffers.SellItemFactory(Items.BREWER_POTTERY_SHERD, 19, 1, 3, 15));
            factories.add(new TradeOffers.SellItemFactory(Items.BURN_POTTERY_SHERD, 19, 1, 3, 15));
            factories.add(new TradeOffers.SellItemFactory(Items.DANGER_POTTERY_SHERD, 19, 1, 3, 15));
            factories.add(new TradeOffers.SellItemFactory(Items.EXPLORER_POTTERY_SHERD, 19, 1, 3, 15));
            factories.add(new TradeOffers.SellItemFactory(Items.FRIEND_POTTERY_SHERD, 19, 1, 3, 15));
            factories.add(new TradeOffers.SellItemFactory(Items.HEART_POTTERY_SHERD, 19, 1, 3, 15));
            factories.add(new TradeOffers.SellItemFactory(Items.HEARTBREAK_POTTERY_SHERD, 19, 1, 3, 15));
            factories.add(new TradeOffers.SellItemFactory(Items.HOWL_POTTERY_SHERD, 19, 1, 3, 15));
            factories.add(new TradeOffers.SellItemFactory(Items.MINER_POTTERY_SHERD, 19, 1, 3, 15));
            factories.add(new TradeOffers.SellItemFactory(Items.MOURNER_POTTERY_SHERD, 19, 1, 3, 15));
            factories.add(new TradeOffers.SellItemFactory(Items.PLENTY_POTTERY_SHERD, 19, 1, 3, 15));
            factories.add(new TradeOffers.SellItemFactory(Items.PRIZE_POTTERY_SHERD, 19, 1, 3, 15));
            factories.add(new TradeOffers.SellItemFactory(Items.SKULL_POTTERY_SHERD, 19, 1, 3, 15));
            factories.add(new TradeOffers.SellItemFactory(Items.SNORT_POTTERY_SHERD, 19, 1, 3, 15));
        });

        TradeOfferHelper.registerVillagerOffers(NumisArchProfession.ARCHEOLOGIST, 5, factories -> {
            ItemStack villagerCoin = NumisArchItems.VILLAGER_COIN.getDefaultStack();
            villagerCoin.getOrCreateNbt().putInt("condition", CoinItem.Condition.SUPERB);
            factories.add(new TradeOffers.SellItemFactory(villagerCoin, 50, 1, 1, 30));
            factories.add(new TradeOffers.ProcessItemFactory(Blocks.SAND, 16, 43, Items.SUSPICIOUS_SAND, 1, 3, 30));
            factories.add(new TradeOffers.ProcessItemFactory(Blocks.GRAVEL, 16, 43, Items.SUSPICIOUS_GRAVEL, 1, 3, 30));
            factories.add(new TradeOffers.ProcessItemFactory(Blocks.RED_SAND, 16, 43, NumisArchItems.SUSPICIOUS_RED_SAND, 1, 3, 30));
        });
    }
}
