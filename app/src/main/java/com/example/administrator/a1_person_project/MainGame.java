package com.example.administrator.a1_person_project;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import Cards.Card;
import Cards.Cards;
import Cards.Deck;

/**
 * Created by Administrator on 2017/1/10.
 */

public class MainGame extends AppCompatActivity {

    private Canvas canvas;
    private Deck deck;
    private DisplayMetrics displaymetrics=new DisplayMetrics();
    private int screenHeight;
    private int screenWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deck=new Deck(this,1);
        Cards cds=deck.getCards(25);
        setContentView(new CardView(this,cds,screenHeight,screenWidth));
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


    @Override
    public Resources getResources() {
        return super.getResources();
    }




}
