package com.example.coronaaware;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.coronaaware.data.NewsData;

import java.util.ArrayList;

public class ViewNewsContent extends AppCompatActivity {
    private ListView lvSavedNews;
    private NewsData newsDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_news_content);

        lvSavedNews = findViewById(R.id.lvSavedNews);
        newsDb = new NewsData(this);

        // Get access to saved data
        final ArrayList<String> savedNewsList = new ArrayList<>();
        Cursor data = newsDb.showData();

        // Create ListView adapted based on the accessed data
        if (data.getCount() == 0) {
            Toast.makeText(ViewNewsContent.this, getString(R.string.data_empty), Toast.LENGTH_LONG).show();
        } else {
            while (data.moveToNext()) {
                savedNewsList.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_list_item_1,
                        savedNewsList);
                lvSavedNews.setAdapter(listAdapter);
            }
        }

        /*lvSavedNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Integer deleteRows = newsDb.deleteData();
                if (deleteRows > 0) {
                    Toast.makeText(ViewNewsContent.this, getString(R.string.delete_mes), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ViewNewsContent.this, getString(R.string.delete_error), Toast.LENGTH_LONG).show();
                }
            }
        });*/
    }
}
