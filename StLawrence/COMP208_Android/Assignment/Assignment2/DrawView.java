package ca.on.sl.comp208.paintgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;

/**
 * Created by dingram20 on 1/30/2017.
 */
public class DrawView extends SurfaceView {
    float x0,y0,x1,y1;

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // onDraw needs to Draw between line segments
        //comes with canvas to draw items.
        //may want to keep the set of line segments to keep track of lines

    }

    public void moveTo(float x, float y, float top, float bottom) {
        x0 = x;
        x1 = bottom;
        y0=y;
        y1=top;
        invalidate();
    }

    public void lineTo(float x, float y, float top, float bottom) {
        setWillNotDraw(false);//invalidate will not do anything until setWillNotDraw is false
        invalidate();//The screen date is not valid anymore.  Sets a flag to program to show no reference
    }
}
