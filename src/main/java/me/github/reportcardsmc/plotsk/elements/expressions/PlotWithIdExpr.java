package me.github.reportcardsmc.plotsk.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.plotsquared.core.plot.Plot;
import me.github.reportcardsmc.plotsk.utils.PlotSquaredUtil;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Plot With ID")
@Description("Returns a plot object from a given id.")
@Examples({"set {_plot} to plot with id \"7;3\""})
@Since("1.3.0")
@RequiredPlugins("PlotSquared")
public class PlotWithIdExpr extends SimpleExpression<Plot> {

    static {
        Skript.registerExpression(PlotWithIdExpr.class, Plot.class, ExpressionType.COMBINED, "[the] plot [with id] %string%");
    }

    private Expression<String> id;

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean kleenean, ParseResult parseResult) {

        id = (Expression<String>) exprs[0];
        return true;

    }

    @Override
    protected @Nullable Plot[] get(Event event) {
        return new Plot[]{PlotSquaredUtil.getPlot(id.getSingle(event))};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Plot> getReturnType() {
        return Plot.class;
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "the plot with id " + id.toString(event, b);
    }


}
