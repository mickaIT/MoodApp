package com.example.moodapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MyMoodActivity extends AppCompatActivity {

    SeekBar talkativenessSeekBar;
    SeekBar insomniaSeekBar;
    SeekBar flightOfIdeasSeekBar;
    SeekBar tirednessSeekBar;
    SeekBar hyperactivitySeekBar;
    SeekBar irritabilitySeekBar;
    SeekBar megalomaniaSeekBar;
    SeekBar poorDecisionsSeekBar;

    int defaultProgress=3;
    int talkativenessValue=defaultProgress;
    int insomniaValue=defaultProgress;
    int flightOfIdeasValue=defaultProgress;
    int tirednessValue=defaultProgress;
    int hyperactivityValue=defaultProgress;
    int irritabilityValue=defaultProgress;
    int megalomaniaValue=defaultProgress;
    int poorDecisionsValue=defaultProgress;

    SeekBar.OnSeekBarChangeListener mlistener;
    TextView textView11;
    TextView textView12;


    Button addMoodData;
//DATAABSE
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_mood);
        myDb=new DatabaseHelper(this);
//SEEKBARS
        talkativenessSeekBar=(SeekBar) findViewById(R.id.talkativenessSeekBar);
        insomniaSeekBar=(SeekBar) findViewById(R.id.insomniaSeekBar);
        flightOfIdeasSeekBar=(SeekBar) findViewById(R.id.flightOfIdeasSeekBar);
        tirednessSeekBar=(SeekBar) findViewById(R.id.tirednessSeekBar);
        hyperactivitySeekBar=(SeekBar) findViewById(R.id.hyperactivitySeekBar);
        irritabilitySeekBar=(SeekBar) findViewById(R.id.irritabilitySeekBar);
        poorDecisionsSeekBar=(SeekBar) findViewById(R.id.poorDecisionsSeekBar);
        megalomaniaSeekBar=(SeekBar) findViewById(R.id.megalomaniaSeekBar);
        textView11=(TextView) findViewById(R.id.textView11);
        textView12=(TextView) findViewById(R.id.textView12);
//ADD BUTTON
        addMoodData=(Button) findViewById(R.id.addMoodData);


        talkativenessSeekBar.setOnSeekBarChangeListener(mlistener);
        insomniaSeekBar.setOnSeekBarChangeListener(mlistener);
        flightOfIdeasSeekBar.setOnSeekBarChangeListener(mlistener);
        tirednessSeekBar.setOnSeekBarChangeListener(mlistener);
        hyperactivitySeekBar.setOnSeekBarChangeListener(mlistener);
        irritabilitySeekBar.setOnSeekBarChangeListener(mlistener);
        poorDecisionsSeekBar.setOnSeekBarChangeListener(mlistener);
        megalomaniaSeekBar.setOnSeekBarChangeListener(mlistener);


/*        talkativenessSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        textView11.setText(String.valueOf(i));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
});*/

        mlistener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch (seekBar.getId()) {
                    case R.id.talkativenessSeekBar:
                        textView12.setText(String.valueOf(progress));
                        talkativenessValue=progress;
                        break;
                    case R.id.insomniaSeekBar:
                      // textView11.setText(String.valueOf(progress));
                        insomniaValue=progress;

                        break;
                    case R.id.flightOfIdeasSeekBar:
                      textView12.setText(String.valueOf(progress));
                        flightOfIdeasValue=progress;

                        break;
                    case R.id.tirednessSeekBar:
                       // textView12.setText(String.valueOf(progress));
                        tirednessValue=progress;

                        break;
                    case R.id.hyperactivitySeekBar:
                       // textView12.setText(String.valueOf(progress));
                        hyperactivityValue=progress;

                        break;
                    case R.id.irritabilitySeekBar:
                       // textView12.setText(String.valueOf(progress));
                        irritabilityValue=progress;

                        break;
                    case R.id.poorDecisionsSeekBar:
                       // textView12.setText(String.valueOf(progress));
                        poorDecisionsValue=progress;

                        break;
                    case R.id.megalomaniaSeekBar:
                      //  textView12.setText(String.valueOf(progress));
                        megalomaniaValue=progress;

                        break;

                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };

        AddData();

    }

    public  void AddData() {
        addMoodData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(talkativenessValue,
                                insomniaValue,
                                flightOfIdeasValue,
                                tirednessValue,
                                hyperactivityValue,
                                irritabilityValue,
                                megalomaniaValue,
                                poorDecisionsValue);
                        textView11.setText(String.valueOf(flightOfIdeasValue));
                        if(isInserted == true)
                            Toast.makeText(MyMoodActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MyMoodActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}
