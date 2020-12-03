package com.example.application.scrollview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Data;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Insights extends AppCompatActivity {
    EditText Height, Weight;
    TextView answer;
    Button calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insights);


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

        //implementation of BMI calculator

        Height=(EditText) findViewById(R.id.Height);
        Weight=(EditText) findViewById(R.id.Weight);
        answer=(TextView) findViewById(R.id.answer);
        calculate=(Button) findViewById(R.id.calculate);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateBMI();
                //Error message if nothing typed
                String str1 = Weight.getText().toString();
                String str2 = Height.getText().toString();

                if(TextUtils.isEmpty(str1)){
                    Weight.setError("You need to enter your weight in order to calculate your BMI");
                    Weight.requestFocus();
                    return;
                }

                if(TextUtils.isEmpty(str2)){
                    Height.setError("You need to enter your height in order to calculate your BMI");
                    Height.requestFocus();
                    return;
                }

            }
        });


    }

    private void calculateBMI(){
        String heightStr=Height.getText().toString();
        String weightStr=Weight.getText().toString();

        if(heightStr!=null && !"".equals(heightStr) && weightStr!=null &&!"".equals(weightStr)){
            float heightvalue=Float.parseFloat(heightStr)/100;
            float weightValue=Float.parseFloat(weightStr);

            float bmi =weightValue/(heightvalue * heightvalue);

            displayBMI(bmi);

        }

    }
    private void displayBMI(float bmi){
        String bmiLabel="";

        if (Float.compare(bmi, 15f) <= 0) {
            bmiLabel = "Very severely underweight";
        } else if (Float.compare(bmi, 15f) > 0  &&  Float.compare(bmi, 16f) <= 0) {
            bmiLabel = "very underweight";
        } else if (Float.compare(bmi, 16f) > 0  &&  Float.compare(bmi, 18.5f) <= 0) {
            bmiLabel = "Underweight";
        } else if (Float.compare(bmi, 18.5f) > 0  &&  Float.compare(bmi, 25f) <= 0) {
            bmiLabel = "Normal";
        } else if (Float.compare(bmi, 25f) > 0  &&  Float.compare(bmi, 30f) <= 0) {
            bmiLabel = "Overweight";
        } else if (Float.compare(bmi, 30f) > 0  &&  Float.compare(bmi, 35f) <= 0) {
            bmiLabel = "Very Overweight";
        } else if (Float.compare(bmi, 35f) > 0  &&  Float.compare(bmi, 40f) <= 0) {
            bmiLabel = "Obese";
        } else {
            bmiLabel = "Fat fuck";
        }

        /* if (bmi<=16){
            bmiLabel="Too fucking skinny";
        }*/

       /* if (Float.compare(bmi,15f)<=0){
            bmiLabel="Too fucking skinny";
        } else if (Float.compare(bmi,15f)>0 &&Float.compare(bmi,16f)<=0) {
            bmiLabel="Very Skinny";
        } else if (Float.compare(bmi,15f)>0 &&Float.compare(bmi,16f)<=0) {
            bmiLabel="Very Skinny";
    }*/
    }
}