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
    private Planet[] planets;
    private int width;
    private int height;
    private int planetNum = 14;
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
        planets = new Planet[] {new Planet(1, 5, natives, 10, 0), new Planet(4, 8, natives, 10, 0), new Planet(8, 16, natives, 10, 0), new Planet(10, 19, natives, 10, 0), new Planet(5, 13, natives, 10, 0), new Planet(2, 1, natives, 10, 0), new Planet(6, 7, natives, 10, 0), new Planet(8, 2, natives, 10, 0)};


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