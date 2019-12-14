package com.example.moodapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = new Date();
//        ContentValues initialValues = new ContentValues();
//        initialValues.put("date_created", dateFormat.format(date));
//        long rowId = db.insert(TABLE_NAME, null, initialValues);
        ContentValues contentValues = new ContentValues();
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
}