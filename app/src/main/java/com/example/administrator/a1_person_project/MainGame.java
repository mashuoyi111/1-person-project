package com.example.administrator.a1_person_project;

import android.app.ActionBar;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import Cards.Card;
import Cards.Cards;
import Cards.Deck;
import Players.Player;

/**
 * Created by Administrator on 2017/1/10.
 */

public class MainGame extends AppCompatActivity {

    private ArrayList<Player> players=new ArrayList<Player>();
    private Deck deck;
    private DisplayMetrics displaymetrics=new DisplayMetrics();
    Cards currentCards;
    Cards opponentCards;
    Player currentPlayer;
    Player opponentPlayer;
    boolean cancelledOut;
    private int screenHeight;
    private int screenWidth;
//    public final Bitmap cardBack=Bitmap.createScaledBitmap(BitmapFactory.decodeResource
//            (this.getResources(),R.drawable.card_back),380,551,false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screenHeight=displaymetrics.heightPixels;
        screenWidth=displaymetrics.widthPixels;
        deck=new Deck(this,1,screenHeight,screenWidth);
        cancelledOut=false;
        Cards cds=deck.getCards(27);
        this.currentCards =cds;
        setContentView(R.layout.game_main);

    }


    public Deck getDeck() {
        return deck;
    }

    public Cards getCards() {
        return currentCards;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void cancelOut(View view){
        int size=currentCards.getNumOfCards();
        ArrayList<Card> addTemp=new ArrayList<Card>();
        for(int i=0;i<size;i++){
            int cardTemp=currentCards.getCard(i).getNumber();
            for(int j=0;j<size;j++){
                int cardTemp2=currentCards.getCard(j).getNumber();
                if(cardTemp==cardTemp2&&i!=j){
                    if(currentCards.getCardType(i)!=-1&&currentCards.getCardType(j)!=-1) {
                        currentCards.setCardType(i, -1);
                        currentCards.setCardType(j, -1);
                    }
                }
            }
        }
        for(int i=0;i<size;i++){
            if(currentCards.getCard(i).getType()!=-1){
                addTemp.add(currentCards.getCard(i));
            }
        }

        if(addTemp.size()>0) {
            currentCards.setCards(addTemp);
            setContentView(R.layout.game_main);
        }
        cancelledOut=true;
        Button b=(Button) findViewById(R.id.buttonCancelOut);
        b.setText("Pick one card from another Player!");
    //    onContentChanged();
    }


    public void shuffleCards(View view){
        currentCards.shuffleCards();
        setContentView(R.layout.game_main);
    }

    public void orderCards(View view){
        currentCards.orderCards();
//        currentCards=currentCards.getBackCopy();
        setContentView(R.layout.game_main);
    }

    @Override
    public Resources getResources() {
        return super.getResources();
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void setContentView(int id){
        super.setContentView(id);
        if(cancelledOut) {
            Button b = (Button) findViewById(R.id.buttonCancelOut);
            b.setText("Pick one card!");
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

    }


}
