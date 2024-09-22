package me.github.reportcardsmc.plotsk.utils.events;

import com.plotsquared.bukkit.player.BukkitPlayer;
import com.plotsquared.core.events.PlayerEnterPlotEvent;
import com.plotsquared.core.plot.Plot;
import org.bukkit.entity.Player;

public class PlayerEnterPlot extends BukkitPlotEvent {
    private final PlayerEnterPlotEvent event;

    public PlayerEnterPlot(PlayerEnterPlotEvent event) {
        this.event = event;
    }

    public Plot getPlot() {
        return event.getPlot();
    }

    public Player getPlayer() {
        return ((BukkitPlayer) event.getPlotPlayer()).player;
    }
}
