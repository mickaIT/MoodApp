package com.example.moodapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moodapp.Graphs_classes.ValueFormatter;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
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
            float val1=(int)(-Math.random()*count+5);
            float val2=(int)(Math.random()*count+10);
            float val3=(int)(Math.random()*count+15);
            float val4=(int)(Math.random()*count+20);
            float val5=(int)(Math.random()*count+30);

            //i -> date valn-> results
            yValues.add(new BarEntry(i,new float[]{val1,val1,val3,val4,-val5}));
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


    public void addDataToGraph(){

        //yVals CHART VALUES
        final ArrayList<BarEntry> yVals=new ArrayList<BarEntry>();
        //xVals SQLite VALUES
        final ArrayList<String> xData=MainActivity.myDb.queryXData();

        //yVals SQLite VALUES
        final ArrayList<String> sympthom_1_data=MainActivity.myDb.querySympthom_1_Data();
        final ArrayList<String> sympthom_2_data=MainActivity.myDb.querySympthom_2_Data();
        final ArrayList<String> sympthom_3_data=MainActivity.myDb.querySympthom_3_Data();
        final ArrayList<String> sympthom_4_data=MainActivity.myDb.querySympthom_4_Data();
        final ArrayList<String> sympthom_5_data=MainActivity.myDb.querySympthom_5_Data();
        final ArrayList<String> sympthom_6_data=MainActivity.myDb.querySympthom_6_Data();
        final ArrayList<String> sympthom_7_data=MainActivity.myDb.querySympthom_7_Data();
        final ArrayList<String> sympthom_8_data=MainActivity.myDb.querySympthom_8_Data();

        //addind BarEtries to Y-values
        for(int i=0;i<sympthom_1_data.size();i++){
            BarEntry barEntry=new BarEntry(i, Float.parseFloat(sympthom_1_data.get(i)));
            yVals.add(barEntry);
        }

        //xVals CHART VALUES (DATE)
        final ArrayList<String> xVals=new ArrayList<String>();


        //addind BarEtries to X-values

        for(int i=0;i<MainActivity.myDb.queryXData().size();i++){
//            BarEntry barEntry=new BarEntry(i, MainActivity.myDb.queryYData().get(i));
            xVals.add(xData.get(i));
        }

        BarDataSet dataSet=new BarDataSet(yVals,"Graph");

        ArrayList<IBarDataSet> dataSets1=new ArrayList<>();
        dataSets1.add(dataSet);
        BarData data=new BarData(dataSets1);

        //      new value formatter !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xVals));
//        barChart.getYAxis().setValueFormatter(new com.example.moodapp.Graphs_classes.ValueFormatter());


        chart.setData(data);

    }
}
