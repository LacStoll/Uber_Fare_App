package com.example.uberfareapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    TextView vehicleTxt, distanceTxt, totalTxt;
    ImageView vehicleImg;
    Button RequestRideBtn, goBackButton;

    SharedPreferences sharedPreferences;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        vehicleTxt = findViewById(R.id.vehicleTxt);
        distanceTxt = findViewById(R.id.distanceTxt);
        totalTxt = findViewById(R.id.totalTxt);
        vehicleImg = findViewById(R.id.vehicleImg);
        RequestRideBtn = findViewById(R.id.RequestRideBtn);
        goBackButton = findViewById(R.id.goBackButton);

        sharedPreferences = getSharedPreferences("UberData", MODE_PRIVATE);

        int distance = sharedPreferences.getInt("distance", 0);
        String vehicle = sharedPreferences.getString("vehicle", "");

        double fare = 3.0 + (3.25 * distance);

//        switch (vehicle) {
//            case "Smart Car":
//                fare += 2.0;
//                break;
//            case "Minivan":
//                fare += 5.0;
//                break;
//            case "Sedan":
//                fare += 0;
//                break;
        switch (vehicle) {
            case "Smart Car":
                fare += 2.0;
                vehicleImg.setImageResource(R.drawable.smart_car);
                break;
            case "Sedan":
                vehicleImg.setImageResource(R.drawable.sedan);
                break;
            case "Minivan":
                fare += 5.0;
                vehicleImg.setImageResource(R.drawable.minivan);
                break;
        }

        vehicleTxt.setText(String.format("Vehicle: " + vehicle));
        distanceTxt.setText(String.format("Distance: " + distance + " miles"));
        totalTxt.setText(String.format("Total: $%.2f", fare));

        RequestRideBtn.setOnClickListener(v -> {
            Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
            startActivity(intent);
        });

        goBackButton.setOnClickListener(v -> {
            Intent intent = new Intent(SecondActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}