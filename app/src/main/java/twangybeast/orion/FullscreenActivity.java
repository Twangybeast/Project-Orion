package twangybeast.orion;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {
    private DrawingView drawingView;
    private GameManager gm;
    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        SCREEN_WIDTH = getScreenWidth();
        SCREEN_HEIGHT = getScreenHeight();

        gm = new GameManager(getResources());
        drawingView = new DrawingView(this, SCREEN_WIDTH, SCREEN_HEIGHT, gm);
        drawingView.setLayoutParams(new ViewGroup.LayoutParams(getScreenWidth(), getScreenHeight()));

        drawingView.setOnTouchListener(new OnTouchEventListener(FullscreenActivity.this, drawingView, gm));

        setContentView(drawingView);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    public int getScreenWidth()
    {
        return getApplicationContext().getResources().getDisplayMetrics().widthPixels;
    }
    public int getScreenHeight()
    {
        return getApplicationContext().getResources().getDisplayMetrics().heightPixels-getBarHeights();
    }
    public int getActionBarHeight()
    {
        TypedValue tv = new TypedValue();
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
        {
            return TypedValue.complexToDimensionPixelSize(tv.data,getResources().getDisplayMetrics());
        }
        return 0;
    }
    public int getBarHeights()
    {
        return getActionBarHeight()+getStatusBarHeight();
    }
    public int getStatusBarHeight()
    {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        System.out.println(result);
        return result;
    }
}