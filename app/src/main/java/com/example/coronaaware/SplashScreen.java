package com.example.coronaaware;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * @author danils2000
 * @version 1.0
 * References: https://www.youtube.com/watch?v=jXtof6OUtcE
 */
public class SplashScreen extends AppCompatActivity {
//time
    private static int  SPLASH_TIME_OUT = 3000;
// function to splash screen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run(){
                //from splash screen to main
                Intent mainIntent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
                //timeout
            }
        },SPLASH_TIME_OUT);
    }
}
