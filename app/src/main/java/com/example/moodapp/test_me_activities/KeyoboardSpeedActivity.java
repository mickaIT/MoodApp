package com.example.moodapp.test_me_activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moodapp.R;

public class KeyoboardSpeedActivity extends AppCompatActivity {

    TextView tv_text, tv_result;
    EditText et_writeText;
    String fullStory;
    long startTime, endTime=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyoboard_speed);



        tv_text=(TextView) findViewById(R.id.tv_text);
        tv_result=(TextView) findViewById(R.id.tv_result);
        et_writeText=(EditText) findViewById(R.id.et_writeText);


        fullStory=tv_text.getText().toString();
        final int numOfWords = countWordsUsingSplit(fullStory);
        et_writeText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String currentStory = et_writeText.getText().toString();

                //start typing

                if(currentStory.length()==1) {
                    startTime = System.currentTimeMillis();
                    tv_result.setText("Started");

                }

                //finished typing

                if(currentStory.equals(fullStory))
                {
                    endTime = System.currentTimeMillis();
                    tv_result.setText("Finished");

                    //calculate the time
                    long totalTime=(endTime-startTime)/1000;
                    tv_result.setText("Finished in " + String.valueOf(totalTime)+" seconds" + String.valueOf(numOfWords*60/totalTime) +" per minute");
                    et_writeText.setEnabled(false);
                    et_writeText.clearFocus();
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public static int countWordsUsingSplit(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        String[] words = input.split("\\s+");
        return words.length;
    }
}
