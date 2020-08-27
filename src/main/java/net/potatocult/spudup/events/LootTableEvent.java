package net.potatocult.spudup.events;

import net.potatocult.spudup.core.SpudUp;
import net.potatocult.spudup.item.ItemHolder;
import net.potatocult.spudup.util.LootUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SpudUp.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class LootTableEvent {
    private static final ResourceLocation MINESHAFT = new ResourceLocation("minecraft:chests/abandoned_mineshaft");
    private static final ResourceLocation WOODLAND = new ResourceLocation("minecraft:chests/woodland_mansion");
    private static final ResourceLocation DUNGEON = new ResourceLocation("minecraft:chests/simple_dungeon");
    private static final ResourceLocation PYRAMID = new ResourceLocation("minecraft:chests/desert_pyramid");
    private static final ResourceLocation STRONGHOLD = new ResourceLocation("minecraft:chests/stronghold_corridor");

    @SubscribeEvent
    public void onLootTableLoad(LootTableLoadEvent event) {
        if(event.getName().equals(STRONGHOLD)) {
            LootTable table = event.getTable();

            LootUtils.addItemToTable(table, ItemHolder.GOLDEN_BAKED_POTATO, 1, 0.8F, 1, 4, ItemHolder.GOLDEN_BAKED_POTATO.getTranslationKey());
        }

        if(event.getName().equals(WOODLAND) || event.getName().equals(DUNGEON) || event.getName().equals(PYRAMID)) {
            LootTable table = event.getTable();

            LootUtils.addItemToTable(table, ItemHolder.GOLDEN_BAKED_POTATO, 15, 0.6F, 1, 3, ItemHolder.GOLDEN_BAKED_POTATO.getTranslationKey());
            LootUtils.addItemToTable(table, ItemHolder.ENCHANTED_GOLDEN_BAKED_POTATO, 2, 0.5F, 1, ItemHolder.ENCHANTED_GOLDEN_BAKED_POTATO.getTranslationKey());
        }

        if(event.getName().equals(MINESHAFT)) {
            LootTable table = event.getTable();

            LootUtils.addItemToTable(table, ItemHolder.GOLDEN_BAKED_POTATO, 20, 0.6F, 1, 3, ItemHolder.GOLDEN_BAKED_POTATO.getTranslationKey());
            LootUtils.addItemToTable(table, ItemHolder.ENCHANTED_GOLDEN_BAKED_POTATO, 1, 0.5F, 1, ItemHolder.ENCHANTED_GOLDEN_BAKED_POTATO.getTranslationKey());
        }
    }
}
