package me.github.reportcardsmc.plotsk.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.plotsquared.core.plot.Plot;
import com.plotsquared.core.plot.flag.PlotFlag;
import me.github.reportcardsmc.plotsk.utils.PlotSquaredUtil;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Flags of Plot")
@Description("A list of flags that are added to the plot")
@Examples({"command /flags:", "    trigger:", "        send \"Flags: %flags in plot plot at player%\""})
@Since("1.0")
@RequiredPlugins("PlotSquared")
public class PlotFlagsExpr extends SimpleExpression<String> {
    static {
        Skript.registerExpression(PlotFlagsExpr.class, String.class, ExpressionType.COMBINED, "[PlotSquared] [value of] [all] flags (of|in|for) plot [with id] %string%");
    }

    private Expression<String> id;

    @Nullable
    @Override
    protected String[] get(Event e) {
        Plot plot;
        if (id.getSingle(e) == null | (plot = PlotSquaredUtil.getPlot(id.getSingle(e))) == null)
            return null;
        return plot.getFlags().stream().map(PlotFlag::getName).toArray(String[]::new);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "All Flags of plot: " + id.toString(e, debug);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        id = (Expression<String>) exprs[0];
        return true;
    }
}
