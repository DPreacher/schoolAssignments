package ca.on.sl.comp208.paintgame;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * Created by dingram20 on 1/30/2017.
 */
public class DrawView extends SurfaceView {
    Integer x0,y0,x1,y1;

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        // onDraw needs to Draw between line segments
        //comes with canvas to draw items.
        //may want to keep the set of line segments to keep track of lines

    }

    public void moveTo(Integer x, Integer y) {

    }

    public void lineTo(Integer x, Integer y) {
        setWillNotDraw(false);//invalidate will not do anything until setWillNotDraw is false
        invalidate();//The screen date is not valid anymore.  Sets a flag to program to show no reference
    }
}
