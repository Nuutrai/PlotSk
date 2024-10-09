package me.github.reportcardsmc.plotsk.utils.events;

import com.plotsquared.core.events.PlotClearEvent;
import com.plotsquared.core.plot.PlotId;
import org.bukkit.event.Cancellable;

public class PlayerClearPlot extends BukkitPlotEvent implements Cancellable {
    private final PlotClearEvent event;

    public PlayerClearPlot(PlotClearEvent event) {
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
        return event.getPlotId();
    }
}
