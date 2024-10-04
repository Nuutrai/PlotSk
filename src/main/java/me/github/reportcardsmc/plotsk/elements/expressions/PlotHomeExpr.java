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
import com.plotsquared.core.location.BlockLoc;
import com.plotsquared.core.plot.Plot;
import com.sk89q.worldedit.math.BlockVector3;
import me.github.reportcardsmc.plotsk.utils.PlotSquaredUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;


@Name("Home of Plot")
@Description("A plots home location")
@Examples({"command /home:", "    trigger:", "        send \"Plot Home: %home of plot plot at player%\""})
@Since("1.0")
@RequiredPlugins("PlotSquared")
public class PlotHomeExpr extends SimpleExpression<Location> {
    static {
        Skript.registerExpression(PlotHomeExpr.class, Location.class, ExpressionType.COMBINED, "[PlotSquared] [the] home [location] of %plot%");
    }

    private Expression<Plot> plot;

    @Nullable
    @Override
    protected Location[] get(Event e) {

        Plot plot = this.plot.getSingle(e);

        if (plot == null) return null;

        BlockVector3 vector3 = plot.getHomeSynchronous().getBlockVector3();

        return new Location[]{new Location(Bukkit.getWorld(plot.getWorldName()), vector3.getX(), vector3.getY(), vector3.getZ())};

    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Location> getReturnType() {
        return Location.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "Home of plot: " + plot.getSingle(e).getId();
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {

        plot = (Expression<Plot>) exprs[0];
        return true;

    }

    @Nullable
    @Override
    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.SET ? CollectionUtils.array(Location.class) : null);
    }

    @Override
    public void change(Event e, @Nullable Object[] delta, Changer.ChangeMode mode) {

        Location location = (Location) delta[0];
        Plot plot = this.plot.getSingle(e);

        if (plot == null) return;

        com.plotsquared.core.location.Location bot = plot.getManager().getPlotBottomLocAbs(plot.getId());

        int x = location.getBlockX() - bot.getX();
        int z = location.getBlockZ() - bot.getZ();
        int y = location.getBlockY();

        plot.setHome(new BlockLoc(x, y, z, location.getYaw(), location.getPitch()));

    }
}
