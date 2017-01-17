package ca.on.sl.comp208.lab1a;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private int[] cardIds = new int[]{
            R.id.imageButton1,
            R.id.imageButton2,
            R.id.imageButton3,
            R.id.imageButton4,
            R.id.imageButton5,
            R.id.imageButton6,
            R.id.imageButton7,
            R.id.imageButton8,
    };
    TypedArray imgs = getResources().obtainTypedArray(R.array.playCards);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton ib;
        for(int i =0; i< imgs.length();i++){
            ib = (ImageButton) findViewById(cardIds[i]);
            ib.setImageResource(imgs.getResourceId(i,-1));
        }

    }

    private void shuffleCards(){
        TypedArray imgs = getResources().obtainTypedArray(R.array.playCards);

        // get resource ID by index
        //imgs.getResourceId(i, -1);


        // recycle the array
        imgs.recycle();
    }

    public void click(View v){
        ImageButton flippedCard = (ImageButton)v;
        switch(flippedCard.getId()){
            case  R.id.imageButton1:
                //TODO change picture of button 1

                break;
            case R.id.imageButton2:
                //TODO change picture of button 2
                break;
            case R.id.imageButton3:
                //TODO change picture of button 3
                break;
            case R.id.imageButton4:
                //TODO change picture of button 4
                break;
            case R.id.imageButton5:
                //TODO change picture of button 5
                break;
            case R.id.imageButton6:
                //TODO change picture of button 6
                break;
            case R.id.imageButton7:
                //TODO change picture of button 7
                break;
            case R.id.imageButton8:
                //TODO change picture of button 8
                break;
            default:
                break;
        }
    }
}
