package com.example.moodapp.Graphs_classes;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AxisValueFormatter implements IAxisValueFormatter {

    private String[] mValues;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd:hh:mm:ss");

    public AxisValueFormatter(String[] values) {
        this.mValues = values; }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return sdf.format(new Date((long) value));
    }
}