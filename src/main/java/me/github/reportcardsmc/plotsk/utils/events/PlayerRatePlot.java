package me.github.reportcardsmc.plotsk.utils.events;

import com.plotsquared.bukkit.player.BukkitPlayer;
import com.plotsquared.core.events.PlotRateEvent;
import com.plotsquared.core.plot.PlotId;
import com.plotsquared.core.plot.Rating;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

public class PlayerRatePlot extends BukkitPlotEvent implements Cancellable {
    private final PlotRateEvent event;

    public PlayerRatePlot(PlotRateEvent event) {
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

    public int getRating() {
        Rating rating = event.getRating();
        if (rating == null) return 0;
        return rating.getLike() ? 10 : rating.getAggregate();
    }

    public PlotId getPlotId() {
        return event.getPlot().getId();
    }

    public Player getPlayer() {
        return ((BukkitPlayer) event.getRater()).player;
    }
}
