package com.example.smartparkingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AllActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);
    }

    //POVEZAVA STRANI
    public static  final String EXTRA_MESSAGE ="com.example.smartparkingapp.MESSAGE";
    public void preobrni1(View view) {
        Intent intent = new Intent(this,MainActivity.class); // mogoce tukaj narobe
        String message = " Add user to list.";
        intent.putExtra(EXTRA_MESSAGE , message);
        startActivity(intent);
    }

    public void preobrni2(View view) {
        Intent intent = new Intent(this,CarActivity.class); // mogoce tukaj narobe
        String message = " Add user to list.";
        intent.putExtra(EXTRA_MESSAGE , message);
        startActivity(intent);
    }

    public void preobrni3(View view) {
        Intent intent = new Intent(this,BusActivity.class); // mogoce tukaj narobe
        String message = " Add user to list.";
        intent.putExtra(EXTRA_MESSAGE , message);
        startActivity(intent);
    }


}