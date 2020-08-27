package net.potatocult.spudup.core;

import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent.LoggedInEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.VersionChecker;
import net.minecraftforge.fml.VersionChecker.CheckResult;
import net.minecraftforge.fml.VersionChecker.Status;

import java.util.Objects;

public class EventHandler {

    public static final EventHandler INSTANCE = new EventHandler();

    @SubscribeEvent
    public void handlePlayerLoggedInEvent(LoggedInEvent event)
    {
        CheckResult versionRAW = VersionChecker.getResult(ModList.get().getModFileById(SpudUp.MODID).getMods().get(0));
        Status result = versionRAW.status;

        if (!(result.equals(Status.UP_TO_DATE) || result.equals(Status.PENDING) || result.equals(Status.AHEAD))) {
            Objects.requireNonNull(event.getPlayer()).sendMessage(new StringTextComponent(TextFormatting.GREEN + "" + TextFormatting.BOLD + "[" + SpudUp.MODNAME + "] " + TextFormatting.WHITE + "" + TextFormatting.BOLD + "A new version is available: (" + versionRAW.target + "), Please Update!"));
            event.getPlayer().sendMessage(new StringTextComponent(TextFormatting.YELLOW + "" + TextFormatting.BOLD + "Changelog:"));

            assert versionRAW.changes != null;
            String changes = versionRAW.changes.get(versionRAW.target);
            if (changes != null) {
                String[] changesFormat = changes.split("\n");

                for (String change : changesFormat) {
                    event.getPlayer().sendMessage(new StringTextComponent(TextFormatting.WHITE + "" + TextFormatting.BOLD + "- " + change));
                }
                if (versionRAW.changes.size() > 1) {
                    event.getPlayer().sendMessage(new StringTextComponent(TextFormatting.WHITE + "" + TextFormatting.BOLD + "- And more..."));
                }
            }
        }
        if (result.equals(Status.AHEAD)) {
            Objects.requireNonNull(event.getPlayer()).sendMessage(new StringTextComponent(TextFormatting.GREEN + "" + TextFormatting.BOLD + "[" + SpudUp.MODNAME + "] " + TextFormatting.WHITE + "" + TextFormatting.BOLD + "Version not released yet"));
        }
    }
}