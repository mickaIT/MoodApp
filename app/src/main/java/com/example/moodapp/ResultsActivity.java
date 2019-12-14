package com.example.moodapp;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ResultsActivity extends AppCompatActivity {

    Button viewAllResultsButton;
    Button deleteResultsButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        viewAllResultsButton=(Button) findViewById(R.id.viewAllResultsButton);
        deleteResultsButton=(Button) findViewById(R.id.deleteResultsButton);

//        viewAllResultsButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent startIntent = new Intent(getApplicationContext(), ViewAllResultsActivity.class);
//                startActivity(startIntent);
//            }
//        });
    }

}
