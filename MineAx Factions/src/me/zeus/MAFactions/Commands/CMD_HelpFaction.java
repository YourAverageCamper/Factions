
package me.zeus.MAFactions.Commands;


import me.zeus.MAFactions.Util.FMessenger;

import org.bukkit.entity.Player;



public class CMD_HelpFaction implements FactionCMD {

    @Override
    public boolean execute(Player sender, String[] args)
    {
        if (args.length >= 1)
        {
            if (args[0].equalsIgnoreCase("help"))
            {
                FMessenger.displayHelp(sender);
            }
        }
        return false;
    }

}
