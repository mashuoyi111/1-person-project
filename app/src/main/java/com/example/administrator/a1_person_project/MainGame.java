package com.example.administrator.a1_person_project;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
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
    private int playerNum;
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
        Intent thisIntent=getIntent();
        playerNum=thisIntent.getIntExtra("peopleNum",2);
        ArrayList<String> names=thisIntent.getStringArrayListExtra("names");
        setDeck(playerNum);
        setPlayers(playerNum);
        setPlayersNames(names);
        cancelledOut=false;
        this.currentSelector=0;
        setContentView(R.layout.game_main);

    }

    private void setPlayersNames(ArrayList<String> names) {
        for (int i=0;i<playerNum;i++) {
            players.get(i).setName(names.get(i));
        }
        currentPlayer=players.get(0);
        opponentPlayer=players.get(1);
        players.remove(0);
        players.remove(0);
        currentCards=currentPlayer.getCards();
        opponentCards=opponentPlayer.getCards();
    }


    private void setDeck(int playerNum) {
        if(playerNum>5) {
            deck = new Deck(this, 3, screenHeight, screenWidth);
        }else if(playerNum>3){
            deck = new Deck(this, 2, screenHeight, screenWidth);
        }else{
            deck = new Deck(this, 1, screenHeight, screenWidth);
        }

    }


    private void setPlayers(int playerNum) {
        switch (playerNum){
            case 2:
                for(int i=0;i<1;i++){
                    Player temp1=new Player("",deck.getCards(26));
                    Player temp2=new Player("",deck.getCards(27));
                    players.add(temp1);
                    players.add(temp2);
                }
                    break;
            case 3:
                for(int i=0;i<1;i++){
                    Player temp=new Player("",deck.getCards(18));
                    Player temp1=new Player("",deck.getCards(17));
                    Player temp2=new Player("",deck.getCards(18));
                    players.add(temp1);
                    players.add(temp2);
                    players.add(temp);
                }
                    break;
            case 4:
                    for(int i=0;i<2;i++){
                        Player temp1=new Player("",deck.getCards(26));
                        Player temp2=new Player("",deck.getCards(27));
                        players.add(temp1);
                        players.add(temp2);
                    }
                    break;
            case 5:
                for(int i=0;i<1;i++){
                    Player temp=new Player("",deck.getCards(21));
                    Player temp1=new Player("",deck.getCards(21));
                    Player temp2=new Player("",deck.getCards(21));
                    Player temp3=new Player("",deck.getCards(21));
                    Player temp4=new Player("",deck.getCards(21));
                    players.add(temp1);
                    players.add(temp2);
                    players.add(temp);
                    }
                    break;
            case 6:
                    for(int i=0;i<3;i++){
                        Player temp1=new Player("",deck.getCards(26));
                        Player temp2=new Player("",deck.getCards(27));
                        players.add(temp1);
                        players.add(temp2);
                    }
                    break;
                }



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

        if(addTemp.size()!=size) {
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
            wonPlayers.add(currentPlayer);
            wonPlayers.add(opponentPlayer);
            if(players.size()>=2){
                currentPlayer=players.get(0);
                opponentPlayer=players.get(1);
                nextPlayer.setText("please pass the phone to " + currentPlayer.getName());
                b.setText("Yeah, I am " + currentPlayer.getName());
                currentCards=currentPlayer.getCards();
                opponentCards=opponentPlayer.getCards();
            }else{
                setContentView(R.layout.game_end);
                TextView endgame= (TextView) findViewById(R.id.endGameText);
                endgame.setText("Player "+players.get(0).getName()+" Lose!!!");
            }


        }else{
            if(currentPlayer.isWin()){
                wininfo.setText("You Win the game!");
                wonPlayers.add(currentPlayer);
                if(players.size()>0){
                    currentPlayer=opponentPlayer;
                    opponentPlayer = players.get(0);
                    players.remove(0);
                    nextPlayer.setText("please pass the phone to " + currentPlayer.getName());
                    b.setText("Yeah, I am " + currentPlayer.getName());
                    currentCards=currentPlayer.getCards();
                    opponentCards=opponentPlayer.getCards();
                }else{
                    players.add(opponentPlayer);
                    setContentView(R.layout.game_end);
                    TextView endgame= (TextView) findViewById(R.id.endGameText);
                    endgame.setText("Player "+players.get(0).getName()+" Lose!!!");
                }
            }
            if(opponentPlayer.isWin()){
                wininfo.setText("Your pick made "+opponentPlayer.getName()+" Win!" );
                wonPlayers.add(opponentPlayer);
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
                    setContentView(R.layout.game_end);
                    TextView endgame= (TextView) findViewById(R.id.endGameText);
                    endgame.setText("Player "+players.get(0).getName()+" Lose!!!");
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

    public void backToMainMenu(View view){
        Intent intent=new Intent();
        intent.setClass(MainGame.this, MainActivity.class);
        startActivity(intent);
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
        if(id==R.layout.game_main){
            TextView t1=(TextView)findViewById(R.id.gameMainText);
            t1.setText("Hi, "+currentPlayer.getName()+". You have to cancel out before picking a card.");
        }

        if(cancelledOut&&id==R.layout.game_main) {

            Button b = (Button) findViewById(R.id.buttonCancelOut);
            if(b!=null) {
                b.setText("I'm done with my cards");
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        currentPlayer.setFirstRound(false);
                        setContentView(R.layout.game_pass_phone);
                        checkWinStatus();
                    }
                });
            }
        }

        if(id==R.layout.game_pass_phone){
            TextView t1=(TextView)findViewById(R.id.currentPlayers);
            TextView t2=(TextView)findViewById(R.id.wonPlayers);
            ListView t3=(ListView)findViewById(R.id.playersCardInfo);
            String t1str="";
            String t2str="";
            String t3str="";
            ArrayList<Player> playerList=new ArrayList<Player>();
            playerList.add(currentPlayer);
            playerList.add(opponentPlayer);
            playerList.addAll(players);
            for(int i=0;i<playerList.size();i++){
                if(i<playerList.size()-1){
                t1str+=(playerList.get(i).getName()+", ");
                }else{t1str+=(playerList.get(i).getName()+".");}
            }
            for(int i=0;i<wonPlayers.size();i++){
                if(i<wonPlayers.size()-1){
                    t2str.concat(wonPlayers.get(i).getName()+", ");
                }else{t2str.concat(wonPlayers.get(i).getName()+".");}
            }
            t1.setText("Still playing: "+ t1str);
            t2.setText("Won game: "+t2str);

            int playerNum=playerList.size();
            String[] str=new String[playerNum+1];
            ArrayList<String> strs=new ArrayList<String>();
            strs.add("number of cards information:");
            for(int i=1;i<=playerNum;i++){
                strs.add(playerList.get(i-1).getName()+": "+playerList.get(i-1).getCards().getNumOfCards().toString());
            }
            ArrayAdapter a=new ArrayAdapter<String>(this,R.layout.game_list_view,R.id.textViewA,strs);
            t3.setAdapter(a);


        }
        if(id==R.layout.game_main_pick){
            TextView t=(TextView)findViewById(R.id.picking);
            t.setText("you are picking from: "+opponentPlayer.getName());
        }


    }


}
