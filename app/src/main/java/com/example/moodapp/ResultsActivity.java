package com.example.moodapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moodapp.Graphs_classes.ValueFormatter;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity {

    Button viewAllResultsButton;
    Button deleteResultsButton;
    private BarChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        viewAllResultsButton=(Button) findViewById(R.id.viewAllResultsButton);
        deleteResultsButton=(Button) findViewById(R.id.deleteResultsButton);
        chart=(BarChart) findViewById(R.id.chart);
        chart.setMaxVisibleValueCount(40);
        setData(10);

        viewAllResultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), ViewAllResultsActivity.class);
                startActivity(startIntent);
            }
        });
    }


    public void setData(int count){
        ArrayList<BarEntry> yValues=new ArrayList<>();
        for (int i=0;i<count;i++){
            float val1=(int)(Math.random()*count+10);
            float val2=(int)(Math.random()*count+10);
            float val3=(int)(Math.random()*count+10);
            float val4=(int)(Math.random()*count+10);
            float val5=(int)(Math.random()*count+10);

            yValues.add(new BarEntry(i,new float[]{val1,val2,val3,val4,val5}));
        }

        BarDataSet set;
        set=new BarDataSet(yValues,"Statistics of USA");
        set.setDrawIcons(false);
        set.setStackLabels(new String[]{"Insomnia","Megalomania","Poor decisions (like my life)"});
        set.setColors(ColorTemplate.JOYFUL_COLORS);

        BarData data=new BarData(set);
        data.setValueFormatter(new ValueFormatter());

        chart.setData(data);
        chart.setFitBars(true);
        chart.invalidate();
    }

}
