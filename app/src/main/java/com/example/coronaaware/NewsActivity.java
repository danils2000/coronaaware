package com.example.coronaaware;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coronaaware.data.NewsData;
import com.example.coronaaware.info.Article;
import com.example.coronaaware.parse.ParsedInfo;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {
    NewsData coronaNewsDb;
    private ListView lvNews;
    private Handler handler = new Handler(Looper.myLooper()) {

        @Override
        public void handleMessage(Message messageInput) {
            if (messageInput.what == 1) {
                // Creating the adaptor for articles ListView
                lvNews.setAdapter(new ArticleAdapter (
                        getApplicationContext(),
                        R.layout.article_adapter,
                        News.getInstance().getNewsList()
                ));
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        coronaNewsDb = new NewsData(this);

        lvNews = findViewById(R.id.listNews);

        // Start background thread for parsing
        String tag = "css-3ez4hu eoo0vm40";
        String url = "https://www.nytimes.com/2020/02/10/world/asia/coronavirus-china.html";
        Thread myThread = new Thread(new ParsedInfo(handler, url, tag));
        myThread.start();

        lvNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                boolean isInserted = coronaNewsDb.addData(
                        News.getInstance().getNewsList().get(position).getTopic()
                );

                if (isInserted) {
                    Toast.makeText(NewsActivity.this, "Data Saved", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(NewsActivity.this, "Data now saved", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}