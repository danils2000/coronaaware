package com.example.coronaaware;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coronaaware.parse.ParsedInfo;
import com.example.coronaaware.info.Statistics;

public class StatsActivity extends AppCompatActivity {
    private TextView showTotal;
    private TextView showDeath;
    private TextView showCured;
    // Create a handler to obtain information from the background thread
    private Handler handler = new Handler(Looper.myLooper()) {

        @Override
        public void handleMessage(Message messageInput) {
            if (messageInput.what == 0) {
                Statistics parsedInfo = (Statistics) messageInput.obj;

                // Updating UI with received information
                showTotal.setText(parsedInfo.getTotalCases());
                showDeath.setText(parsedInfo.getDeath());
                showCured.setText(parsedInfo.getCured());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        showTotal = findViewById(R.id.tvShowStats);
        showDeath = findViewById(R.id.tvShowDeath);
        showCured = findViewById(R.id.tvShowCured);

        Thread myThread = new Thread(new ParsedInfo(handler, "https://www.worldometers.info/coronavirus/"));
        myThread.start();
    }
}
