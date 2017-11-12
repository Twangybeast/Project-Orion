package twangybeast.orion;


import android.content.res.Resources;
import android.content.res.TypedArray;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class Science
{
    private String name;
    private Set<Improvement> improvements;
    private int cost;
    private Set<String> prereqs;
    public Science(String name, Set<Improvement> improvements, int cost, Set<String> prereqs)
    {
        this.name = name;
        this.improvements = improvements;
        this.cost = cost;
        this.prereqs = prereqs;

    }
    @SuppressWarnings("ResourceType")
    public static List<Science> getSciences(Resources res)
    {
        List<Science> sciences = new LinkedList<>();
        TypedArray ta = res.obtainTypedArray(R.array.sciences);
        int i =0;
        int id;
        while(true)
        {
            id = ta.getResourceId(i, 0);
            if (id ==0)
            {
                break;
            }
            sciences.add(getScience(id, res));
            i++;
        }
        return sciences;
    }
    @SuppressWarnings("ResourceType")
    public static Science getScience(int id, Resources res)
    {
        TypedArray ta = res.obtainTypedArray(id);
        Science science = new Science(ta.getString(0),Improvement.getImprovements(ta.getResourceId(1,0), res),ta.getInt(2,0),getPrereqs(ta.getResourceId(3,0), res));
        ta.recycle();
        return science;
    }
    @SuppressWarnings("ResourceType")
    public static Set<String> getPrereqs(int id, Resources res)
    {
        Set<String> prereqs = new HashSet<>();
        String[] sarray = res.getStringArray(id);
        for (String s : sarray)
        {
            prereqs.add(s);
        }
        return prereqs;
    }
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Set<Improvement> getImprovements()
    {
        return improvements;
    }

    public void setImprovements(Set<Improvement> improvements)
    {
        this.improvements = improvements;
    }

    public int getCost()
    {
        return cost;
    }

    public void setCost(int cost)
    {
        this.cost = cost;
    }

    public Set<String> getPrereqs()
    {
        return prereqs;
    }

    public void setPrereqs(Set<String> prereqs)
    {
        this.prereqs = prereqs;
    }
}
