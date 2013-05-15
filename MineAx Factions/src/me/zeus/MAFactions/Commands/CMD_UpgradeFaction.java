
package me.zeus.MAFactions.Commands;


import me.zeus.MAFactions.Factions.Faction;
import me.zeus.MAFactions.Factions.FactionPlayer;
import me.zeus.MAFactions.Factions.FactionRole;
import me.zeus.MAFactions.Util.FMessenger;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;



public class CMD_UpgradeFaction implements FactionCMD {

    @Override
    public boolean execute(Player sender, String[] args)
    {
        if (args.length >= 1)
        {
            if (args[0].equalsIgnoreCase("upgrade"))
            {
                FactionPlayer fp = FactionPlayer.getPlayer(sender.getName());
                if (fp.getFaction() == null)
                {
                    FMessenger.factionRequired(sender);
                    return false;
                }
                if (fp.getRole() != FactionRole.ADMIN)
                {
                    FMessenger.adminRequired(sender);
                    return false;
                }
                if (!sender.getInventory().contains(new ItemStack(Material.EMERALD, 10)))
                {
                    FMessenger.requiredEmeralds(sender, 10);
                    return false;
                }

                Faction f = fp.getFaction();
                for (FactionPlayer fpp : fp.getFaction().getMembers())
                {
                    FMessenger.factionUpgraded(fpp.getName(), f.getLevel(), f.getSlots());
                }
                return false;
            }
        }
        return false;
    }
}
