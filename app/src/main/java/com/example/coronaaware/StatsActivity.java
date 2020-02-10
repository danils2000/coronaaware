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
    private Statistics parsedInfo;
    private String url = "https://www.worldometers.info/coronavirus/";
    private String tag = "maincounter-number";
    // Create a handler to obtain information from the background thread
    private Handler handler = new Handler(Looper.myLooper()) {

        @Override
        public void handleMessage(Message messageInput) {
            if (messageInput.what == 0) {
                parsedInfo = (Statistics) messageInput.obj;
                updateUI();
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

        // Start background thread for parsing
        Thread myThread = new Thread(new ParsedInfo(handler, url, tag));
        myThread.start();
    }

    /**
     * Updating activity layout
     */
    protected void updateUI() {
        showTotal.setText(parsedInfo.getTotalCases());
        showDeath.setText(parsedInfo.getDeath());
        showCured.setText(parsedInfo.getCured());
    }

}
