
package me.zeus.MAFactions.Commands;


import me.zeus.MAFactions.Core.MAFactions;
import me.zeus.MAFactions.Util.FMessenger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class FactionCommand implements CommandExecutor {

    // ================================================================== \\

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (sender instanceof Player)
        {
            for (FactionCMD fcmd : MAFactions.getInstance().getCommands())
            {
                fcmd.execute(((Player) sender), args);
            }
            if (args.length == 0)
            {
                FMessenger.displayHelp(((Player) sender));
            }
        }
        return false;
    }
    // ================================================================== \\

}
