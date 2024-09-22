package me.github.reportcardsmc.plotsk.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.plotsquared.core.plot.Plot;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static me.github.reportcardsmc.plotsk.utils.PlotSquaredUtil.getPlot;

@Name("Owners of Plot")
@Description("A list of owners in a plot")
@Examples({"command /owners:", "    trigger:", "        send \"Plot Owners: %owners of plot plot at player%\""})
@Since("1.0")
@RequiredPlugins("PlotSquared")
public class OwnersOfPlotExpr extends SimpleExpression<OfflinePlayer> {

    static {
        Skript.registerExpression(OwnersOfPlotExpr.class, OfflinePlayer.class, ExpressionType.COMBINED, "[PlotSquared] owners of plot [with id] %string%", "[PlotSquared] plot owners of [id] %string%");
    }

    private Expression<String> id;

    @Nullable
    @Override
    protected OfflinePlayer[] get(Event e) {
        Plot plot;
        if (id.getSingle(e) == null || (plot = getPlot(id.getSingle(e))) == null || plot.getOwner() == null)
            return null; // Inspired from SkUniversal (us.donut.skuniversal.plotsquared.expressions.ExprPlotOwner)
        return plot.getOwners().stream().map(Bukkit::getOfflinePlayer).toArray(OfflinePlayer[]::new);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public @NotNull Class<? extends OfflinePlayer> getReturnType() {
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
        // Disabled since owners is read-only
    }

    @Nullable
    @Override
    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        return null;
    }
}
