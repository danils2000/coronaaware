package com.example.coronaaware;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coronaaware.info.Article;
import com.example.coronaaware.parse.ParsedInfo;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {
    private ListView lvNews;
    private String tag = "css-3ez4hu eoo0vm40";
    private String url = "https://www.nytimes.com/2020/02/10/world/asia/coronavirus-china.html";
    private Handler handler = new Handler(Looper.myLooper()) {

        @Override
        public void handleMessage(Message messageInput) {
            if (messageInput.what == 1) {
                // Creating the adaptor for articles ListView
                lvNews.setAdapter(new ArrayAdapter<Article>(
                        getApplicationContext(),
                        android.R.layout.simple_list_item_1,
                        News.getInstance().getNewsList()
                ));
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        lvNews = findViewById(R.id.listNews);

        // Start background thread for parsing
        Thread myThread = new Thread(new ParsedInfo(handler, url, tag));
        myThread.start();
    }

}
