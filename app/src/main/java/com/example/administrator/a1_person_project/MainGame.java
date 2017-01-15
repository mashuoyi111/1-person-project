package com.example.administrator.a1_person_project;

import android.app.ActionBar;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import Cards.Card;
import Cards.Cards;
import Cards.Deck;
import Players.Player;

/**
 * Created by Administrator on 2017/1/10.
 */

public class MainGame extends AppCompatActivity {

    private ArrayList<Player> players=new ArrayList<Player>();
    private ArrayList<Player> wonPlayers=new ArrayList<Player>();
    private int currentPlayerPointer;
    private Deck deck;
    private DisplayMetrics displaymetrics=new DisplayMetrics();
    private int currentSelector;
    private Cards currentCards;
    private Cards opponentCards;
    private Player currentPlayer;
    private Player opponentPlayer;
    boolean cancelledOut;
    private int screenHeight;
    private int screenWidth;
//    public final Bitmap cardBack=Bitmap.createScaledBitmap(BitmapFactory.decodeResource
//            (this.getResources(),R.drawable.card_back),380,551,false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screenHeight=displaymetrics.heightPixels;
        screenWidth=displaymetrics.widthPixels;
        deck=new Deck(this,1,screenHeight,screenWidth);
        cancelledOut=false;
        Cards cds=deck.getCards(26);
        Cards opcds=deck.getCards(27);
        Player p1=new Player("P1",cds);
        Player p2=new Player("p2",opcds);
        players.add(p1);
        players.add(p2);
        currentPlayer=p1;
        opponentPlayer=p2;
        currentPlayerPointer=0;
        this.currentCards =cds;
        this.opponentCards=opcds;
        this.currentSelector=0;
        setContentView(R.layout.game_main);

    }

    public int getCurrentSelector() {
        return currentSelector;
    }

    public Deck getDeck() {
        return deck;
    }

    public Cards getCards() {
        return currentCards;
    }

    public Cards getOpponentCards() {
        return opponentCards;
    }


    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void cancelOut(View view){
        int size=currentCards.getNumOfCards();
        ArrayList<Card> addTemp=new ArrayList<Card>();
        for(int i=0;i<size;i++){
            int cardTemp=currentCards.getCard(i).getNumber();
            for(int j=0;j<size;j++){
                int cardTemp2=currentCards.getCard(j).getNumber();
                if(cardTemp==cardTemp2&&i!=j){
                    if(currentCards.getCardType(i)!=-1&&currentCards.getCardType(j)!=-1) {
                        currentCards.setCardType(i, -1);
                        currentCards.setCardType(j, -1);
                    }
                }
            }
        }
        for(int i=0;i<size;i++){
            if(currentCards.getCard(i).getType()!=-1){
                addTemp.add(currentCards.getCard(i));
            }
        }

        if(addTemp.size()>0) {
            currentCards.setCards(addTemp);
            setContentView(R.layout.game_main);
        }
        cancelledOut=true;
//        if(currentPlayer.isFirstRound()) {
//            Button b = (Button) findViewById(R.id.buttonCancelOut);
//            b.setText("Pick one card from another Player!");
//            b.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    currentPlayer.setFirstRound(false);
//                    setContentView(R.layout.game_main_pick);
//                }
//            });
//        }else{
            Button b = (Button) findViewById(R.id.buttonCancelOut);
            b.setText("I'm done with my cards");
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentPlayer.setFirstRound(false);
                    setContentView(R.layout.game_pass_phone);
                    checkWinStatus();
                }
            });
//        }

    }

    private void checkWinStatus() {
        currentPlayer.setCards(currentCards);
        opponentPlayer.setCards(opponentCards);
        currentPlayer.setWin();
        opponentPlayer.setWin();
        cancelledOut=false;
        TextView wininfo= (TextView) findViewById(R.id.winInfo);
        TextView nextPlayer= (TextView) findViewById(R.id.passPhoneInfo);
        Button b = (Button) findViewById(R.id.playerConfirmed);
        if(currentPlayer.isWin()||opponentPlayer.isWin()){
        if(currentPlayer.isWin()&&opponentPlayer.isWin()){
            wininfo.setText("Your pick made you both Win!");
        }else{
            if(currentPlayer.isWin()){
                wininfo.setText("You Win the game!");
                currentPlayer=opponentPlayer;
                if(players.size()>0){
                    opponentPlayer = players.get(0);
                    players.remove(0);
                    nextPlayer.setText("please pass the phone to " + currentPlayer.getName());
                    b.setText("Yeah, I am " + currentPlayer.getName());
                    currentCards=currentPlayer.getCards();
                    opponentCards=opponentPlayer.getCards();
                }else{
                    System.exit(0);
                }
            }
            if(opponentPlayer.isWin()){
                wininfo.setText("Your pick made "+opponentPlayer.getName()+" Win!" );
                players.add(currentPlayer);
                if(players.size()>=2) {
                    currentPlayer = players.get(0);
                    opponentPlayer = players.get(1);
                    players.remove(0);
                    players.remove(1);
                    nextPlayer.setText("please pass the phone to " + currentPlayer.getName());
                    b.setText("Yeah, I am " + currentPlayer.getName());
                    currentCards=currentPlayer.getCards();
                    opponentCards=opponentPlayer.getCards();
                }else{
                    System.exit(0);
                }
            }
        }
        }else{
            players.add(currentPlayer);
            currentPlayer=opponentPlayer;
            opponentPlayer=players.get(0);
            players.remove(0);
            nextPlayer.setText("please pass the phone to "+currentPlayer.getName());
            b.setText("Yeah, I am "+ currentPlayer.getName());
            currentCards=currentPlayer.getCards();
            opponentCards=opponentPlayer.getCards();
        }

    }


    public void shuffleCards(View view){
        currentCards.shuffleCards();
        setContentView(R.layout.game_main);
    }

    public void orderCards(View view){
        currentCards.orderCards();
//        currentCards=currentCards.getBackCopy();
        setContentView(R.layout.game_main);

    }


    public void moveLeft(View view){
        if(currentSelector==14||(currentSelector==0&&opponentCards.getNumOfCards()<=14)){
            currentSelector=opponentCards.getNumOfCards()-1;
            setContentView(R.layout.game_main_pick);

        }else if(currentSelector>0){
            currentSelector--;
            setContentView(R.layout.game_main_pick);
        }else {
            currentSelector=13;
            setContentView(R.layout.game_main_pick);
        }

    }

    public void moveRight(View view){

            if(currentSelector==13) {
                currentSelector = 0;
                setContentView(R.layout.game_main_pick);
            }else if(opponentCards.getNumOfCards()>14&&currentSelector==opponentCards.getNumOfCards()-1){
                currentSelector=14;
                setContentView(R.layout.game_main_pick);
            }else {
                currentSelector++;
                setContentView(R.layout.game_main_pick);
        }

    }


    public void moveUp(View view){
        if(opponentCards.getNumOfCards()>14&&currentSelector<14){
            if(currentSelector+14<opponentCards.getNumOfCards()){
                currentSelector+=14;
                setContentView(R.layout.game_main_pick);
            }else {
                currentSelector=opponentCards.getNumOfCards()-1;
                setContentView(R.layout.game_main_pick);
            }
        }

    }

    public void moveDown(View view){
        if(currentSelector-14>=0){
            currentSelector-=14;
            setContentView(R.layout.game_main_pick);
        }
    }

    public void pickCard(View view){
        cancelledOut=false;
        setContentView(R.layout.game_show_picked);


    }

    public void pickedOne(View view){
        currentCards.addCard(opponentCards.getCard(currentSelector));
        opponentCards.removeCard(currentSelector);
        currentSelector=0;

        setContentView(R.layout.game_main);

    }

    public void loadNextRound(View view){
        if(currentPlayer.isFirstRound()) {

            setContentView(R.layout.game_main);
        }else{
            setContentView(R.layout.game_main_pick);
        }

    }



    @Override
    public Resources getResources() {
        return super.getResources();
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void setContentView(int id){
        super.setContentView(id);
        if(cancelledOut) {

            Button b = (Button) findViewById(R.id.buttonCancelOut);
            if(b!=null) {
//                if(currentPlayer.isFirstRound()) {
//                    b.setText("I'm done with my cards");
//                    b.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            currentPlayer.setFirstRound(false);
//                            setContentView(R.layout.game_main_pick);
//                        }
//                    });
//                }else{
                    b.setText("I'm done with my cards");
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            currentPlayer.setFirstRound(false);
                            setContentView(R.layout.game_pass_phone);
                            checkWinStatus();
                        }
                    });

//                }
            }
        }

    }


}
