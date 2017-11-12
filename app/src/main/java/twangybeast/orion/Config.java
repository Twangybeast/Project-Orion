package twangybeast.orion;


import android.graphics.*;

public class Config
{
    public static int GRID_WIDTH = 11;
    public static int GRID_HEIGHT = 21;
    public static Point getScreenCoordinates(int x, int y, int width, int height)
    {
        return new Point((int)((1.0*width/GRID_WIDTH)*x), (int)((1.0*height/GRID_HEIGHT)*y));
    }
    public static float getCellWidth(int width)
    {
        return (float)(1.0*width/GRID_WIDTH);
    }
    public static float getCellHeight(int height)
    {
        return (float)(1.0*height/GRID_HEIGHT);
    }
    public static float getPlanetDiameter(int width, int height)
    {
        return Math.min(getCellWidth(width), getCellHeight(height));
    }
}
