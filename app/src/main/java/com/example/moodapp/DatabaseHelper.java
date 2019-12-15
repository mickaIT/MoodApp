package com.example.moodapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="MoodApp.db";
    public static final String TABLE_NAME="MyMoods_table";
    public static final String ID="ID";
    public static final String DATE="DATE";
    public static final String SYMPTHOM_1="TALKATIVENESS";
    public static final String SYMPTHOM_2="INSOMNIA";
    public static final String SYMPTHOM_3="FLIGHT_OFI_IDEAS";
    public static final String SYMPTHOM_4="TIREDNESS";
    public static final String SYMPTHOM_5="HYPERACTIVITY";
    public static final String SYMPTHOM_6="IRRITABILITY";
    public static final String SYMPTHOM_7="MEGALOMANIA";
    public static final String SYMPTHOM_8="POOR_DECISIONS";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    //(datetime('now','localtime'))
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" " +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "DATE DATETIME DEFAULT CURRENT_TIMESTAMP," +
                "TALKATIVENESS INTEGER," +
                "INSOMNIA INTEGER," +
                "FLIGHT_OFI_IDEAS INTEGER," +
                "TIREDNESS INTEGER," +
                "HYPERACTIVITY INTEGER," +
                "IRRITABILITY INTEGER," +
                "MEGALOMANIA INTEGER," +
                "POOR_DECISIONS INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData( Integer talkativeness, Integer insomnia, Integer flightOfIdeas, Integer tiredness,
                               Integer hyperactivity, Integer irritability, Integer megalomania, Integer poorDecisions){

        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
//        ContentValues initialValues = new ContentValues();
//        long rowId = db.insert(TABLE_NAME, null, initialValues);
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, dateFormat.format(date));
        contentValues.put(SYMPTHOM_1,talkativeness);
        contentValues.put(SYMPTHOM_2,insomnia);
        contentValues.put(SYMPTHOM_3,flightOfIdeas);
        contentValues.put(SYMPTHOM_4,tiredness);
        contentValues.put(SYMPTHOM_5,hyperactivity);
        contentValues.put(SYMPTHOM_6,irritability);
        contentValues.put(SYMPTHOM_7,megalomania);
        contentValues.put(SYMPTHOM_8,poorDecisions);
        long result=db.insert(TABLE_NAME,null,contentValues);

        if (result==-1){
            return false;
        }
        else return true;

    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return res;
    }

    public boolean isDbEmpty() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = null;
        try {
            c = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
            if (c == null || c.getCount() == 0) {
                return true;
            } else if (c.moveToFirst()) {
                Log.d(TAG,"isDbEmpty: not empty");
                return false;
            }
        } catch (SQLiteException e) {
            Log.d(TAG, "isDbEmpty: doesn't exist");
            return true;
        }finally {
            if(c != null){
                c.close();
            }
        }
        return true;
    }

//    public ArrayList<BarEntry> queryXData(){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ArrayList<BarEntry> xData= new ArrayList<>();
//
//        String query="SELECT "+DATE+" FROM "+TABLE_NAME+ " GROUP BY "+DATE;
//        Cursor cursor=db.rawQuery(query,null);
//
//        for(cursor.moveToFirst();cursor.moveToLast();cursor.moveToNext()){
//
//            //?
////            xData.add((Date) cursor);
//        }
//        cursor.close();
//        return xData;
//    }

    public ArrayList<String> queryXData(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> xData= new ArrayList<>();

        String query="SELECT "+DATE+" FROM "+TABLE_NAME;
                //+ " GROUP BY "+DATE;
        Cursor cursor=db.rawQuery(query,null);
//        for(cursor.moveToFirst();cursor.moveToLast();cursor.moveToNext()){


        while(cursor.moveToNext()){

             xData.add(cursor.getString(0));
        }
        cursor.close();
        return xData;
    }

    public ArrayList<String> querySympthom_1_Data(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> yData=new ArrayList<String>();
//
        String query="SELECT "+SYMPTHOM_1+" FROM "+TABLE_NAME;
                //+" WHERE "+SYMPTHOM_1+" IS NOT NULL";
        Cursor cursor=db.rawQuery(query,null);

//        for(cursor.moveToFirst();cursor.moveToLast();cursor.moveToNext()){

        while(cursor.moveToNext()){
            yData.add(cursor.getString(0));
//            talkativenessData.add(cursor.getInt(0));
//            talkativenessData.add(0);

        }
        cursor.close();
        return yData;
    }

    public ArrayList<String> querySympthom_2_Data(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> yData=new ArrayList<String>();
//
        String query="SELECT "+SYMPTHOM_2+" FROM "+TABLE_NAME;
        //+" WHERE "+SYMPTHOM_1+" IS NOT NULL";
        Cursor cursor=db.rawQuery(query,null);

//        for(cursor.moveToFirst();cursor.moveToLast();cursor.moveToNext()){

        while(cursor.moveToNext()){
            yData.add(cursor.getString(0));
//            talkativenessData.add(cursor.getInt(0));
//            talkativenessData.add(0);

        }
        cursor.close();
        return yData;
    }

    public ArrayList<String> querySympthom_3_Data(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> yData=new ArrayList<String>();
//
        String query="SELECT "+SYMPTHOM_3+" FROM "+TABLE_NAME;
        //+" WHERE "+SYMPTHOM_1+" IS NOT NULL";
        Cursor cursor=db.rawQuery(query,null);

//        for(cursor.moveToFirst();cursor.moveToLast();cursor.moveToNext()){

        while(cursor.moveToNext()){
            yData.add(cursor.getString(0));
//            talkativenessData.add(cursor.getInt(0));
//            talkativenessData.add(0);

        }
        cursor.close();
        return yData;
    }

    public ArrayList<String> querySympthom_4_Data(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> yData=new ArrayList<String>();
//
        String query="SELECT "+SYMPTHOM_4+" FROM "+TABLE_NAME;
        //+" WHERE "+SYMPTHOM_1+" IS NOT NULL";
        Cursor cursor=db.rawQuery(query,null);

//        for(cursor.moveToFirst();cursor.moveToLast();cursor.moveToNext()){

        while(cursor.moveToNext()){
            yData.add(cursor.getString(0));
//            talkativenessData.add(cursor.getInt(0));
//            talkativenessData.add(0);

        }
        cursor.close();
        return yData;
    }

    public ArrayList<String> querySympthom_5_Data(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> yData=new ArrayList<String>();
//
        String query="SELECT "+SYMPTHOM_5+" FROM "+TABLE_NAME;
        //+" WHERE "+SYMPTHOM_1+" IS NOT NULL";
        Cursor cursor=db.rawQuery(query,null);

//        for(cursor.moveToFirst();cursor.moveToLast();cursor.moveToNext()){

        while(cursor.moveToNext()){
            yData.add(cursor.getString(0));
//            talkativenessData.add(cursor.getInt(0));
//            talkativenessData.add(0);

        }
        cursor.close();
        return yData;
    }

    public ArrayList<String> querySympthom_6_Data(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> yData=new ArrayList<String>();
//
        String query="SELECT "+SYMPTHOM_6+" FROM "+TABLE_NAME;
        //+" WHERE "+SYMPTHOM_1+" IS NOT NULL";
        Cursor cursor=db.rawQuery(query,null);

//        for(cursor.moveToFirst();cursor.moveToLast();cursor.moveToNext()){

        while(cursor.moveToNext()){
            yData.add(cursor.getString(0));
//            talkativenessData.add(cursor.getInt(0));
//            talkativenessData.add(0);

        }
        cursor.close();
        return yData;
    }

    public ArrayList<String> querySympthom_7_Data(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> yData=new ArrayList<String>();
//
        String query="SELECT "+SYMPTHOM_7+" FROM "+TABLE_NAME;
        //+" WHERE "+SYMPTHOM_1+" IS NOT NULL";
        Cursor cursor=db.rawQuery(query,null);

//        for(cursor.moveToFirst();cursor.moveToLast();cursor.moveToNext()){

        while(cursor.moveToNext()){
            yData.add(cursor.getString(0));
//            talkativenessData.add(cursor.getInt(0));
//            talkativenessData.add(0);

        }
        cursor.close();
        return yData;
    }

    public ArrayList<String> querySympthom_8_Data(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> yData=new ArrayList<String>();
//
        String query="SELECT "+SYMPTHOM_8+" FROM "+TABLE_NAME;
        //+" WHERE "+SYMPTHOM_1+" IS NOT NULL";
        Cursor cursor=db.rawQuery(query,null);

//        for(cursor.moveToFirst();cursor.moveToLast();cursor.moveToNext()){

        while(cursor.moveToNext()){
            yData.add(cursor.getString(0));
//            talkativenessData.add(cursor.getInt(0));
//            talkativenessData.add(0);

        }
        cursor.close();
        return yData;
    }



}