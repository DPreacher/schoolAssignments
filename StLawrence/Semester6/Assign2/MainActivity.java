package ca.on.sl.comp208.paintgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DrawView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = (DrawView)findViewById(R.id.canvas);
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
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item); //returns the value of the base constructor
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        Integer x = (int)event.getX();
        Integer y = (int)event.getY(); //comes in as a floating point value
        if(action == MotionEvent.ACTION_DOWN)
            view.moveTo(x,y);
        else
            if(action == MotionEvent.ACTION_MOVE || action == MotionEvent.ACTION_UP)
                view.lineTo(x,y);
        return super.onTouchEvent(event); //returns the value of the base constructor
    }
}
