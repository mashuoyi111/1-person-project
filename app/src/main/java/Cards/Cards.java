package Cards;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/1/11.
 */

public class Cards {
    // this class contains several cards that kept by one player
    private ArrayList<Card> cards=new ArrayList<Card>();
    public Cards(ArrayList<Card> cards){
        this.cards=cards;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public Integer getNumOfCards(){
        return cards.size();
    }
}
