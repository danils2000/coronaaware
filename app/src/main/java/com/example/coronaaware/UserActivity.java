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
    EditText etDay, etTemperature, etRate;
    Button buttonInsert;
    Button buttonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        coronaDb = new UserData(this);

        etDay = (EditText)findViewById(R.id.etDay);
        etTemperature = (EditText)findViewById(R.id.etTemperature);
        etRate = (EditText)findViewById(R.id.etRate);
        buttonInsert = (Button)findViewById(R.id.buttonInsert);
        buttonView = (Button)findViewById(R.id.buttonView);

        Insert_Data();
        Represent_Data();
    }

    public void Insert_Data(){
        buttonInsert.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = coronaDb.Add_Data(etDay.getText().toString(),
                                etTemperature.getText().toString(),
                                etRate.getText().toString());

                        if (isInserted) {
                            Toast.makeText(UserActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(UserActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();

                        }
                    }
                }
        );
    }
    public void Represent_Data(){
        buttonView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = coronaDb.Show_Data();
                        if (res.getCount()==0) {
                            Alert_Window("SOMETHING WENT WRONG","NO DATA");
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
                        Alert_Window("Health Data", builder.toString());
                    }
                }
        );
    }

    public void Alert_Window(String Heading, String Alert) {
        AlertDialog.Builder Dialog = new AlertDialog.Builder(this);
        Dialog.setCancelable(true);
        Dialog.setTitle(Heading);
        Dialog.setMessage(Alert);
        Dialog.show();
    }
}