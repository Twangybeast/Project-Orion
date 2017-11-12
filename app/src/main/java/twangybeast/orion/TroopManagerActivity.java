package twangybeast.orion;

/**
 * Created by Twangybeast on 11/11/2017.
 */

public class TroopManagerActivity
{
    public static final String TROOP_NAME = "Troop";
    public static int getTroopCost(Player player)
    {
        return player.troopLevel;
    }
    public static int getUnitStrength(Player player)
    {
        return player.troopLevel*player.troopLevel;
    }
    public static void moveTroops(Planet src, Planet target, int amount)
    {
        src.getTroop().removeStrength(amount);
        target.getTroop().addStrength(amount);
    }
    public static void attack(Planet attacker, Planet defender, int attackingStrength)
    {

    }
}
