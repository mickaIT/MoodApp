package com.example.moodapp.Graphs_classes;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AxisValueFormatter extends com.github.mikephil.charting.formatter.IndexAxisValueFormatter implements IAxisValueFormatter {

    private ArrayList<String> mValues=new ArrayList();

    SimpleDateFormat sdf = new SimpleDateFormat("MM.dd");

    public AxisValueFormatter() {
 }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM:dd");
        return sdf.format(mValues);
    }
}