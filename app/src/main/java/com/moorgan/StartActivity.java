package com.moorgan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.WindowManager;

public class StartActivity extends AppCompatActivity {

    private final int DURACION_SPLASH = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        SharedPreferences preferences = getSharedPreferences("logInfo", Context.MODE_PRIVATE);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable(){
            public void run(){

                String isRegister = preferences.getString("isRegister", "0");

                if (isRegister.equals("0")){

                    Intent main = new Intent(StartActivity.this, RegisterActivity.class);
                    startActivity(main);
                    finish();

                }else if(isRegister.equals("1")){

                    Intent main = new Intent(StartActivity.this, MainActivity.class);
                    startActivity(main);
                    finish();
                }

            };
        }, DURACION_SPLASH);
    }




}