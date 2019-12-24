package com.example.moodapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moodapp.Graphs_classes.ValueFormatter;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
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

    Button viewAllResultsButton;
    BarChart chart;
    List<String> months = new ArrayList<>();
    ScrollChoice scrollMonths;
    static TextView textView;
    LocalDate today = LocalDate.now();
    TextView textYear;
    ImageButton buttonRight;
    ImageButton buttonLeft;
    int currentMonth = today.getMonthValue();
    int currentYear = today.getYear();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        viewAllResultsButton=(Button) findViewById(R.id.viewAllResultsButton);
        chart=(BarChart) findViewById(R.id.barChart);
        scrollMonths=(ScrollChoice) findViewById(R.id.scroll_choice_months);
        textView=(TextView)findViewById(R.id.textView2);
        textYear=(TextView)findViewById(R.id.textYear);
        buttonLeft=(ImageButton)findViewById(R.id.left_nav);
        buttonRight=(ImageButton)findViewById(R.id.right_nav);


        MainActivity.myDb.addTestData();
        addDataToGraph(currentMonth,currentYear);

        textYear.setText(Integer.toString(currentYear));

        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentYear=currentYear-1;
                textYear.setText(Integer.toString(currentYear));
                chart.clear();
                addDataToGraph(currentMonth,currentYear);
            }
        });

        // Images right navigatin
        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentYear=currentYear+1;
                textYear.setText(Integer.toString(currentYear));
                chart.clear();
                addDataToGraph(currentMonth,currentYear);
            }
        });




        loadMonths();
        //zmień na aktualne===========================================================
        scrollMonths.addItems(months,currentMonth-1); //index starts with zero
        scrollMonths.setOnItemSelectedListener(new ScrollChoice.OnItemSelectedListener() {
            @Override
            public void onItemSelected(ScrollChoice scrollChoice, int position, String name) {
                int intPosition=position+1;
                currentMonth=intPosition;
                chart.clear();
                addDataToGraph(intPosition,currentYear);
                textView.setText(String.valueOf(intPosition));
            }
        });



//        addDataToGraph(currentMonth,currentYear);


//        chart.setVisibleXRangeMaximum(7);

        viewAllResultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), ViewAllResultsActivity.class);
                startActivity(startIntent);
            }
        });
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

    public void addDataToGraph(int month,int year) {

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
            chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xVals));
//        barChart.getYAxis().setValueFormatter(new com.example.moodapp.Graphs_classes.ValueFormatter());


            chart.setData(data);
            chart.setFitBars(true);
            chart.notifyDataSetChanged();
            chart.refreshDrawableState();
            chart.invalidate();

        }
    }
}
