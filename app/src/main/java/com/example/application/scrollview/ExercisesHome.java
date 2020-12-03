package com.example.application.scrollview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ExercisesHome extends AppCompatActivity {



    TextView t1;
    Button b1;
    Button viewExBtn;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_home);


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


        //start timer

        t1 = findViewById(R.id.textView);
        b1 = findViewById(R.id.startProgram_btn);

        countDownTimer = new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                t1.setText(""+ millisUntilFinished/1000);
            }



            @Override
            public void onFinish() {

                Toast.makeText(ExercisesHome.this,"finish",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ExercisesHome.this, Pushups.class);
                startActivity(intent);
                finish();

            }


        };

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(ExercisesHome.this,"time start",Toast.LENGTH_SHORT).show();
                countDownTimer.start();
            }


        });

        //open ExercisesHome list

        viewExBtn = (Button)findViewById(R.id.viewExercises_btn);
        viewExBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ExercisesHome.this, ExercisesList.class);
                startActivity(intent);

            }
        });


    }

}