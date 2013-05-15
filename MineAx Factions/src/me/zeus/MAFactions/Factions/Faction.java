
package me.zeus.MAFactions.Factions;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import me.zeus.MAFactions.Core.MAFactions;
import me.zeus.MAFactions.Util.FMessenger;
import me.zeus.MAFactions.Util.SerializableLocation;

import org.bukkit.Location;
import org.bukkit.entity.Player;



public class Faction implements Serializable {

    // ================================================================== \\

    private static final long serialVersionUID = -1761114572147235478L;

    public FactionPlayer owner;
    public String name;
    public String description;
    public int level = 0;
    public int slots = 5;
    public SerializableLocation home;

    public boolean disbanding;

    public Map<FactionPlayer, FactionRole> roles;
    public List<FactionPlayer> members;
    public List<Faction> enemies;
    public List<Faction> allies;

    // ================================================================== \\

    public Faction()
    {

    }

    public static void createFaction(Player owner, String name, String description, int level, int slots, SerializableLocation home)
    {
        Faction f = new Faction();
        FactionPlayer fp = FactionPlayer.getPlayer(owner.getName());
        f.setOwner(fp);
        f.setFactionName(name);
        f.setDescription(description);
        f.setLevel(level);
        f.setSlots(slots);
        f.setHome(home);
        f.setMembers(Arrays.asList(fp));
        f.allies = new ArrayList<Faction>();
        f.enemies = new ArrayList<Faction>();
        f.save();
        fp.setFaction(f);
        fp.setFactionRole(FactionRole.ADMIN);
        MAFactions.getInstance().getFactions().put(name, f);
        System.out.println("Created faction: " + name);
    }

    public static Faction getFaction(String name)
    {
        if (MAFactions.getInstance().getFactions().containsKey(name))
        {
            return MAFactions.getInstance().getFactions().get(name);
        }
        return null;
    }

    public static Faction loadFaction(String fileName)
    {
        File f = new File(MAFactions.getInstance().getDataFolder() + File.separator + "factions" + File.separator + fileName);
        try
        {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
            Faction loaded = (Faction) ois.readObject();
            ois.close();
            return loaded;
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    // ================================================================== \\

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public FactionPlayer getOwner()
    {
        return owner;
    }

    public List<FactionPlayer> getMembers()
    {
        return members;
    }

    public List<Faction> getAllies()
    {
        return allies;
    }

    public List<Faction> getEnemies()
    {
        return enemies;
    }

    public Map<FactionPlayer, FactionRole> getRoles()
    {
        return roles;
    }

    public int getLevel()
    {
        return level;
    }

    public int getSlots()
    {
        return slots;
    }

    public Location getHome()
    {
        return home.getLocation();
    }

    // ================================================================== \\

    public void setFactionName(String name)
    {
        this.name = name;
    }

    public void setDescription(String desc)
    {
        description = desc;
    }

    public void setOwner(FactionPlayer fp)
    {
        owner = fp;
    }

    public void setLevel(int lvl)
    {
        level = lvl;
    }

    public void setSlots(int slots)
    {
        this.slots = slots;
    }

    public void setHome(SerializableLocation home2)
    {
        home = home2;
    }

    public void setMembers(List<FactionPlayer> members)
    {
        this.members = members;
    }

    public void setAllies(List<Faction> allies)
    {
        this.allies = allies;
    }

    public void setEnemies(List<Faction> enemies)
    {
        this.enemies = enemies;
    }

    // ================================================================== \\

    public boolean isNeutralTo(Faction f)
    {
        if (f != null)
        {
            return (!enemies.contains(f) || !allies.contains(f));
        }
        return false;
    }

    public boolean isEnemyTo(Faction f)
    {
        if (f != null)
        {
            return enemies.contains(f);
        }
        return false;
    }

    public boolean isAlliedTo(Faction f)
    {
        return allies.contains(f);
    }

    // ================================================================== \\

    public void save()
    {
        File file = new File(MAFactions.getInstance().getDataFolder() + File.separator + "factions" + File.separator + name + ".bin");
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(this);
            oos.close();
            System.out.println("Saved data for " + name);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void disband()
    {
        File f = new File(MAFactions.getInstance().getDataFolder() + File.separator + "factions" + File.separator + name + ".bin");
        disbanding = true;
        Iterator<FactionPlayer> it = members.listIterator();
        while (it.hasNext())
        {
            FactionPlayer next = it.next();
            System.out.println(next.getName() + " has been kicked. ");
            next.kickPlayer();
            next.save();
        }
        MAFactions.getInstance().getFactions().remove(name);
        f.delete();
    }

    public void upgrade()
    {
        level++;
        slots += 5;
        for (FactionPlayer mem : members)
        {
            FMessenger.factionUpgraded(mem.getName(), level, slots);
        }
    }

    public void invite(Player p)
    {
        FactionPlayer target = FactionPlayer.getPlayer(p.getName());
        target.setPendingFaction(this);
    }

    // ================================================================== \\

}
