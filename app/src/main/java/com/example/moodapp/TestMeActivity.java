package com.example.moodapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moodapp.test_me_activities.faceMoodActivity.FaceMoodActivity;
import com.example.moodapp.test_me_activities.HeartRateActivity;
import com.example.moodapp.test_me_activities.KeyoboardSpeedActivity;

public class TestMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_me);

        final Button keyboardSpeedBtn = (Button)findViewById(R.id.keyboardSpeedBtn);
        keyboardSpeedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), KeyoboardSpeedActivity.class);
                startActivity(startIntent);

            }
        });

        final Button heartRateBtn = (Button)findViewById(R.id.heartRateBtn);
        heartRateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), HeartRateActivity.class);
                startActivity(startIntent);

            }
        });

        final Button faceMoodBtn = (Button)findViewById(R.id.faceMoodBtn);
        faceMoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), FaceMoodActivity.class);
                startActivity(startIntent);

            }
        });
    }
}
