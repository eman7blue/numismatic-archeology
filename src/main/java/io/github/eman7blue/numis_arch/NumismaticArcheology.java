package io.github.eman7blue.numis_arch;

import io.github.eman7blue.numis_arch.item.CoinItems;
import io.github.eman7blue.numis_arch.loottable.ArchaeologyLootTableRegistry;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NumismaticArcheology implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("numis_arch");

	@Override
	public void onInitialize() {
		CoinItems.init();
		ArchaeologyLootTableRegistry.init();
		LOGGER.info("Maj says \"coinr!\"");

	}
}
