package twangybeast.orion;

import android.graphics.Color;

import java.util.ArrayList;


public class Player
{
    private ArrayList<Planet> planets = new ArrayList<>();
    private int color;
    private ArrayList<Science> sciences = new ArrayList<>();

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


}
