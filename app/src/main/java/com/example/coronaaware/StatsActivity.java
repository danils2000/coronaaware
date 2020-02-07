package com.example.coronaaware;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coronaaware.info.ParsedInfo;

public class StatsActivity extends AppCompatActivity {
    private TextView showStats;
    // Create a handler to obtain information from the background thread
    private Handler handler = new Handler(Looper.myLooper()) {

        @Override
        public void handleMessage(Message messageInput) {
            if (messageInput.what == 0) {
                String parsedInfoString = messageInput.obj.toString();
                showStats.setText(parsedInfoString);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        showStats = findViewById(R.id.textViewShowStats);

        Thread myThread = new Thread(new ParsedInfo(handler, "https://www.worldometers.info/coronavirus/"));
        myThread.start();
    }
}
