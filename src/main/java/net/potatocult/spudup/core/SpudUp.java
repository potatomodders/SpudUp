package net.potatocult.spudup.core;

import net.potatocult.spudup.events.LootTableEvent;
import net.potatocult.spudup.item.ItemHolder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.*;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

@Mod(SpudUp.MODID)
public class SpudUp {
    public static final String MODID = "spudup";
    public static final String MODNAME = "SpudUp!";
    public static final String VERSION = "1.0.0";


    public static final Logger LOGGER = LogManager.getLogger(SpudUp.MODID);

    public static SpudUp INSTANCE;

    public SpudUp() {
        MinecraftForge.EVENT_BUS.register(new LootTableEvent());

        INSTANCE = this;
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(EventHandler.INSTANCE);
    }

    public static String getVersion(boolean correctInDev) {
        Optional<? extends ModContainer> o = ModList.get().getModContainerById(SpudUp.MODID);
        if (o.isPresent()) {
            String str = o.get().getModInfo().getVersion().toString();
            if (correctInDev && "NONE".equals(str))
                return SpudUp.VERSION;
            return str;
        }
        return "0.0.0";
    }

    public static boolean isDevBuild() {
        return "NONE".equals(getVersion(false));
    }

    public static ResourceLocation location(String name) {
        return new ResourceLocation(SpudUp.MODID, name);
    }

    public static final ItemGroup GROUP = new ItemGroup(SpudUp.MODID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemHolder.ENCHANTED_GOLDEN_BAKED_POTATO);
        }
    };
}
