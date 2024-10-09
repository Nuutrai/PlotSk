package me.github.reportcardsmc.plotsk.elements.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.plotsquared.core.plot.Plot;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Player is Trusted")
@Description("Check if a player is trusted in a plot.")
@Examples({"if player is trusted in plot with id \"0;0\":", "    send \"You are trusted in 0;0!\""})
@Since("1.0")
@RequiredPlugins("PlotSquared")
public class PlayerIsTrustedCond extends Condition {

    static {
        Skript.registerCondition(PlayerIsTrustedCond.class,
                "[PlotSquared] %offlineplayer% is trusted in %plot%",
                "[PlotSquared] %offlineplayer% is(n't| not) trusted in %plot%"
                );
    }

    private Expression<OfflinePlayer> playerExpression;
    private Expression<Plot> plot;

    @Override
    public boolean check(Event e) {
        Plot plot = this.plot.getSingle(e);
        OfflinePlayer player = playerExpression.getSingle(e);
        if (plot == null || player == null) return false;
        boolean value = plot.isOwner(player.getUniqueId()) || plot.getTrusted().stream().anyMatch((v) -> v == player.getUniqueId());
        if (isNegated()) return !value;
        return value;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return playerExpression.toString(e, debug) + (isNegated() ? " is not" : " is") + " trusted in " + plot.toString(e, debug) + "?";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        playerExpression = (Expression<OfflinePlayer>) exprs[0];
        plot = (Expression<Plot>) exprs[1];
        setNegated(parseResult.mark == 2);
        return true;
    }
}
