package io.github.eman7blue.numis_arch.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

import static io.github.eman7blue.numis_arch.NumismaticArcheology.id;


public class NumisArchItemGroup {
    protected static final ItemGroup ITEM_GROUP;

    public static void registerItemGroup(){
        Registry.register(Registries.ITEM_GROUP, id("numismatic_archeology"), ITEM_GROUP);
    }

    static {
        ITEM_GROUP = FabricItemGroup.builder().displayName(Text.translatable("itemGroup.numis_arch.numismaticArcheology")).icon(() -> new ItemStack(NumisArchItems.BEE_COIN))
                .entries((displayContext, entries) -> {
                    entries.add(Items.SUSPICIOUS_SAND);
                    entries.add(Items.SUSPICIOUS_GRAVEL);
                    entries.add(Items.BRUSH);
                    entries.add(NumisArchItems.ANIMAL_COIN);
                    entries.add(NumisArchItems.BEE_COIN);
                    entries.add(NumisArchItems.ENDER_COIN);
                    entries.add(NumisArchItems.PARROT_COIN);
                    entries.add(NumisArchItems.PIGLIN_COIN);
                    entries.add(NumisArchItems.SNIFFER_COIN);
                    entries.add(NumisArchItems.TURTLE_COIN);
                    entries.add(NumisArchItems.VILLAGER_COIN);
                    entries.add(NumisArchItems.ODD_GREEN_FIGURINE);
                    entries.add(NumisArchItems.SUSPICIOUS_RED_SAND);
                    entries.add(NumisArchItems.SUSPICIOUS_SOUL_SAND);
                    entries.add(NumisArchItems.SUSPICIOUS_END_STONE);
                    entries.add(NumisArchItems.NUMISMATIC_DESK);
                    entries.add(NumisArchItems.COIN_COLLECTOR_TROPHY);
                }).build();
    }
}
