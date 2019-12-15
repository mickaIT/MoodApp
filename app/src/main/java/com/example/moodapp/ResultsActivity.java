package com.example.moodapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moodapp.Graphs_classes.ValueFormatter;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;

//import com.example.moodapp.Graphs_classes.AxisValueFormatter;

//import com.example.moodapp.Graphs_classes.AxisValueFormatter;

public class ResultsActivity extends AppCompatActivity {

    Button viewAllResultsButton;
    Button deleteResultsButton;
    BarChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        viewAllResultsButton=(Button) findViewById(R.id.viewAllResultsButton);
        deleteResultsButton=(Button) findViewById(R.id.deleteResultsButton);
        chart=(BarChart) findViewById(R.id.barChart);
//        chart.setVisibleXRangeMaximum(7);
        addDataToGraph();

        viewAllResultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), ViewAllResultsActivity.class);
                startActivity(startIntent);
            }
        });

        deleteResultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), BarChartActivity.class);
                startActivity(startIntent);
            }
        });
    }


//
//    public void setData(int count){
//        ArrayList<BarEntry> yValues=new ArrayList<>();
//        for (int i=0;i<count;i++){
//            float val1=(int)(Math.random()*count+5);
//            float val2=(int)(Math.random()*count+10);
//            float val3=(int)(Math.random()*count+15);
//            float val4=(int)(Math.random()*count+20);
//            float val5=(int)(Math.random()*count+30);
////i -column, vartości w danej kolumnie kolejno
//            //i -> date valn-> results
//            yValues.add(new BarEntry(i,new float[]{val1,val1,val3,val4,val5}));
//        }
// n
//        //set of Bar values
//        BarDataSet set;
//        set=new BarDataSet(yValues,"Results");
//        set.setDrawIcons(false);
//        set.setStackLabels(new String[]{"Insomnia","Megalomania","Poor decisions (like my life)"});
//
//        //joyful colors returns a tab[] of color values
//        set.setColors(ColorTemplate.JOYFUL_COLORS);
//
//        BarData data=new BarData(set);
//        data.setValueFormatter(new ValueFormatter());
//
//        chart.setData(data);
//        chart.setFitBars(true);
//        chart.invalidate();
//    }


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
        for(int i=0;i<xData.size();i++){
            BarEntry barEntry=new BarEntry(i, new float[]{Float.parseFloat(sympthom_1_data.get(i)),
                    Float.parseFloat(sympthom_2_data.get(i)),
                    Float.parseFloat(sympthom_3_data.get(i)),
                    Float.parseFloat(sympthom_4_data.get(i)),
                    Float.parseFloat(sympthom_5_data.get(i)),
                    Float.parseFloat(sympthom_6_data.get(i)),
                    Float.parseFloat(sympthom_7_data.get(i)),
                    Float.parseFloat(sympthom_8_data.get(i))});
            yVals.add(barEntry);
        }

        //xVals CHART VALUES (DATE)
        final ArrayList<String> xVals=new ArrayList<String>();


        //addind BarEtries to X-values

        for(int i=0;i<MainActivity.myDb.queryXData().size();i++){
//            BarEntry barEntry=new BarEntry(i, MainActivity.myDb.queryYData().get(i));
            xVals.add(xData.get(i));
        }

        BarDataSet dataSet=new BarDataSet(yVals,"Bar Chart");
        //additionals
        dataSet.setDrawIcons(false);
        dataSet.setStackLabels(new String[]{
                "Talkativeness",
                "Insomnia",
                "Flight of ideas",
                "Tiredness",
                "Hyperactivity",
                "Irritability",
                "Megalomania",
                "Poor decisions"});
        //joyful colors returns a tab[] of color values
        int[] colorsArr=new int[]{Color.rgb(235, 140, 52),
                Color.rgb(52, 168, 235),
                Color.rgb(210, 150, 255),
                Color.rgb(255, 246, 77),
                Color.rgb(61, 139, 255),
                Color.rgb(52, 153, 76),
                Color.rgb(255, 166, 195),
                Color.rgb(42, 109, 130)};
//                Color.GRAY, Color.CYAN, Color.YELLOW, Color.DKGRAY, Color.RED, Color.MAGENTA, Color.BLACK};
//        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        dataSet.setColors(colorsArr);


        ArrayList<IBarDataSet> dataSets1=new ArrayList<>();
        dataSets1.add(dataSet);
        BarData data=new BarData(dataSets1);

        data.setValueFormatter(new ValueFormatter());
        //      new value formatter !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xVals));
//        barChart.getYAxis().setValueFormatter(new com.example.moodapp.Graphs_classes.ValueFormatter());


        chart.setData(data);
        chart.setFitBars(true);
        chart.invalidate();

    }
//=======================================================================ORIGINAL
//    public void addDataToGraph(){
//        final ArrayList<BarEntry> yVals=new ArrayList<BarEntry>();
//        final ArrayList<String> yData=MainActivity.myDb.querySympthom_1_Data();
//
//        //addind BarEtries to Y-values
//        for(int i=0;i<MainActivity.myDb.querySympthom_1_Data().size();i++){
//            BarEntry barEntry=new BarEntry(i, Float.parseFloat(MainActivity.myDb.querySympthom_1_Data().get(i)));
//            yVals.add(barEntry);
//        }
//
//        final ArrayList<String> xVals=new ArrayList<String>();
//        final ArrayList<String> xData=MainActivity.myDb.queryXData();
//
//        //addind BarEtries to X-values
//
//        for(int i=0;i<MainActivity.myDb.queryXData().size();i++){
////            BarEntry barEntry=new BarEntry(i, MainActivity.myDb.queryYData().get(i));
//            xVals.add(xData.get(i));
//        }
//        BarDataSet dataSet=new BarDataSet(yVals,"Graph");
//
//        ArrayList<IBarDataSet> dataSets1=new ArrayList<>();
//        dataSets1.add(dataSet);
//        BarData data=new BarData(dataSets1);
//
//        //      new value formatter !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
//        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xVals));
////        barChart.getYAxis().setValueFormatter(new com.example.moodapp.Graphs_classes.ValueFormatter());
//
//
//        barChart.setData(data);
//
//    }
//=======================================================================

//    public void addDataToGraph(){
//        final ArrayList<BarEntry> yVals=new ArrayList<BarEntry>();
//        final ArrayList<Integer> yData=MainActivity.myDb.queryYData();
//
//        for(int i=0;i<MainActivity.myDb.queryYData().size();i++){
//            BarEntry barEntry=new BarEntry(i, yData.get(i));
//            yVals.add(barEntry);
//        }
//
//        final ArrayList<Date> xVals=new ArrayList<Date>();
//        final ArrayList<Date> xData=MainActivity.myDb.queryXData();
//
//        for(int i=0;i<MainActivity.myDb.queryXData().size();i++){
////            BarEntry barEntry=new BarEntry(i, Integer.parseInt(MainActivity.myDb.queryYData().get(i)));
//            xVals.add(xData.get(i));
//        }
//        BarDataSet dataSet=new BarDataSet(yVals,"Graph");
//
//        ArrayList<IBarDataSet> dataSets1=new ArrayList<>();
//        dataSets1.add(dataSet);
//        BarData data=new BarData(dataSets1);
//
//  //      new value formatter !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
//        chart.getXAxis().setValueFormatter(new AxisValueFormatter(xVals));
//        chart.getXAxis().setValueFormatter(new AxisValueFormatter(xVals));
//
//
//        chart.setData(data);
//
//    }

}
