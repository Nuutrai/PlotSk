package me.github.reportcardsmc.plotsk.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.plotsquared.core.plot.Plot;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Rating of Plot")
@Description("A plots average rating")
@Examples({"command /rating:", "    trigger:", "        send \"Plot Rating: %rating of plot plot at player%\""})
@Since("1.0")
@RequiredPlugins("PlotSquared")
public class PlotRatingExpr extends SimpleExpression<Number> {
    static {
        Skript.registerExpression(PlotRatingExpr.class, Number.class, ExpressionType.COMBINED, "[PlotSquared] [the] [average] rating of %plot%");
    }

    private Expression<Plot> plot;

    @Nullable
    @Override
    protected Number[] get(Event e) {
        if (this.plot.getSingle(e) == null) return null;
        return Double.isNaN(this.plot.getSingle(e).getAverageRating()) ? null : new Number[]{plot.getSingle(e).getAverageRating()};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "Rating of plot: " + plot.toString(e, debug);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        plot = (Expression<Plot>) exprs[0];
        return true;
    }
}
