package com.example.coronaaware;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coronaaware.info.Article;
import com.example.coronaaware.parse.ParsedInfo;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {
    private TextView lastTopic;
    private ArrayList<Article> parsedInfo = new ArrayList<>();
    private String tag = "css-3ez4hu eoo0vm40";
    private String url = "https://www.nytimes.com/2020/02/10/world/asia/coronavirus-china.html";
    private Handler handler = new Handler(Looper.myLooper()) {

        @Override
        public void handleMessage(Message messageInput) {
            if (messageInput.what == 1) {
                parsedInfo = (ArrayList<Article>) messageInput.obj;
                updateUI();
            }
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        lastTopic = findViewById(R.id.tvLastTopic);

        // Start background thread for parsing
        Thread myThread = new Thread(new ParsedInfo(handler, url, tag));
        myThread.start();
    }

    /**
     * Updating activity layout
     */
    protected void updateUI() {
        lastTopic.setText(parsedInfo.get(1).getTopic());
    }
}
