package twangybeast.orion;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ScienceManagerActivity
{
    public static final String FIRST_SCIENCE = "Science Level 1";
    List<Science> sciences;
    public ScienceManagerActivity(List<Science> sciences)
    {
        this.sciences = sciences;
    }
    //Returns true when science is completed
    public boolean advanceScience(Player player)
    {
        for (Planet planet : player.getPlanets())
        {
            player.getCurrentResearch().progress += planet.getScience();
        }
        Science curResearch = getScience(player.getCurrentResearch().scienceName);
        if (curResearch == null)
        {
            return false;
        }
        if (player.getCurrentResearch().progress >= curResearch.getCost())
        {
            player.getSciences().add(curResearch);
            List<Science> availableNext = getAvailableSciences(player.getSciences());
            if (availableNext.size() == 0)
            {
                player.getCurrentResearch().scienceName = "All Science Completed";
            }
            else
            {
                player.getCurrentResearch().scienceName = availableNext.get(0).getName();
            }
            return true;
        }
        return false;
    }
    public List<Science> getAvailableSciences(List<Science> researched)
    {
        List<Science> available = new LinkedList<>();
        Set<String> sciSet = new HashSet<>();
        for (Science sc : researched)
        {
            sciSet.add(sc.getName());
        }
        for (Science science:sciences)
        {
            if (sciSet.containsAll(science.getPrereqs()))
            {
                available.add(science);
            }
        }
        return sciences;
    }
    public Science getScience(String name)
    {
        for (Science sci : sciences)
        {
            if (sci.getName().equals(name))
            {
                return sci;
            }
        }
        System.out.println("Science not found.");
        return null;
    }
}
