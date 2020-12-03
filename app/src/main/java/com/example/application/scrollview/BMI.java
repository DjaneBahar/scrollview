package com.example.application.scrollview;

public class BMI {

    //Singleton support


    private static BMI instance;
    BMI() {


    }

    public static BMI getInstance(){
        if(instance == null){
            instance = new BMI();
        }

        return instance;
    }



}
