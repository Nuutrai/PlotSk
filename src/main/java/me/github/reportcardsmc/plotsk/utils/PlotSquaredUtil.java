package me.github.reportcardsmc.plotsk.utils;

import com.plotsquared.core.PlotAPI;
import com.plotsquared.core.plot.Plot;
import com.plotsquared.core.plot.PlotId;
import org.jetbrains.annotations.Nullable;


public class PlotSquaredUtil {

    public static PlotAPI plotAPI = new PlotAPI();

    public static Plot getPlot(@Nullable String id) {
        if (id == null) return null;
        PlotId plotId = PlotId.fromString(id);
        for (Plot plot : plotAPI.getAllPlots()) {
            if (plot.getId().equals(plotId)) return plot;
        }
        return null;
    }

}
