
package me.zeus.MAFactions.Events;


import me.zeus.MAFactions.Core.MAFactions;
import me.zeus.MAFactions.Factions.FactionPlayer;
import me.zeus.MAFactions.Factions.FactionRole;
import me.zeus.MAFactions.Factions.SocialStatus;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;



public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        Player p = e.getPlayer();
        if (MAFactions.getInstance().getPlayers().containsKey(p.getName()))
        {
            System.out.println("Loaded stats for " + p.getName());
            return;
        }
        FactionPlayer.create(p.getName(), SocialStatus.NEUTRAL, FactionRole.UNOCCUPIED, null);
    }
}
