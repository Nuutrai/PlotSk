package me.github.reportcardsmc.plotsk;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import com.plotsquared.core.PlotSquared;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class PlotSk extends JavaPlugin {

    public static PlotSk instance;
    public static SkriptAddon addon;
    public static PlotSquared plot;

    @Override
    public void onEnable() {
        instance = this;
        plot = PlotSquared.get();
        if (plot == null) {
            getLogger().severe("You don't have plotsquared 7 installed.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        addon = Skript.registerAddon(this);
        if (!loadClasses()) {
            getLogger().severe("Couldn't load skript classes.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        } else {
            getLogger().info("Classes were registered.");
        }
        getLogger().info("Addon has been enabled");
    }

    private boolean loadClasses() {
        try {
            addon.loadClasses("me.github.reportcardsmc.plotsk", "elements");
        } catch (IOException e) {
            getLogger().severe(e.toString());
            return false;
        }
        return true;
    }
}
