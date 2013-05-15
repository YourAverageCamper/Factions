
package me.zeus.MAFactions.Factions;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import me.zeus.MAFactions.Core.MAFactions;



public class FactionPlayer implements Serializable {

    // ================================================================== \\

    private static final long serialVersionUID = 488016960936709119L;
    private String name;
    public Faction faction;
    private FactionRole role;
    private Faction pendingFaction;
    private SocialStatus reputation;

    // ================================================================== \\

    public FactionPlayer()
    {
        super();
    }

    /**
     * 
     * Attempts to grab and return the FactionPlayer with the specified name
     * 
     * @param name
     * @return
     */
    public static FactionPlayer getPlayer(String name)
    {
        return MAFactions.getInstance().getPlayers().get(name);
    }

    /**
     * Creates a data file (.bin) for the specified player [name,status,role,faction]
     * 
     * @param username
     * @param status
     * @param role
     * @param faction
     */
    public static void create(String username, SocialStatus status, FactionRole role, Faction faction)
    {
        File f = new File(MAFactions.getInstance().getDataFolder() + File.separator + "players" + File.separator + username + ".bin");
        if (!f.exists())
        {
            try
            {
                f.createNewFile();
                FactionPlayer created = new FactionPlayer();
                created.setName(username);
                created.setReputation(status);
                created.setFactionRole(role);
                created.setFaction(faction);
                System.out.println("Created data for " + username);
                created.save();
                MAFactions.getInstance().getPlayers().put(username, created);
            } catch (IOException ioe)
            {
                System.out.println("There was an error CREATING data for " + username + "!");
                ioe.printStackTrace();
                return;
            }
        }
    }

    /**
     * 
     * Saves data for the current FactionPlayer object to a .bin file
     * 
     */
    public void save()
    {
        File f = new File(MAFactions.getInstance().getDataFolder() + File.separator + "players" + File.separator + name + ".bin");
        if (f.exists())
        {
            try
            {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
                oos.writeObject(this);
                oos.close();
                System.out.println("Saved data for " + name);
            } catch (IOException ioe)
            {
                System.out.println("There was an error SAVING data for " + name + "!");
                ioe.printStackTrace();
                return;
            }
        }
    }

    // ================================================================== \\

    /**
     * 
     * Kicks the player from their current faction
     * 
     */
    public void kickPlayer()
    {
        if (faction != null)
        {
            if (faction.disbanding == false)
            {
                faction.getMembers().remove(this);
                faction.getRoles().remove(this);
                faction = null;
                role = FactionRole.UNOCCUPIED;
            } else
            {
                faction = null;
            }
        }
    }

    // ================================================================== \\

    /**
     * Returns the username of the FactionPlayer
     * 
     * 
     * @return name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns the Faction the FactionPlayer is in, or null if none
     * 
     * 
     * @return faction
     */
    public Faction getFaction()
    {
        return faction;
    }

    /**
     * Returns the FactionRole of the FactionPlayer, or UNOCCUPIED if none.
     * 
     * 
     * @return role
     */
    public FactionRole getRole()
    {
        return role;
    }

    /**
     * 
     * Returns the pending faction the FactionPlayer was invited to, or null if none.
     * 
     * 
     * @return pendingFaction
     */
    public Faction getPendingFaction()
    {
        return pendingFaction;
    }

    /**
     * 
     * Returns the current reputation of the FactionPlayer, or 0/null if none.
     * 
     * 
     * @return reputation
     */
    public SocialStatus getReputation()
    {
        return reputation;
    }

    /* Setters */

    /**
     * Sets the player's username to the specified name
     * 
     * 
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Sets the player's faction to the specified faction
     * 
     * 
     * @param faction
     */
    public void setFaction(Faction faction)
    {
        this.faction = faction;
    }

    /**
     * 
     * Sets the player's FactionRole to the specified faction role
     * 
     * 
     * @param role
     */
    public void setFactionRole(FactionRole role)
    {
        this.role = role;
    }

    /**
     * 
     * Sets the player's pending faction to the specified faction
     * 
     * 
     * @param faction
     */
    public void setPendingFaction(Faction faction)
    {
        pendingFaction = faction;
    }

    /**
     * 
     * Sets the player's reputation to the specified SocialStatus
     * 
     * 
     * @param rep
     */
    public void setReputation(SocialStatus rep)
    {
        reputation = rep;
    }

    // ================================================================== \\

}
