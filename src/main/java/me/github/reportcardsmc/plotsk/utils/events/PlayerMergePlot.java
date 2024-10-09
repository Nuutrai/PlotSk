package me.github.reportcardsmc.plotsk.utils.events;

import com.plotsquared.bukkit.player.BukkitPlayer;
import com.plotsquared.core.events.PlotMergeEvent;
import com.plotsquared.core.plot.PlotId;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

public class PlayerMergePlot extends BukkitPlotEvent implements Cancellable {
    private final PlotMergeEvent event;

    public PlayerMergePlot(PlotMergeEvent event) {
        this.event = event;
    }

    @Override
    public boolean isCancelled() {
        return event.getEventResult() == com.plotsquared.core.events.Result.DENY;
    }

    @Override
    public void setCancelled(boolean isCancelled) {
        event.setEventResult(isCancelled ? com.plotsquared.core.events.Result.DENY : com.plotsquared.core.events.Result.ACCEPT);
    }

    public PlotId getPlotId() {
        return event.getPlot().getId();
    }

    public String getDirection() {
        return event.getDir().toString();
    }

    public Player getPlayer() {
        return ((BukkitPlayer) event.getPlotPlayer()).player;
    }
}
