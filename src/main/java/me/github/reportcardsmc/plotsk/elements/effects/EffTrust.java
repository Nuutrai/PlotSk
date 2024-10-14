package me.github.reportcardsmc.plotsk.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.plotsquared.core.plot.Plot;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;

import java.util.UUID;

@Name("Trust To Plot")
@Description("Trusts and untrusts a player to and from a plot.")
@Examples({
        "if player is trusted in plot(0,5):",
        "\tuntrust player from plot(0,5)"})
@Since("1.3.0")
@RequiredPlugins("PlotSquared")
public class EffTrust extends Effect {

    static {
        Skript.registerEffect(EffTrust.class,
                "trust %offlineplayer% to %plot%",
                "untrust %offlineplayer% from %plot%");
    }

    private Expression<Plot> plot;
    private Expression<OfflinePlayer> player;
    private boolean untrust;

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean kleenean, SkriptParser.ParseResult parseResult) {

        plot = (Expression<Plot>) exprs[1];
        player = (Expression<OfflinePlayer>) exprs[0];
        untrust = (matchedPattern == 1);

        return true;
    }

    @Override
    protected void execute(Event event) {

        Plot plot = this.plot.getSingle(event);
        UUID player = this.player.getSingle(event).getUniqueId();


        if (!untrust) {
            if (!plot.getTrusted().contains(player)) {
                plot.addTrusted(player);
            }
        } else {
            if (plot.getTrusted().contains(player)) {
                plot.removeTrusted(player);
            }
        }

    }

    @Override
    public String toString(@org.jetbrains.annotations.Nullable Event event, boolean b) {
        return (untrust ? "un" : "") + "trust " + player.toString() + (untrust ? "from" : "to") + " plot with id " + plot.toString();
    }

}
