package com.example.administrator.a1_person_project;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;

import Cards.Cards;
import Cards.Deck;

/**
 * Created by Administrator on 2017/1/10.
 */

public class MainGame extends AppCompatActivity {

    private Canvas canvas;
    private Deck deck;
    private DisplayMetrics displaymetrics=new DisplayMetrics();
    Cards currentCards;
    private int screenHeight;
    private int screenWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deck=new Deck(this,1);
        Cards cds=deck.getCards(20);
        this.currentCards =cds;
        setContentView(R.layout.game_main);
//        CardView cardView=(CardView) findViewById(R.id.cardView);
//        cardView.setCards(currentCards);
//        cardView.postInvalidate();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screenHeight=displaymetrics.heightPixels;
        screenWidth=displaymetrics.widthPixels;

    }

    public void waitit(){
        try {
            wait(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Deck getDeck() {
        return deck;
    }

    public Cards getCards() {
        return currentCards;
    }

    public void cancelOut(View view){
        int size=currentCards.getNumOfCards();
        for(int i=0;i<currentCards.getNumOfCards();i++){
            int cardTemp=currentCards.getCard(i).getNumber();
            for(int j=0;j<currentCards.getNumOfCards();j++){
                int cardTemp2=currentCards.getCard(j).getNumber();
                if(cardTemp==cardTemp2&&i!=j){
                    if(currentCards.getCardType(cardTemp)!=-1||currentCards.getCardType(cardTemp2)!=-1)
                    currentCards.setCardType(cardTemp,-1);
                    currentCards.setCardType(cardTemp2,-1);
                    //                   cancelOut();
                    //                   return;
                }
            }
        }
        for(int i=0;i<currentCards.getNumOfCards();i++){
            if(currentCards.getCard(i).getType()==-1){
                currentCards.pop(i);
            }
        }

        if(size!=currentCards.getNumOfCards()) {
            setContentView(R.layout.game_main);
        }
    //    onContentChanged();
    }



    @Override
    public Resources getResources() {
        return super.getResources();
    }




}
