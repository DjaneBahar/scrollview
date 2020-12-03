package com.example.application.scrollview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Yoga extends AppCompatActivity {

    TextView textView;
    Button buttonStart;
    TextView timeView;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga);

        //implementing timer

        timeView = findViewById(R.id.textViewsYogaTimer);
        buttonStart = findViewById(R.id.startYogaBtn);

        countDownTimer = new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeView.setText(millisUntilFinished/1000 + "sec left");
            }

            @Override
            public void onFinish() {

                // timeView.setText("time finish");
                Toast.makeText(Yoga.this,"finish",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Yoga.this, ExercisesList.class);
                startActivity(intent);
                finish();

            }

        };

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Yoga.this,"time start",Toast.LENGTH_SHORT).show();
                countDownTimer.start();


            }


        });

    }
}