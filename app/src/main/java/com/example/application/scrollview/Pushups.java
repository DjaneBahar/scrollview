package com.example.application.scrollview;


import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Timer;

public class Pushups extends AppCompatActivity {
    private SensorManager sensorManager;
    private Sensor proximitySensor;
    private SensorEventListener proximitySensorListener;
    TextView timeView;
    Button buttonStart;
    CountDownTimer countDownTimer;
    TextView textView;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pushups);



        //implementing timer


        timeView = findViewById(R.id.textViewsPushupsTimer);
        buttonStart = findViewById(R.id.startPushupsBtn);

        countDownTimer = new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeView.setText(millisUntilFinished/1000 + "");
            }



            @Override
            public void onFinish() {

                // timeView.setText("time finish");
                Toast.makeText(Pushups.this,"finish",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Pushups.this, ExercisesList.class);
                startActivity(intent);
                finish();

            }


        };

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Pushups.this,"time start",Toast.LENGTH_SHORT).show();
                countDownTimer.start();





            }


        });


        //implementing pushup sensors


        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        final TextView textview=(TextView) findViewById(R.id.textViewP);


        if(proximitySensor == null){
            Toast.makeText(this, "Proximity sensor not available !", Toast.LENGTH_LONG).show();
            finish();
        }


        proximitySensorListener = new SensorEventListener() {
            int reps = 0;
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {

                if (sensorEvent.values[0] < proximitySensor.getMaximumRange()){
                    getWindow().getDecorView().setBackgroundColor(Color.RED);
                    reps++;
                } else {
                    getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                }
                System.out.println(reps);
                textView.setText(String.valueOf(reps));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

        sensorManager.registerListener(proximitySensorListener, proximitySensor, 2*1000*1000, 1000);
    }
    @Override
    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(proximitySensorListener);
    }
}

