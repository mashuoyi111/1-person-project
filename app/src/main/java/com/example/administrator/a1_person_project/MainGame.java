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
    private Card c;
    private Canvas canvas;
    private Deck d;
    private DisplayMetrics displaymetrics=new DisplayMetrics();
    private int screenHeight;
    private int screenWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        d=new Deck(this,1);
        Bitmap b=BitmapFactory.decodeResource(getResources(),R.drawable.two_of_clubs);
        createCard(1,2,b);
        ArrayList<Card> cards=new ArrayList<Card>();
        for(int i=0;i<26;i++){
            cards.add(d.getOneCard());
        }
        Cards cds=new Cards(cards);
        setContentView(new CardView(this,cds,screenHeight,screenWidth));
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screenHeight=displaymetrics.heightPixels;
        screenWidth=displaymetrics.widthPixels;

    }


    @Override
    public Resources getResources() {
        return super.getResources();
    }

    public void createCard(int type,int number, Bitmap cardPic){
        c=new Card(type,number,cardPic);
    }


}
