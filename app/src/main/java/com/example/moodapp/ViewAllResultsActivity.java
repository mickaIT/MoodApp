package com.example.moodapp;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ViewAllResultsActivity extends AppCompatActivity {

    TextView textView;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_results);
        linearLayout = findViewById(R.id.linearLayout);
        textView=(TextView) findViewById(R.id.textView);
        viewAll();

    }
    public void viewAll(){
//                        textView.setText();
                        Cursor res= MainActivity.myDb.getAllData();
                        if (MainActivity.myDb.isDbEmpty()) {

                            //res.getColumnCount()==0){0
                            //show message

                            showMessage("Error", "Nothing found");
                            return;
                        }
                        else {
                            StringBuffer buffer=new StringBuffer();
                            while(res.moveToNext()) {
                                buffer.append("\nId: " + res.getString(0)+"\n\n");
                                buffer.append("Date :" + res.getString(1)+"\n\n");
                                buffer.append("Talkativeness :" + res.getString(2)+"\n");
                                buffer.append("Insomnia :" + res.getString(3)+"\n");
                                buffer.append("Flight of ideas :" + res.getString(4)+"\n");
                                buffer.append("Tiredness :" + res.getString(5)+"\n");
                                buffer.append("Hyperactivity :" + res.getString(6)+"\n");
                                buffer.append("Irritability :" + res.getString(7)+"\n");
                                buffer.append("Megalomania :" + res.getString(8)+"\n");
                                buffer.append("PoorDecisions :" + res.getString(9)+"\n");
                            }

                            //Show all data
                            textView.setText(buffer.toString());

                            showMessage("Data",buffer.toString());
                        }
    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);

    }
}
