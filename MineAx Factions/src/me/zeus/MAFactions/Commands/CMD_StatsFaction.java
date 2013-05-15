
package me.zeus.MAFactions.Commands;


import me.zeus.MAFactions.Factions.FactionPlayer;
import me.zeus.MAFactions.Util.FMessenger;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;



public class CMD_StatsFaction implements FactionCMD {

    @Override
    public boolean execute(Player sender, String[] args)
    {
        if (args.length >= 1)
        {
            if (args[0].equalsIgnoreCase("stats"))
            {
                FactionPlayer fp = FactionPlayer.getPlayer(sender.getName());
                if (fp == null)
                {
                    sender.kickPlayer("Your data is unsynced with the server! If this becomes persistent, contact an administrator!");
                    return false;
                }
                if (fp.getFaction() == null)
                {
                    FMessenger.factionRequired(sender);
                    return false;
                }
                sender.sendMessage(ChatColor.GRAY + "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                sender.sendMessage(" \n \n ");
                sender.sendMessage(ChatColor.GREEN + "Faction: " + ChatColor.RESET + fp.getFaction().getName());
                sender.sendMessage(ChatColor.GREEN + "Description: " + ChatColor.RESET + fp.getFaction().getDescription());
                sender.sendMessage(ChatColor.GREEN + "Level: " + ChatColor.RESET + fp.getFaction().getLevel());
                sender.sendMessage(ChatColor.GREEN + "Slots: " + ChatColor.RESET + fp.getFaction().getSlots());
                sender.sendMessage(" \n \n ");
                sender.sendMessage(ChatColor.GRAY + "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                return false;
            }
        }
        return false;
    }
}
