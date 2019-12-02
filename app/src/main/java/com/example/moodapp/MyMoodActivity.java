package com.example.moodapp;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

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

    TextView sexDriveTxt;
    SeekBar sexDriveSeekBar;

    SeekBar.OnSeekBarChangeListener mlistener;
    TextView textView11;
    TextView textView12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_mood);

        talkativenessTxt=(TextView) findViewById(R.id.talkativenessTxt);
        insomniaTxt=(TextView) findViewById(R.id.insomniaTxt);
        flightOfIdeasTxt=(TextView) findViewById(R.id.flightOfIdeasTxt);
        tirednessTxt=(TextView) findViewById(R.id.tirednessTxt);
        hyperActivityTxt=(TextView) findViewById(R.id.hyperActivityTxt);
        inrritabilityTxt=(TextView) findViewById(R.id.inrritabilityTxt);
        megalomaniaTxt=(TextView) findViewById(R.id.megalomaniaTxt);
        sexDriveTxt=(TextView) findViewById(R.id.sexDriveTxt);

        talkativenessSeekBar=(SeekBar) findViewById(R.id.talkativenessSeekBar);
        insomniaSeekBar=(SeekBar) findViewById(R.id.insomniaSeekBar);
        flightOfIdeasSeekBar=(SeekBar) findViewById(R.id.flightOfIdeasSeekBar);
        tirednessSeekBar=(SeekBar) findViewById(R.id.tirednessSeekBar);
        hyperactivitySeekBar=(SeekBar) findViewById(R.id.hyperactivitySeekBar);
        irritabilitySeekBar=(SeekBar) findViewById(R.id.irritabilitySeekBar);
        sexDriveSeekBar=(SeekBar) findViewById(R.id.sexDriveSeekBar);
        megalomaniaSeekBar=(SeekBar) findViewById(R.id.megalomaniaSeekBar);
        textView11=(TextView) findViewById(R.id.textView11);
        textView12=(TextView) findViewById(R.id.textView12);

        talkativenessSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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
});

        mlistener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch (seekBar.getId()) {
                    case R.id.insomniaSeekBar:
                        textView11.setText(String.valueOf(progress));
                        break;
                    case R.id.flightOfIdeasSeekBar:
                        textView12.setText(String.valueOf(progress));
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

        insomniaSeekBar.setOnSeekBarChangeListener(mlistener);
        flightOfIdeasSeekBar.setOnSeekBarChangeListener(mlistener);
    }
}
