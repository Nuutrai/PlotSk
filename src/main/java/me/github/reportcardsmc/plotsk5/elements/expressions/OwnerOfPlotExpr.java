package me.github.reportcardsmc.plotsk5.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import com.plotsquared.core.plot.Plot;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import static me.github.reportcardsmc.plotsk5.utils.PlotSquaredUtil.getPlot;

public class OwnerOfPlotExpr extends SimpleExpression<OfflinePlayer> {

    static {
        Skript.registerExpression(OwnerOfPlotExpr.class, OfflinePlayer.class, ExpressionType.COMBINED, "[plotsquared] owner[s] of plot [with id] %string%", "[plotsquared] plot owner[s] of [id] %string%");
    }

    private Expression<String> id;

    @Nullable
    @Override
    protected OfflinePlayer[] get(Event e) {
        Plot plot;
        if (id.getSingle(e) == null || (plot = getPlot(id.getSingle(e))) == null) return null; // Inspired from SkUniversal (us.donut.skuniversal.plotsquared.expressions.ExprPlotOwner)
        return plot.getOwners().stream().map(Bukkit::getOfflinePlayer).toArray(OfflinePlayer[]::new);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends OfflinePlayer> getReturnType() {
        return OfflinePlayer.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "Owners of plot: " + id.toString(e, debug);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        id = (Expression<String>) exprs[0];
        return true;
    }

    @Override
    public void change(Event e, @Nullable Object[] delta, Changer.ChangeMode mode) {
        Plot plot;
        if (id.getSingle(e) == null || (plot = getPlot(id.getSingle(e))) == null) return;
        OfflinePlayer player = (OfflinePlayer) delta[0];
        if (mode == Changer.ChangeMode.SET) {
            plot.setOwner(player.getUniqueId());
        }
    }

    @Nullable
    @Override
    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.SET) ? CollectionUtils.array(OfflinePlayer.class) : null;
    }
}
