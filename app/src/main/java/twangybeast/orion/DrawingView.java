package twangybeast.orion;


import android.content.*;
import android.graphics.*;
import android.view.*;

public class DrawingView extends View
{
    private Paint paint;
    private Context context;
    
    public DrawingView(Context context, int width, int height)
    {
        super(context);
        this.context = context;

        paint.setColor(0xFF000000);
        paint.setAntiAlias(true);
    }
    @Override
    public void onDraw(Canvas canvas)
    {

    }

}
