package net.potatocult.spudup.item;

import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;
import net.potatocult.spudup.core.SpudUp;

import static net.potatocult.spudup.util.InjectionUtil.Null;

@ObjectHolder(SpudUp.MODID)
public class ItemHolder {
    @ObjectHolder("minecraft:potato")
    public static final Item POTATO = Null();
    @ObjectHolder("minecraft:baked_potato")
    public static final Item BAKED_POTATO = Null();
    @ObjectHolder("minecraft:poisonous_potato")
    public static final Item POISONOUS_POTATO = Null();

    @ObjectHolder("upside_down_baked_potato")
    public static final Item UPSIDE_DOWN_BAKED_POTATO = Null();
    @ObjectHolder("golden_baked_potato")
    public static final Item GOLDEN_BAKED_POTATO = Null();
    @ObjectHolder("enchanted_golden_baked_potato")
    public static final Item ENCHANTED_GOLDEN_BAKED_POTATO = Null();
}
