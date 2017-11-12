package twangybeast.orion;


import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ProductionManagerActivity
{
    List<Science> sciences;
    public ProductionManagerActivity(List<Science> sciences)
    {
        this.sciences = sciences;
    }
    public void advanceProduction(Player player)
    {
        for (Planet planet : player.getPlanets())
        {
            planet.getCurrentProduction().progress += planet.getProduction();
            if (planet.getCurrentProduction().progress >= planet.getCurrentProduction().cost)
            {
                planet.getCurrentProduction().progress -= planet.getCurrentProduction().cost;
                if (planet.getCurrentProduction().prodName.equals(TroopManagerActivity.TROOP_NAME))
                {
                    planet.getTroop().addStrength(TroopManagerActivity.getUnitStrength(planet.getOwner()));
                }
                else
                {
                    Improvement improvement = getImprovement(planet.getCurrentProduction().prodName);
                    planet.getImprovements().add(improvement);
                    planet.production += improvement.getProduction();
                    planet.science += improvement.getScience();
                    planet.defense += improvement.getDefense();
                }
                List<Improvement> available = getAvailableImprovements(planet);
                if (available.size() > 0)
                {
                    planet.getCurrentProduction().prodName = available.get(0).getName();
                    planet.getCurrentProduction().cost = available.get(0).getCost();
                }
                else
                {
                    planet.getCurrentProduction().prodName = TroopManagerActivity.TROOP_NAME;
                    planet.getCurrentProduction().cost = TroopManagerActivity.getTroopCost(player);
                }
            }
        }
    }
    public static int getTurnsToFinish(Planet planet)
    {
        return (int) Math.ceil((1.0*planet.getCurrentProduction().cost-planet.getCurrentProduction().progress)/planet.getProduction());
    }
    public void selectImprovement(Planet planet, String name)
    {
        if (name.startsWith(TroopManagerActivity.TROOP_NAME))
        {
            planet.getCurrentProduction().prodName = TroopManagerActivity.TROOP_NAME;
            planet.getCurrentProduction().cost = TroopManagerActivity.getTroopCost(planet.getOwner());
        }
        else
        {
            planet.getCurrentProduction().prodName = getImprovement(name).getName();
            planet.getCurrentProduction().cost = getImprovement(name).getCost();
        }
    }
    //Also need to include troop
    public List<Improvement> getAvailableImprovements(Planet planet)
    {
        Set<Improvement> improvements = new HashSet<>();
        for (Science sci : planet.getOwner().getSciences())
        {
            for (Improvement improvement : sci.getImprovements())
            {
                improvements.add(improvement);
            }
        }
        improvements.removeAll(planet.getImprovements());
        return new LinkedList<>(improvements);
    }
    public Improvement getImprovement(String name)
    {
        for (Science sci : sciences)
        {
            for (Improvement improvement : sci.getImprovements())
            {
                System.out.println(name);
                System.out.println(improvement.getName());
                if (name.startsWith(improvement.getName()))
                {
                    return improvement;
                }
            }
        }
        System.out.println("Improvement not found.");
        return null;
    }
}
