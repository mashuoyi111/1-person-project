package Views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.administrator.a1_person_project.MainGame;
import com.example.administrator.a1_person_project.R;

import java.util.ArrayList;
import java.util.Collections;

import Cards.Card;
import Cards.Deck;
import Cards.Cards;


/**
 * Created by Administrator on 2017/1/12.
 */

public class CardBackView extends View {
    Context context;
    private Cards cards;
    private int screenWidth;
    private int screenHeight;
    private int cardHeight;
    private int cardWidth;
    private int selector;
    private int cardGap;
    private Bitmap cardBack;
    private Bitmap cardBackSelected;

    public CardBackView(Context context,AttributeSet attrs) {
        super(context);
        this.context=context;
        cards=new Cards(context,new ArrayList<Card>());
        MainGame host=(MainGame) this.getContext();
        cards=host.getOpponentCards();
        screenWidth=host.getScreenWidth();
        screenHeight=host.getScreenHeight();
        selector=host.getCurrentSelector();
        setCardGap(screenWidth);
        setCardSize(screenHeight,screenWidth);
        cardBack= Deck.back;
        cardBackSelected=Deck.backSelected;
    }

    private void setCardSize(int screenHeight, int screenWidth) {
        cardHeight=(551*screenHeight)/1920;
        cardWidth=(380*screenWidth)/1080;
    }

    private void setCardGap(int screenWidth) {
        cardGap=(70*screenWidth)/1080;
    }

    private Bitmap getBitmapFromFile(int id){
        return BitmapFactory.decodeResource(context.getResources(),id);
    }


    public void setCards(Cards cards){
        this.cards=cards;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int numofcards=cards.getNumOfCards();
        if(numofcards>=1) {
            if (numofcards > 14) {
                int heightTempT = 0;
                //heightTempT is height of top part of cards, should appear just under 1/2 of the screen
                int heightTempB = (canvas.getHeight() - cards.getCards().get(0).getCardPic().getHeight());
                //heightTempB is height of bottom part of cards, should appear at bottom of the screen
                int topnum = numofcards - 14;
                int botnum = 14;
                for (int i = 0; i < numofcards; i++) {
                    Card cardTemp = cards.getCards().get(i);
                    if (i < botnum) {
                        if(i!=selector) {
                            canvas.drawBitmap(cardBack, i * cardGap, heightTempB, null);
                        }else {canvas.drawBitmap(cardBackSelected, i * cardGap, heightTempB, null);}
                    } else {
                        if(i!=selector) {
                            canvas.drawBitmap(cardBack, (i - botnum) * cardGap, heightTempT, null);
                        }else {canvas.drawBitmap(cardBackSelected, (i - botnum) * cardGap, heightTempT, null);}
                    }
                }

            } else {
                int heightTemp = canvas.getHeight()-cards.getCards().get(0).getCardPic().getHeight();
                //heightTemp is the height that make the cards display at 3/4 of the screen;
                for (int i = 0; i < numofcards; i++) {
                    if(i!=selector) {
                        canvas.drawBitmap(cardBack, i * cardGap, heightTemp, null);
                    }else{canvas.drawBitmap(cardBackSelected, i * cardGap, heightTemp, null);}
                }

            }
        }
    }





}
