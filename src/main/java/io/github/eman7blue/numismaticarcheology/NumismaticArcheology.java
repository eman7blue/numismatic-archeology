package io.github.eman7blue.numismaticarcheology;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NumismaticArcheology implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("numismaticarcheology");

	@Override
	public void onInitialize() {
		LOGGER.info("Maj says \"coinr!\"");
	}
}
