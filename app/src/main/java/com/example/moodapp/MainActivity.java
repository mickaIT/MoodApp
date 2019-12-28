package com.example.moodapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    static DatabaseHelper myDb;
static SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TEST ME
        myDb=new DatabaseHelper(this);
        sqLiteDatabase=myDb.getWritableDatabase();
       final Button testMeBtn = (Button)findViewById(R.id.testMeBtn);
        testMeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), TestMeActivity.class);
                startActivity(startIntent);

            }
        });

        // MY MOOD

        final Button myMoodBtn = (Button)findViewById(R.id.myMoodBtn);
        myMoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), MyMoodActivity.class);
                startActivity(startIntent);

            }
        });

        //NOTIFICATIONS

        final Button setNotificationsBtn = (Button)findViewById(R.id.setNotificationsBtn);
        setNotificationsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), SetNotificationsActivity.class);
                startActivity(startIntent);

            }
        });

        // MY MOOD

        final Button resultsBtn = (Button)findViewById(R.id.resultsBtn);
        resultsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), ResultsActivity.class);
                startActivity(startIntent);

            }
        });


    }
}
