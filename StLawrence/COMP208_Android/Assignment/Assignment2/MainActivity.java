package ca.on.sl.comp208.paintgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DrawView view;
    private Canvas canvas;
    private Bitmap bmp;
    private SurfaceHolder holder;
    float x0,y0;
    boolean init;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = (DrawView)findViewById(R.id.canvas);
        holder = view.getHolder();
        init = true;

    }

    public void init(){
        canvas = holder.lockCanvas();

        Paint whiteBrush = new Paint();
        whiteBrush.setColor(Color.WHITE);
        float x,y;
        for(int v = 0; v < 10; v++){
            x=v*50f;
            for(int h=0;h<10;h++) {
                y = h*50f;
                canvas.drawRect(x, y,x + 50,y+ 50, whiteBrush);
            }
        }

        holder.unlockCanvasAndPost(canvas);
    }
    public void draw(View view){
        canvas = holder.lockCanvas();

        Paint blueBrush = new Paint();
        blueBrush.setColor(Color.BLUE);
        if(x0<450)
            x0 += 50;
        else {
            x0 = 0;
            y0 += 50;
        }
        canvas.drawRect(x0,y0,x0+50,y0+50,blueBrush);


        holder.unlockCanvasAndPost(canvas);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Style");
        menu.add("Colour");
        menu.add("width");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i("Menu","Item Selected");
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item); //returns the value of the base constructor
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // got here was here
        if(init) {
            init();
            init=false;
        }
        draw(view);

        /*
        int action = event.getAction();
        Log.i("onTouchEvent","Action Retrieved");
        float x = event.getX();
        float y = event.getY(); //comes in as a floating point value
        if(action == MotionEvent.ACTION_DOWN) {
            view.moveTo(x, y, x + 1, y + 1);
            Log.i("onTouchEvent", "ActionDown");
        }
        else {
            if (action == MotionEvent.ACTION_MOVE || action == MotionEvent.ACTION_UP) {
                view.lineTo(x, y, x + 1, y + 1);
                Log.i("onTouchEvent", "ActionMove or Up");
            }
            Log.i("onTouchEvent","else Stmt");
        }
        Log.i("onTouchEvent","Finished");
        */
        return super.onTouchEvent(event); //returns the value of the base constructor

    }
}
