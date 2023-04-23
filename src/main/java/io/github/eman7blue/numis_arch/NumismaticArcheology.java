package io.github.eman7blue.numis_arch;

import io.github.eman7blue.numis_arch.advancements.ArcheologyBlockDestroyedCriterion;
import io.github.eman7blue.numis_arch.block.NumisArchBlocks;
import io.github.eman7blue.numis_arch.item.NumisArchItemGroup;
import io.github.eman7blue.numis_arch.item.NumisArchItems;
import io.github.eman7blue.numis_arch.loottable.LootTableModifier;
import io.github.eman7blue.numis_arch.structure.NumisArchStructure;
import io.github.eman7blue.numis_arch.structure.NumisArchStructureProcessorModifier;
import io.github.eman7blue.numis_arch.village.NumisArchPointOfInterestType;
import io.github.eman7blue.numis_arch.village.NumisArchProfession;
import io.github.eman7blue.numis_arch.village.NumisArchTradeOffers;
import io.github.eman7blue.numis_arch.worldgen.feature.NumisArchFeatures;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class NumismaticArcheology implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("numis_arch");
	public static final String MOD_ID = "numis_arch";

	public static ArcheologyBlockDestroyedCriterion ARCHEOLOGY_BLOCK_DESTROYED = Criteria.register(new ArcheologyBlockDestroyedCriterion());

	@Override
	public void onInitialize() {
		LootTableModifier.modifyLootTables();
		NumisArchBlocks.registerBlocks();
		NumisArchItems.registerItems();
		NumisArchItemGroup.registerItemGroup();
		NumisArchFeatures.registerFeatures();
		ServerLifecycleEvents.SERVER_STARTING.register(NumisArchStructure::addToStructurePools);
		NumisArchStructureProcessorModifier.modifyProcessors();
		NumisArchPointOfInterestType.registerPOI();
		NumisArchProfession.registerProfessions();
		NumisArchTradeOffers.registerTrades();

		LOGGER.info("Maj says \"COINR!\"");
	}

	public static Identifier id(String string) {
		return new Identifier(MOD_ID, string);
	}
}
