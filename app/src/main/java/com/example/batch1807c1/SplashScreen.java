package com.example.batch1807c1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import static com.example.batch1807c1.Login.sharedPref;
import static com.example.batch1807c1.Login.sp_name;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences=getSharedPreferences(sharedPref,MODE_PRIVATE);
                String value=sharedPreferences.getString(sp_name,"");
                if(value.equals("")){
                    startActivity(new Intent(SplashScreen.this,Login.class));
                }
                else{
                    startActivity(new Intent(SplashScreen.this,Dashboard.class));
                }
                finish();

            }
        },4000);
    }
}