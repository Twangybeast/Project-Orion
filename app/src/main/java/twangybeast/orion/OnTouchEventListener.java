package twangybeast.orion;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.support.constraint.solver.widgets.Rectangle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.NumberPicker;

public class OnTouchEventListener implements View.OnTouchListener
{
    public static final int TAP_TIME = 1000;//Max time to be considered a tap
    public static final int HOLD_TIME = 1500; //Min time to be considered a hold
    public static final int DEFAULT_SENSITIVITY = 30;
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
            Planet planet = collidePlanet(downPos.x, downPos.y, (int)event.getX(), (int)event.getY(), DEFAULT_SENSITIVITY);
            if (planet != null)
            {
                if (gm.selectedPlanet == null && gm.players[gm.turn]==planet.getOwner())
                {
                    gm.hoveredPlanet = planet;
                    gm.selectedPlanet = gm.hoveredPlanet;

                    Context context = view.getContext();

                    Intent intent = new Intent(context, ProductionManagerPageActivity.class);
                    int index = gm.planets.length - 1;
                    for(int i = 0; i < gm.planets.length - 1; i++){
                        if(planet.equals(gm.planets[i])){
                            index = i;
                            break;
                        }
                    }
                    intent.putExtra("planet_index", String.valueOf(index));
                    context.startActivity(intent);
                }
            }
        }
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                downPos = new Point((int)event.getX(), (int)event.getY());
                down = true;
                gm.ae = null;
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
                    Planet planet = collidePlanet(downPos.x, downPos.y, (int)event.getX(), (int)event.getY(), DEFAULT_SENSITIVITY);
                    gm.hoveredPlanet = planet;
                }
                //Swipe
                final Planet start = collidePlanet(downPos.x, downPos.y, DEFAULT_SENSITIVITY);
                if (start!= null && start.getOwner() == gm.players[gm.turn])
                {
                    final Planet target = collidePlanet((int)event.getX(), (int)event.getY(), DEFAULT_SENSITIVITY*2);
                    if (target != null && target != start)
                    {

                        final NumberPicker numberPicker = new NumberPicker(FullscreenActivity.gm.context);

                        numberPicker.setMaxValue(start.getTroop().getStrength());
                        numberPicker.setMinValue(0);

                        AlertDialog.Builder builder = new AlertDialog.Builder(FullscreenActivity.gm.context);
                        builder.setTitle("Attack Planet " + target.getName());
                        builder.setMessage("Choose a number of troops to deploy: ");
                        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        builder.setView(numberPicker);

                        builder.setPositiveButton("ATTACK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int moveStrength = numberPicker.getValue();

                                //Moves some number of troops
                                //TODO
                                if (start.getOwner() == target.getOwner())
                                {
                                    //Friendly, transport troops
                                    TroopManagerActivity.moveTroops(start, target, moveStrength);
                                }
                                else
                                {
                                    gm.ae = TroopManagerActivity.attack(start, target, moveStrength);
                                }
                            }
                        });

                        builder.create();
                        builder.show();
                    }
                }
                gm.selectedPlanet = null;
                break;
        }
        return true;
    }
    public boolean collideEndTurn(int x, int y)
    {
        return x >= view.xEndBound && y >= view.yEndBound;
    }
    public Planet collidePlanet(int x, int y, int sensitivity)
    {
        Planet closest = null;
        double minDist = Double.MAX_EXPONENT;
        for (Planet planet : gm.planets)
        {
            Point planetPosition = planet.getPositionInPixels();
            double distance = Math.hypot(planetPosition.x - x, planetPosition.y-y);
            if (distance < minDist)
            {
                closest = planet;
                minDist = distance;
            }
        }
        if (minDist <= Config.getPlanetDiameter(view.getWidth(), view.getHeight())+sensitivity)
        {
            return closest;
        }
        return null;
    }
    public Planet collidePlanet(int x, int y, int newX, int newY, int sensitivity)
    {
        Planet planet = collidePlanet(x, y, sensitivity);
        if (collidePlanet(newX, newY, sensitivity)== planet)
        {
            return planet;
        }
        return null;
    }
}