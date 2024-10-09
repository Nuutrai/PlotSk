package me.github.reportcardsmc.plotsk.elements.expressions.events;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.github.reportcardsmc.plotsk.utils.events.PlayerDeniedFromPlot;
import me.github.reportcardsmc.plotsk.utils.events.PlayerUndeniedFromPlot;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Denied Player")
@Description("The denied/undenied player in a `plot deny`, and `plot undeny` event")
@Examples({"on plot deny", "    send \"You have been denied from %event-string%\" to denied player"})
@Since("1.0")
@RequiredPlugins("PlotSquared")
public class DeniedPlayerExpr extends SimpleExpression<OfflinePlayer> {

    static {
        Skript.registerExpression(DeniedPlayerExpr.class, OfflinePlayer.class, ExpressionType.SIMPLE, "[the] denied(-| )player", "[the] undenied(-| )player");
    }

    @Nullable
    private static OfflinePlayer getPlayer(final @Nullable Event e) {
        return switch (e) {
            case PlayerDeniedFromPlot main -> main.getDenied();
            case PlayerUndeniedFromPlot main -> main.getUndenied();
            case null, default -> null;
        };
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        if (!getParser().isCurrentEvent(PlayerDeniedFromPlot.class, PlayerUndeniedFromPlot.class)) {
            Skript.error("Cannot use 'denied player' outside of a denied/undenied from plot event");
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
        return "the denied player in a plot denied event";
    }
}
