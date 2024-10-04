package me.github.reportcardsmc.plotsk.elements.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.sk89q.worldedit.math.BlockVector3;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Location in Road")
@Description("Check if a location is in a plot road.")
@Examples({"if player is in the plot road:", "    send \"You are in the road!\""})
@Since("1.0")
@RequiredPlugins("PlotSquared")
public class LocationInRoadCond extends Condition {
    static {
        Skript.registerCondition(LocationInRoadCond.class,
                "[PlotSquared] %location% is in[side] [the] [plot] road",
                "[PlotSquared] %location% is(n't| not) in[side] [the] [plot] road"
        );
    }

    Expression<Location> location;

    @Override
    public boolean check(Event e) {
        Location loc = location.getSingle(e);
        if (loc == null) return isNegated();
        com.plotsquared.core.location.Location plotLoc = com.plotsquared.core.location.Location.at(loc.getWorld().getName(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
        boolean isRoad = plotLoc.isPlotRoad();
        if (isNegated()) return !isRoad;
        return isRoad;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "Location in plot world road " + location.toString(e, debug);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        location = (Expression<Location>) exprs[0];
        setNegated(matchedPattern == 1);
        return true;
    }
}
