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

@Name("Owned Plots of Player")
@Description("A list of plot id's the player owns")
@Examples({"command /owned:", "    trigger:", "        send \"Owned Plots: %owned plots of player%\""})
@Since("1.0")
@RequiredPlugins("PlotSquared")
public class OwnedPlotsExpr extends SimpleExpression<Plot> {

    static {
        Skript.registerExpression(OwnedPlotsExpr.class, Plot.class, ExpressionType.COMBINED, "[PlotSquared] [owned] plots of %offlineplayer%", "[PlotSquared] %offlineplayer%'[s] [owned] plots");
    }

    private Expression<OfflinePlayer> playerExpression;

    @Override
    protected Plot[] get(Event e) {

        if (playerExpression.getSingle(e) == null) return null;

        var plotPlayer = PlotSquaredUtil.plotAPI.wrapPlayer(playerExpression.getSingle(e).getUniqueId());

        if (plotPlayer == null) return null;

        return PlotSquaredUtil.plotAPI.getPlayerPlots(plotPlayer).toArray(Plot[]::new);

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
    public String toString( Event e, boolean debug) {
        return "owned plots of " + playerExpression.toString(e, debug);
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {

        playerExpression = (Expression<OfflinePlayer>) exprs[0];
        return true;

    }
}
