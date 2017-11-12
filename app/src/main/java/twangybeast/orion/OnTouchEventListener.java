package twangybeast.orion;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

public class OnTouchEventListener implements View.OnTouchListener {

    DrawingView view;

    public OnTouchEventListener(Context ctx, DrawingView drawingPanel) {
        view = drawingPanel;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        view.touchAt(event.getX(), event.getY());

        return true;
    }
}