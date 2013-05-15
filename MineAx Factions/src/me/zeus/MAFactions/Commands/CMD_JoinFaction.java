
package me.zeus.MAFactions.Commands;


import me.zeus.MAFactions.Core.MAFactions;
import me.zeus.MAFactions.Factions.Faction;
import me.zeus.MAFactions.Factions.FactionPlayer;
import me.zeus.MAFactions.Factions.FactionRole;
import me.zeus.MAFactions.Util.FMessenger;

import org.bukkit.entity.Player;



public class CMD_JoinFaction implements FactionCMD {

    @Override
    public boolean execute(Player sender, String[] args)
    {
        if (args.length >= 2)
        {
            if (args[0].equalsIgnoreCase("join"))
            {
                FactionPlayer fp = FactionPlayer.getPlayer(sender.getName());
                if (fp.getFaction() == null)
                {
                    for (Faction f : MAFactions.getInstance().getFactions().values())
                    {
                        if (args[1].equalsIgnoreCase(f.getName()))
                        {
                            if (fp.getPendingFaction().equals(f))
                            {
                                fp.setFaction(f);
                                fp.setFactionRole(FactionRole.USER);
                                fp.setPendingFaction(null);
                                FMessenger.joinedFaction(sender, f);
                            } else
                            {
                                FMessenger.invitationRequired(sender);
                                return false;
                            }
                        } else
                        {
                            FMessenger.invalidFaction(sender);
                            return false;
                        }
                    }
                } else
                {
                    FMessenger.alreadyHaveFaction(sender);
                    return false;
                }
            }
        }
        return false;
    }

}
