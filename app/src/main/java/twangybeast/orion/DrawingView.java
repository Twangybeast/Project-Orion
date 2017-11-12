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
    private int planetNum = 7;
    private Planet[] planets = new Planet[planetNum];

    private Planet hoveredPlanet = null;
    private Planet selectedPlanet = null;

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

    public void touchAt(float x, float y, long heldDuration) {
        for(Planet planet : planets) {
            Point planetPosition = planet.getPositionInPixels();
            float changeX = planetPosition.x - x;
            float changeY = planetPosition.y - y;
            double distance = Math.sqrt((changeX*changeX + changeY*changeY));

            if(Config.getPlanetDiameter(width, height) + 10 >= distance) {
                if(hoveredPlanet != null && hoveredPlanet.equals(planet) && selectedPlanet == null) {
                    if(heldDuration > 1500) {
                        selectedPlanet = hoveredPlanet;
                        System.out.println("HELD FOR MORE THAN TWO SECONDS");

                        Context context = this.getContext();

                        Intent intent = new Intent(context, ProductionManagerPageActivity.class);
                        intent.putExtra("PLANET", "test");
                        context.startActivity(intent);
                    }
                } else {
                    hoveredPlanet = planet;
                }

                return;
            }
        }

        hoveredPlanet = null;
        selectedPlanet = null;
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        canvas.drawColor(BACKGROUND);

        for (Planet planet : planets)
        {
            drawPlanet(canvas, paint, planet, 0, 0);
        }

        if(hoveredPlanet != null) {
            drawContextBox(canvas, paint, hoveredPlanet);
        }

        invalidate();
    }

    private void drawContextBox(Canvas canvas, Paint paint, Planet planet) {
        paint.setColor(0xFF444444);
        Point planetPosition = planet.getPositionInPixels();
        int cw = 300;
        int ch = 200;
        float planetRadius = Config.getPlanetDiameter(width, height) / 2;
        int px = (int) (planetPosition.x + planetRadius*2);
        int py = (int) (planetPosition.y + planetRadius);

        int backwards = (planetPosition.x + cw + 30 > width) ? -1 : 1;
        if(backwards < 0) {
            px = planetPosition.x;
        }

        Path path = new Path();
        path.moveTo(px, py);
        path.rLineTo(30 * backwards, -30);
        path.rLineTo(0, -ch/2 + 30); // top left
        path.rLineTo(cw * backwards, 0); // top right
        path.rLineTo(0, ch); // bottom right
        path.rLineTo(-cw * backwards, 0); // bottom left
        path.rLineTo(0, -ch/2 + 30);
        path.rLineTo(-30 * backwards, -30);
        path.close();
        canvas.drawPath(path, paint);

        /*
        defense
        production
        science
        troops
        what producing
        change production
         */

        paint.setColor(0xFFEEEEEE);
        paint.setTextSize(45);
        int textX = px + 30*backwards + (backwards < 0 ? -cw : 0);
        int textY = py - ch/2;
        int lineHeight = 40;
        canvas.drawText("Defense: " + planet.getDefense(), textX, textY + lineHeight, paint);
        canvas.drawText("Production: " + planet.getProduction(), textX, textY + lineHeight*2, paint);
        canvas.drawText("Science: " + planet.getProduction(), textX, textY + lineHeight*3, paint);
        canvas.drawText("Troops: " + planet.getProduction(), textX, textY + lineHeight*4, paint);
    }

    public void drawPlanet(Canvas canvas, Paint paint, Planet planet, int dx, int dy)
    {
        paint.setColor(planet.getColor());
        Point p = Config.getScreenCoordinates(planet.getPosition().x, planet.getPosition().y, width, height);
        canvas.drawCircle(p.x+Config.getCellWidth(width)/2, p.y+Config.getCellHeight(height)/2, (int)(Config.getPlanetDiameter(width, height)/2), paint);
    }
}