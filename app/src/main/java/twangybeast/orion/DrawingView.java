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
    private Set<Planet> planets;
    private int width;
    private int height;
    public DrawingView(Context context, int width, int height)
    {
        super(context);
        this.context = context;
        this.width = width;
        this.height = height;
        planets = new HashSet<>();
        //temp
        for (int r = 0; r < Config.GRID_HEIGHT; r++)
        {
            for (int c = 0; c < Config.GRID_WIDTH; c++)
            {
                planets.add(new Planet(c, r, new Random().nextInt(0xFFFFFF)+0xFF000000));
            }
        }
        paint = new Paint();
        paint.setColor(0xFF000000);
        paint.setAntiAlias(true);
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
        paint.setColor(planet.color);
        Point p = Config.getScreenCoordinates(planet.x, planet.y, width, height);
        canvas.drawCircle(p.x+Config.getCellWidth(width)/2, p.y+Config.getCellHeight(height)/2, (int)(Config.getPlanetDiameter(width, height)/2), paint);
    }
}
class Planet
{
    int color;
    int x;
    int y;
    public Planet(int x, int y, int color)
    {
        this.color = color;
        this.x = x;
        this.y = y;
    }
}