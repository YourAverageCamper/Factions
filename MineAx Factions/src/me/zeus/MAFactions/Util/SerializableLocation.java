
package me.zeus.MAFactions.Util;


import java.io.Serializable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;



public class SerializableLocation implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1850143858207821726L;
    private double x, y, z;
    private float pitch, yaw;
    private String world;

    public SerializableLocation(Location loc)
    {
        x = loc.getX();
        y = loc.getY();
        z = loc.getZ();
        pitch = loc.getPitch();
        yaw = loc.getYaw();
        world = loc.getWorld().getName();
    }

    public Location getLocation()
    {
        World w = Bukkit.getServer().getWorld(world);
        if (w == null)
        {
            return null;
        }
        Location toRet = new Location(w, x, y, z);
        toRet.setPitch(pitch);
        toRet.setYaw(yaw);
        return toRet;
    }

}
