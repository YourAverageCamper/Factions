
package me.zeus.MAFactions.Commands;


import org.bukkit.entity.Player;

import me.zeus.MAFactions.Factions.FactionPlayer;
import me.zeus.MAFactions.Util.FMessenger;



public class CMD_HomeFaction implements FactionCMD {

    @Override
    public boolean execute(Player sender, String[] args)
    {
        if (args.length >= 1)
        {
            if (args[0].equalsIgnoreCase("home"))
            {
                FactionPlayer fp = FactionPlayer.getPlayer(sender.getName());
                if (fp.getFaction() != null)
                {
                    sender.teleport(fp.getFaction().getHome());
                    FMessenger.teleportedHome(sender);
                } else
                {
                    FMessenger.factionRequired(sender);
                    return false;
                }
            }
        }
        return false;
    }
}
