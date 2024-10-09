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

import java.util.HashSet;

@Name("Trusted Players in Plot")
@Description("A list of players that are trusted in the plot")
@Examples({"command /trusted:", "    trigger:", "        send \"Trusted players: %trusted players in plot plot at player%\""})
@Since("1.0")
@RequiredPlugins("PlotSquared")
public class TrustedInPlotExpr extends SimpleExpression<OfflinePlayer> {

    static {
        Skript.registerExpression(TrustedInPlotExpr.class, OfflinePlayer.class, ExpressionType.COMBINED, "[PlotSquared] trusted player[s] (in|on) %plot%");
    }

    private Expression<Plot> plot;

    @Nullable
    @Override
    protected OfflinePlayer[] get(Event e) {
        if (this.plot.getSingle(e) == null) return null;
        return plot.getSingle(e).getTrusted().stream().map(Bukkit::getOfflinePlayer).toArray(OfflinePlayer[]::new);
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
        return "Find trusted players in plot " + plot.toString(e, debug);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        plot = (Expression<Plot>) exprs[0];
        return true;
    }

    @Override
    public void change(Event e, @Nullable Object[] delta, Changer.ChangeMode mode) {
        if (this.plot.getSingle(e) == null) return;
        OfflinePlayer player = (OfflinePlayer) delta[0];
        if (player == null) return;
        switch (mode) {
            case ADD:
                plot.getSingle(e).addTrusted(player.getUniqueId());
                break;
            case REMOVE:
                plot.getSingle(e).removeTrusted(player.getUniqueId());
                break;
            case REMOVE_ALL:
            case RESET:
                plot.getSingle(e).setTrusted(new HashSet<>());
                break;
        }
    }

    @Nullable
    @Override
    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        return valid(mode) ? CollectionUtils.array(OfflinePlayer.class) : null;
    }

    private boolean valid(Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.ADD || mode == Changer.ChangeMode.REMOVE || mode == Changer.ChangeMode.REMOVE_ALL || mode == Changer.ChangeMode.RESET);
    }
}
