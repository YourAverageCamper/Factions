
package me.zeus.MAFactions.Commands;


import me.zeus.MAFactions.Factions.FactionPlayer;
import me.zeus.MAFactions.Factions.FactionRole;
import me.zeus.MAFactions.Util.FMessenger;
import me.zeus.MAFactions.Util.SerializableLocation;

import org.bukkit.entity.Player;



public class CMD_SethomeFaction implements FactionCMD {

    @Override
    public boolean execute(Player sender, String[] args)
    {
        if (args.length >= 1)
        {
            if (args[0].equalsIgnoreCase("sethome"))
            {
                FactionPlayer fp = FactionPlayer.getPlayer(sender.getName());
                if (fp.getFaction() != null)
                {
                    if (fp.getRole().equals(FactionRole.ADMIN) || fp.getRole().equals(FactionRole.MOD))
                    {
                        fp.getFaction().setHome(new SerializableLocation(sender.getLocation()));
                        FMessenger.homeSet(sender);
                    } else
                    {
                        FMessenger.adminOrModRequired(sender);
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
