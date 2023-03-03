package io.github.eman7blue.numis_arch.datagen;

import io.github.eman7blue.numis_arch.advancements.Advancements;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;

import java.util.function.Consumer;

public class AdvancementsProvider extends FabricAdvancementProvider {
    protected AdvancementsProvider(FabricDataOutput dataGen) {
        super(dataGen);
    }
    @Override
    public void generateAdvancement(Consumer<Advancement> consumer) {
        new Advancements().accept(consumer);
    }
}
