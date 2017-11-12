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
    private int science;
    private int color = Color.rgb(200, 200, 200);
    private ArrayList<Improvement> improvements = new ArrayList<Improvement>();
    private ArrayList<Troop> troops = new ArrayList<>();
    private CurrentProduction currentProduction = new CurrentProduction();
    public Planet(int x, int y, Player player, int production, int defense, int science)
    {
        this.x = x;
        this.y = y;
        this.owner = player;
        this.production = production;
        this.defense = defense;
        this.science = science;
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

    public Point getPosition() {
        return new Point(x, y);
    }

    public Point getPositionInPixels() {
        return Config.getScreenCoordinates(x, y, FullscreenActivity.SCREEN_WIDTH, FullscreenActivity.SCREEN_HEIGHT);
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

    public int getScience()
    {
        return science;
    }

    public void setScience(int science)
    {
        this.science = science;
    }

    private void refreshColor()
    {
        this.color = owner.getColor();
    }
}
