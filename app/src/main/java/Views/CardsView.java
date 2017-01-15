package Views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
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

import java.util.ArrayList;
import java.util.Collections;

import Cards.Card;
import Cards.Cards;
import Cards.Deck;



/**
 * Created by Administrator on 2017/1/11.
 */

public class CardsView extends View {
    Context context;
    private Cards cards;
    private int cardGap;

    public CardsView(Context context, AttributeSet attrs) {
        super(context);
        this.context=context;
        cards=new Cards(context,new ArrayList<Card>());
        MainGame host=(MainGame) this.getContext();
        cards=host.getCards();
        cardGap= Deck.getCardGap();
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
                        canvas.drawBitmap(cardTemp.getCardPic(), i * cardGap, heightTempB, null);
                    } else {
                        canvas.drawBitmap(cardTemp.getCardPic(), (i - botnum) * cardGap, heightTempT, null);
                    }
                }

            } else {
                int heightTemp = canvas.getHeight()-cards.getCards().get(0).getCardPic().getHeight();
                //heightTemp is the height that make the cards display at 3/4 of the screen;
                for (int i = 0; i < numofcards; i++) {
                    Card cardTemp = cards.getCards().get(i);
                    canvas.drawBitmap(cardTemp.getCardPic(), i * cardGap, heightTemp, null);
                }

            }
        }
    }





}
