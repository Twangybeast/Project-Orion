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
    public int xEndBound;
    public int yEndBound;
    private int width;
    private int height;
    public static final int[] PLAYERS_COLOR = {Color.RED, Color.GREEN, Color.CYAN, Color.MAGENTA, Color.BLUE};
    public GameManager gm;

    public DrawingView(Context context, int width, int height, GameManager gm)
    {
        super(context);
        this.context = context;
        this.width = width;
        this.height = height;
        this.gm = gm;
        paint = new Paint();
        paint.setColor(0xFF000000);
        paint.setAntiAlias(true);


        //play();
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        canvas.drawColor(BACKGROUND);

        for (Planet planet : gm.planets)
        {
            drawPlanet(canvas, paint, planet, 0, 0);
        }
        drawTurn(canvas, paint);
        if(gm.hoveredPlanet != null) {
            drawContextBox(canvas, paint, gm.hoveredPlanet);
        }
        drawAttackResult(canvas, paint);
        invalidate();
    }
    private void drawAttackResult(Canvas canvas, Paint paint)
    {
        if (gm.ae != null)
        {
            paint.setTextSize(100);
            int cwidth = width/2;
            int cheight = height/2;
            int textX = cwidth/2;
            int textY = cheight/2;
            int lineHeight = 95;
            paint.setColor(0xFF444444);
            canvas.drawRect(cwidth/2, cheight/2, (int)(cwidth*1.5f), (int)(cheight*1.5f), paint);
            paint.setColor(0xFFEEEEEE);
            canvas.drawText("Winner: ", textX, textY + lineHeight, paint);
            canvas.drawText((gm.ae.successfulCapture ? "Attacker" : "Defender"), textX, textY + lineHeight*2, paint);
            canvas.drawText("Attacking: ", textX, textY + lineHeight*3, paint);
            canvas.drawText(gm.ae.attackingStart + " - "+gm.ae.attackingLoss + " = " + Math.max(0,gm.ae.attackingStart-gm.ae.attackingLoss), textX, textY + lineHeight*4, paint);
            canvas.drawText("Defending: ", textX, textY + lineHeight*5, paint);
            canvas.drawText(gm.ae.defendingStart + " - "+gm.ae.defendingLoss + " = " + Math.max(0,gm.ae.defendingStart-gm.ae.defendingLoss), textX, textY + lineHeight*6, paint);

        }
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

        paint.setColor(0xFFEEEEEE);
        paint.setTextSize(45);
        int textX = px + 30*backwards + (backwards < 0 ? -cw : 0);
        int textY = py - ch/2;
        int lineHeight = 40;
        canvas.drawText("Defense: " + planet.getDefense(), textX, textY + lineHeight, paint);
        canvas.drawText("Production: " + planet.getProduction(), textX, textY + lineHeight*2, paint);
        canvas.drawText("Science: " + planet.getScience(), textX, textY + lineHeight*3, paint);
        canvas.drawText("Troops: " + planet.getTroop().getStrength(), textX, textY + lineHeight*4, paint);
    }

    public void drawPlanet(Canvas canvas, Paint paint, Planet planet, int dx, int dy)
    {
        paint.setColor(planet.getColor());
        Point p = Config.getScreenCoordinates(planet.getPosition().x, planet.getPosition().y, width, height);
        canvas.drawCircle(p.x+Config.getCellWidth(width)/2, p.y+Config.getCellHeight(height)/2, (int)(Config.getPlanetDiameter(width, height)/2), paint);

    }
    public void drawTurn(Canvas canvas, Paint paint)
    {
        int playerColor = gm.players[gm.turn].getColor();
        paint.setColor(playerColor);
        paint.setTextSize(50);
        Rect bounds = new Rect();
        paint.getTextBounds("End Turn",0, "End Turn".length(), bounds);
        xEndBound = width - bounds.width() - 25;
        yEndBound = height - bounds.height() - 30;
        canvas.drawText("End Turn",width-bounds.width()-10, height-20, paint);
        Rect tBounds = new Rect();
        paint.getTextBounds("Player " + gm.turn,0, ("Player " + gm.turn).length(), bounds);
        canvas.drawText("Player " + (gm.turn + 1),0, tBounds.height()+50, paint);
    }
}