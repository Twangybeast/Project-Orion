package twangybeast.orion;

/**
 * Created by Twangybeast on 11/11/2017.
 */

public class TroopManagerActivity
{
    public static final String TROOP_NAME = "Troop";
    public static final float DEFENSE_MULTIPLIER = 1.0f;
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
    public static AttackEvent attack(Planet attacker, Planet defender, int attackingStrength)
    {
        if (attackingStrength ==0)
        {
            return new AttackEvent(0, defender.getTroop().getStrength(), defender.getDefense(), 0, 0,false);
        }
        if (defender.getTroop().getStrength() == 0)
        {
            capturePlanet(attacker, defender);
            defender.getTroop().addStrength(attackingStrength);
            attacker.getTroop().removeStrength(attackingStrength);
            return new AttackEvent(attackingStrength,0,defender.getTroop().getStrength(),0,0,true);
        }
        attacker.getTroop().removeStrength(attackingStrength);
        int defenseStrength = (int)(defender.getDefense()*DEFENSE_MULTIPLIER)+defender.getTroop().getStrength();
        //Using warlight.net rules https://www.warlight.net/wiki/Combat_Basics
        int attackStart = attackingStrength;
        int defenseStart = defenseStrength;
        attackingStrength -= defenseStart *0.7;
        defender.getTroop().removeStrength((int)(attackStart*0.6));
        if (defender.getTroop().getStrength() == 0)
        {
            capturePlanet(attacker, defender);
            defender.getTroop().addStrength(attackingStrength);
            return new AttackEvent(attackStart,defenseStart,defender.getDefense(),attackStart-attackingStrength,defenseStart,true);
        }
        else
        {
            attacker.getTroop().addStrength(attackingStrength);
            return new AttackEvent(attackStart,defenseStart,defender.getDefense(),attackStart-attackingStrength,defenseStart-defender.getTroop().getStrength(),false);
        }
    }
    public static void capturePlanet(Planet attacker, Planet defender)
    {
        defender.getOwner().getPlanets().remove(defender);
        defender.setOwner(attacker.getOwner());
        attacker.getOwner().getPlanets().add(defender);
    }

}
