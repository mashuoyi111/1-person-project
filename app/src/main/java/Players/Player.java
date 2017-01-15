package Players;
import Cards.Card;
import Cards.Deck;
import Cards.Cards;
/**
 * Created by Administrator on 2017/1/11.
 */

public class Player {
    private boolean firstRound;
    private boolean win;
    private String name;
    private Cards cards;

    public Player(String name,Cards cards){
        this.name=name;
        this.cards=cards;
        firstRound=true;
        win=false;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCards(Cards cards) {
        this.cards = cards;
    }

    public Cards getCards() {
        return cards;
    }

    public void setFirstRound(boolean firstRound) {
        this.firstRound = firstRound;
    }

    public boolean isFirstRound() {
        return firstRound;
    }

    public void setWin(){
        if(cards.getNumOfCards()==0){
            win=true;
        }
    }

    public boolean isWin(){
        return win;
    }


}
