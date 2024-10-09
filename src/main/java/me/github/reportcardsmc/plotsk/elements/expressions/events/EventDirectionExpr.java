package me.github.reportcardsmc.plotsk.elements.expressions.events;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.github.reportcardsmc.plotsk.utils.events.PlayerMergePlot;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Merge Direction")
@Description("The direction of a merge in a `plot merge` event")
@Examples({"on plot merge", "    send \"You have merged the plot in direction %merge direction%\" to player"})
@Since("1.0")
@RequiredPlugins("PlotSquared")
public class EventDirectionExpr extends SimpleExpression<String> {

    static {
        Skript.registerExpression(EventDirectionExpr.class, String.class, ExpressionType.SIMPLE, "[plotsquared] [event(-| )]merge(-| )direction");
    }

    @Nullable
    @Override
    protected String[] get(Event e) {
        if (e instanceof PlayerMergePlot main) {
            return new String[]{main.getDirection()};
        }
        return null;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "Direction in plot merge event";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        if (!getParser().isCurrentEvent(PlayerMergePlot.class)) {
            Skript.error("Cannot use 'merge direction' outside of a plot merge event");
            return false;
        }
        return true;
    }
}
