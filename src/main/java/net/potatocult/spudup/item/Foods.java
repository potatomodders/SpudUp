package net.potatocult.spudup.item;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class Foods {
    public static final Food POTATO = (new Food.Builder()).hunger(1).saturation(0.1F).effect(new EffectInstance(Effects.SPEED, 100, 0), 0.3F).setAlwaysEdible().build();
    public static final Food BAKED_POTATO = (new Food.Builder()).hunger(3).saturation(0.3F).effect(new EffectInstance(Effects.GLOWING, 100, 0), 0.3F).setAlwaysEdible().build();
    public static final Food POISONOUS_POTATO = (new Food.Builder()).hunger(1).saturation(0.1F).effect(new EffectInstance(Effects.SPEED, 200, 0), 0.1F).effect(new EffectInstance(Effects.POISON, 200, 2), 1.0F).setAlwaysEdible().build();
    public static final Food UPSIDE_DOWN_BAKED_POTATO = (new Food.Builder()).hunger(-6).saturation(-1.3F).effect(new EffectInstance(Effects.BAD_OMEN, 50000, 4), 0.8F).setAlwaysEdible().build();
    public static final Food GOLDEN_POTATO = (new Food.Builder()).hunger(6).saturation(1.5F).effect(new EffectInstance(Effects.HASTE, 500, 1), 1.0F).effect(new EffectInstance(Effects.SPEED, 600, 1), 1.0F).effect(new EffectInstance(Effects.ABSORPTION, 2400, 2), 1.0F).setAlwaysEdible().build();
    public static final Food ENCHANTED_GOLDEN_POTATO = (new Food.Builder()).hunger(6).saturation(1.8F).effect(new EffectInstance(Effects.HASTE, 800, 2), 1.0F).effect(new EffectInstance(Effects.SPEED, 900, 2), 1.0F).effect(new EffectInstance(Effects.ABSORPTION, 2400, 4), 1.0F).setAlwaysEdible().build();
}
