
package me.zeus.MAFactions.Core;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.zeus.MAFactions.Commands.CMD_CreateFaction;
import me.zeus.MAFactions.Commands.CMD_Debug;
import me.zeus.MAFactions.Commands.CMD_DisbandFaction;
import me.zeus.MAFactions.Commands.CMD_HelpFaction;
import me.zeus.MAFactions.Commands.CMD_HomeFaction;
import me.zeus.MAFactions.Commands.CMD_InfoFaction;
import me.zeus.MAFactions.Commands.CMD_InviteFaction;
import me.zeus.MAFactions.Commands.CMD_JoinFaction;
import me.zeus.MAFactions.Commands.CMD_SethomeFaction;
import me.zeus.MAFactions.Commands.CMD_StatsFaction;
import me.zeus.MAFactions.Commands.CMD_UpgradeFaction;
import me.zeus.MAFactions.Commands.FactionCMD;
import me.zeus.MAFactions.Commands.FactionCommand;
import me.zeus.MAFactions.Events.JoinListener;
import me.zeus.MAFactions.Events.LeaveListener;
import me.zeus.MAFactions.Factions.Faction;
import me.zeus.MAFactions.Factions.FactionPlayer;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;



public class MAFactions extends JavaPlugin {

    // ================================================================== \\

    private File factionsDir;
    private File rootDir;
    private File settingsDir;
    private File playersDir;
    private File[] factionFiles;
    private File[] playerFiles;

    // ================================================================== \\

    /* INSTANCES AND STUFF */

    private Map<String, Faction> factions;

    public Map<String, Faction> getFactions()
    {
        return factions;
    }

    private Map<String, FactionPlayer> factionPlayers;

    public Map<String, FactionPlayer> getPlayers()
    {
        return factionPlayers;
    }

    private List<FactionCMD> cmds = new ArrayList<FactionCMD>();

    public List<FactionCMD> getCommands()
    {
        return cmds;
    }

    private static MAFactions instance;

    public static MAFactions getInstance()
    {
        return instance;
    }

    // ================================================================== \\

    /* When the plugin loads*/

    @Override
    public void onEnable()
    {
        instance = this;

        factions = new HashMap<String, Faction>();
        factionPlayers = new HashMap<String, FactionPlayer>();

        handleDirs();
        loadFactions();
        loadPlayers();
        registerEvents();
        loadCommands();
    }

    // ================================================================== \\

    /* When the plugin unloads (disables) */
    @Override
    public void onDisable()
    {
        for (Faction f : factions.values())
        {
            try
            {
                f.save();
            } catch (Exception e)
            {
                System.out.println("Error saving factions!");
                e.printStackTrace();
            }
        }
        for (FactionPlayer fpp : factionPlayers.values())
        {
            try
            {
                fpp.save();
            } catch (Exception e)
            {
                System.out.println("Error saving players data!");
                e.printStackTrace();
            }
        }
        instance = null;
    }

    // ================================================================== \\

    /* Load all faction files*/
    private void loadFactions()
    {
        factionFiles = factionsDir.listFiles();
        for (int i = 0; i < factionFiles.length; i++)
        {
            Faction faction = Faction.loadFaction(factionFiles[i].getName());
            if (faction != null)
            {
                factions.put(faction.getName(), faction);
                System.out.println("Loaded faction: " + faction.getName());
            }
        }
    }

    /* Load all FactionPlayers */
    private void loadPlayers()
    {
        playerFiles = playersDir.listFiles();
        for (int i = 0; i < playerFiles.length; i++)
        {
            if (playerFiles[i].exists())
            {
                try
                {
                    FileInputStream fis = new FileInputStream(playerFiles[i]);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    FactionPlayer loaded = (FactionPlayer) ois.readObject();
                    System.out.println("Loaded data for " + playerFiles[i].getName());
                    ois.close();
                    fis.close();
                    factionPlayers.put(loaded.getName(), loaded);
                } catch (IOException ee1)
                {
                    System.out.println("ERROR: IOEXCEPTION");
                } catch (ClassNotFoundException e1)
                {
                    System.out.println("ERROR: CLASS NOT FOUND EXCEPTION");
                }
            }
        }
    }

    /* Register le events! xD */
    private void registerEvents()
    {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new JoinListener(), this);
        pm.registerEvents(new LeaveListener(), this);
    }

    /* Load all the commands needed*/
    private void loadCommands()
    {
        getCommand("faction").setExecutor(new FactionCommand());
        cmds.add(new CMD_CreateFaction());
        cmds.add(new CMD_DisbandFaction());
        cmds.add(new CMD_StatsFaction());
        cmds.add(new CMD_HelpFaction());
        cmds.add(new CMD_StatsFaction());
        cmds.add(new CMD_InfoFaction());
        cmds.add(new CMD_SethomeFaction());
        cmds.add(new CMD_HomeFaction());
        cmds.add(new CMD_InviteFaction());
        cmds.add(new CMD_JoinFaction());
        cmds.add(new CMD_Debug());
        cmds.add(new CMD_UpgradeFaction());
    }

    /* Create and check directories and existence */
    private void handleDirs()
    {
        rootDir = new File(getDataFolder() + "");
        if (!rootDir.exists())
        {
            rootDir.mkdir();
        }

        factionsDir = new File(getDataFolder() + File.separator + "factions");
        if (!factionsDir.exists())
        {
            factionsDir.mkdir();
        }

        settingsDir = new File(getDataFolder() + File.separator + "settings");
        if (!settingsDir.exists())
        {
            settingsDir.mkdir();
        }

        playersDir = new File(getDataFolder() + File.separator + "players");
        if (!playersDir.exists())
        {
            playersDir.mkdir();
        }
    }
    // ================================================================== \\

}
