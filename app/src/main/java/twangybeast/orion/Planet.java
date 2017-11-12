package twangybeast.orion;


import android.graphics.Color;
import android.graphics.Point;

import java.util.ArrayList;

/**
 * Created by ajay on 11/11/17.
 */

public class Planet {
    private int x;
    private int y;
    private Player owner;
    private double production;
    private double defense;
    private int color = Color.rgb(200,200,200);
    private ArrayList<Improvement> improvements = new ArrayList<Improvement>();
    private ArrayList<Troop> troops = new ArrayList<Troop>();
    private ArrayList<Resource> resources = new ArrayList<Resource>();

    public Planet(int x, int y, Player player, double production, double defense){
        setPosition(x, y);
        setOwner(player);
        setProduction(production);
        setDefense(defense);

    }
    public int getColor(){
        return color;
    }
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }
    public Point getPosition(){
        return new Point(x, y);
    }
    public void setOwner(Player player){
        this.owner = player;
        setColor();
    }
    public Player getOwner(){
        return owner;
    }
    public void setProduction(double amount){
        this.production = amount;
    }
    public double getProduction(){
        return production;
    }
    public void setDefense(double amount){
        this.defense = amount;
    }
    public double getDefense(){
        return defense;
    }
    private void setColor(){
        this.color = owner.getColor();
    }
}
