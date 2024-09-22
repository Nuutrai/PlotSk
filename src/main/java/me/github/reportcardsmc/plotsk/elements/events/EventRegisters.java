package me.github.reportcardsmc.plotsk.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import com.plotsquared.core.PlotAPI;
import com.plotsquared.core.plot.Plot;
import com.plotsquared.core.plot.PlotId;
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
        Skript.registerEvent("PlotSquared: Plot Enter", PlotSkEvent.class, PlayerEnterPlot.class, "[PlotSquared] plot enter[ing]", "[PlotSquared] enter[ing] plot")
                .description("Called when a player goes into a plot")
                .examples("on plot enter:", "\tbroadcast \"%player% has went into plot %event-string%\"");
        EventValues.registerEventValue(PlayerEnterPlot.class, String.class, new Getter<String, PlayerEnterPlot>() {
            public String get(PlayerEnterPlot e) {
                return e.getPlot().getId().toString();
            }
        }, 0);
        EventValues.registerEventValue(PlayerEnterPlot.class, Player.class, new Getter<Player, PlayerEnterPlot>() {
            public Player get(PlayerEnterPlot e) {
                return e.getPlayer();
            }
        }, 0);
        /*
        Plot Leave Event
        Has string value for plot id
        Has player value for player who caused event
         */
        Skript.registerEvent("PlotSquared: Plot Exit", PlotSkEvent.class, PlayerLeavePlot.class, "[PlotSquared] plot exit[ing]", "[PlotSquared] exit[ing] plot")
                .description("Called when a player leaves a plot")
                .examples("on plot exit:", "\tbroadcast \"%player% has left plot %event-string%\"");
        EventValues.registerEventValue(PlayerLeavePlot.class, String.class, new Getter<String, PlayerLeavePlot>() {
            public String get(PlayerLeavePlot e) {
                return e.getPlot().getId().toString();
            }
        }, 0);
        EventValues.registerEventValue(PlayerLeavePlot.class, Player.class, new Getter<Player, PlayerLeavePlot>() {
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
        EventValues.registerEventValue(PlayerClaimPlot.class, String.class, new Getter<String, PlayerClaimPlot>() {
            public String get(PlayerClaimPlot e) {
                return e.getPlot().getId().toString();
            }
        }, 0);
        EventValues.registerEventValue(PlayerClaimPlot.class, Player.class, new Getter<Player, PlayerClaimPlot>() {
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
        EventValues.registerEventValue(PlayerMergePlot.class, String.class, new Getter<String, PlayerMergePlot>() {
            public String get(PlayerMergePlot e) {
                return e.getPlotId().toString();
            }
        }, 0);
        EventValues.registerEventValue(PlayerMergePlot.class, Player.class, new Getter<Player, PlayerMergePlot>() {
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
        EventValues.registerEventValue(PlayerDeletePlot.class, String.class, new Getter<String, PlayerDeletePlot>() {
            public String get(PlayerDeletePlot e) {
                return e.getPlotId().toString();
            }
        }, 0);
        /*
        Plot Clear Event
        Has string value for plot id
         */
        Skript.registerEvent("PlotSquared: Plot Clear", PlotSkEvent.class, PlayerClearPlot.class, "[PlotSquared] [player] plot clear[ed]", "[PlotSquared] [player] clear[ed] plot")
                .description("Called when a plot is cleared")
                .examples("on plot delete:", "\tbroadcast \"%event-string% was cleared\"");
        EventValues.registerEventValue(PlayerClearPlot.class, String.class, new Getter<String, PlayerClearPlot>() {
            public String get(PlayerClearPlot e) {
                return e.getPlotId().toString();
            }
        }, 0);
        /*
        Plot Rating Event
        Has string value for plot id
        Has integer value for plot rating value
        Has player value for player who caused event
         */
        Skript.registerEvent("PlotSquared: Plot Rating", PlotSkEvent.class, PlayerRatePlot.class, "[PlotSquared] plot rat(e|ing)", "[PlotSquared] rat(e|ing) plot")
                .description("Called when a player rates a plot")
                .examples("on plot rate:", "\tbroadcast \"%player% has voted %event-integer% on %event-string%\"");
        EventValues.registerEventValue(PlayerRatePlot.class, String.class, new Getter<String, PlayerRatePlot>() {
            public String get(PlayerRatePlot e) {
                return e.getPlotId().toString();
            }
        }, 0);
        EventValues.registerEventValue(PlayerRatePlot.class, Player.class, new Getter<Player, PlayerRatePlot>() {
            public Player get(PlayerRatePlot e) {
                return e.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(PlayerRatePlot.class, Number.class, new Getter<Number, PlayerRatePlot>() {
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
        Skript.registerEvent("PlotSquared: Plot Denied", PlotSkEvent.class, PlayerDeniedFromPlot.class, "[PlotSquared] player deny player [from plot]", "[PlotSquared] player den(y|ied) from plot")
                .description("Called when a player denies a player from a plot")
                .examples("on player deny player from plot:", "\tbroadcast \"%player% denied %denied player% from %event-string%\"");
        EventValues.registerEventValue(PlayerDeniedFromPlot.class, String.class, new Getter<String, PlayerDeniedFromPlot>() {
            public String get(PlayerDeniedFromPlot e) {
                return e.getPlot().getId().toString();
            }
        }, 0);
        EventValues.registerEventValue(PlayerDeniedFromPlot.class, Player.class, new Getter<Player, PlayerDeniedFromPlot>() {
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
        Skript.registerEvent("PlotSquared: Plot Undenied", PlotSkEvent.class, PlayerUndeniedFromPlot.class, "[PlotSquared] player undeny player [from plot]", "[PlotSquared] player unden(y|ied) from plot")
                .description("Called when a player undenies a player from a plot")
                .examples("on player undeny player from plot:", "\tbroadcast \"%player% undenied %undenied player% from %event-string%\"");
        EventValues.registerEventValue(PlayerUndeniedFromPlot.class, String.class, new Getter<String, PlayerUndeniedFromPlot>() {
            public String get(PlayerUndeniedFromPlot e) {
                return e.getPlot().getId().toString();
            }
        }, 0);
        EventValues.registerEventValue(PlayerUndeniedFromPlot.class, Player.class, new Getter<Player, PlayerUndeniedFromPlot>() {
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
        Skript.registerEvent("PlotSquared: Plot Trusted", PlotSkEvent.class, PlayerTrustedOnPlot.class, "[PlotSquared] player trust player [(in|on|at) plot]", "[PlotSquared] player trusted (in|on|at) plot")
                .description("Called when a player trusts a player on a plot")
                .examples("on player trust player in plot:", "\tbroadcast \"%player% trusted %trusted player% in %event-string%\"");
        EventValues.registerEventValue(PlayerTrustedOnPlot.class, String.class, new Getter<String, PlayerTrustedOnPlot>() {
            public String get(PlayerTrustedOnPlot e) {
                return e.getPlot().getId().toString();
            }
        }, 0);
        EventValues.registerEventValue(PlayerTrustedOnPlot.class, Player.class, new Getter<Player, PlayerTrustedOnPlot>() {
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
        Skript.registerEvent("PlotSquared: Plot Untrusted", PlotSkEvent.class, PlayerUntrustedFromPlot.class, "[PlotSquared] player untrust player [in plot]", "[PlotSquared] player untrusted (on|in) plot")
                .description("Called when a player untrusts a player on a plot")
                .examples("on player untrust player in plot:", "\tbroadcast \"%player% untrusted %untrusted player% in %event-string%\"");
        EventValues.registerEventValue(PlayerUntrustedFromPlot.class, String.class, new Getter<String, PlayerUntrustedFromPlot>() {
            public String get(PlayerUntrustedFromPlot e) {
                return e.getPlot().getId().toString();
            }
        }, 0);
        EventValues.registerEventValue(PlayerUntrustedFromPlot.class, Player.class, new Getter<Player, PlayerUntrustedFromPlot>() {
            public Player get(PlayerUntrustedFromPlot e) {
                return e.getPlayer();
            }
        }, 0);
        /*
        Plot Teleport Event
        Has string value for plot id
        Has player value for player who caused event
         */
        Skript.registerEvent("PlotSquared: Plot Teleport", PlotSkEvent.class, PlotTeleportEvent.class, "[PlotSquared] [player] teleport to plot")
                .description("Called when a player teleports to a plot")
                .examples("on player teleport to plot:", "\tbroadcast \"%player% teleported to %event-string%\"");
        EventValues.registerEventValue(PlotTeleportEvent.class, String.class, new Getter<String, PlotTeleportEvent>() {
            public String get(PlotTeleportEvent e) {
                return e.getPlot().getId().toString();
            }
        }, 0);
        EventValues.registerEventValue(PlotTeleportEvent.class, Player.class, new Getter<Player, PlotTeleportEvent>() {
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
