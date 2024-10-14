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
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Home Plot")
@Description("Returns the home plot of a player.")
@Examples({"send player to home plot of player"})
@Since("1.3")
@RequiredPlugins("PlotSquared")
public class ExprHomePlot extends SimpleExpression<Plot> {

    static {
        Skript.registerExpression(ExprHomePlot.class, Plot.class, ExpressionType.COMBINED, "home plot of %offlineplayer%");
    }

    private Expression<OfflinePlayer> player;

    @Override
    protected Plot[] get(Event event) {
        return new Plot[]{PlotSquaredUtil.getPlotOwner(player.getSingle(event))};
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
        return "home plot of " + player.toString();
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {

        player = (Expression<OfflinePlayer>) expressions[0];
        return true;

    }
}
