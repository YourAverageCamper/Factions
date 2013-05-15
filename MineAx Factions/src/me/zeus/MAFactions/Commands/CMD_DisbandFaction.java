
package me.zeus.MAFactions.Commands;


import me.zeus.MAFactions.Factions.FactionPlayer;
import me.zeus.MAFactions.Factions.FactionRole;
import me.zeus.MAFactions.Util.FMessenger;

import org.bukkit.entity.Player;



public class CMD_DisbandFaction implements FactionCMD {

    @Override
    public boolean execute(Player sender, String[] args)
    {
        if (args.length == 1)
        {
            if (args[0].equalsIgnoreCase("disband"))
            {
                FactionPlayer fp = FactionPlayer.getPlayer(sender.getName());
                if (fp.getFaction() != null)
                {
                    if (fp.getRole().equals(FactionRole.ADMIN))
                    {
                        FMessenger.factionDisbanded(fp.getFaction());
                        fp.getFaction().disband();
                        fp.faction = null;
                    } else
                    {
                        FMessenger.adminRequired(sender);
                        return false;
                    }
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
