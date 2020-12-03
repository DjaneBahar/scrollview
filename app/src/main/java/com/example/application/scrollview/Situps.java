package com.example.application.scrollview;


import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Situps extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor acceleroMeter;
    private SensorEventListener acceleroSensorListener;
    TextView textview;
    Button buttonStart;
    TextView timeView;

    CountDownTimer countDownTimer;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situps);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        acceleroMeter = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(Situps.this, acceleroMeter,  sensorManager.SENSOR_DELAY_NORMAL);
        textview = (TextView) findViewById(R.id.textViewsitups);


        //implementing timer

        timeView = findViewById(R.id.textViewsSitupsTimer);
        buttonStart = findViewById(R.id.startSitupsBtn);

        countDownTimer = new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeView.setText(millisUntilFinished/1000 + "");
            }



            @Override
            public void onFinish() {

                // timeView.setText("time finish");
                Toast.makeText(Situps.this,"finish",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Situps.this, ExercisesList.class);
                startActivity(intent);
                finish();

            }


        };

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Situps.this,"time start",Toast.LENGTH_SHORT).show();
                countDownTimer.start();





            }


        });




    }

    @Override
    protected void onDestroy() {
        sensorManager.unregisterListener(acceleroSensorListener);
        super.onDestroy();
    }

    int reps = 0;
    boolean situp;
    double currentValue;
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        currentValue = sensorEvent.values[1];

        //i++;
        //System.out.println(i);

        //for(int i = 0; reps < 10; i++){
        if(/*sensorEvent.values[1] > 0 lastValue < 0.0 && */currentValue < 1.0) {
            situp = true;
            //int a = 1;
        }
        if(situp==true && currentValue > 4.0){
            //reps++;
            situp=false;
            reps++;
        }

        textview.setText(sensorEvent.values[1] + " " + reps);
        double lastValue = currentValue;

        if(reps == 2){
            Intent exercise3 = new Intent(Situps.this, Squats.class);
            startActivity(exercise3);
        }
    }

    //System.out.println(sensorEvent.values[1]);

    //}

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}