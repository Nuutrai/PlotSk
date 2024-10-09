package me.github.reportcardsmc.plotsk.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.*;
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

@Name("Owners of Plot")
@Description("The main owner of the plot")
@Examples({"command /owner:", "    trigger:", "        send \"Plot Owner: %owner of plot plot at player%\""})
@Since("1.0")
@RequiredPlugins("PlotSquared")
public class OwnerOfPlotExpr extends SimpleExpression<OfflinePlayer> {

    static {
        Skript.registerExpression(OwnerOfPlotExpr.class, OfflinePlayer.class, ExpressionType.COMBINED, "[PlotSquared] owner of [the] %plot%", "[PlotSquared] plot owner of %plot%");
    }

    private Expression<Plot> plot;

    @Nullable
    @Override
    protected OfflinePlayer[] get(Event e) {

        Plot plot = this.plot.getSingle(e);

        if ((plot == null) || plot.getOwner() == null)
            return null; // Inspired from SkUniversal (us.donut.skuniversal.plotsquared.expressions.ExprPlotOwner)

        return new OfflinePlayer[]{Bukkit.getOfflinePlayer(plot.getOwner())};

    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends OfflinePlayer> getReturnType() {
        return OfflinePlayer.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "owner of plot " + plot.getSingle(e).toString();
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {

        plot = (Expression<Plot>) exprs[0];
        return true;

    }

    @Override
    public void change(Event e, @Nullable Object[] delta, Changer.ChangeMode mode) {
        Plot plot = this.plot.getSingle(e);

        if (plot == null) return;

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
