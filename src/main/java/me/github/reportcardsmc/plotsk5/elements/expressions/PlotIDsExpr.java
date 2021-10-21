package me.github.reportcardsmc.plotsk5.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.github.reportcardsmc.plotsk5.utils.PlotSquaredUtil;
import org.bukkit.World;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class PlotIDsExpr extends SimpleExpression<String> {

    static {
        Skript.registerExpression(PlotIDsExpr.class, String.class, ExpressionType.SIMPLE, "[plotsquared] all [of the] plot ids [in %-world%]");
    }

    private Expression<World> worldExpression;
    @Nullable
    @Override
    protected String[] get(Event e) {
        World world;
        if (worldExpression != null) {
            world = worldExpression.getSingle(e);
            if (world != null) return PlotSquaredUtil.plotAPI.getAllPlots().stream().filter((p) -> Objects.equals(p.getWorldName(), world.getName())).map((p) -> p.getId().toString()).toArray(String[]::new);
        }
        return PlotSquaredUtil.plotAPI.getAllPlots().stream().map((p) -> p.getId().toString()).toArray(String[]::new);
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
        return "Get all plot ids in server";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        if (exprs.length != 0) worldExpression = (Expression<World>) exprs[0];
        return true;
    }
}
