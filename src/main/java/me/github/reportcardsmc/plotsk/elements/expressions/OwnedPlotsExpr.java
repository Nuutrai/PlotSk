package me.github.reportcardsmc.plotsk.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.plotsquared.bukkit.util.BukkitUtil;
import me.github.reportcardsmc.plotsk.utils.PlotSquaredUtil;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Owned Plots of Player")
@Description("A list of plot id's the player owns")
@Examples({"command /owned:", "    trigger:", "        send \"Owned Plots: %owned plots of player%\""})
@Since("1.0")
@RequiredPlugins("PlotSquared")
public class OwnedPlotsExpr extends SimpleExpression<String> {

    static {
        Skript.registerExpression(OwnedPlotsExpr.class, String.class, ExpressionType.COMBINED, "[PlotSquared] owned plots of %offlineplayer%", "[PlotSquared] %offlineplayer%['][s] owned plots");
    }

    private Expression<OfflinePlayer> playerExpression;

    @Nullable
    @Override
    protected String[] get(Event e) {
        if (playerExpression.getSingle(e) == null) return null;
        var plotPlayer = PlotSquaredUtil.plotAPI.wrapPlayer(playerExpression.getSingle(e).getUniqueId());
        if (plotPlayer == null) return null;
        return PlotSquaredUtil.plotAPI.getPlayerPlots(plotPlayer).stream().map(plot -> plot.getId().toString()).toArray(String[]::new);
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
        return "Checking owned plots of " + playerExpression.toString(e, debug);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        playerExpression = (Expression<OfflinePlayer>) exprs[0];
        return true;
    }
}
