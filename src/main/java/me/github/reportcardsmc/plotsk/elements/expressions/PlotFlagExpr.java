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
import com.plotsquared.core.plot.flag.GlobalFlagContainer;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Plot Flag")
@Description("Get the value of a plot flag")
@Examples({"command /flag <text>:", "    trigger:", "        send \"Flag %arg-1%: %flag arg-1 in plot at player%\""})
@Since("1.0")
@RequiredPlugins("PlotSquared")
public class PlotFlagExpr extends SimpleExpression<Object> {
    static {
        Skript.registerExpression(PlotFlagExpr.class, Object.class, ExpressionType.COMBINED, "[PlotSquared] [value of] [the] flag %string% (in|for) %plot%");
    }

    private Expression<Plot> plot;
    private Expression<String> flag;

    @Nullable
    @Override
    protected Object[] get(Event e) {

        Plot plot = this.plot.getSingle(e);

        if (flag.getSingle(e) == null || (plot == null))
            return null;

        return new Object[]{plot.getFlag(GlobalFlagContainer.getInstance().getFlagFromString(flag.getSingle(e)))};

    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<?> getReturnType() {
        return Object.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "flag " + flag.toString(e, debug) + " of plot " + plot.getSingle(e).getId();
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {

        plot = (Expression<Plot>) exprs[1];
        flag = (Expression<String>) exprs[0];
        return true;

    }

    @Nullable
    @Override
    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        return mode.equals(Changer.ChangeMode.SET) || mode.equals(Changer.ChangeMode.DELETE) || mode.equals(Changer.ChangeMode.RESET) ? CollectionUtils.array(Boolean.class) : null;
    }

    @Override
    public void change(Event e, @Nullable Object[] delta, Changer.ChangeMode mode) {

        Boolean value = (Boolean) delta[0];
        Plot plot = this.plot.getSingle(e);
        String f = flag.getSingle(e);

        if (f == null || value == null || plot == null) return;

        switch (mode) {
            case SET:
                plot.setFlag(GlobalFlagContainer.getInstance().getFlagClassFromString(f), value.toString());
                break;
            case RESET, DELETE:
                plot.removeFlag(GlobalFlagContainer.getInstance().getFlagFromString(f));
                break;
        }
    }
}
