
package me.zeus.MAFactions.Util;


import me.zeus.MAFactions.Factions.Faction;
import me.zeus.MAFactions.Factions.FactionPlayer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;



public class FMessenger {

    /* Required X emeralds */
    public static void requiredEmeralds(Player to, int required)
    {
        to.sendMessage("§cYou do not have enough emeralds to do this. Required: §e" + required);
    }

    /* Faction created */
    public static void factionCreated(String e, String f)
    {
        Bukkit.broadcastMessage("§3" + e + "§e created the faction §e" + f);
    }

    /* Faction disbanded */
    public static void factionDisbanded(Faction f)
    {
        Bukkit.broadcastMessage("§3" + f.getOwner().getName() + "§e disbanded  §e" + f.getName());
    }

    /* Send message */
    public static void sendMessage(Player to, String message)
    {
        to.sendMessage(message.replace("&", "§"));
    }

    /* Display help menu */
    public static void displayHelp(Player player)
    {
        player.sendMessage(ChatColor.GOLD + "§3/f help - §eShows this message");
        player.sendMessage(ChatColor.GOLD + "§3/f list - §eShows this message");
        player.sendMessage(ChatColor.GOLD + "§3/f create - §eCreates faction");
        player.sendMessage(ChatColor.GOLD + "§3/f disband - §eDisbands current faction");
        player.sendMessage(ChatColor.GOLD + "§3/f upgrade - §eUpgrades current faction");
        player.sendMessage(ChatColor.GOLD + "§3/f ally <name> - §eAllies specified faction");
        player.sendMessage(ChatColor.GOLD + "§3/f enemy <name> - §eEnemies specified faction");
        player.sendMessage(ChatColor.GOLD + "§3/f mod <user> - §eSets faction mod for user");
        player.sendMessage(ChatColor.GOLD + "§3/f unclaim <name> - §eUnclaims current chunk");
        player.sendMessage(ChatColor.GOLD + "§3/f claim - §eClaims current chunk");
        player.sendMessage(ChatColor.GOLD + "§3/f home - §eTeleports to faction home");
        player.sendMessage(ChatColor.GOLD + "§3/f sethome - §eSets faction home");
        player.sendMessage(ChatColor.GOLD + "§3/f invite <user> - §eInvites user to faction");
        player.sendMessage(ChatColor.GOLD + "§3/f kick <user> - §eKicks user from faction");
        player.sendMessage(ChatColor.GOLD + "§3/f stats - §eDisplays faction stats");
    }

    /* Already has a faction... */
    public static void alreadyHaveFaction(Player sender)
    {
        sender.sendMessage("§cYou already have a faction! ");
    }

    /* Faction is required... */
    public static void factionRequired(Player target)
    {
        target.sendMessage("§cYou need a faction to do this!");
    }

    /* Admin is required.... */
    public static void adminRequired(Player sender)
    {
        sender.sendMessage("§cYou must be a faction §4admin §cto do this!");
    }

    /* Upgraded faction... */
    public static void factionUpgraded(String name, int level, int slots)
    {
        Bukkit.getPlayerExact(name).sendMessage("§aFaction ugpraded to level §e" + level + "  §awith §e" + slots + " §aslots!");
    }

    /* Display stats of player that has a faction... */
    public static void displayStatsWith(Player sender)
    {
        FactionPlayer fp = FactionPlayer.getPlayer(sender.getName());
        sender.sendMessage(" \n \n ");
        sender.sendMessage("§5§l=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        sender.sendMessage("§a[" + fp.getFaction().getName() + "§a]");
        sender.sendMessage("§e§oSocial Status: §r" + fp.getReputation());
        sender.sendMessage("§e§oFaction Rank: §r" + fp.getRole().toString());
        sender.sendMessage("§5§l=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        sender.sendMessage(" \n \n ");
    }

    /* Display stats of player that has no faction... */
    public static void displayStatsWithout(Player sender)
    {
        FactionPlayer fp = FactionPlayer.getPlayer(sender.getName());
        sender.sendMessage(" \n \n ");
        sender.sendMessage("§5§l=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        sender.sendMessage("§a[N/A]");
        sender.sendMessage("§e§oSocial Status: §r" + fp.getReputation());
        sender.sendMessage("§5§l=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        sender.sendMessage(" \n \n ");
    }

    /* Admin or mod is required for function... */
    public static void adminOrModRequired(Player sender)
    {
        sender.sendMessage("§cYou must be a faction §4admin §c or §4moderator §cto do this!");
    }

    /* Set Faction Home... */
    public static void homeSet(Player sender)
    {
        sender.sendMessage("§aFaction home has been set to your current location!");
    }

    /* Teleported to faction home... */
    public static void teleportedHome(Player sender)
    {
        sender.sendMessage("§aTeleported to faction home.");
    }

    /* Specified player is null... */
    public static void invalidPlayer(Player sender)
    {
        sender.sendMessage("§cThat player is offline or does not exist!");
    }

    /* Invited to faction... */
    public static void invitedToFaction(Player sender, Faction f)
    {
        sender.sendMessage("§e" + sender.getName() + ", you have been invited to §5" + f.getName());
    }

    /* Faction is invalid... */
    public static void invalidFaction(Player sender)
    {
        sender.sendMessage("§cThe faction specified is either inexistant or corrupted!");
    }

    /* Need invitation... */
    public static void invitationRequired(Player sender)
    {
        sender.sendMessage("§cYou must be invited to this faction!");
    }

    /* Joining a faction... */
    public static void joinedFaction(Player sender, Faction joined)
    {
        sender.sendMessage("§aYou successfully joined " + joined.getName() + " !");
    }

    /* Inviting self... */
    public static void denySelfInvite(Player sender)
    {
        sender.sendMessage("§cYou can't invite yourself to the faction!");
    }

    /* Not enough slots */
    public static void notEnoughSlots(Player sender)
    {
        sender.sendMessage("§cThere are not enough slots to invite any more!");
    }

}
