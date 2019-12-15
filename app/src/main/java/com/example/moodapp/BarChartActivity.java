package com.example.moodapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moodapp.Graphs_classes.ValueFormatter;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BarChartActivity extends AppCompatActivity {

    private BarChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        chart=(BarChart) findViewById(R.id.chart);
        chart.setMaxVisibleValueCount(40);
//        addDataToGraph();

        setData(10);
    }

    public void setData(int count){
        ArrayList<BarEntry> yValues=new ArrayList<>();
        //for i -column, vartości w danej kolumnie kolejno
        for (int i=0;i<count;i++){
            float val1=(int)(Math.random()*count+5);
            float val2=(int)(Math.random()*count+10);
            float val3=(int)(Math.random()*count+15);
            float val4=(int)(Math.random()*count+20);
            float val5=(int)(Math.random()*count+30);

            //i -> date valn-> results
            yValues.add(new BarEntry(i,new float[]{val1,val1,val3,val4,val5}));
        }

        //set of Bar values
        BarDataSet set;
        set=new BarDataSet(yValues,"Results");

        //additionals
        set.setDrawIcons(false);
        set.setStackLabels(new String[]{"Insomnia","Megalomania","Poor decisions (like my life)"});

        //joyful colors returns a tab[] of color values
        set.setColors(ColorTemplate.JOYFUL_COLORS);

        BarData data=new BarData(set);
        data.setValueFormatter(new ValueFormatter());

        chart.setData(data);
        chart.setFitBars(true);
        chart.invalidate();
    }
}
