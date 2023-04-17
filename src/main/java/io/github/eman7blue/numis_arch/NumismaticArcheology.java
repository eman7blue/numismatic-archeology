package io.github.eman7blue.numis_arch;

import io.github.eman7blue.numis_arch.block.NumisArchBlocks;
import io.github.eman7blue.numis_arch.item.NumisArchItems;
import io.github.eman7blue.numis_arch.loottable.LootTableModifier;
import io.github.eman7blue.numis_arch.structure.NumisArchStructure;
import io.github.eman7blue.numis_arch.village.TradeOffersModifier;
import io.github.eman7blue.numis_arch.worldgen.feature.NumisArchFeatures;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class NumismaticArcheology implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("numis_arch");

	public static final String MOD_ID = "numis_arch";

	@Override
	public void onInitialize() {
		LootTableModifier.init();
		NumisArchFeatures.init();
		TradeOffersModifier.init();
		NumisArchBlocks.init();
		NumisArchStructure.init();
		LOGGER.info("Maj says \"COINR!\"");

	}
}
