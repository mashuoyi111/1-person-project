package com.example.administrator.a1_person_project;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int peopleNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSpinner();
        peopleNum=2;
    }

    public void setSpinner(){
        Spinner playerNum=(Spinner)findViewById(R.id.numberOfPlayers);
        String[] playerOptions=new String[]{"2 players","3 players","4 players","5 players","6 players"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, playerOptions);
        playerNum.setAdapter(adapter);
    }

    public void startGame(View view){
        setNumOfPeople(view);
        ArrayList<String> names=new ArrayList<String>();
        Intent intent=new Intent();
        intent.setClass(MainActivity.this, MainGame.class);
        intent.putExtra("peopleNum",peopleNum);
        EditText p1=(EditText) findViewById(R.id.p1name);
        EditText p2=(EditText) findViewById(R.id.p2name);
        EditText p3=(EditText) findViewById(R.id.p3name);
        EditText p4=(EditText) findViewById(R.id.p4name);
        EditText p5=(EditText) findViewById(R.id.p5name);
        EditText p6=(EditText) findViewById(R.id.p6name);
        names.add(p1.getText().toString());
        names.add(p2.getText().toString());
        switch (peopleNum){
            case 3:
                names.add(p3.getText().toString());
                break;
            case 4:
                names.add(p3.getText().toString());
                names.add(p4.getText().toString());
                break;
            case 5:
                names.add(p3.getText().toString());
                names.add(p4.getText().toString());
                names.add(p5.getText().toString());
                break;
            case 6:
                names.add(p3.getText().toString());
                names.add(p4.getText().toString());
                names.add(p5.getText().toString());
                names.add(p6.getText().toString());
                break;
        }
        intent.putStringArrayListExtra("names",names);
        startActivity(intent);
    }


    public void setNumOfPeople(View view){
        Spinner num=(Spinner) findViewById(R.id.numberOfPlayers);
        TextView p3t=(TextView)findViewById(R.id.p3text);
        TextView p4t=(TextView)findViewById(R.id.p4text);
        TextView p5t=(TextView)findViewById(R.id.p5text);
        TextView p6t=(TextView)findViewById(R.id.p6text);
        EditText p3=(EditText) findViewById(R.id.p3name);
        EditText p4=(EditText) findViewById(R.id.p4name);
        EditText p5=(EditText) findViewById(R.id.p5name);
        EditText p6=(EditText) findViewById(R.id.p6name);
        if(num!=null){
            peopleNum =num.getSelectedItemPosition()+2;
        }
        switch (peopleNum){
            case 2:
                p3.setVisibility(View.INVISIBLE);
                p4.setVisibility(View.INVISIBLE);
                p5.setVisibility(View.INVISIBLE);
                p6.setVisibility(View.INVISIBLE);
                p3t.setVisibility(View.INVISIBLE);
                p4t.setVisibility(View.INVISIBLE);
                p5t.setVisibility(View.INVISIBLE);
                p6t.setVisibility(View.INVISIBLE);
                break;
            case 3:
                p3.setVisibility(View.VISIBLE);
                p4.setVisibility(View.INVISIBLE);
                p5.setVisibility(View.INVISIBLE);
                p6.setVisibility(View.INVISIBLE);
                p3t.setVisibility(View.VISIBLE);
                p4t.setVisibility(View.INVISIBLE);
                p5t.setVisibility(View.INVISIBLE);
                p6t.setVisibility(View.INVISIBLE);
                break;
            case 4:
                p3.setVisibility(View.VISIBLE);
                p4.setVisibility(View.VISIBLE);
                p5.setVisibility(View.INVISIBLE);
                p6.setVisibility(View.INVISIBLE);
                p3t.setVisibility(View.VISIBLE);
                p4t.setVisibility(View.VISIBLE);
                p5t.setVisibility(View.INVISIBLE);
                p6t.setVisibility(View.INVISIBLE);
                break;
            case 5:
                p3.setVisibility(View.VISIBLE);
                p4.setVisibility(View.VISIBLE);
                p5.setVisibility(View.VISIBLE);
                p6.setVisibility(View.INVISIBLE);
                p3t.setVisibility(View.VISIBLE);
                p4t.setVisibility(View.VISIBLE);
                p5t.setVisibility(View.VISIBLE);
                p6t.setVisibility(View.INVISIBLE);
                break;
            case 6:
                p3.setVisibility(View.VISIBLE);
                p4.setVisibility(View.VISIBLE);
                p5.setVisibility(View.VISIBLE);
                p6.setVisibility(View.VISIBLE);
                p3t.setVisibility(View.VISIBLE);
                p4t.setVisibility(View.VISIBLE);
                p5t.setVisibility(View.VISIBLE);
                p6t.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        System.exit(0);
    }

}
