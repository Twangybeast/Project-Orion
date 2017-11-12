package twangybeast.orion;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Troop
{
    private int troops = 0;
    public Troop()
    {
        troops = 0;
    }
    public boolean noTroops()
    {
        return troops == 0;
    }
    public int getStrength()
    {
        return troops;
    }
    public void removeStrength(int strength)
    {
        troops -= strength;
        if (troops < 0)
        {
            troops = 0;
        }
    }
    public void addStrength(int strength)
    {
        troops += strength;
    }
}
