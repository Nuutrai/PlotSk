package me.github.reportcardsmc.plotsk.utils.events;

import com.google.common.eventbus.Subscribe;
import com.plotsquared.core.events.*;
import org.bukkit.Bukkit;

public class PlotSquaredListener {

    @Subscribe
    public void onPlotEnter(PlayerEnterPlotEvent event) {
        Bukkit.getPluginManager().callEvent(new PlayerEnterPlot(event));
    }

    @Subscribe
    public void onPlotExit(PlayerLeavePlotEvent event) {
        Bukkit.getPluginManager().callEvent(new PlayerLeavePlot(event));
    }

    @Subscribe
    public void onPlotClaim(PlayerClaimPlotEvent event) {
        Bukkit.getPluginManager().callEvent(new PlayerClaimPlot(event));
    }

    @Subscribe
    public void onPlotDelete(PlotDeleteEvent event) {
        Bukkit.getPluginManager().callEvent(new PlayerDeletePlot(event));
    }

    @Subscribe
    public void onPlotClear(PlotClearEvent event) {
        Bukkit.getPluginManager().callEvent(new PlayerClearPlot(event));
    }

    @Subscribe
    public void onPlotMerge(PlotMergeEvent event) {
        Bukkit.getPluginManager().callEvent(new PlayerMergePlot(event));
    }

    @Subscribe
    public void onPlotRate(PlotRateEvent event) {
        Bukkit.getPluginManager().callEvent(new PlayerRatePlot(event));
    }

    @Subscribe
    public void onPlotTeleport(PlayerTeleportToPlotEvent event) {
        Bukkit.getPluginManager().callEvent(new PlotTeleportEvent(event));
    }

    @Subscribe
    public void onPlotDenied(PlayerPlotDeniedEvent event) {
        if (event.wasAdded()) Bukkit.getPluginManager().callEvent(new PlayerDeniedFromPlot(event));
        else Bukkit.getPluginManager().callEvent(new PlayerUndeniedFromPlot(event));
    }

    @Subscribe
    public void onPlotTrust(PlayerPlotTrustedEvent event) {
        if (event.wasAdded()) Bukkit.getPluginManager().callEvent(new PlayerTrustedOnPlot(event));
        else Bukkit.getPluginManager().callEvent(new PlayerUntrustedFromPlot(event));
    }

}
