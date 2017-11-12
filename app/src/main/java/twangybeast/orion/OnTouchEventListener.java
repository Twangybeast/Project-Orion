package twangybeast.orion;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.support.constraint.solver.widgets.Rectangle;
import android.view.MotionEvent;
import android.view.View;

public class OnTouchEventListener implements View.OnTouchListener
{
    public static final int TAP_TIME = 1000;//Max time to be considered a tap
    public static final int HOLD_TIME = 1500; //Min time to be considered a hold
    DrawingView view;
    Point downPos;
    GameManager gm;
    boolean down = false;
    public OnTouchEventListener(Context ctx, DrawingView drawingPanel, GameManager gm)
    {
        view = drawingPanel;
        this.gm = gm;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        long holdTime = event.getEventTime()-event.getDownTime();
        if (holdTime > HOLD_TIME && down)
        {
            Planet planet = collidePlanet(downPos.x, downPos.y, (int)event.getX(), (int)event.getY());
            if (planet != null)
            {
                if (gm.selectedPlanet == null && gm.players[gm.turn]==planet.getOwner())
                {
                    gm.hoveredPlanet = planet;
                    gm.selectedPlanet = gm.hoveredPlanet;

                    Context context = view.getContext();

                    Intent intent = new Intent(context, ProductionManagerPageActivity.class);
                    intent.putExtra("PLANET", "test");
                    context.startActivity(intent);
                }
            }
        }
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                downPos = new Point((int)event.getX(), (int)event.getY());
                down = true;
                break;
            case MotionEvent.ACTION_UP:
                down = false;
                //Tap
                if (holdTime < TAP_TIME)
                {
                    if (collideEndTurn(downPos.x, downPos.y))
                    {
                        if (collideEndTurn((int)event.getX(), (int)event.getY()))
                        {
                            gm.endTurn();
                        }
                    }
                    Planet planet = collidePlanet(downPos.x, downPos.y, (int)event.getX(), (int)event.getY());
                    System.out.println(planet);
                    gm.hoveredPlanet = planet;
                }
                gm.selectedPlanet = null;
                break;
        }
        System.out.println(event.getAction());
        long eventDuration = event.getEventTime() - event.getDownTime(); // in milliseconds
        //view.touchAt(event.getX(), event.getY(), eventDuration, event);
        return true;
    }
    public boolean collideEndTurn(int x, int y)
    {
        return x >= view.xEndBound && y >= view.yEndBound;
    }
    public Planet collidePlanet(int x, int y)
    {
        for (Planet planet : gm.planets)
        {
            Point planetPosition = planet.getPositionInPixels();
            float changeX = planetPosition.x - x;
            float changeY = planetPosition.y - y;
            double distance = Math.sqrt((changeX * changeX + changeY * changeY));

            if (Config.getPlanetDiameter(view.getWidth(), view.getHeight()) + 10 >= distance)
            {
                return planet;
            }
        }
        return null;
    }
    public Planet collidePlanet(int x, int y, int newX, int newY)
    {
        Planet planet = collidePlanet(x, y);
        if (collidePlanet(newX, newY)== planet)
        {
            return planet;
        }
        return null;
    }
}