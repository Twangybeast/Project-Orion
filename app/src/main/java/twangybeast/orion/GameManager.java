package twangybeast.orion;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.text.InputType;
import android.widget.EditText;

import java.util.List;
import java.util.Random;

/**
 * Created by Twangybeast on 11/12/2017.
 */

public class GameManager
{
    public int turn = 0;
    public int round = 1;
    public static final int planetNum = 7;
    public static int playerNum = 3;
    public Planet[] planets;
    public Player[] players;
    public Planet hoveredPlanet = null;
    public Planet selectedPlanet = null;

    private String[] planetNames = {"Hopper", "Kepler", "Hubble", "Brahe", "Halley", "Sagan", "Hawking"};

    public ScienceManagerActivity sma;
    public ProductionManagerActivity pma;
    public List<Science> sciences;
    public AttackEvent ae;
    public Context context;
    public int winner = -1;
    public GameManager(Resources res, int numOfPlayers, Context context)
    {
        playerNum = numOfPlayers;
        planets = new Planet[planetNum];
        players = new Player[playerNum];
        generatePlanets();
        sciences = Science.getSciences(res);
        sma = new ScienceManagerActivity(sciences);
        pma = new ProductionManagerActivity(sciences);
        ae = null;
        this.context = context;
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

            planets[i] = new Planet(planetNames[i], posX, posY, natives, 2, 0, 1);
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
    public void advanceRound()
    {
        for (Player player : players)
        {
            sma.advanceScience(player);
            pma.advanceProduction(player);
        }
    }
    public void endTurn()
    {
        if (turn == playerNum - 1)
        {
            turn = 0;
            round++;
            advanceRound();
        } else
        {
            turn++;
        }
        if (players[turn].getPlanets().isEmpty())
        {
            endTurn();
            return;
        }
        if (gameOver())
        {
            endgame();
        }
    }
    public boolean gameOver()
    {
        for (Player player : players)
        {
            if (player != players[turn])
            {
                if (!player.getPlanets().isEmpty())
                {
                    return false;
                }
            }
        }
        return true;
    }
    public void endgame()
    {
        System.out.println("GAME OVER, Player #"+(turn+1)+" wins!");
        winner = turn+1;
    }
    public int promptForTroop(final int max)
    {
        return 3;
    }
}
