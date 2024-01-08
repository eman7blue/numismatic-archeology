package io.github.eman7blue.numis_arch.advancements;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;

import java.util.Optional;

import static io.github.eman7blue.numis_arch.NumismaticArcheology.id;

public class ArcheologyBlockDestroyedCriterion extends AbstractCriterion<ArcheologyBlockDestroyedCriterion.Conditions>  {

    static final Identifier ID = id("archeology_block_destroyed");

    public ArcheologyBlockDestroyedCriterion() {
    }

    public Identifier getId() {
        return ID;
    }


    public void trigger(ServerPlayerEntity player, BlockState state, Identifier loot_table) {
        this.trigger(player, (conditions) -> conditions.test(state, loot_table));
    }

    @Override
    public Codec<Conditions> getConditionsCodec() {
        return Conditions.CODEC;
    }

    public record Conditions(Optional<RegistryEntry<Block>> block) implements AbstractCriterion.Conditions {

            public static final Codec<Conditions> CODEC = RecordCodecBuilder.create((instance) ->
                    instance.group(Codecs.createStrictOptionalFieldCodec(Registries.BLOCK.createEntryCodec(), "block").forGetter(Conditions::block)).apply(instance, Conditions::new));

        public static AdvancementCriterion<Conditions> create(Block block) {
                return NumisArchCriteria.ARCHEOLOGY_BLOCK_DESTROYED.create(new Conditions(Optional.of(block.getRegistryEntry())));
            }

            public boolean test(BlockState state, Identifier loot_table) {
                return this.block.isPresent() && state.isOf(this.block.get()) && loot_table != null;
            }

            @Override
            public Optional<LootContextPredicate> player() {
                return Optional.empty();
            }
        }
}
