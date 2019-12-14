package com.example.moodapp.Graphs_classes;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DecimalFormat;

public class AxisValueFormatter implements IAxisValueFormatter {

    private DecimalFormat mFormat;

    public AxisValueFormatter(){
        mFormat=new DecimalFormat("######.0");
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return mFormat.format(value)+"$";
    }
}
