package com.example.moodapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MyMoodActivity extends AppCompatActivity {

    TextView symptomTxt_1;
    SeekBar seekBar_1;

    TextView symptomTxt_2;
    SeekBar seekBar_2;

    TextView symptomTxt_3;
    SeekBar seekBar_3;

    TextView symptomTxt_4;
    SeekBar seekBar_4;

    TextView symptomTxt_5;
    SeekBar seekBar_5;

    TextView symptomTxt_6;
    SeekBar seekBar_6;

    TextView symptomTxt_7;
    SeekBar seekBar_7;

    TextView symptomTxt_8;
    SeekBar seekBar_8;

    SeekBar.OnSeekBarChangeListener mlistener;
    int zeroValue=5; //diff to <-5,5>range
    Integer symptomVal_1=0;
    Integer symptomVal_2=0;
    Integer symptomVal_3=0;
    Integer symptomVal_4=0;
    Integer symptomVal_5=0;
    Integer symptomVal_6=0;
    Integer symptomVal_7=0;
    Integer symptomVal_8=0;

    //=======================================DATAABSE===================

    Button addMoodData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_mood);

        initViews();

        //ADD BUTTON
        addMoodData=(Button) findViewById(R.id.addMoodData);

        mlistener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch (seekBar.getId()) {
                    case R.id.seekBar_1:
                        symptomVal_1=(Integer)progress-zeroValue;
                        break;

                    case R.id.seekBar_2:
                        symptomVal_2=(Integer)progress-zeroValue;
                        AddData();

                        break;
                    case R.id.seekBar_3:
                        symptomVal_3=(Integer)progress-zeroValue;

                        break;
                    case R.id.seekBar_4:
                        symptomVal_4=(Integer)progress-zeroValue;

                        break;
                    case R.id.seekBar_5:
                        symptomVal_5=(Integer)progress-zeroValue;
                        break;

                    case R.id.seekBar_6:
                        symptomVal_6=(Integer)progress-zeroValue;
                        break;

                    case R.id.seekBar_7:
                        symptomVal_7=(Integer)progress-zeroValue;
                        break;

                    case R.id.seekBar_8:
                        symptomVal_8=(Integer)progress-zeroValue;
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

        seekBar_1.setOnSeekBarChangeListener(mlistener);
        seekBar_2.setOnSeekBarChangeListener(mlistener);
        seekBar_3.setOnSeekBarChangeListener(mlistener);
        seekBar_4.setOnSeekBarChangeListener(mlistener);
        seekBar_5.setOnSeekBarChangeListener(mlistener);
        seekBar_6.setOnSeekBarChangeListener(mlistener);
        seekBar_8.setOnSeekBarChangeListener(mlistener);
        seekBar_7.setOnSeekBarChangeListener(mlistener);

        AddData();
    }


    public  void AddData() {
        addMoodData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = MainActivity.myDb.insertData(symptomVal_1,
                                symptomVal_2,
                                symptomVal_3,
                                symptomVal_4,
                                symptomVal_5,
                                symptomVal_6,
                                symptomVal_7,
                                symptomVal_8);
//                        textView11.setText(String.valueOf(symptomVal_3));
                        if(isInserted == true)
                            Toast.makeText(MyMoodActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MyMoodActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    private void initViews(){
        symptomTxt_1=(TextView) findViewById(R.id.symptomTxt_1);
        symptomTxt_2=(TextView) findViewById(R.id.symptomTxt_2);
        symptomTxt_3=(TextView) findViewById(R.id.symptomTxt_3);
        symptomTxt_4=(TextView) findViewById(R.id.symptomTxt_4);
        symptomTxt_5=(TextView) findViewById(R.id.symptomTxt_5);
        symptomTxt_6=(TextView) findViewById(R.id.symptomTxt_6);
        symptomTxt_7=(TextView) findViewById(R.id.symptomTxt_7);
        symptomTxt_8=(TextView) findViewById(R.id.symptomTxt_8);

        seekBar_1=(SeekBar) findViewById(R.id.seekBar_1);
        seekBar_2=(SeekBar) findViewById(R.id.seekBar_2);
        seekBar_3=(SeekBar) findViewById(R.id.seekBar_3);
        seekBar_4=(SeekBar) findViewById(R.id.seekBar_4);
        seekBar_5=(SeekBar) findViewById(R.id.seekBar_5);
        seekBar_6=(SeekBar) findViewById(R.id.seekBar_6);
        seekBar_8=(SeekBar) findViewById(R.id.seekBar_7);
        seekBar_7=(SeekBar) findViewById(R.id.seekBar_8);

    }

}