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
        if(index>0||index<cards.size()) {
            Card temp = cards.get(index);
            cards.remove(index);
            return temp;
        }else{
            return null;
        }
    }

    public void orderCards(){
        ArrayList<Card> temp=new ArrayList<Card>();
        for(int j=1;j<16;j++) {
            for (int i = 0; i < getNumOfCards(); i++) {
                if(cards.get(i).getNumber()==j){
                    temp.add(cards.get(i));
                }
            }
        }
        cards=temp;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
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
