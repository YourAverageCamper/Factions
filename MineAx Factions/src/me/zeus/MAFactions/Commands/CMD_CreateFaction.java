
package me.zeus.MAFactions.Commands;


import me.zeus.MAFactions.Factions.Faction;
import me.zeus.MAFactions.Factions.FactionPlayer;
import me.zeus.MAFactions.Util.FMessenger;
import me.zeus.MAFactions.Util.SerializableLocation;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;



public class CMD_CreateFaction implements FactionCMD {

    @Override
    public boolean execute(Player sender, String[] args)
    {
        if (args.length >= 2)
        {
            if (args[0].equalsIgnoreCase("create"))
            {
                FactionPlayer fp = FactionPlayer.getPlayer(sender.getName());
                if (fp.getFaction() != null)
                {
                    FMessenger.alreadyHaveFaction(sender);
                    return false;
                }
                if (sender.getInventory().containsAtLeast(new ItemStack(Material.EMERALD), 10))
                {
                    sender.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.EMERALD, 10) });
                    Faction.createFaction(sender, args[1], "N/A", 0, 5, new SerializableLocation(sender.getLocation()));
                    FMessenger.factionCreated(sender.getName(), args[1]);
                } else
                {
                    FMessenger.requiredEmeralds(sender, 10);
                    return false;
                }
            }
        }
        return false;
    }
}
