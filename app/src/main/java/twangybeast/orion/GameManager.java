package twangybeast.orion;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by Twangybeast on 11/12/2017.
 */

public class GameManager
{
    public int turn = 0;
    public static final int planetNum = 7;
    public static final int playerNum = 3;
    public Planet[] planets;
    public Player[] players;
    public Planet hoveredPlanet = null;
    public Planet selectedPlanet = null;

    public GameManager()
    {
        planets = new Planet[planetNum];
        players = new Player[playerNum];
        generatePlanets();
    }

    public void generatePlanets()
    {
        Player natives = new Player(Color.rgb(200, 200, 200));
        int[] xs = new int[planetNum];
        int[] ys = new int[planetNum];

        Random seed = new Random();

        for (int i = 0; i < planetNum; ++i)
        {
            int posX;
            int posY;
            do
            {
                posX = seed.nextInt(Config.GRID_WIDTH - 2) + 1;
                posY = seed.nextInt(Config.GRID_HEIGHT - 2) + 1;
            } while (arrayContainsNear(xs, posX) && arrayContainsNear(ys, posY));
            xs[i] = posX;
            ys[i] = posY;

            planets[i] = new Planet(posX, posY, natives, 10, 0, 1);
        }

        for (int i = 0; i < playerNum; i++)
        {
            players[i] = new Player(DrawingView.PLAYERS_COLOR[i]);
            int playerPlanet;
            do
            {
                playerPlanet = seed.nextInt(planetNum);

            }
            while (planets[playerPlanet].getOwner() != natives);
            planets[playerPlanet].setOwner(players[i]);
            players[i].getPlanets().add(planets[playerPlanet]);
        }
    }

    private boolean arrayContainsNear(int[] array, int value)
    {
        for (int val : array)
        {
            if (val == value || val == value - 1 || val == value + 1)
            {
                return true;
            }
            if (val == value - 2 || val == value + 2)
            {
                if (Math.random() < 0.5)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public void endTurn()
    {
        if (turn == playerNum - 1)
        {
            turn = 0;
        } else
        {
            turn++;
        }
    }
    public void selectPlanet(Planet planet)
    {

    }

}
