package twangybeast.orion;


import android.content.*;
import android.graphics.*;
import android.view.*;
import java.util.*;

public class DrawingView extends View
{
    private static final int BACKGROUND = 0xFF080808;
    private Paint paint;
    private Context context;
    private int width;
    private int height;
    private int planetNum = 9;
    private Planet[] planets = new Planet[planetNum];
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

            planets[i] = new Planet(posX, posY, natives, 10, 0);
        }
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

    @Override
    public void onDraw(Canvas canvas)
    {
        canvas.drawColor(BACKGROUND);

        for (Planet planet : planets)
        {
            drawPlanet(canvas, paint, planet, 0, 0);
        }
    }
    public void drawPlanet(Canvas canvas, Paint paint, Planet planet, int dx, int dy)
    {
        paint.setColor(planet.getColor());
        Point p = Config.getScreenCoordinates(planet.getPosition().x, planet.getPosition().y, width, height);
        canvas.drawCircle(p.x+Config.getCellWidth(width)/2, p.y+Config.getCellHeight(height)/2, (int)(Config.getPlanetDiameter(width, height)/2), paint);
    }
}