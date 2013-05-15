
package me.zeus.MAFactions.Commands;


import me.zeus.MAFactions.Factions.FactionPlayer;
import me.zeus.MAFactions.Util.FMessenger;

import org.bukkit.entity.Player;



public class CMD_InviteFaction implements FactionCMD {

    @Override
    public boolean execute(Player sender, String[] args)
    {
        if (args.length >= 2)
        {
            if (args[0].equalsIgnoreCase("invite"))
            {
                FactionPlayer fp = FactionPlayer.getPlayer(sender.getName());
                if (fp.getFaction() == null)
                {
                    FMessenger.factionRequired(sender);
                    return false;
                }
                for (Player p : sender.getServer().getOnlinePlayers())
                {
                    if (!args[1].equalsIgnoreCase(p.getName()))
                    {
                        FMessenger.invalidPlayer(sender);
                        return false;
                    }
                    if (args[1].equalsIgnoreCase(sender.getName()))
                    {
                        FMessenger.denySelfInvite(sender);
                        return false;
                    }
                    if (fp.getFaction().getMembers().size() >= fp.getFaction().getSlots())
                    {
                        FMessenger.notEnoughSlots(sender);
                        return false;
                    }
                    FMessenger.invitedToFaction(p, fp.getFaction());
                    fp.getFaction().invite(p);
                    return false;
                }
            }
        }
        return false;
    }
}
