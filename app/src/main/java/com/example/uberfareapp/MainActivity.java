package com.example.uberfareapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // Declaring the UI components
    EditText EditMiles;
    RadioGroup vehicleoption;
    RadioButton smartCarbtn, sedanbtn, minivanbtn;
    Button estimatebtn;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditMiles = findViewById(R.id.EditMiles);
        vehicleoption = findViewById(R.id.vehicleoptions);
        smartCarbtn = findViewById(R.id.smartCarbtn);
        sedanbtn = findViewById(R.id.sedanbtn);
        minivanbtn = findViewById(R.id.minivanbtn);
        estimatebtn = findViewById(R.id.estimatebtn);

        sharedPreferences = getSharedPreferences("UberData", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        //Setting a listener for the estimate button using Lambda
        estimatebtn.setOnClickListener(v -> {
            int distance = Integer.parseInt(EditMiles.getText().toString());
            int vehicleId = vehicleoption.getCheckedRadioButtonId();
            String vehicle = "";



            // Setting vehicleId depending on which RadioBtn is clicked
            if (vehicleId == R.id.smartCarbtn) {
                vehicle = "Smart Car";
            } else if (vehicleId == R.id.sedanbtn) {
                vehicle = "Sedan";
            } else if (vehicleId == R.id.minivanbtn) {
                vehicle = "Minivan";
            }

            // Stores the data in shared preferences
            editor.putInt("distance", distance);
            editor.putString("vehicle", vehicle);
            editor.apply();

            //Starts the second activity
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        });
    }
}