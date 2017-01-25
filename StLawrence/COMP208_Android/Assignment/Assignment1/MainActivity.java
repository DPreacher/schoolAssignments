package ca.on.sl.comp208.assign1dingram;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private String debugText;
    private ImageButton lastCard, prevCard;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    List<Integer> imgs = new ArrayList<>();
    List<Integer> btnIDs = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeArrays();
        //shuffleCards();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void flipCard(View v) {
        if(prevCard==null){
            ImageButton currentCard = (ImageButton) v;
            CardModel cardTags = (CardModel) currentCard.getTag();
            currentCard.setBackgroundColor(ContextCompat.getColor(this, R.color.card_flipped));
            Log.i("Flip Card"," "+cardTags.getCurrentState());
            switch (cardTags.getCurrentState()) {
                case FACE_DOWN:
                    // change tag to Face up
                    cardTags.setCurrentState(CardState.FACE_UP);
                    //Show card image
                    currentCard.setImageResource(cardTags.getImage());
                    //Check if there is a card flipped already
                    if (lastCard == null) {
                        // Store the Card for Matching later
                        lastCard = currentCard;
                    } else {
                        prevCard = currentCard;
                        //Open stored Card
                        CardModel lastCardFlipped = (CardModel) lastCard.getTag();
                        //check if images match
                        if (lastCardFlipped.getImage() == cardTags.getImage()) {
                            //Set both Cards to Matched
                            lastCardFlipped.setCurrentState(CardState.MATCHED);
                            cardTags.setCurrentState(CardState.MATCHED);
                            prevCard = null;
                            lastCard = null;
                        } else {
                            // TODO: Add time delay so player can see second card
                            Timer timer = new Timer();


                            TimerTask timerTask = new TimerTask(){
                                @Override
                                public void run(){
                                    runOnUiThread(new Runnable (){
                                        @Override
                                        public void run(){
                                            Log.i("runOnUiThread"," started");
                                            //Flip stored card over
                                            ((CardModel) lastCard.getTag()).setCurrentState(CardState.FACE_DOWN);
                                            lastCard.setImageResource(R.drawable.final_fantasy_cover);
                                            lastCard = null;
                                            //Flip current card over
                                            ((CardModel) prevCard.getTag()).setCurrentState(CardState.FACE_DOWN);
                                            prevCard.setImageResource(R.drawable.final_fantasy_cover);
                                            prevCard =null;
                                        }
                                    });
                                }
                            };
                            timer.schedule(timerTask,2000);
                        }
                    }
                    break;
                case FACE_UP:
                    //if Card is face up, flip back over
                    currentCard.setImageResource(R.drawable.final_fantasy_cover);
                    currentCard.setImageResource(R.color.colorAccent);
                    lastCard = null;
                    break;
                case MATCHED:
                    break;
            }
        }
    }
    public void flipCards(){

    }
    private void shuffleCards() {
        Log.v("Shuffle Cards"," Shuffle Cards Called");
        Resources res = getResources();
        Random rndNum = new Random();

        //Select All Images
        Log.v("Shuffle Cards","Retrieving image IDs");
        int[] cardImgs = res.getIntArray(R.array.cardImgs);
        Log.v("Shuffle Cards","Image Array "+cardImgs.toString());
        List<Integer> orgCards = new ArrayList<>();
        Log.v("Shuffle Cards","Image IDs are: ");
        for (int img : cardImgs) {
            Log.v("Shuffle Cards","     "+img);
            orgCards.add(img);
        }

        //Choose 8 Random Images
        List<Integer> choosenImg = new ArrayList<>();
        int numCards = orgCards.size();
        Log.v("Shuffle Cards","Choosing Images");
        debugText = "OrgCards Size is: "+ numCards;
        Toast.makeText(this,debugText,Toast.LENGTH_SHORT).show();
        for (int i = 0; i < 8; i++) {

            int rndImgIndex = rndNum.nextInt(orgCards.size());
            choosenImg.add(orgCards.get(rndImgIndex));
            choosenImg.add(orgCards.get(rndImgIndex));
            Log.v("Shuffle Cards","Card "+(i+1)+" "+rndImgIndex+" "+orgCards.get(rndImgIndex));
            orgCards.remove(rndImgIndex);
        }
        // Shuffle the images 16 times for good shuffle
        for (int i = 0; i < 16; i++) {
            Collections.shuffle(choosenImg);
        }

        //Tag data with new image
        int[] cardIDs = res.getIntArray(R.array.buttonIDs);
        Log.v("Shuffle Cards","Assign All Cards");
        for (int i = 0; i < 16; i++) {

            ImageButton btn = (ImageButton) findViewById(cardIDs[i]);

            CardModel card = new CardModel(choosenImg.get(i));
            btn.setImageResource(R.drawable.final_fantasy_cover);
            btn.setTag(card);
        }


    }

    private void initializeArrays(){
        Log.i("Init Arrays", "Storing Imgs");
        imgs.add(R.drawable.black_mage);
        imgs.add(R.drawable.white_mage);
        imgs.add(R.drawable.dancer);
        imgs.add(R.drawable.dark_knight);
        imgs.add(R.drawable.dragoon);
        imgs.add(R.drawable.knight);
        imgs.add(R.drawable.ninja);
        imgs.add(R.drawable.norm);
        imgs.add(R.drawable.scholar);
        imgs.add(R.drawable.thief);

        Log.i("Init Arrays", "Storing Btns");
        btnIDs.add(R.id.imageButton1);
        btnIDs.add(R.id.imageButton2);
        btnIDs.add(R.id.imageButton3);
        btnIDs.add(R.id.imageButton4);
        btnIDs.add(R.id.imageButton5);
        btnIDs.add(R.id.imageButton6);
        btnIDs.add(R.id.imageButton7);
        btnIDs.add(R.id.imageButton8);
        btnIDs.add(R.id.imageButton9);
        btnIDs.add(R.id.imageButton10);
        btnIDs.add(R.id.imageButton11);
        btnIDs.add(R.id.imageButton12);
        btnIDs.add(R.id.imageButton13);
        btnIDs.add(R.id.imageButton14);
        btnIDs.add(R.id.imageButton15);
        btnIDs.add(R.id.imageButton16);
        Log.i("Init Arrays", "Shuffle Imgs");
        Log.i("Init Arrays", "    "+imgs.toString());
        Collections.shuffle(imgs);
        Log.i("Init Arrays", "Shuffle Imgs Complete");
        Log.i("Init Arrays", "    "+imgs.toString());

        Log.i("Init Arrays", "Shuffle Btns");
        Log.i("Init Arrays", "    "+btnIDs.toString());
        Collections.shuffle(btnIDs);
        Log.i("Init Arrays", "Shuffle Btns Complete");
        Log.i("Init Arrays", "    "+btnIDs.toString());

        Log.i("Init Arrays","Assign Imgs to Btns");
        for(int i=0;i<16;i++){
            CardModel cardData;
            if(i>=8) {
                int tmpInt = i-8;
                cardData = new CardModel(imgs.get(tmpInt));
            }
            else {
                cardData = new CardModel(imgs.get(i));
            }
            ImageButton tmpBtn = (ImageButton) findViewById(btnIDs.get(i));
            tmpBtn.setTag(cardData);
            tmpBtn.setImageResource(R.drawable.final_fantasy_cover);
            Log.i("Init Arrays", "Set CardData to btn "+(i+1));
        }

    }
    private void gameOver() {

    }
