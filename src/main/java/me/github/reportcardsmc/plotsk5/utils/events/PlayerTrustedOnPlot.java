package me.github.reportcardsmc.plotsk5.utils.events;

import com.plotsquared.bukkit.player.BukkitPlayer;
import com.plotsquared.core.events.PlayerPlotTrustedEvent;
import com.plotsquared.core.plot.Plot;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PlayerTrustedOnPlot extends BukkitPlotEvent {
    private final PlayerPlotTrustedEvent event;

    public PlayerTrustedOnPlot(PlayerPlotTrustedEvent event) {
        this.event = event;
    }

    public Plot getPlot() {
        return event.getPlot();
    }

    public Player getPlayer() {
        return ((BukkitPlayer) event.getInitiator()).player;
    }

    public OfflinePlayer getTrusted() {
        return Bukkit.getOfflinePlayer(event.getPlayer());
    }
}
