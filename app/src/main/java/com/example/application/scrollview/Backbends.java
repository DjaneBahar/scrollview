package com.example.application.scrollview;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


public class Backbends extends AppCompatActivity {
    private SensorManager sensorManager;
    private Sensor proximitySensor;
    private SensorEventListener proximitySensorListener;
    TextView textView;
    TextView textview;
    Button buttonStart;
    TextView timeView;
    CountDownTimer countDownTimer;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backbends);
        //implementing timer

        timeView = findViewById(R.id.textViewsBackbendsTimer);
        buttonStart = findViewById(R.id.startBackbendsBtn);

        countDownTimer = new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeView.setText(millisUntilFinished/1000 + "");
            }



            @Override
            public void onFinish() {

                // timeView.setText("time finish");
                Toast.makeText(Backbends.this,"finish",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Backbends.this, ExercisesList.class);
                startActivity(intent);
                finish();

            }


        };

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Backbends.this,"time start",Toast.LENGTH_SHORT).show();
                countDownTimer.start();


            }


        });


        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        final TextView textview=(TextView) findViewById(R.id.textView4);

        if(proximitySensor == null){
            Toast.makeText(this, "Proximity sensor not available !", Toast.LENGTH_LONG).show();
            finish();
        }

        proximitySensorListener = new SensorEventListener() {
            int reps = 0;
            boolean rep;

            //Fungerende med rygbøjninger.
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float currentValue = sensorEvent.values[0];
                System.out.println(currentValue); // Skal bare lige se hvad værdierne er


                if(currentValue == 0.0){
                    rep = false;
                }
                if (currentValue == 5.0 && rep == false){
                    reps++;
                    rep = true;
                }
                System.out.println(reps);
                textview.setText(String.valueOf(reps - 1)); //-1, fordi den starter på 1 af en eller anden grund.

                if ((reps - 1) == 2){
                    //Burde nok tilføje en slutskærm, hvor man viser reps og sådan
                    Intent workoutDone = new Intent(Backbends.this, MainActivity.class);
                    startActivity(workoutDone);
                }
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