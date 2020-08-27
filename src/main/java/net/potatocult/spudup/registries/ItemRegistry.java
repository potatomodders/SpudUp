package net.potatocult.spudup.registries;

import com.google.common.base.Preconditions;
import net.potatocult.spudup.core.SpudUp;
import net.potatocult.spudup.item.Foods;
import net.minecraft.item.EnchantedGoldenAppleItem;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(SpudUp.MODID)
public class ItemRegistry {
    @Mod.EventBusSubscriber(modid = SpudUp.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class Register {

        @SubscribeEvent
        public static void RegisterItems(final RegistryEvent.Register<Item> event) {
            final Item[] items = {
                    new Item((new Item.Properties()).group(SpudUp.GROUP).maxStackSize(64).food(Foods.POTATO).rarity(Rarity.COMMON)).setRegistryName("minecraft:potato"),
                    new Item((new Item.Properties()).group(SpudUp.GROUP).maxStackSize(64).food(Foods.BAKED_POTATO).rarity(Rarity.COMMON)).setRegistryName("minecraft:baked_potato"),
                    new Item((new Item.Properties()).group(SpudUp.GROUP).maxStackSize(64).food(Foods.POISONOUS_POTATO).rarity(Rarity.UNCOMMON)).setRegistryName("minecraft:poisonous_potato"),
                new Item((new Item.Properties()).group(SpudUp.GROUP).maxStackSize(64).food(Foods.UPSIDE_DOWN_BAKED_POTATO).rarity(Rarity.UNCOMMON)).setRegistryName(SpudUp.location("upside_down_baked_potato")),
                new Item((new Item.Properties()).group(SpudUp.GROUP).maxStackSize(64).food(Foods.GOLDEN_POTATO).rarity(Rarity.RARE)).setRegistryName(SpudUp.location("golden_baked_potato")),
                new EnchantedGoldenAppleItem((new Item.Properties()).group(SpudUp.GROUP).maxStackSize(64).food(Foods.ENCHANTED_GOLDEN_POTATO).rarity(Rarity.EPIC)).setRegistryName(SpudUp.location("enchanted_golden_baked_potato"))
            };

            for (final Item item: items) {
                Preconditions.checkNotNull(item.getRegistryName(), "Block: %s has a NULL registry name", item);
                event.getRegistry().register(item);
            }
        }

    }
}
