package com.example.moodapp.Graphs_classes;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class AxisValueFormatter extends com.github.mikephil.charting.formatter.IndexAxisValueFormatter implements IAxisValueFormatter {

    private ArrayList<String> mValues=new ArrayList();

    SimpleDateFormat sdf = new SimpleDateFormat("MM.dd");

    public AxisValueFormatter() {
 }


//    @Override
    public String getFormattedValue(String value, AxisBase axis) {
        //Specify the format you'd like
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd", Locale.ENGLISH);
        return sdf.format(value);

    }
}