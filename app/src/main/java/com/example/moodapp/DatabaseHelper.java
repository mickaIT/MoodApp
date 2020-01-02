package com.example.moodapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="MoodApp.db";
    public static final String TABLE_NAME="MyMoods_table";
    public static final String ID="ID";
    public static final String DATE="DATE";
    public static final String SYMPTHOM_1="SYMPTHOM_1";
    public static final String SYMPTHOM_2="SYMPTHOM_2";
    public static final String SYMPTHOM_3="SYMPTHOM_3";
    public static final String SYMPTHOM_4="SYMPTHOM_4";
    public static final String SYMPTHOM_5="SYMPTHOM_5";
    public static final String SYMPTHOM_6="SYMPTHOM_6";
    public static final String SYMPTHOM_7="SYMPTHOM_7";
    public static final String SYMPTHOM_8="SYMPTHOM_8";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    //(datetime('now','localtime'))
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" " +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "DATE DATETIME DEFAULT CURRENT_TIMESTAMP," +
                "SYMPTHOM_1 INTEGER," +
                "SYMPTHOM_2 INTEGER," +
                "SYMPTHOM_3 INTEGER," +
                "SYMPTHOM_4 INTEGER," +
                "SYMPTHOM_5 INTEGER," +
                "SYMPTHOM_6 INTEGER," +
                "SYMPTHOM_7 INTEGER," +
                "SYMPTHOM_8 INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData( Integer symptom_1, Integer symptom_2, Integer symptom_3, Integer symptom_4,
                               Integer symptom_5, Integer symptom_6, Integer symptom_7, Integer symptom_8){

        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, dateFormat.format(date));
        contentValues.put(SYMPTHOM_1,symptom_1);
        contentValues.put(SYMPTHOM_2,symptom_2);
        contentValues.put(SYMPTHOM_3,symptom_3);
        contentValues.put(SYMPTHOM_4,symptom_4);
        contentValues.put(SYMPTHOM_5,symptom_5);
        contentValues.put(SYMPTHOM_6,symptom_6);
        contentValues.put(SYMPTHOM_7,symptom_7);
        contentValues.put(SYMPTHOM_8,symptom_8);
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


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addTestData(){
        SQLiteDatabase db=this.getWritableDatabase();
        String query="INSERT INTO "+TABLE_NAME+ " (DATE, SYMPTHOM_1, SYMPTHOM_2, SYMPTHOM_3, SYMPTHOM_4, SYMPTHOM_5, SYMPTHOM_6, SYMPTHOM_7, SYMPTHOM_8)" + " VALUES (NULL, 2019/11/10,5,3,-4,3,3,-4,2)";

        Cursor cursor=db.rawQuery(query,null);
        cursor.close();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar myCalendar = new GregorianCalendar(2019, 1, 11) {
        };
        Date date = myCalendar.getTime();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, dateFormat.format(date));
        contentValues.put(SYMPTHOM_1,5);
        contentValues.put(SYMPTHOM_2,-5);
        contentValues.put(SYMPTHOM_3,4);
        contentValues.put(SYMPTHOM_4,-3);
        contentValues.put(SYMPTHOM_5,4);
        contentValues.put(SYMPTHOM_6,2);
        contentValues.put(SYMPTHOM_7,7);
        contentValues.put(SYMPTHOM_8,2);
        db.insert(TABLE_NAME,null,contentValues);
    }

    public ArrayList<String> queryXData(int month, int year){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> xData= new ArrayList<>();

//        String sMonth="0"+month;

        String sMonth = String.valueOf(month).length()==1 ? "0"+month : String.valueOf(month);

        String query="SELECT DATE FROM MyMoods_table WHERE\n" +
                " strftime('%Y-%m', DATE)='"+year+"-"+sMonth+"'";

//
//                "SELECT "+ "DATE" +" FROM "+TABLE_NAME+" WHERE"+
//                " strftime('%Y-%m', DATE)='"+(year)+"-"+(month)+"'";

        Cursor cursor=db.rawQuery(query,null);

        while(cursor.moveToNext()){

             xData.add(cursor.getString(0));
        }

        cursor.close();
        return xData;
    }

    public ArrayList<String> querySympthom_1_Data(int month, int year){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> yData=new ArrayList<String>();
//
        String sMonth = String.valueOf(month).length()==1 ? "0"+month : String.valueOf(month);

        String query="SELECT "+SYMPTHOM_1+" FROM "+TABLE_NAME+" WHERE"
                + " strftime('%Y-%m', DATE)='"+year+"-"+sMonth+"'";

//                " strftime('%m', DATE)='"+dupa+
//                "' AND strftime('%Y', DATE)='"+(year)+"'";

                //+" WHERE "+SYMPTHOM_1+" IS NOT NULL";
        Cursor cursor=db.rawQuery(query,null);

        while(cursor.moveToNext()){
            yData.add(cursor.getString(0));

        }
        cursor.close();
        return yData;
    }

    public ArrayList<String> querySympthom_2_Data(int month, int year){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> yData=new ArrayList<String>();
//
        String sMonth = String.valueOf(month).length()==1 ? "0"+month : String.valueOf(month);

        String query="SELECT "+SYMPTHOM_2+" FROM "+TABLE_NAME+" WHERE"
                + " strftime('%Y-%m', DATE)='"+year+"-"+sMonth+"'";

        //+" WHERE "+SYMPTHOM_1+" IS NOT NULL";
        Cursor cursor=db.rawQuery(query,null);

        while(cursor.moveToNext()){
            yData.add(cursor.getString(0));


        }
        cursor.close();
        return yData;
    }

    public ArrayList<String> querySympthom_3_Data(int month, int year){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> yData=new ArrayList<String>();
//
        String sMonth = String.valueOf(month).length()==1 ? "0"+month : String.valueOf(month);

        String query="SELECT "+SYMPTHOM_3+" FROM "+TABLE_NAME+" WHERE"
                + " strftime('%Y-%m', DATE)='"+year+"-"+sMonth+"'";
        //+" WHERE "+SYMPTHOM_1+" IS NOT NULL";
        Cursor cursor=db.rawQuery(query,null);

        while(cursor.moveToNext()){
            yData.add(cursor.getString(0));

        }
        cursor.close();
        return yData;
    }

    public ArrayList<String> querySympthom_4_Data(int month, int year){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> yData=new ArrayList<String>();
//
        String sMonth = String.valueOf(month).length()==1 ? "0"+month : String.valueOf(month);

        String query="SELECT "+SYMPTHOM_4+" FROM "+TABLE_NAME+" WHERE"
                + " strftime('%Y-%m', DATE)='"+year+"-"+sMonth+"'";
        //+" WHERE "+SYMPTHOM_1+" IS NOT NULL";
        Cursor cursor=db.rawQuery(query,null);

        while(cursor.moveToNext()){
            yData.add(cursor.getString(0));
        }

        cursor.close();
        return yData;
    }

    public ArrayList<String> querySympthom_5_Data(int month, int year){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> yData=new ArrayList<String>();
//
        String sMonth = String.valueOf(month).length()==1 ? "0"+month : String.valueOf(month);

        String query="SELECT "+SYMPTHOM_5+" FROM "+TABLE_NAME+" WHERE"
                + " strftime('%Y-%m', DATE)='"+year+"-"+sMonth+"'";
        //+" WHERE "+SYMPTHOM_1+" IS NOT NULL";
        Cursor cursor=db.rawQuery(query,null);

        while(cursor.moveToNext()){
            yData.add(cursor.getString(0));

        }
        cursor.close();
        return yData;
    }

    public ArrayList<String> querySympthom_6_Data(int month, int year){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> yData=new ArrayList<String>();
//
        String sMonth = String.valueOf(month).length()==1 ? "0"+month : String.valueOf(month);

        String query="SELECT "+SYMPTHOM_6+" FROM "+TABLE_NAME+" WHERE"
                + " strftime('%Y-%m', DATE)='"+year+"-"+sMonth+"'";
        //+" WHERE "+SYMPTHOM_1+" IS NOT NULL";
        Cursor cursor=db.rawQuery(query,null);

        while(cursor.moveToNext()){
            yData.add(cursor.getString(0));
        }
        cursor.close();
        return yData;
    }

    public ArrayList<String> querySympthom_7_Data(int month, int year){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> yData=new ArrayList<String>();
//
        String sMonth = String.valueOf(month).length()==1 ? "0"+month : String.valueOf(month);

        String query="SELECT "+SYMPTHOM_7+" FROM "+TABLE_NAME+" WHERE"
                + " strftime('%Y-%m', DATE)='"+year+"-"+sMonth+"'";
        //+" WHERE "+SYMPTHOM_1+" IS NOT NULL";
        Cursor cursor=db.rawQuery(query,null);

        while(cursor.moveToNext()){
            yData.add(cursor.getString(0));


        }
        cursor.close();
        return yData;
    }

    public ArrayList<String> querySympthom_8_Data(int month, int year){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> yData=new ArrayList<String>();
//
        String sMonth = String.valueOf(month).length()==1 ? "0"+month : String.valueOf(month);

        String query="SELECT "+SYMPTHOM_8+" FROM "+TABLE_NAME+" WHERE"
                + " strftime('%Y-%m', DATE)='"+year+"-"+sMonth+"'";
        Cursor cursor=db.rawQuery(query,null);


        while(cursor.moveToNext()){
            yData.add(cursor.getString(0));


        }
        cursor.close();
        return yData;
    }

    public ArrayList<Float> querySumData(int month, int year){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Float> yData=new ArrayList<Float>();

        String sMonth = String.valueOf(month).length()==1 ? "0"+month : String.valueOf(month);

        String query="SELECT "+SYMPTHOM_1+" FROM "+TABLE_NAME+" WHERE"
                + " strftime('%Y-%m', DATE)='"+year+"-"+sMonth+"'";

        Cursor cursor=db.rawQuery(query,null);

        while(cursor.moveToNext()){
            yData.add(cursor.getFloat(0));

        }
        cursor.close();
        return yData;
    }




}