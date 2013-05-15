
package me.zeus.MAFactions.Commands;


import me.zeus.MAFactions.Core.MAFactions;
import me.zeus.MAFactions.Factions.FactionPlayer;

import org.bukkit.entity.Player;



public class CMD_Debug implements FactionCMD {

    @Override
    public boolean execute(Player sender, String[] args)
    {
        if (args.length >= 1)
        {
            if (args[0].equalsIgnoreCase("debug") || args[0].equalsIgnoreCase("dbg"))
            {
                //!format
                FactionPlayer fp = FactionPlayer.getPlayer(sender.getName());
                String fac = "";
                String role = "";
                String pending = "";
                if(fp.getFaction() == null){ 
                    fac = "N/A";
                    role = "UNOCCUPIED";
                } else {
                    fac = fp.getFaction().getName();
                    role = fp.getRole().toString();
                }
                if(fp.getPendingFaction() != null){
                    pending = fp.getPendingFaction().getName();
                } else {
                    pending = "N/A";
                }
                String[] messages = new String[]{
                        "Current faction: " + fac,
                        "Pending faction: " + pending,
                        "Current faction role: " + role,
                        "Reputation: " + fp.getReputation(),
                        "All FactionPlayers: " + MAFactions.getInstance().getPlayers().keySet(),
                        "All Factions: " + MAFactions.getInstance().getFactions().keySet(),
                };
                //format
                for (int i = 0; i < messages.length; i++)
                {
                    sender.sendMessage(messages[i] + "\n");
                }
            }
        }
        return false;
    }

}
