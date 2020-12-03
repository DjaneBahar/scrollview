package com.example.application.scrollview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Plank extends AppCompatActivity {

    TextView timeView;
    Button buttonStart;
    CountDownTimer countDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plank);


        //implementing timer

        timeView = findViewById(R.id.textViewsPlankTimer);
        buttonStart = findViewById(R.id.startPlankBtn);

        countDownTimer = new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeView.setText(millisUntilFinished/1000 +"");
            }



            @Override
            public void onFinish() {

                // timeView.setText("time finish");
                Toast.makeText(Plank.this,"finish",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Plank.this, ExercisesList.class);
                startActivity(intent);
                finish();

            }


        };

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Plank.this,"time start",Toast.LENGTH_SHORT).show();
                countDownTimer.start();





            }


        });




    }
}