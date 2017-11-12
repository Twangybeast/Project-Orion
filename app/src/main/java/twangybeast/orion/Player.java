package twangybeast.orion;

import android.graphics.Color;

import java.util.ArrayList;

/**
 * Created by ajay on 11/11/17.
 */

public class Player {
    private ArrayList<Planet> planets = new ArrayList<Planet>();
    private int color;
    private ArrayList<Science> sciences = new ArrayList<Science>();

    public Player(int c){
        setColor(c);
    }
    public void setColor(int c){
        this.color = c;
    }
    public int getColor(){
        return color;
    }


}
