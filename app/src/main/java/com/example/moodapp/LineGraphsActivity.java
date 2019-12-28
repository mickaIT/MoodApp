package com.example.moodapp;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moodapp.Graphs_classes.ValueFormatter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.time.LocalDate;
import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)
public class LineGraphsActivity extends AppCompatActivity {

    private LineChart[] lineCharts = new LineChart[8]; //ilość linechartsów

    LocalDate today = LocalDate.now();
    int currentMonth = today.getMonthValue();
    int currentYear = today.getYear();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_graphs);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        lineCharts[0] = (LineChart) findViewById(R.id.lineChart1);
        lineCharts[1] = (LineChart) findViewById(R.id.lineChart2);
        lineCharts[2] = (LineChart) findViewById(R.id.lineChart3);
        lineCharts[3] = (LineChart) findViewById(R.id.lineChart4);
        lineCharts[4] = (LineChart) findViewById(R.id.lineChart5);
        lineCharts[5] = (LineChart) findViewById(R.id.lineChart6);
        lineCharts[6] = (LineChart) findViewById(R.id.lineChart7);
        lineCharts[7] = (LineChart) findViewById(R.id.lineChart8);

drawLineGraphs(currentMonth,currentYear);
    }

    public void drawLineGraphs(int month, int year) {
        //yVals CHART VALUES
        final ArrayList<Entry> y_vals_1 = new ArrayList<Entry>();
        final ArrayList<Entry> y_vals_2 = new ArrayList<Entry>();
        final ArrayList<Entry> y_vals_3 = new ArrayList<Entry>();
        final ArrayList<Entry> y_vals_4 = new ArrayList<Entry>();
        final ArrayList<Entry> y_vals_5 = new ArrayList<Entry>();
        final ArrayList<Entry> y_vals_6 = new ArrayList<Entry>();
        final ArrayList<Entry> y_vals_7 = new ArrayList<Entry>();
        final ArrayList<Entry> y_vals_8 = new ArrayList<Entry>();


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
                Entry line_entry_1 = new BarEntry(i, new float[]{Float.parseFloat(sympthom_1_data.get(i))});
                Entry line_entry_2 = new BarEntry(i, new float[]{Float.parseFloat(sympthom_2_data.get(i))});
                Entry line_entry_3 = new BarEntry(i, new float[]{Float.parseFloat(sympthom_3_data.get(i))});
                Entry line_entry_4 = new BarEntry(i, new float[]{Float.parseFloat(sympthom_4_data.get(i))});
                Entry line_entry_5 = new BarEntry(i, new float[]{Float.parseFloat(sympthom_5_data.get(i))});
                Entry line_entry_6 = new BarEntry(i, new float[]{Float.parseFloat(sympthom_6_data.get(i))});
                Entry line_entry_7 = new BarEntry(i, new float[]{Float.parseFloat(sympthom_7_data.get(i))});
                Entry line_entry_8 = new BarEntry(i, new float[]{Float.parseFloat(sympthom_8_data.get(i))});

//                        ,
//                        Float.parseFloat(sympthom_2_data.get(i)),
//                        Float.parseFloat(sympthom_3_data.get(i)),
//                        Float.parseFloat(sympthom_4_data.get(i)),
//                        Float.parseFloat(sympthom_5_data.get(i)),
//                        Float.parseFloat(sympthom_6_data.get(i)),
//                        Float.parseFloat(sympthom_7_data.get(i)),
//                        Float.parseFloat(sympthom_8_data.get(i))

                y_vals_1.add(line_entry_1);
                y_vals_2.add(line_entry_2);
                y_vals_3.add(line_entry_3);
                y_vals_4.add(line_entry_4);
                y_vals_5.add(line_entry_5);
                y_vals_6.add(line_entry_6);
                y_vals_7.add(line_entry_7);
                y_vals_8.add(line_entry_8);

            }

            //xVals CHART VALUES (DATE)
            final ArrayList<String> xVals = new ArrayList<String>();


            //addind BarEtries to X-values

            for (int i = 0; i < MainActivity.myDb.queryXData(month, year).size(); i++) {
//            BarEntry barEntry=new BarEntry(i, MainActivity.myDb.queryYData().get(i));
                xVals.add(xData.get(i));
            }

            LineDataSet lineDataSet_1 =new LineDataSet(y_vals_1,"Line graph 1");
            LineDataSet lineDataSet_2 =new LineDataSet(y_vals_2,"Line graph 2");
            LineDataSet lineDataSet_3 =new LineDataSet(y_vals_3,"Line graph 3");
            LineDataSet lineDataSet_4 =new LineDataSet(y_vals_4,"Line graph 4");
            LineDataSet lineDataSet_5 =new LineDataSet(y_vals_5,"Line graph 5");
            LineDataSet lineDataSet_6 =new LineDataSet(y_vals_6,"Line graph 6");
            LineDataSet lineDataSet_7 =new LineDataSet(y_vals_7,"Line graph 7");
            LineDataSet lineDataSet_8 =new LineDataSet(y_vals_8,"Line graph 8");
//            lineDataSet_1.setColors(ColorTemplate.JOYFUL_COLORS);
//            lineDataSet_2.setColors(ColorTemplate.JOYFUL_COLORS);

            lineDataSet_1.setCircleRadius(2f); //dots
            lineDataSet_1.setCircleHoleRadius(5f);
            lineDataSet_1.setColor(Color.LTGRAY);
            lineDataSet_1.setCircleColor(Color.WHITE);
            lineDataSet_1.setDrawValues(false);
            lineDataSet_1.setFillAlpha(150);  //fill transparency
            lineDataSet_1.setDrawFilled(true); //fill
            lineDataSet_1.setFillColor(Color.GRAY); //fill color


            LineData lineData_1=new LineData(lineDataSet_1);
            LineData lineData_2=new LineData(lineDataSet_2);
            LineData lineData_3=new LineData(lineDataSet_3);
            LineData lineData_4=new LineData(lineDataSet_4);
            LineData lineData_5=new LineData(lineDataSet_5);
            LineData lineData_6=new LineData(lineDataSet_6);
            LineData lineData_7=new LineData(lineDataSet_7);
            LineData lineData_8=new LineData(lineDataSet_8);

            lineData_1.setValueFormatter(new ValueFormatter());
            lineData_2.setValueFormatter(new ValueFormatter());
            lineData_3.setValueFormatter(new ValueFormatter());
            lineData_4.setValueFormatter(new ValueFormatter());
            lineData_5.setValueFormatter(new ValueFormatter());
            lineData_6.setValueFormatter(new ValueFormatter());
            lineData_7.setValueFormatter(new ValueFormatter());
            lineData_8.setValueFormatter(new ValueFormatter());

            lineCharts[0].setData(lineData_1);
            lineCharts[1].setData(lineData_2);
            lineCharts[2].setData(lineData_3);
            lineCharts[3].setData(lineData_4);
            lineCharts[4].setData(lineData_5);
            lineCharts[5].setData(lineData_6);
            lineCharts[6].setData(lineData_7);
            lineCharts[7].setData(lineData_8);

            for (int i=0;i<lineCharts.length;i++) {
                lineCharts[i].getXAxis().setValueFormatter(new IndexAxisValueFormatter(xVals));
                lineCharts[i].setBackgroundColor(Color.BLUE);
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
}
