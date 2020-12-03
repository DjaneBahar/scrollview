package com.example.application.scrollview;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Squats extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor acceleroMeter;
    private SensorEventListener acceleroSensorListener;

    TextView textView;
    Button buttonStart;
    TextView timeView;
    CountDownTimer countDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_squats);

        //implementing timer

        timeView = findViewById(R.id.textViewsSquatsTimer);
        buttonStart = findViewById(R.id.startSquatsBtn);

        countDownTimer = new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeView.setText(millisUntilFinished/1000 + "");
            }



            @Override
            public void onFinish() {

                // timeView.setText("time finish");
                Toast.makeText(Squats.this,"finish",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Squats.this, ExercisesList.class);
                startActivity(intent);
                finish();

            }


        };

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Squats.this,"time start",Toast.LENGTH_SHORT).show();
                countDownTimer.start();




            }


        });





        //implementing Squats methods


        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        acceleroMeter = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(Squats.this, acceleroMeter,  sensorManager.SENSOR_DELAY_NORMAL);
        textView = (TextView) findViewById(R.id.textViewsquats);


    }

    @Override
    protected void onDestroy() {
        sensorManager.unregisterListener(acceleroSensorListener);
        super.onDestroy();
    }

    int reps = 0;
    int i = 0;
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        i++;
        System.out.println(i);
        System.out.println(sensorEvent.values[1]);
        if(sensorEvent.values[1] >= 9.00000) {
            reps++;
        }
        textView.setText(sensorEvent.values[1] + " " +reps);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}