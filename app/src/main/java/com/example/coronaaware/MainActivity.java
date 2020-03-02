package com.example.coronaaware;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button stats;
    private Button news;
    private Button user;
    private Button advice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing buttons' IDs
        stats = findViewById(R.id.buttonStats);
        news = findViewById(R.id.buttonNews);
        user = findViewById(R.id.buttonUser);
        advice = findViewById(R.id.buttonAdvice);

        // Setting the event listeners for buttons
        stats.setOnClickListener(this);
        news.setOnClickListener(this);
        user.setOnClickListener(this);
        advice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Each case for each button clicked
        switch (v.getId()) {
            case R.id.buttonStats:
                goNext(StatsActivity.class);
                break;
            case R.id.buttonNews:
                goNext(NewsActivity.class);
                break;
            case R.id.buttonUser:
                goNext(UserActivity.class);
                break;
            case R.id.buttonAdvice:
                goNext(AdviceActivity.class);
                break;
        }
    }

    /**
     * Create a new activity, given as a parameter
     * @param next chosen activity
     */
    protected void goNext(Class next) {
        Intent intent = new Intent(this, next);
        startActivity(intent);
    }
}
