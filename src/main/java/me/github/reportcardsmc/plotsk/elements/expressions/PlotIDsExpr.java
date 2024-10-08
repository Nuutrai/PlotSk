package me.github.reportcardsmc.plotsk.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.plotsquared.core.plot.Plot;
import me.github.reportcardsmc.plotsk.utils.PlotSquaredUtil;
import org.bukkit.World;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@Name("All plot ID's")
@Description("A list of plot id's in a world, or all worlds")
@Examples({"command /ids:", "    trigger:", "        send \"Plots: %all plot ids in player's world%\""})
@Since("1.0")
@RequiredPlugins("PlotSquared")
public class PlotIDsExpr extends SimpleExpression<Plot> {

    static {
        Skript.registerExpression(PlotIDsExpr.class, Plot.class, ExpressionType.SIMPLE, "[PlotSquared] all [of the] plots [in %-world%]");
    }

    private Expression<World> worldExpression;

    @Nullable
    @Override
    protected Plot[] get(Event e) {
        World world;
        if (worldExpression != null) {
            world = worldExpression.getSingle(e);
            if (world != null)
                return (Plot[]) PlotSquaredUtil.plotAPI.getAllPlots().stream().filter((p) -> Objects.equals(p.getWorldName(), world.getName())).toArray();
        }
        return PlotSquaredUtil.plotAPI.getAllPlots().toArray(new Plot[0]);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Plot> getReturnType() {
        return Plot.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "Get all plot ids in server";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        if (exprs.length != 0) worldExpression = (Expression<World>) exprs[0];
        return true;
    }
}
