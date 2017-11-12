package twangybeast.orion;

import android.content.res.Resources;
import android.content.res.TypedArray;

import java.util.HashSet;
import java.util.Set;

public class Improvement
{
    private String name;
    private int science;
    private int defense;
    private int production;
    private int cost;
    public Improvement(String name, int science, int defense, int production, int cost)
    {
        this.name = name;
        this.science = science;
        this.defense = defense;
        this.production = production;
        this.cost = cost;
    }
    @SuppressWarnings("ResourceType")
    public static Improvement getImprovement(int id, Resources res)
    {
        Improvement improvement;
        TypedArray ta = res.obtainTypedArray(id);
        improvement = new Improvement(ta.getString(0), ta.getInt(1,0), ta.getInt(2,0), ta.getInt(3,0),ta.getInt(4,0));
        ta.recycle();
        return improvement;
    }
    @SuppressWarnings("ResourceType")
    public static Set<Improvement> getImprovements(int id, Resources res)
    {
        Set<Improvement> improvements = new HashSet<>();
        TypedArray ta = res.obtainTypedArray(id);
        int i = 0;
        int arrayId;
        while(i < ta.length())
        {
            arrayId = ta.getResourceId(i, 0);
            if (arrayId==0)
            {
                break;
            }
            improvements.add(getImprovement(arrayId, res));
            i++;
        }
        ta.recycle();
        return improvements;
    }
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getScience()
    {
        return science;
    }

    public void setScience(int science)
    {
        this.science = science;
    }

    public int getDefense()
    {
        return defense;
    }

    public void setDefense(int defense)
    {
        this.defense = defense;
    }

    public int getProduction()
    {
        return production;
    }

    public void setProduction(int production)
    {
        this.production = production;
    }

    public int getCost()
    {
        return cost;
    }

    public void setCost(int cost)
    {
        this.cost = cost;
    }
}
