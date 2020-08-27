package net.potatocult.spudup.util;

import net.minecraft.item.Item;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.minecraft.world.storage.loot.conditions.RandomChance;
import net.minecraft.world.storage.loot.functions.ILootFunction;
import net.minecraft.world.storage.loot.functions.SetCount;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.potatocult.spudup.core.SpudUp;
import org.apache.logging.log4j.Level;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

public class LootUtils {
    private static final Field tablePools;
    private static final Field poolEntries;
    private static final Field entryItem;

    static {
        tablePools = ObfuscationReflectionHelper.findField(net.minecraft.world.storage.loot.LootTable.class, "field_186466_c");
        tablePools.setAccessible(true);
        poolEntries = ObfuscationReflectionHelper.findField(net.minecraft.world.storage.loot.LootPool.class, "field_186453_a");
        poolEntries.setAccessible(true);
        entryItem = ObfuscationReflectionHelper.findField(net.minecraft.world.storage.loot.ItemLootEntry.class, "field_186368_a");
        entryItem.setAccessible(true);
    }

    public static boolean removeLootFromTable(LootTable table, Item toRemove)
    {
        try
        {
            List<LootPool> pools = (List<LootPool>) tablePools.get(table);

            for(LootPool pool : pools)
            {
                List<LootEntry> entries = (List<LootEntry>) poolEntries.get(pool);
                Iterator<LootEntry> iterator = entries.iterator();

                while(iterator.hasNext())
                {
                    LootEntry entry = iterator.next();

                    if(entry instanceof ItemLootEntry)
                    {
                        ItemLootEntry itemLootEntry = (ItemLootEntry) entry;
                        Item item = (Item) entryItem.get(itemLootEntry);

                        if(item == toRemove)
                        {
                            iterator.remove();
                            return true;
                        }
                    }
                }
            }
        } catch(Exception exception)
        {
            SpudUp.LOGGER.log(Level.ERROR, exception.toString());
            return false;
        }

        return false;
    }

    public static LootPool createLootPool(String name, RandomValueRange numRolls, RandomValueRange bonusRolls, LootEntry.Builder entryBuilder, ILootCondition.IBuilder conditionBuilder, ILootFunction.IBuilder functionBuilder)
    {
        LootPool.Builder builder = LootPool.builder();
        builder.name(name);
        builder.rolls(numRolls);
        builder.bonusRolls(bonusRolls.getMin(), bonusRolls.getMax());
        builder.addEntry(entryBuilder);
        builder.acceptCondition(conditionBuilder);
        builder.acceptFunction(functionBuilder);

        return builder.build();
    }

    public static void addItemToTable(LootTable table, LootPool pool)
    {
        table.addPool(pool);
    }

    public static void addItemToTable(LootTable table, Item item, int weight, float probability, int minQuantity, int maxQuantity, String name)
    {
        addItemToTable(table, item, weight, 1, probability, minQuantity, maxQuantity, name);
    }

    public static void addItemToTable(LootTable table, Item item, int weight, float probability, int quantity, String name)
    {
        addItemToTable(table, item, weight, 1, probability, quantity, quantity, name);
    }

    private static void addItemToTable(LootTable table, Item item, int weight, float numRolls, float probability, int minQuantity, int maxQuantity, String name)
    {
        RandomChance.IBuilder conditionBuilder = RandomChance.builder(probability);
        SetCount.IBuilder functionBuilder = SetCount.builder(new RandomValueRange(minQuantity, maxQuantity));

        ItemLootEntry.Builder entryBuilder = ItemLootEntry.builder(item);
        entryBuilder.weight(weight);
        entryBuilder.quality(1);
        entryBuilder.acceptCondition(conditionBuilder);
        entryBuilder.acceptFunction(functionBuilder);

        LootPool newPool = createLootPool(name, new RandomValueRange(numRolls), new RandomValueRange(0), entryBuilder, conditionBuilder, functionBuilder);

	    addItemToTable(table, newPool);
    }
}
