
package me.zeus.MAFactions.Events;


import me.zeus.MAFactions.Core.MAFactions;
import me.zeus.MAFactions.Factions.FactionPlayer;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;



public class LeaveListener implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent e)
    {
        FactionPlayer fp = FactionPlayer.getPlayer(e.getPlayer().getName());
        fp.save();

        MAFactions.getInstance().getPlayers().remove(e.getPlayer().getName());
        MAFactions.getInstance().getPlayers().put(e.getPlayer().getName(), fp);
        System.out.println("Saved data for " + e.getPlayer().getName());
    }
}
