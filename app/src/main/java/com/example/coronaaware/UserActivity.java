package com.example.coronaaware;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coronaaware.data.NewsData;
import com.example.coronaaware.data.UserData;

public class UserActivity extends AppCompatActivity {
    private NewsData coronaNewsDb;
    private UserData coronaDb;
    private EditText etDay, etTemperature, etRate, etDelete;
    private Button buttonInsert;
    private Button buttonView;
    private Button buttonDelete;
    private Button buttonViewNews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        coronaDb = new UserData(this);
        coronaNewsDb = new NewsData(this);

        etDay = findViewById(R.id.etDay);
        etTemperature = findViewById(R.id.etTemperature);
        etRate = findViewById(R.id.etRate);
        etDelete = findViewById(R.id.etDelete);
        buttonInsert = findViewById(R.id.buttonInsert);
        buttonView = findViewById(R.id.buttonView);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonViewNews = findViewById(R.id.buttonViewNews);

        insertUserData();
        representUserData();
        deleteUserData();
        representDataNews();
    }
// saves inserted data
    public void insertUserData(){
        buttonInsert.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = coronaDb.addData(etDay.getText().toString(),
                                etTemperature.getText().toString(),
                                etRate.getText().toString());

                        if (isInserted) {
                            Toast.makeText(UserActivity.this, getString(R.string.insert_mes),Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(UserActivity.this, getString(R.string.insert_error),Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }
    // represents users data
    public void representUserData(){
        buttonView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = coronaDb.showData();
                        if (res.getCount()==0) {
                            alertWindow(getString(R.string.data_empty_error), getString(R.string.data_empty));
                            return;
                        }

                        StringBuilder builder = new StringBuilder();
                        while(res.moveToNext()) {
                            builder.append(getString(R.string.data,
                                    res.getString(0),
                                    res.getString(1),
                                    res.getString(2),
                                    res.getString(3)));
                        }
                        alertWindow(getString(R.string.data_head), builder.toString());
                    }
                }
        );
    }
//deletes data by its ID
    public void deleteUserData(){
        buttonDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deleteRows = coronaDb.deleteData(etDelete.getText().toString());
                        if (deleteRows > 0) {
                            Toast.makeText(UserActivity.this, getString(R.string.delete_mes), Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(UserActivity.this, getString(R.string.delete_error), Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }
//represents data saved from news
    public void representDataNews() {
        buttonViewNews.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(UserActivity.this, ViewNewsContent.class);
                        startActivity(intent);
                    }
                }
        );
    }
// creates alert window that pops up by clicking a certain button
    public void alertWindow(String Heading, String Alert) {
        AlertDialog.Builder Dialog = new AlertDialog.Builder(this);
        Dialog.setCancelable(true);
        Dialog.setTitle(Heading);
        Dialog.setMessage(Alert);
        Dialog.show();
    }
}