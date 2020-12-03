package com.example.application.scrollview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ExercisesList extends AppCompatActivity implements View.OnClickListener{

    private CardView pushups;
    private CardView squats;
    private CardView situps;
    private CardView backbends;
    private CardView plank;
    private CardView yoga;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exerciseslist);

        //implementing bottom navigationBar

        //init and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);


        //perform itemselectedlistener

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                , MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext()
                                , Settings.class));
                        overridePendingTransition(0,0);
                        return true;



                    case R.id.notifications:
                        startActivity(new Intent(getApplicationContext()
                                , Notifications.class));
                        overridePendingTransition(0,0);
                        return true;


                }
                return false;
            }
        });


        pushups = (CardView) findViewById(R.id.cardPushups);
        squats = (CardView)findViewById(R.id.cardSquats);
        situps = (CardView)findViewById(R.id.cardSitups);
        plank = (CardView)findViewById(R.id.cardPlank);
        backbends = (CardView)findViewById(R.id.cardBackbend);
        yoga = (CardView)findViewById(R.id.cardYoga);

       pushups.setOnClickListener(this);
        squats.setOnClickListener(this);
        situps.setOnClickListener(this);
        plank.setOnClickListener(this);
        backbends.setOnClickListener(this);
        yoga.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent i;

        switch (v.getId()){
            case R.id.cardPushups : i = new Intent(this,Pushups.class);startActivity(i);break;
            case R.id.cardSquats : i = new Intent(this,Squats.class);startActivity(i);break;
            case R.id.cardSitups : i = new Intent(this, Situps.class);startActivity(i);break;
            case R.id.cardPlank : i = new Intent(this,Plank.class);startActivity(i);break;
            case R.id.cardBackbend : i = new Intent(this, Backbends.class);startActivity(i);break;
            case R.id.cardYoga : i = new Intent(this,Yoga.class);startActivity(i);break;
            default:break;

        }

    }
}