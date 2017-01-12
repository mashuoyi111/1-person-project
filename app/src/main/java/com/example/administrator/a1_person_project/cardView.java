package com.example.administrator.a1_person_project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;

import Cards.Card;
import Cards.Cards;

/**
 * Created by Administrator on 2017/1/11.
 */

public class CardView extends View {
    Context context;
    private int screenHeight;
    private int screenWidth;
    private Cards cards;

    public CardView(Context context, Cards cards, Integer screenheight, Integer screenwidth) {
        super(context);
        this.context=context;
        this.cards=cards;
        this.screenHeight=screenheight;
        this.screenWidth=screenwidth;

    }

    @Override
    protected void onDraw(Canvas canvas) {

        for(int i=0;i<cards.getNumOfCards();i++){
            Card cardTemp= cards.getCards().get(i);
            canvas.drawBitmap(cardTemp.getCardPic(),i*70,0,null);
            Paint paint = new Paint();
            paint.setColor(Color.BLACK);
            paint.setTextSize(100);

        }


    }

}
