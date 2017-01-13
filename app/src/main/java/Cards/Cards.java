package Cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Created by Administrator on 2017/1/11.
 */

public class Cards{
    // this class contains several cards that kept by one player
    private ArrayList<Card> cards=new ArrayList<Card>();
    public Cards(ArrayList<Card> cards){
        this.cards=cards;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public Card getCard(int index) {
        return cards.get(index);
    }

    public Card pop(int index){
        Card temp=cards.get(index);
        cards.remove(index);
        return temp;
    }

    public Integer getNumOfCards(){
        return cards.size();
    }

    public void setCardType(int index, int type){
        cards.get(index).setType(type);
    }


    public int getCardType(int index){
        return cards.get(index).getType();
    }

    public void shuffleCards(){
        Collections.shuffle(cards);
    }

}
