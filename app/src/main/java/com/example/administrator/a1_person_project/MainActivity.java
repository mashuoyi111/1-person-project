package com.example.administrator.a1_person_project;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSpinner();
    }

    public void setSpinner(){
        Spinner playerNum=(Spinner)findViewById(R.id.numberOfPlayers);
        String[] playerOptions=new String[]{"1 player","2 players","3 players","4 players","5 players","6 players"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, playerOptions);
        playerNum.setAdapter(adapter);
    }

    public void startGame(View view){
        Intent intent=new Intent();
        intent.setClass(MainActivity.this, MainGame.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        finish();
        System.exit(0);
    }

}
