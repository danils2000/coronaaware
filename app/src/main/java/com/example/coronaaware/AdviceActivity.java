package com.example.coronaaware;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdviceActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonGeneral;
    private Button buttonTravel;
    private Button buttonWHO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);

        // Initializing buttons' IDs
        buttonGeneral = findViewById(R.id.buttonGeneralAdvice);
        buttonTravel = findViewById(R.id.buttonTravelAdvice);
        buttonWHO = findViewById(R.id.buttonWHO);

        // Setting the event listeners for buttons
        buttonGeneral.setOnClickListener(this);
        buttonTravel.setOnClickListener(this);
        buttonWHO.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Each case for each button clicked
        switch (v.getId()) {
            case R.id.buttonGeneralAdvice:
                goNext(ViewGeneralAdvice.class);
                break;
            case R.id.buttonTravelAdvice:
                goNext(ViewTravellingAdvice.class);
                break;
            case R.id.buttonWHO:
                browse(v);
                break;
        }
    }

    /**
     * Create a new activity, given as a parameter
     * @param next chosen activity
     */
    protected void goNext(Class next) {
        Intent intent = new Intent(this, next);
        startActivity(intent);
    }

    /**
     * Open World Health Organisation website
     * @param view accessed by WHO button widget
     */
    public void browse(View view) {
        Intent browseIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.who.int/emergencies/diseases/novel-coronavirus-2019"));
        startActivity(browseIntent);
    }
}
