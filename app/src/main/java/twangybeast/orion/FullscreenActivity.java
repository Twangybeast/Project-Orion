package twangybeast.orion;

<<<<<<< HEAD
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.ActionBar;
=======
import android.graphics.Rect;
>>>>>>> refs/remotes/origin/master
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import java.util.ArrayList;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {
    private DrawingView drawingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

<<<<<<< HEAD
        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);


        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);



=======
        //idk what i'm doing
        drawingView = new DrawingView(this, getScreenWidth(), getScreenHeight());
        drawingView.setLayoutParams(new ViewGroup.LayoutParams(getScreenWidth(), getScreenHeight()));
        setContentView(drawingView);
>>>>>>> refs/remotes/origin/master
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
