package me.github.reportcardsmc.plotsk.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.plotsquared.core.plot.Plot;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Plot at Location")
@Description("The plot at a location")
@Examples({"command /plot:", "    trigger:", "        send \"Plot: %plot at player%\""})
@Since("1.0")
@RequiredPlugins("PlotSquared")
public class PlotAtLocationExpr extends SimpleExpression<String> {
    static {
        Skript.registerExpression(PlotAtLocationExpr.class, String.class, ExpressionType.COMBINED, "[the] [ID of [the]] [PlotSquared] plot at %location%");
    }

    private Expression<Location> loc;

    @Nullable
    @Override
    protected String[] get(Event e) {
        Location l = loc.getSingle(e);
        if (l == null) return null;
        com.plotsquared.core.location.Location plotLoc = com.plotsquared.core.location.Location.at(l.getWorld().getName(), l.getBlockX(), l.getBlockY(), l.getBlockZ());
        Plot plot = plotLoc.getPlot();
        return plot == null ? null : new String[]{plot.getId().toString()};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "Plot at Location: " + loc.toString(e, debug);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        loc = (Expression<Location>) exprs[0];
        return true;
    }
}
