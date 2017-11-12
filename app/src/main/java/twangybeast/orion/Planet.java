package twangybeast.orion;


import android.graphics.Color;
import android.graphics.Point;

import java.util.ArrayList;


public class Planet
{
    private int x;
    private int y;
    private Player owner;
    public int production;
    public int defense;
    public int science;
    private String name;
    private int color = Color.rgb(200, 200, 200);
    private ArrayList<Improvement> improvements = new ArrayList<Improvement>();
    private Troop troop;
    //TO DO CHANGE DEFAULT PRODUCTION
    private CurrentProduction currentProduction;
    public Planet(String name, int x, int y, Player player, int production, int defense, int science)
    {
        this.name = name;
        this.x = x;
        this.owner = player;
        this.production = production;
        this.defense = defense;
        this.science = science;
        troop = new Troop();
        troop.addStrength(2);
        this.y = y;
        currentProduction = new CurrentProduction(TroopManagerActivity.TROOP_NAME, TroopManagerActivity.getTroopCost(owner));
    }
    public String getName(){
        return this.name;
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

    public CurrentProduction getCurrentProduction()
    {
        return currentProduction;
    }

    public void setCurrentProduction(CurrentProduction currentProduction)
    {
        this.currentProduction = currentProduction;
    }

    public ArrayList<Improvement> getImprovements()
    {
        return improvements;
    }

    public void setImprovements(ArrayList<Improvement> improvements)
    {
        this.improvements = improvements;
    }

    public Troop getTroop()
    {
        return troop;
    }

    public void setTroop(Troop troop)
    {
        this.troop = troop;
    }

    private void refreshColor()
    {
        this.color = owner.getColor();
    }
}
