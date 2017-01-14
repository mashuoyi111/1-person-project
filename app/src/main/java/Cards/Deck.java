package Cards;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.administrator.a1_person_project.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/1/11.
 */

public class Deck {
    // this class contains a certain number of cards in one deck
    private ArrayList<Card> deck=new ArrayList<Card>();
    private int numOfDeck;
    private Context context;
    private int screenHeight;
    private int screenWidth;
    private int cardHeight;
    private int cardWidth;
    public static Bitmap back;
    public static Bitmap backSelected;

    public Deck(Context context, int numOfDeck,int height, int width){
        this.context=context;
        this.numOfDeck=numOfDeck;
        this.screenHeight=height;
        this.screenWidth=width;
        setCardSize(screenHeight,screenWidth);
        deck=createDeck();
        shuffleCards();
        back=Bitmap.createScaledBitmap(getBitmapFromFile(R.drawable.card_back),cardWidth,cardHeight,false);
        backSelected=Bitmap.createScaledBitmap(getBitmapFromFile(R.drawable.card_back_selected),cardWidth,cardHeight,false);
    }

    private void setCardSize(int screenHeight, int screenWidth) {
        cardHeight=(551*screenHeight)/1920;
        cardWidth=(380*screenWidth)/1080;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public Card getOneCard(){
        Card c=deck.get(0);
        deck.remove(0);
        return c;
    }

    public Integer getNumOfCards(){
        return deck.size();
    }

    public Cards getCards(int num){
        ArrayList<Card> cardsTemp=new ArrayList<Card>();
        for(int i=0;i<num;i++){
            cardsTemp.add(getOneCard());
        }
        return new Cards(context,cardsTemp);
    }

    public int getNumOfDeck() {
        return numOfDeck;
    }



    private Bitmap getBitmapFromFile(int id){
        return BitmapFactory.decodeResource(context.getResources(),id);
    }

    private ArrayList<Card> createDeck() {
        ArrayList<Card> deckTemp=new ArrayList<Card>();
        int bitTemp=0;
        for(int k=0;k<numOfDeck;k++) {
            //number of cards is determined by number of decks
            for (int j = 1; j <= 4; j++) {
                //j is type
                for (int i = 1; i <= 13; i++) {
                    // i is number
                    switch (j) {
                        case 1:
                            switch (i) {
                                case 1:
                                    bitTemp = R.drawable.ace_of_clubs;
                                    break;
                                case 2:
                                    bitTemp = R.drawable.two_of_clubs;
                                    break;
                                case 3:
                                    bitTemp = R.drawable.three_of_clubs;
                                    break;
                                case 4:
                                    bitTemp = R.drawable.four_of_clubs;
                                    break;
                                case 5:
                                    bitTemp = R.drawable.five_of_clubs;
                                    break;
                                case 6:
                                    bitTemp = R.drawable.six_of_clubs;
                                    break;
                                case 7:
                                    bitTemp = R.drawable.seven_of_clubs;
                                    break;
                                case 8:
                                    bitTemp = R.drawable.eight_of_clubs;
                                    break;
                                case 9:
                                    bitTemp = R.drawable.nine_of_clubs;
                                    break;
                                case 10:
                                    bitTemp = R.drawable.ten_of_clubs;
                                    break;
                                case 11:
                                    bitTemp = R.drawable.jack_of_clubs;
                                    break;
                                case 12:
                                    bitTemp = R.drawable.queen_of_clubs;
                                    break;
                                case 13:
                                    bitTemp = R.drawable.king_of_clubs;
                                    break;
                            }
                            break;
                        case 2:
                            switch (i) {
                                case 1:
                                    bitTemp = R.drawable.ace_of_diamonds;
                                    break;
                                case 2:
                                    bitTemp = R.drawable.two_of_diamonds;
                                    break;
                                case 3:
                                    bitTemp = R.drawable.three_of_diamonds;
                                    break;
                                case 4:
                                    bitTemp = R.drawable.four_of_diamonds;
                                    break;
                                case 5:
                                    bitTemp = R.drawable.five_of_diamonds;
                                    break;
                                case 6:
                                    bitTemp = R.drawable.six_of_diamonds;
                                    break;
                                case 7:
                                    bitTemp = R.drawable.seven_of_diamonds;
                                    break;
                                case 8:
                                    bitTemp = R.drawable.eight_of_diamonds;
                                    break;
                                case 9:
                                    bitTemp = R.drawable.nine_of_diamonds;
                                    break;
                                case 10:
                                    bitTemp = R.drawable.ten_of_diamonds;
                                    break;
                                case 11:
                                    bitTemp = R.drawable.jack_of_diamonds;
                                    break;
                                case 12:
                                    bitTemp = R.drawable.queen_of_diamonds;
                                    break;
                                case 13:
                                    bitTemp = R.drawable.king_of_diamonds;
                                    break;
                            }
                            break;
                        case 3:
                            switch (i) {
                                case 1:
                                    bitTemp = R.drawable.ace_of_hearts;
                                    break;
                                case 2:
                                    bitTemp = R.drawable.two_of_hearts;
                                    break;
                                case 3:
                                    bitTemp = R.drawable.three_of_hearts;
                                    break;
                                case 4:
                                    bitTemp = R.drawable.four_of_hearts;
                                    break;
                                case 5:
                                    bitTemp = R.drawable.five_of_hearts;
                                    break;
                                case 6:
                                    bitTemp = R.drawable.six_of_hearts;
                                    break;
                                case 7:
                                    bitTemp = R.drawable.seven_of_hearts;
                                    break;
                                case 8:
                                    bitTemp = R.drawable.eight_of_hearts;
                                    break;
                                case 9:
                                    bitTemp = R.drawable.nine_of_hearts;
                                    break;
                                case 10:
                                    bitTemp = R.drawable.ten_of_hearts;
                                    break;
                                case 11:
                                    bitTemp = R.drawable.jack_of_hearts;
                                    break;
                                case 12:
                                    bitTemp = R.drawable.queen_of_hearts;
                                    break;
                                case 13:
                                    bitTemp = R.drawable.king_of_hearts;
                                    break;
                            }
                            break;
                        case 4:
                            switch (i) {
                                case 1:
                                    bitTemp = R.drawable.ace_of_spades;
                                    break;
                                case 2:
                                    bitTemp = R.drawable.two_of_spades;
                                    break;
                                case 3:
                                    bitTemp = R.drawable.three_of_spades;
                                    break;
                                case 4:
                                    bitTemp = R.drawable.four_of_spades;
                                    break;
                                case 5:
                                    bitTemp = R.drawable.five_of_spades;
                                    break;
                                case 6:
                                    bitTemp = R.drawable.six_of_spades;
                                    break;
                                case 7:
                                    bitTemp = R.drawable.seven_of_spades;
                                    break;
                                case 8:
                                    bitTemp = R.drawable.eight_of_spades;
                                    break;
                                case 9:
                                    bitTemp = R.drawable.nine_of_spades;
                                    break;
                                case 10:
                                    bitTemp = R.drawable.ten_of_spades;
                                    break;
                                case 11:
                                    bitTemp = R.drawable.jack_of_spades;
                                    break;
                                case 12:
                                    bitTemp = R.drawable.queen_of_spades;
                                    break;
                                case 13:
                                    bitTemp = R.drawable.king_of_spades;
                                    break;
                            }
                            break;

                    }
                    Bitmap bTemp=Bitmap.createScaledBitmap(getBitmapFromFile(bitTemp),cardWidth,cardHeight,false);

                    deckTemp.add(new Card(j, i, bTemp));
                }
            }
        }
        Bitmap bTemp=Bitmap.createScaledBitmap(getBitmapFromFile(R.drawable.black_joker),cardWidth,cardHeight,false);
        deckTemp.add(new Card(0, 14, bTemp));
        return deckTemp;
    }

    public void shuffleCards(){
        Collections.shuffle(deck);
    }
}
