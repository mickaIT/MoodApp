package com.example.moodapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MyMoodActivity extends AppCompatActivity {

    TextView talkativenessTxt;
    SeekBar talkativenessSeekBar;

    TextView insomniaTxt;
    SeekBar insomniaSeekBar;

    TextView flightOfIdeasTxt;
    SeekBar flightOfIdeasSeekBar;

    TextView tirednessTxt;
    SeekBar tirednessSeekBar;

    TextView hyperActivityTxt;
    SeekBar hyperactivitySeekBar;

    TextView inrritabilityTxt;
    SeekBar irritabilitySeekBar;

    TextView megalomaniaTxt;
    SeekBar megalomaniaSeekBar;

    TextView poorDecisionsTxt;
    SeekBar poorDecisionsSeekBar;

    SeekBar.OnSeekBarChangeListener mlistener;
    TextView textViewTalk;
    TextView textViewIns;

    Integer talkativenessValue=3;
    Integer insomniaValue=3;
    Integer flightOfIdeasValue=3;
    Integer tirednessValue=3;
    Integer hyperactivityValue=3;
    Integer irritabilityValue=3;
    Integer megalomaniaValue=3;
    Integer poorDecisionsValue=3;

    //=======================================DATAABSE===================

    Button addMoodData;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_mood);
        myDb=new DatabaseHelper(this);

        talkativenessTxt=(TextView) findViewById(R.id.talkativenessTxt);
        insomniaTxt=(TextView) findViewById(R.id.insomniaTxt);
        flightOfIdeasTxt=(TextView) findViewById(R.id.flightOfIdeasTxt);
        tirednessTxt=(TextView) findViewById(R.id.tirednessTxt);
        hyperActivityTxt=(TextView) findViewById(R.id.hyperActivityTxt);
        inrritabilityTxt=(TextView) findViewById(R.id.inrritabilityTxt);
        megalomaniaTxt=(TextView) findViewById(R.id.megalomaniaTxt);
        poorDecisionsTxt=(TextView) findViewById(R.id.poorDecisionsTxt);

        talkativenessSeekBar=(SeekBar) findViewById(R.id.talkativenessSeekBar);
        insomniaSeekBar=(SeekBar) findViewById(R.id.insomniaSeekBar);
        flightOfIdeasSeekBar=(SeekBar) findViewById(R.id.flightOfIdeasSeekBar);
        tirednessSeekBar=(SeekBar) findViewById(R.id.tirednessSeekBar);
        hyperactivitySeekBar=(SeekBar) findViewById(R.id.hyperactivitySeekBar);
        irritabilitySeekBar=(SeekBar) findViewById(R.id.irritabilitySeekBar);
        poorDecisionsSeekBar=(SeekBar) findViewById(R.id.poorDecisionsSeekBar);
        megalomaniaSeekBar=(SeekBar) findViewById(R.id.megalomaniaSeekBar);
        textViewTalk=(TextView) findViewById(R.id.textViewTalk);
        textViewIns=(TextView) findViewById(R.id.textViewIns);

        //ADD BUTTON
        addMoodData=(Button) findViewById(R.id.addMoodData);

        talkativenessSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textViewTalk.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mlistener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch (seekBar.getId()) {
                    case R.id.talkativenessSeekBar:
                        talkativenessValue=(Integer)progress;
                        break;

                    case R.id.insomniaSeekBar:
                        insomniaValue=(Integer)progress;
                        AddData();

                        break;
                    case R.id.flightOfIdeasSeekBar:
                        flightOfIdeasValue=(Integer)progress;

                        break;
                    case R.id.tirednessSeekBar:
                        tirednessValue=(Integer)progress;

                        break;
                    case R.id.hyperactivitySeekBar:
                        hyperactivityValue=(Integer)progress;
                        break;

                    case R.id.irritabilitySeekBar:
                        irritabilityValue=(Integer)progress;
                        break;

                    case R.id.poorDecisionsSeekBar:
                        poorDecisionsValue=(Integer)progress;
                        break;

                    case R.id.megalomaniaSeekBar:
                        megalomaniaValue=(Integer)progress;
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

        //----------------ODŚWIEŻAM DANE SEEKBARÓW---------------------------------

        talkativenessSeekBar.setOnSeekBarChangeListener(mlistener);
        insomniaSeekBar.setOnSeekBarChangeListener(mlistener);
        flightOfIdeasSeekBar.setOnSeekBarChangeListener(mlistener);
        tirednessSeekBar.setOnSeekBarChangeListener(mlistener);
        hyperactivitySeekBar.setOnSeekBarChangeListener(mlistener);
        irritabilitySeekBar.setOnSeekBarChangeListener(mlistener);
        poorDecisionsSeekBar.setOnSeekBarChangeListener(mlistener);
        megalomaniaSeekBar.setOnSeekBarChangeListener(mlistener);

        AddData();
    }

    public  void AddData() {
        tirednessTxt.setText(String.valueOf(talkativenessValue));

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
//                        textView11.setText(String.valueOf(flightOfIdeasValue));
                        if(isInserted == true)
                            Toast.makeText(MyMoodActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MyMoodActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

}