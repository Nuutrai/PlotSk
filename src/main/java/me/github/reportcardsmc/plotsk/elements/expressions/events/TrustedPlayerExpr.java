package me.github.reportcardsmc.plotsk.elements.expressions.events;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.github.reportcardsmc.plotsk.utils.events.PlayerTrustedOnPlot;
import me.github.reportcardsmc.plotsk.utils.events.PlayerUntrustedFromPlot;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Trusted Player")
@Description("The trusted/untrusted player in a `plot trust`, and `plot untrust` event")
@Examples({"on plot trust", "    send \"You have been trusted on %event-string%\" to trusted player"})
@Since("1.0")
@RequiredPlugins("PlotSquared")
public class TrustedPlayerExpr extends SimpleExpression<OfflinePlayer> {

    static {
        Skript.registerExpression(TrustedPlayerExpr.class, OfflinePlayer.class, ExpressionType.SIMPLE, "[the] trusted(-| )player", "[the] untrusted(-| )player");
    }

    @Nullable
    private static OfflinePlayer getPlayer(final @Nullable Event e) {
        if (e == null) return null;
        if (e instanceof PlayerTrustedOnPlot) {
            final PlayerTrustedOnPlot main = (PlayerTrustedOnPlot) e;
            return main.getTrusted();
        } else if (e instanceof PlayerUntrustedFromPlot) {
            final PlayerUntrustedFromPlot main = (PlayerUntrustedFromPlot) e;
            return main.getTrusted();
        }
        return null;
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        if (!ScriptLoader.isCurrentEvent(PlayerTrustedOnPlot.class, PlayerUntrustedFromPlot.class)) {
            Skript.error("Cannot use 'trusted player' outside of a trusted/untrusted from plot event");
            return false;
        }
        return true;
    }

    @Nullable
    @Override
    protected OfflinePlayer[] get(Event e) {
        return new OfflinePlayer[]{getPlayer(e)};
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
        return "the [un]trusted player in a plot [un]trusted event";
    }
}
