
package me.zeus.MAFactions.Factions;


import java.io.Serializable;



public enum SocialStatus
        implements
        Serializable
{

    NEUTRAL(0),
    GOOD(1),
    EVIL(2);

    private int value;

    SocialStatus(int val)
    {

    }

    public int getValue()
    {
        return value;
    }

    public static int getValue(SocialStatus stat)
    {
        return stat.getValue();
    }

}
