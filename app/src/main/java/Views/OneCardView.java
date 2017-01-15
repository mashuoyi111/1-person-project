package Views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;


import com.example.administrator.a1_person_project.MainGame;

import Cards.Card;
import Cards.Cards;
import Cards.Deck;

/**
 * Created by Administrator on 2017/1/14.
 */

public class OneCardView extends View {
    private int selector;
    private Cards cards;
    private int cardHeight;
    private int cardWidth;

    public OneCardView(Context context, AttributeSet attr) {
        super(context);
        MainGame host=(MainGame) this.getContext();
        cards=host.getOpponentCards();
        selector=host.getCurrentSelector();
        cards=host.getOpponentCards();
        cardHeight= Deck.getOneCardHeight();
        cardWidth=Deck.getOneCardWidth();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Card card=cards.getCard(selector);
        int widthTemp=(canvas.getWidth()-cardWidth)/2;
        int heightTemp=(canvas.getHeight()-cardHeight)/2;
        canvas.drawBitmap(Bitmap.createScaledBitmap(card.getCardPic(),cardWidth,cardHeight,false),widthTemp,heightTemp,null);

    }




}
