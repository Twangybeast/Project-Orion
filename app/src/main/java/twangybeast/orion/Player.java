package twangybeast.orion;

import android.graphics.Color;

import java.util.ArrayList;


public class Player
{
    private ArrayList<Planet> planets = new ArrayList<>();
    private int color;
    private ArrayList<Science> sciences = new ArrayList<>();
    private CurrentResearch currentResearch = new CurrentResearch(ScienceManagerActivity.FIRST_SCIENCE);
    public int troopLevel = 1;
    public Player(int c)
    {
        this.color = c;
    }

    public void setColor(int c)
    {
        this.color = c;
    }

    public int getColor()
    {
        return color;
    }

    public ArrayList<Planet> getPlanets()
    {
        return planets;
    }

    public void setPlanets(ArrayList<Planet> planets)
    {
        this.planets = planets;
    }

    public ArrayList<Science> getSciences()
    {
        return sciences;
    }

    public void setSciences(ArrayList<Science> sciences)
    {
        this.sciences = sciences;
    }

    public CurrentResearch getCurrentResearch()
    {
        return currentResearch;
    }

    public void setCurrentResearch(CurrentResearch currentResearch)
    {
        this.currentResearch = currentResearch;
    }
}
