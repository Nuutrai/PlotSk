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
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;


@Name("Biome of Plot")
@Description("A plots biome type")
@Examples({"command /biome:", "    trigger:", "        send \"Plot Biome: %biome of plot plot at player%\""})
@Since("1.0")
@RequiredPlugins("PlotSquared")
public class PlotBiomeExpr extends SimpleExpression<String> {
    static {
        Skript.registerExpression(PlotBiomeExpr.class, String.class, ExpressionType.COMBINED, "[PlotSquared] [the] biome of [the] %plot%");
    }

    private Expression<Plot> plot;

    @Nullable
    @Override
    protected String[] get(Event e) {

        Plot plot = this.plot.getSingle(e);

        if (plot == null) return null;

        return new String[]{plot.getBiomeSynchronous().toString()};

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
        return "biome of plot " + plot.getSingle(e).getId();
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {

        plot = (Expression<Plot>) exprs[0];
        return true;

    }
}
