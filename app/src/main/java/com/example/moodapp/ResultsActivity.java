package com.example.moodapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moodapp.Graphs_classes.ValueFormatter;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.webianks.library.scroll_choice.ScrollChoice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//import com.example.moodapp.Graphs_classes.AxisValueFormatter;

//import com.example.moodapp.Graphs_classes.AxisValueFormatter;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ResultsActivity extends AppCompatActivity {

    LocalDate today = LocalDate.now();

    int currentMonth = today.getMonthValue();
    int currentYear = today.getYear();

    TextView test;
    Button viewAllResultsButton;
    Button buttonPieChart;
    BarChart stackedBarChart;
    BarChart barChart;
    List<String> months = new ArrayList<>();
    ScrollChoice scrollMonths;
    TextView textYear;
    ImageButton buttonRight;
    ImageButton buttonLeft;
    static int mBackgroundColor;
    private LineChart[] lineCharts = new LineChart[8]; //ilość linechartsów
    final ArrayList<String>[] sympthoms=(ArrayList<String>[]) new ArrayList[lineCharts.length];

    int[] colorsArr = new int[]{Color.rgb(235, 140, 52),
            Color.rgb(52, 168, 235),
            Color.rgb(210, 150, 255),
            Color.rgb(255, 246, 77),
            Color.rgb(61, 139, 255),
            Color.rgb(52, 153, 76),
            Color.rgb(255, 166, 195),
            Color.rgb(42, 109, 130)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        mBackgroundColor=Color.parseColor("#f3f3f3");

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initializeViews();
        //load sympthoms (improve efficency)
MainActivity.myDb.addTestData();
        setSympthomsData(sympthoms,currentMonth,currentYear);

        drawStackedBarChart(currentMonth,currentYear);

        drawBarChart(currentMonth,currentYear);
        drawLineCharts(currentMonth,currentYear);

        textYear.setText(Integer.toString(currentYear));

        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentYear=currentYear-1;
                textYear.setText(Integer.toString(currentYear));

                setSympthomsData(sympthoms,currentMonth,currentYear);
                stackedBarChart.clear();
                barChart.clear();
                clearLineCharts();

                drawBarChart(currentMonth,currentYear);
                drawStackedBarChart(currentMonth,currentYear);

                drawLineCharts(currentMonth,currentYear);

            }
        });

        // Images right navigatin
        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentYear=currentYear+1;
                textYear.setText(Integer.toString(currentYear));

                setSympthomsData(sympthoms,currentMonth,currentYear);



                stackedBarChart.clear();
                barChart.clear();
                clearLineCharts();

                drawBarChart(currentMonth,currentYear);
                drawStackedBarChart(currentMonth,currentYear);
                drawLineCharts(currentMonth,currentYear);

            }
        });

        scrollMonths.setOnItemSelectedListener(new ScrollChoice.OnItemSelectedListener() {
            @Override
            public void onItemSelected(ScrollChoice scrollChoice, int position, String name) {
                int intPosition=position+1;
                currentMonth=intPosition;

                setSympthomsData(sympthoms,currentMonth,currentYear);

                //TEST

                //END TEST

                stackedBarChart.clear();
                barChart.clear();
                clearLineCharts();


                //TEST
                test.setText(Integer.toString(currentMonth));
                //END TEST

                drawStackedBarChart(currentMonth,currentYear);
                drawBarChart(currentMonth,currentYear);
                drawLineCharts(currentMonth,currentYear);

//                textView.setText(String.valueOf(intPosition));
            }
        });

        loadMonths();
        //zmień na aktualne===========================================================
        scrollMonths.addItems(months,currentMonth-1); //index starts with zero

        viewAllResultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), ViewAllResultsActivity.class);
                startActivity(startIntent);
            }
        });

    }

    private void initializeViews(){

        test=(TextView)findViewById(R.id.testTxt);

        barChart = (BarChart) findViewById(R.id.sumChart);
        viewAllResultsButton=(Button) findViewById(R.id.buttonViewAllResults);
        stackedBarChart=(BarChart) findViewById(R.id.barChart);
        scrollMonths=(ScrollChoice) findViewById(R.id.scroll_choice_months);
        textYear=(TextView)findViewById(R.id.textYear);
        buttonLeft=(ImageButton)findViewById(R.id.left_nav);
        buttonRight=(ImageButton)findViewById(R.id.right_nav);
        buttonPieChart=(Button)findViewById(R.id.buttonPieChart);

        lineCharts[0] = (LineChart) findViewById(R.id.lineChart1);
        lineCharts[1] = (LineChart) findViewById(R.id.lineChart2);
        lineCharts[2] = (LineChart) findViewById(R.id.lineChart3);
        lineCharts[3] = (LineChart) findViewById(R.id.lineChart4);
        lineCharts[4] = (LineChart) findViewById(R.id.lineChart5);
        lineCharts[5] = (LineChart) findViewById(R.id.lineChart6);
        lineCharts[6] = (LineChart) findViewById(R.id.lineChart7);
        lineCharts[7] = (LineChart) findViewById(R.id.lineChart8);

    }

    private void loadMonths(){
        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");
    }

    private void clearLineCharts(){
        for(int i=0; i<lineCharts.length;i++){
            lineCharts[i].clear();
        }
    }
    public void drawStackedBarChart(int month,int year) {
        //yVals CHART VALUES
        final ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();
        //xVals SQLite VALUES
        final ArrayList<String> xData = MainActivity.myDb.queryXData(month, year);

        //yVals SQLite VALUES
        final ArrayList<String> sympthom_1_data = MainActivity.myDb.querySympthom_1_Data(month, year);
        final ArrayList<String> sympthom_2_data = MainActivity.myDb.querySympthom_2_Data(month, year);
        final ArrayList<String> sympthom_3_data = MainActivity.myDb.querySympthom_3_Data(month, year);
        final ArrayList<String> sympthom_4_data = MainActivity.myDb.querySympthom_4_Data(month, year);
        final ArrayList<String> sympthom_5_data = MainActivity.myDb.querySympthom_5_Data(month, year);
        final ArrayList<String> sympthom_6_data = MainActivity.myDb.querySympthom_6_Data(month, year);
        final ArrayList<String> sympthom_7_data = MainActivity.myDb.querySympthom_7_Data(month, year);
        final ArrayList<String> sympthom_8_data = MainActivity.myDb.querySympthom_8_Data(month, year);

        if (xData.isEmpty()) {
            return;
        } else {

            //addind BarEtries to Y-values
            for (int i = 0; i < xData.size(); i++) {
                BarEntry barEntry = new BarEntry(i, new float[]{Float.parseFloat(sympthom_1_data.get(i)),
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
            final ArrayList<String> xVals = new ArrayList<String>();


            //addind BarEtries to X-values

            for (int i = 0; i < MainActivity.myDb.queryXData(month, year).size(); i++) {
//            BarEntry barEntry=new BarEntry(i, MainActivity.myDb.queryYData().get(i));
                xVals.add(xData.get(i));
            }

            BarDataSet dataSet = new BarDataSet(yVals, "Bar Chart");
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

            stackedBarChart.getLegend().setWordWrapEnabled(true);
            //joyful colors returns a tab[] of color values
            int[] colorsArr = new int[]{Color.rgb(235, 140, 52),
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


            ArrayList<IBarDataSet> dataSets1 = new ArrayList<>();
            dataSets1.add(dataSet);
            BarData data = new BarData(dataSets1);

            data.setValueFormatter(new ValueFormatter());
            //      new value formatter !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
            stackedBarChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xVals));


            XAxis xAxis = stackedBarChart.getXAxis();
            xAxis.setGranularity(1f);
            xAxis.setGranularityEnabled(true);
            stackedBarChart.getAxisRight().setEnabled(false);

            stackedBarChart.animateXY(1300,1300);
//        barChart.getYAxis().setValueFormatter(new com.example.moodapp.Graphs_classes.ValueFormatter());


            stackedBarChart.setData(data);
            stackedBarChart.setFitBars(true);
            stackedBarChart.notifyDataSetChanged();
            stackedBarChart.refreshDrawableState();
            stackedBarChart.invalidate();

        }
    }

    public void drawBarChart(int month,int year){
        ArrayList<BarEntry> mVals=new ArrayList<BarEntry>();
        final ArrayList<Float> mData = MainActivity.myDb.querySumData(month, year);

        float floatVals[]=new float[8];

        for(int i=0; i<mData.size();i++){
            for(int j=0; j<lineCharts.length;j++) {
                floatVals[j] += Float.parseFloat(sympthoms[j].get(i));
            }
        }

        for(int i=0;i<lineCharts.length;i++){

            mVals.add(new BarEntry(i,floatVals[i]));
        }

        String[] labels = new String[]{
                "Talkativeness",
                "Insomnia",
                "Flight of ideas",
                "Tiredness",
                "Hyperactivity",
                "Irritability",
                "Megalomania",
                "Poor decisions"};

        //set of Bar values
        BarDataSet set;
        set=new BarDataSet(mVals,"Sum of results");
        set.setStackLabels(labels);
        set.setDrawIcons(false);

        //joyful colors returns a tab[] of color values
        int[] colorsArr = new int[]{Color.rgb(235, 140, 52),
                Color.rgb(52, 168, 235),
                Color.rgb(210, 150, 255),
                Color.rgb(255, 246, 77),
                Color.rgb(61, 139, 255),
                Color.rgb(52, 153, 76),
                Color.rgb(255, 166, 195),
                Color.rgb(42, 109, 130)};

        set.setColors(colorsArr);

        BarData data=new BarData(set);
        data.setValueFormatter(new ValueFormatter());



        XAxis xAxis = barChart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        barChart.getAxisRight().setEnabled(false);

        barChart.animateXY(1300,1300);

        barChart.setData(data);
        barChart.setFitBars(true);
        barChart.notifyDataSetChanged();
        barChart.refreshDrawableState();
        barChart.invalidate();
    }

    public void drawLineCharts(int month, int year) {

        //xVals SQLite VALUES
        final ArrayList<String> xData = MainActivity.myDb.queryXData(month, year);

//        //yVals CHART VALUES
        ArrayList<Entry>[] group = (ArrayList<Entry>[]) new ArrayList[8]; //array storing vals 0->1' sympthom 1->2' sympthom etc.

        for (int i = 0; i < group.length; i++) {
            group[i]=new ArrayList<Entry>();
        }

        if (xData.isEmpty()) {
            return;
        } else {

            Entry[] lineEntries=new Entry[lineCharts.length];

            //addind BarEtries to Y-values
            for (int i = 0; i < xData.size(); i++) {
                for(int j=0;j<lineCharts.length;j++){

                    lineEntries[j] = new BarEntry(i, new float[]{Float.parseFloat(sympthoms[j].get(i))});
                    group[j].add(lineEntries[j]);
                }
            }

            //xVals CHART VALUES (DATE)
            final ArrayList<String> xVals = new ArrayList<String>();


            //addind BarEtries to X-values

            for (int i = 0; i < MainActivity.myDb.queryXData(month, year).size(); i++) {
//            BarEntry barEntry=new BarEntry(i, MainActivity.myDb.queryYData().get(i));
                xVals.add(xData.get(i));
            }

            LineDataSet[] lineDataSets = new LineDataSet[lineCharts.length]; //ilość linechartsów

            lineDataSets[0]=new LineDataSet(group[0],"Line graph 1");
            lineDataSets[1]=new LineDataSet(group[1],"Line graph 2");
            lineDataSets[2]=new LineDataSet(group[2],"Line graph 3");
            lineDataSets[3]=new LineDataSet(group[3],"Line graph 4");
            lineDataSets[4]=new LineDataSet(group[4],"Line graph 5");
            lineDataSets[5]=new LineDataSet(group[5],"Line graph 6");
            lineDataSets[6]=new LineDataSet(group[6],"Line graph 7");
            lineDataSets[7]=new LineDataSet(group[7],"Line graph 8");

            LineData[] lineDatas=new LineData[lineCharts.length];

            for(int i=0;i<lineCharts.length;i++) {
                lineDataSets[i].setCircleRadius(2f); //dots radius
                lineDataSets[i].setCircleHoleRadius(5f);
                lineDataSets[i].setColor(colorsArr[i]); //lines color
                lineDataSets[i].setCircleColor(colorsArr[i]); //dots color
                lineDataSets[i].setDrawValues(false);
                lineDataSets[i].setFillAlpha(200);  //fill transparency
                lineDataSets[i].setDrawFilled(true); //fill
                lineDataSets[i].setFillColor(colorsArr[i]);//fill color

                lineDatas[i]=new LineData(lineDataSets[i]);

                lineDatas[i].setValueFormatter(new ValueFormatter());

                lineCharts[i].setData(lineDatas[i]);

                lineCharts[i].getXAxis().setValueFormatter(new IndexAxisValueFormatter(xVals));
                lineCharts[i].setBackgroundColor(mBackgroundColor);
                lineCharts[i].notifyDataSetChanged();
                lineCharts[i].refreshDrawableState();
                lineCharts[i].invalidate();

                XAxis xAxis = lineCharts[i].getXAxis();
                xAxis.setGranularity(1f);
                xAxis.setGranularityEnabled(true);
                lineCharts[i].getAxisRight().setEnabled(false);

                lineCharts[i].animateXY(1300,1300);

            }
        }
    }

    public  void setSympthomsData(ArrayList<String>[] sympthoms,int month, int year){

        sympthoms[0] = MainActivity.myDb.querySympthom_1_Data(month, year);
        sympthoms[1] = MainActivity.myDb.querySympthom_2_Data(month, year);
        sympthoms[2] = MainActivity.myDb.querySympthom_3_Data(month, year);
        sympthoms[3] = MainActivity.myDb.querySympthom_4_Data(month, year);
        sympthoms[4] = MainActivity.myDb.querySympthom_5_Data(month, year);
        sympthoms[5] = MainActivity.myDb.querySympthom_6_Data(month, year);
        sympthoms[6] = MainActivity.myDb.querySympthom_7_Data(month, year);
        sympthoms[7] = MainActivity.myDb.querySympthom_8_Data(month, year);

    }

}
