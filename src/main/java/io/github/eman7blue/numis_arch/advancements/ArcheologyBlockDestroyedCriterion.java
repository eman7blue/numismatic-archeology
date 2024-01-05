package io.github.eman7blue.numis_arch.advancements;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.advancement.criterion.AbstractCriterionConditions;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.predicate.entity.AdvancementEntityPredicateDeserializer;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static io.github.eman7blue.numis_arch.NumismaticArcheology.id;

public class ArcheologyBlockDestroyedCriterion extends AbstractCriterion<ArcheologyBlockDestroyedCriterion.Conditions>  {

    static final Identifier ID = id("archeology_block_destroyed");

    public ArcheologyBlockDestroyedCriterion() {
    }

    @Override
    protected Conditions conditionsFromJson(JsonObject obj, Optional<LootContextPredicate> predicate, AdvancementEntityPredicateDeserializer predicateDeserializer) {
        Identifier blockIdentifier = new Identifier(JsonHelper.getString(obj, "block"));
        Block block = Registries.BLOCK.getOrEmpty(blockIdentifier).orElseThrow(() ->
                new JsonSyntaxException("Unknown block type '" + blockIdentifier + "'"));
        Identifier lootTableIdentifier = getLootTable(obj);
        return new ArcheologyBlockDestroyedCriterion.Conditions(predicate, block, lootTableIdentifier);
    }

    public Identifier getId() {
        return ID;
    }


    @Nullable
    private static Identifier getLootTable(JsonObject root) {
        if (root.has("loot_table")) {
            return new Identifier(JsonHelper.getString(root, "loot_table"));
        } else {
            return null;
        }
    }

    public void trigger(ServerPlayerEntity player, BlockState state, Identifier loot_table) {
        this.trigger(player, (conditions) -> conditions.test(state, loot_table));
    }

    public static class Conditions extends AbstractCriterionConditions {
        private final Block block;
        @Nullable
        private final Identifier lootTable;

        public Conditions(Optional<LootContextPredicate> predicate, Block block, @Nullable Identifier lootTable) {
            super(predicate);
            this.block = block;
            this.lootTable = lootTable;
        }

        public static AdvancementCriterion<Conditions> create(Block block, Identifier lootTable) {
            return NumisArchCriteria.ARCHEOLOGY_BLOCK_DESTROYED.create(new Conditions(Optional.empty(), block, lootTable));
        }

        public boolean test(BlockState state, Identifier loot_table) {
            if (this.block != null && !state.isOf(this.block)) {
                return false;
            } else if (this.lootTable != null) {
                return this.lootTable.equals(loot_table);
            } else {
                return loot_table != null;
            }
        }

        public JsonObject toJson() {
            JsonObject jsonObject = super.toJson();
            jsonObject.addProperty("block", Registries.BLOCK.getId(this.block).toString());
            if (this.lootTable != null) {
                jsonObject.addProperty("loot_table", this.lootTable.toString());
            }
            return jsonObject;
        }
    }
}
