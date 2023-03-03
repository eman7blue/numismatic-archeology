package io.github.eman7blue.numis_arch.village;

import io.github.eman7blue.numis_arch.item.CoinItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.village.TradeOffers;

public class TradeOffersModifier {

    public static void init(){
        TradeOfferHelper.registerWanderingTraderOffers(2, factories ->
                factories.add(new TradeOffers.SellItemFactory(CoinItems.VILLAGER_COIN, 30, 1, 1, 10)));
    }
}
