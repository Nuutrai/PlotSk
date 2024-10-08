package me.github.reportcardsmc.plotsk.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import com.plotsquared.core.PlotAPI;
import com.plotsquared.core.plot.Plot;
import com.plotsquared.core.plot.PlotId;
import me.github.reportcardsmc.plotsk.utils.PlotSquaredUtil;
import me.github.reportcardsmc.plotsk.utils.events.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public class EventRegisters {
    public static PlotAPI plotAPI = new PlotAPI();

    static {
        plotAPI.registerListener(new PlotSquaredListener());
        /*
        Plot Enter Event
        Has string value for plot id
        Has player value for player who caused event
         */
        Skript.registerEvent("PlotSquared: Plot Enter", PlotSkEvent.class, PlayerEnterPlot.class, "[PlotSquared] [player] plot enter[ing]", "[PlotSquared] [player] enter[ing] plot")
                .description("Called when a player goes into a plot")
                .examples("on plot enter:", "\tbroadcast \"%player% has went into plot %event-string%\"");
        EventValues.registerEventValue(PlayerEnterPlot.class, Plot.class, new Getter<>() {
            public Plot get(PlayerEnterPlot e) {
                return e.getPlot();
            }
        }, 0);
        EventValues.registerEventValue(PlayerEnterPlot.class, Player.class, new Getter<>() {
            public Player get(PlayerEnterPlot e) {
                return e.getPlayer();
            }
        }, 0);
        /*
        Plot Leave Event
        Has string value for plot id
        Has player value for player who caused event
         */
        Skript.registerEvent("PlotSquared: Plot Exit", PlotSkEvent.class, PlayerLeavePlot.class, "[PlotSquared] [player] plot exit[ing]", "[PlotSquared] [player] exit[ing] plot")
                .description("Called when a player leaves a plot")
                .examples("on plot exit:", "\tbroadcast \"%player% has left plot %event-string%\"");
        EventValues.registerEventValue(PlayerLeavePlot.class, Plot.class, new Getter<>() {
            public Plot get(PlayerLeavePlot e) {
                return e.getPlot();
            }
        }, 0);
        EventValues.registerEventValue(PlayerLeavePlot.class, Player.class, new Getter<>() {
            public Player get(PlayerLeavePlot e) {
                return e.getPlayer();
            }
        }, 0);
        /*
        Plot Claim Event
        Has string value for plot id
        Has player value for player who caused event
         */
        Skript.registerEvent("PlotSquared: Plot Claim", PlotSkEvent.class, PlayerClaimPlot.class, "[PlotSquared] plot claim[ed] [by player]", "[PlotSquared] [player] claim[ed] plot")
                .description("Called when a player claims a plot")
                .examples("on plot claim:", "\tbroadcast \"%player% has claimed plot %event-string%\"");
        EventValues.registerEventValue(PlayerClaimPlot.class, Plot.class, new Getter<>() {
            public Plot get(PlayerClaimPlot e) {
                return e.getPlot();
            }
        }, 0);
        EventValues.registerEventValue(PlayerClaimPlot.class, Player.class, new Getter<>() {
            public Player get(PlayerClaimPlot e) {
                return e.getPlayer();
            }
        }, 0);
        /*
        Plot Merge Event
        Has string value for plot id
        Has player value for player who caused event
         */
        Skript.registerEvent("PlotSquared: Plot Merge", PlotSkEvent.class, PlayerMergePlot.class, "[PlotSquared] [player] plot merge[d]", "[PlotSquared] [player] merge[d] plot")
                .description("Called when a player merges a plot")
                .examples("on plot merge:", "\tbroadcast \"%player% has merged plot %event-string% with another plot\"");
        EventValues.registerEventValue(PlayerMergePlot.class, Plot.class, new Getter<>() {
            public Plot get(PlayerMergePlot e) {
                String plotId = e.getPlotId().toString();
                return PlotSquaredUtil.getPlot(plotId);
            }
        }, 0);
        EventValues.registerEventValue(PlayerMergePlot.class, Player.class, new Getter<>() {
            public Player get(PlayerMergePlot e) {
                return e.getPlayer();
            }
        }, 0);
        /*
        Plot Delete Event
        Has string value for plot id
         */
        Skript.registerEvent("PlotSquared: Plot Delete", PlotSkEvent.class, PlayerDeletePlot.class, "[PlotSquared] [player] plot delete[d]", "[PlotSquared] [player] delete[d] plot")
                .description("Called when a plot is deleted")
                .examples("on plot delete:", "\tbroadcast \"%event-string% was deleted\"");
        EventValues.registerEventValue(PlayerDeletePlot.class, Plot.class, new Getter<>() {
            public Plot get(PlayerDeletePlot e) {
                String plotId = e.getPlotId().toString();
                return PlotSquaredUtil.getPlot(plotId);
            }
        }, 0);
        /*
        Plot Clear Event
        Has string value for plot id
         */
        Skript.registerEvent("PlotSquared: Plot Clear", PlotSkEvent.class, PlayerClearPlot.class, "[PlotSquared] [player] plot clear[ed]", "[PlotSquared] [player] clear[ed] plot")
                .description("Called when a plot is cleared")
                .examples("on plot delete:", "\tbroadcast \"%event-plot% was cleared\"");
        EventValues.registerEventValue(PlayerClearPlot.class, Plot.class, new Getter<>() {
            public Plot get(PlayerClearPlot e) {
                String plotId = e.getPlotId().toString();
                return PlotSquaredUtil.getPlot(plotId);
            }
        }, 0);
        /*
        Plot Rating Event
        Has string value for plot id
        Has integer value for plot rating value
        Has player value for player who caused event
         */
        Skript.registerEvent("PlotSquared: Plot Rating", PlotSkEvent.class, PlayerRatePlot.class, "[PlotSquared] [player] plot rat(e|ing)", "[PlotSquared] [player] rat(e|ing) plot")
                .description("Called when a player rates a plot")
                .examples("on plot rate:", "\tbroadcast \"%player% has voted %event-integer% on %event-plot%\"");
        EventValues.registerEventValue(PlayerRatePlot.class, Plot.class, new Getter<>() {
            public Plot get(PlayerRatePlot e) {
                String plotId = e.getPlotId().toString();
                return PlotSquaredUtil.getPlot(plotId);
            }
        }, 0);
        EventValues.registerEventValue(PlayerRatePlot.class, Player.class, new Getter<>() {
            public Player get(PlayerRatePlot e) {
                return e.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(PlayerRatePlot.class, Number.class, new Getter<>() {
            public Number get(PlayerRatePlot e) {
                return e.getRating();
            }
        }, 0);
        /*
        Plot Denied Event
        Has string value for plot id
        Has player value for player who caused event
        Has "denied player" for player who got denied
         */
        Skript.registerEvent("PlotSquared: Plot Denied", PlotSkEvent.class, PlayerDeniedFromPlot.class, "[PlotSquared] player deny[ing] player [from plot]", "[PlotSquared] player den(y|ied) from plot")
                .description("Called when a player denies a player from a plot")
                .examples("on player deny player from plot:", "\tbroadcast \"%player% denied %denied player% from %event-plot%\"");
        EventValues.registerEventValue(PlayerDeniedFromPlot.class, Plot.class, new Getter<>() {
            public Plot get(PlayerDeniedFromPlot e) {
                return e.getPlot();
            }
        }, 0);
        EventValues.registerEventValue(PlayerDeniedFromPlot.class, Player.class, new Getter<>() {
            public Player get(PlayerDeniedFromPlot e) {
                return e.getPlayer();
            }
        }, 0);
        /*
        Plot Undenied Event
        Has string value for plot id
        Has player value for player who caused event
        Has "undenied player" for player who got undenied
         */
        Skript.registerEvent("PlotSquared: Plot Undenied", PlotSkEvent.class, PlayerUndeniedFromPlot.class, "[PlotSquared] player undeny[ing] player [from plot]", "[PlotSquared] player unden(y|ied) from plot")
                .description("Called when a player undenies a player from a plot")
                .examples("on player undeny player from plot:", "\tbroadcast \"%player% undenied %undenied player% from %event-plot%\"");
        EventValues.registerEventValue(PlayerUndeniedFromPlot.class, Plot.class, new Getter<>() {
            public Plot get(PlayerUndeniedFromPlot e) {
                return e.getPlot();
            }
        }, 0);
        EventValues.registerEventValue(PlayerUndeniedFromPlot.class, Player.class, new Getter<>() {
            public Player get(PlayerUndeniedFromPlot e) {
                return e.getPlayer();
            }
        }, 0);
        /*
        Plot Trust Event
        Has string value for plot id
        Has player value for player who caused event
        Has "trusted player" for player who got trusted
         */
        Skript.registerEvent("PlotSquared: Plot Trusted", PlotSkEvent.class, PlayerTrustedOnPlot.class, "[PlotSquared] player trust player [(in|on|at|to) [a] plot]", "[PlotSquared] player trusted (in|on|at|to) [a] plot")
                .description("Called when a player trusts a player on a plot")
                .examples("on player trust player in plot:", "\tbroadcast \"%player% trusted %trusted player% in %event-plot%\"");
        EventValues.registerEventValue(PlayerTrustedOnPlot.class, Plot.class, new Getter<>() {
            public Plot get(PlayerTrustedOnPlot e) {
                return e.getPlot();
            }
        }, 0);
        EventValues.registerEventValue(PlayerTrustedOnPlot.class, Player.class, new Getter<>() {
            public Player get(PlayerTrustedOnPlot e) {
                return e.getPlayer();
            }
        }, 0);
        /*
        Plot Untrust Event
        Has string value for plot id
        Has player value for player who caused event
        Has "trusted player" for player who got untrusted
         */
        Skript.registerEvent("PlotSquared: Plot Untrusted", PlotSkEvent.class, PlayerUntrustedFromPlot.class, "[PlotSquared] player untrust player [(in|from [a]) plot]", "[PlotSquared] player untrusted (in|from [a]) plot")
                .description("Called when a player untrusts a player on a plot")
                .examples("on player untrust player in plot:", "\tbroadcast \"%player% untrusted %untrusted player% in %event-string%\"");
        EventValues.registerEventValue(PlayerUntrustedFromPlot.class, Plot.class, new Getter<>() {
            public Plot get(PlayerUntrustedFromPlot e) {
                return e.getPlot();
            }
        }, 0);
        EventValues.registerEventValue(PlayerUntrustedFromPlot.class, Player.class, new Getter<>() {
            public Player get(PlayerUntrustedFromPlot e) {
                return e.getPlayer();
            }
        }, 0);
        /*
        Plot Teleport Event
        Has string value for plot id
        Has player value for player who caused event
         */
        Skript.registerEvent("PlotSquared: Plot Teleport", PlotSkEvent.class, PlotTeleportEvent.class, "[PlotSquared] [player] teleport to [a] plot")
                .description("Called when a player teleports to a plot")
                .examples("on player teleport to plot:", "\tbroadcast \"%player% teleported to %event-string%\"");
        EventValues.registerEventValue(PlotTeleportEvent.class, Plot.class, new Getter<>() {
            public Plot get(PlotTeleportEvent e) {
                return e.getPlot();
            }
        }, 0);
        EventValues.registerEventValue(PlotTeleportEvent.class, Player.class, new Getter<>() {
            public Player get(PlotTeleportEvent e) {
                return e.getPlayer();
            }
        }, 0);
    }

    public static Plot getPlot(@Nullable String id) {
        if (id == null) return null;
        PlotId plotId = PlotId.fromString(id);
        for (Plot plot : plotAPI.getAllPlots()) {
            if (plot.getId().equals(plotId)) return plot;
        }
        return null;
    }
}
