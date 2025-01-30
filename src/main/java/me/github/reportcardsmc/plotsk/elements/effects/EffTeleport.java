package me.github.reportcardsmc.plotsk.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.plotsquared.core.command.Command;
import com.plotsquared.core.location.Location;
import com.plotsquared.core.player.PlotPlayer;
import com.plotsquared.core.plot.Plot;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Teleport To Plot")
@Description("Teleports a player to a plot.")
@Examples({"send player to home plot of player"})
@Since("1.3.0")
@RequiredPlugins("PlotSquared")
public class EffTeleport extends Effect {

    static {
        Skript.registerEffect(EffTeleport.class, "plot( |-)teleport %player% to %plot%");
    }

    Expression<Player> player;
    Expression<Plot> plot;

    @Override
    public boolean init(Expression<?>[] exprs, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {

        player = (Expression<Player>) exprs[0];
        plot = (Expression<Plot>) exprs[1];

        return true;

    }

    @Override
    protected void execute(Event e) {

        Plot plot = this.plot.getSingle(e);

        if (plot == null) {
            Skript.error("This plot doesn't exist!");
            return;
        }

        Location plotLoc = plot.getHomeSynchronous();
        World world = Bukkit.getServer().getWorld(plotLoc.getWorldName());
        org.bukkit.Location location = new org.bukkit.Location(world, plotLoc.getX(), plotLoc.getY(), plotLoc.getZ());

        Bukkit.getLogger().info(location.toString());

        player.getSingle(e).teleport(location);

    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "plot-teleport " + player.toString(event, b) + " to plot " + plot.toString(event, b);
    }


}
