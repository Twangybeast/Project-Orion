package twangybeast.orion;


import android.content.*;
import android.graphics.*;
import android.view.*;
import java.util.*;

public class DrawingView extends View
{
    private static final int BACKGROUND = 0xFF080808;
    private boolean didSomething = false;
    private int turn = 0;
    private Paint paint;
    private Context context;
    private int xEndBound;
    private int yEndBound;
    private int width;
    private int height;
    private int planetNum = 9;
    private int playerNum = 3;
    private Planet[] planets = new Planet[planetNum];
    private Player[] players = new Player[playerNum];
    private int[] playersColor = {Color.RED, Color.GREEN, Color.BLUE, Color.MAGENTA, Color.CYAN};
    public DrawingView(Context context, int width, int height)
    {
        super(context);
        this.context = context;
        this.width = width;
        this.height = height;
        paint = new Paint();
        paint.setColor(0xFF000000);
        paint.setAntiAlias(true);
        Player natives = new Player(Color.rgb(200, 200, 200));

        int[] xs = new int[planetNum];
        int[] ys = new int[planetNum];

        Random seed = new Random();

        for(int i = 0; i < planetNum; ++i) {
            int posX;
            int posY;
            do {
                posX = seed.nextInt(Config.GRID_WIDTH - 2) + 1;
                posY = seed.nextInt(Config.GRID_HEIGHT - 2) + 1;
            } while(arrayContainsNear(xs, posX) && arrayContainsNear(ys, posY));
            xs[i] = posX;
            ys[i] = posY;

            planets[i] = new Planet(posX, posY, natives, 10, 0, 1);
        }

        for(int i = 0; i < playerNum; i++){
            players[i] = new Player(playersColor[i]);
            planets[(int)(Math.random()*planets.length)].setOwner(players[i]);
        }
        //play();
    }

    private boolean arrayContainsNear(int[] array, int value) {
        for(int val : array) {
            if(val == value || val == value - 1 || val == value + 1) {
                return true;
            }
            if(val == value - 2 || val == value + 2) {
                if(Math.random() < 0.5) {
                    return true;
                }
            }
        }
        return false;
    }

    public void touchAt(float x, float y) {
        if(x >= xEndBound && y >= yEndBound && didSomething){
            if(turn == playerNum-1){
                turn = 0;
            } else {
                turn++;
            }
            didSomething = false;
        } else {
            didSomething = true;
            for (Planet planet : planets) {
                Point planetPosition = planet.getPositionInPixels();
                float changeX = planetPosition.x - x;
                float changeY = planetPosition.y - y;
                double distance = Math.sqrt((changeX * changeX + changeY * changeY));

                if (Config.getPlanetDiameter(width, height) >= distance) {
                    System.out.println("PLANET TOUCHED");
                }
            }
        }

    }

    @Override
    public void onDraw(Canvas canvas)
    {
        canvas.drawColor(BACKGROUND);

        for (Planet planet : planets)
        {
            drawPlanet(canvas, paint, planet, 0, 0);
        }

        invalidate();
    }
    private void drawPlanet(Canvas canvas, Paint paint, Planet planet, int dx, int dy)
    {
        paint.setColor(planet.getColor());
        Point p = Config.getScreenCoordinates(planet.getPosition().x, planet.getPosition().y, width, height);
        canvas.drawCircle(p.x+Config.getCellWidth(width)/2, p.y+Config.getCellHeight(height)/2, (int)(Config.getPlanetDiameter(width, height)/2), paint);

        paint.setColor(players[turn].getColor());
        paint.setTextSize(50);
        Rect bounds = new Rect();
        paint.getTextBounds("End Turn",0, "End Turn".length(), bounds);
        xEndBound = width-bounds.width()-25;
        yEndBound = height-bounds.height()+10;
        canvas.drawText("End Turn",xEndBound, yEndBound, paint);
        Rect tBounds = new Rect();
        paint.getTextBounds("Player " + turn,0, ("Player " + turn).length(), bounds);
        canvas.drawText("Player " + (turn+1),0, tBounds.height()+50, paint);
    }
}