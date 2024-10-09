package me.github.reportcardsmc.plotsk.utils.events;

import com.plotsquared.bukkit.player.BukkitPlayer;
import com.plotsquared.core.events.PlayerPlotDeniedEvent;
import com.plotsquared.core.plot.Plot;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PlayerDeniedFromPlot extends BukkitPlotEvent {
    private final PlayerPlotDeniedEvent event;

    public PlayerDeniedFromPlot(PlayerPlotDeniedEvent event) {
        this.event = event;
    }

    public Plot getPlot() {
        return event.getPlot();
    }

    public Player getPlayer() {
        return ((BukkitPlayer) event.getInitiator()).player;
    }

    public OfflinePlayer getDenied() {
        return Bukkit.getOfflinePlayer(event.getPlayer());
    }
}
