package twangybeast.orion;


import android.graphics.Color;
import android.graphics.Point;

import java.util.ArrayList;


public class Planet
{
    private int x;
    private int y;
    private Player owner;
    private int production;
    private int defense;
    private int color = Color.rgb(200, 200, 200);
    private ArrayList<Improvement> improvements = new ArrayList<Improvement>();
    private ArrayList<Troop> troops = new ArrayList<>();

    public Planet(int x, int y, Player player, int production, int defense)
    {
        this.x = x;
        this.y = y;
        this.owner = player;
        this.production = production;
        this.defense = defense;
    }

    public int getColor()
    {
        return color;
    }

    public void setPosition(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Point getPosition()
    {
        return new Point(x, y);
    }

    public void setOwner(Player player)
    {
        this.owner = player;
        refreshColor();
    }

    public Player getOwner()
    {
        return owner;
    }

    public void setProduction(int amount)
    {
        this.production = amount;
    }

    public int getProduction()
    {
        return production;
    }

    public void setDefense(int amount)
    {
        this.defense = amount;
    }

    public int getDefense()
    {
        return defense;
    }

    private void refreshColor()
    {
        this.color = owner.getColor();
    }
}
