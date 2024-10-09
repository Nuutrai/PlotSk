package me.github.reportcardsmc.plotsk.utils.events;

import com.plotsquared.bukkit.player.BukkitPlayer;
import com.plotsquared.core.events.PlayerLeavePlotEvent;
import com.plotsquared.core.plot.Plot;
import org.bukkit.entity.Player;

public class PlayerLeavePlot extends BukkitPlotEvent {

    private final PlayerLeavePlotEvent event;

    public PlayerLeavePlot(PlayerLeavePlotEvent event) {
        this.event = event;
    }

    public Plot getPlot() {
        return event.getPlot();
    }

    public Player getPlayer() {
        return ((BukkitPlayer) event.getPlotPlayer()).player;
    }
}
