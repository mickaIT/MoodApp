package com.example.moodapp.Graphs_classes;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

public class ValueFormatter extends com.github.mikephil.charting.formatter.ValueFormatter implements IValueFormatter {

    private DecimalFormat mFormat;

    public ValueFormatter(){
        mFormat=new DecimalFormat("######.0");
    }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return mFormat.format(value)+"$";
    }
}
