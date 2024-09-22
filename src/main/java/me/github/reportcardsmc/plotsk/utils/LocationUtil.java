package me.github.reportcardsmc.plotsk.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationUtil {
    private static Location toLocation(com.plotsquared.core.location.Location loc) {
        return new Location(Bukkit.getWorld(loc.getWorld().getName()), loc.getX(), loc.getY(), loc.getZ());
    }

}
