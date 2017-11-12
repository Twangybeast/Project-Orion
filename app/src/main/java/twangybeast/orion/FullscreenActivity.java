package twangybeast.orion;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    public static GameManager gm;
    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        int numPlayers = 3;

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            numPlayers = Integer.parseInt(extras.getString("playerNum"));
        }

        SCREEN_WIDTH = getScreenWidth();
        SCREEN_HEIGHT = getScreenHeight();

        gm = new GameManager(getResources(), numPlayers, this);
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
        return result;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.science) {
            System.out.println("SCIENCE CLICKED");

            Intent intent = new Intent(this, ScienceManagerPageActivity.class);
            intent.putExtra("player_index", String.valueOf(gm.turn));
            this.startActivity(intent);


            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}