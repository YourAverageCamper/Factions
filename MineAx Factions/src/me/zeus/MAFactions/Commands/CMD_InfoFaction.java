
package me.zeus.MAFactions.Commands;


import me.zeus.MAFactions.Factions.FactionPlayer;
import me.zeus.MAFactions.Util.FMessenger;

import org.bukkit.entity.Player;



public class CMD_InfoFaction implements FactionCMD {

    @Override
    public boolean execute(Player sender, String[] args)
    {
        if (args.length >= 1)
        {
            if (args[0].equalsIgnoreCase("info"))
            {
                FactionPlayer fp = FactionPlayer.getPlayer(sender.getName());
                if (fp.getFaction() != null)
                {
                    FMessenger.displayStatsWith(sender);
                } else
                {
                    FMessenger.displayStatsWithout(sender);
                    return false;
                }
            }
        }
        return false;
    }

}
