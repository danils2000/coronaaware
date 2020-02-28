package com.example.coronaaware;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coronaaware.data.UserData;

public class UserActivity extends AppCompatActivity {

    UserData coronaDb;
    EditText etDay, etTemperature, etRate, etDelete;
    Button buttonInsert;
    Button buttonView;
    Button buttonDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        coronaDb = new UserData(this);

        etDay = (EditText)findViewById(R.id.etDay);
        etTemperature = (EditText)findViewById(R.id.etTemperature);
        etRate = (EditText)findViewById(R.id.etRate);
        etDelete = (EditText)findViewById(R.id.etDelete);
        buttonInsert = (Button)findViewById(R.id.buttonInsert);
        buttonView = (Button)findViewById(R.id.buttonView);
        buttonDelete = (Button)findViewById(R.id.buttonDelete);

        insertData();
        representData();
        deleteData();
    }

    public void insertData(){
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
    public void representData(){
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

    public void deleteData(){
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

    public void alertWindow(String Heading, String Alert) {
        AlertDialog.Builder Dialog = new AlertDialog.Builder(this);
        Dialog.setCancelable(true);
        Dialog.setTitle(Heading);
        Dialog.setMessage(Alert);
        Dialog.show();
    }
}