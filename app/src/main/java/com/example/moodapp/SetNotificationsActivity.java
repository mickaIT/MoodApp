package com.example.moodapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.DialogFragment;

import com.example.moodapp.notifications.AlertReceiver;
import com.example.moodapp.notifications.NotificationHelper;
import com.example.moodapp.notifications.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;

public class SetNotificationsActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{

    private Button timePickerBtn;
    private Button buttonCancel;
    private TextView textView;
    private EditText editTitle;
    private EditText editMessage;
    private Button channelBtn;
    private NotificationHelper notificationHelper;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_notifications);

        editTitle=(EditText)findViewById(R.id.editTitleTxt);
        editMessage=(EditText)findViewById(R.id.editMessageTxt);

        timePickerBtn=(Button) findViewById(R.id.buttonTimePicker);
        buttonCancel=(Button)findViewById(R.id.buttonCancel);
        channelBtn=(Button)findViewById(R.id.channelBtn);
        textView=(TextView)findViewById(R.id.textView3);

        notificationHelper=new NotificationHelper(this);

        timePickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker=new TimePicker();
                timePicker.show(getSupportFragmentManager(),"time picker");

            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelNotification();
            }
        });

        channelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOnChannel(editTitle.getText().toString(),editMessage.getText().toString());
            }
        });
    }

    public void sendOnChannel(String title, String message){
        NotificationCompat.Builder nb=notificationHelper.getChannelNotification();
        notificationHelper.getManager().notify(1,nb.build());

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onTimeSet(android.widget.TimePicker timePicker, int hour, int minute) {
        Calendar c=Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY,hour);
        c.set(Calendar.MINUTE,minute);
        c.set(Calendar.SECOND,0);

        updateTimeText(c);
        startAlarm(c);

        textView=(TextView) findViewById(R.id.textView3);
    textView.setText("H: "+hour+"M :"+minute);}

    private void updateTimeText(Calendar c){
        String timeTxt="Alarm set: ";
        timeTxt+= DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime()); //SHORT - we only take minutes,
        // format(c) - we format Calendar to DateFormat
        textView.setText(timeTxt);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void startAlarm(Calendar c){
        AlarmManager alarmManager=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent=new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(this,1,intent,0);

        alarmManager.setExact(AlarmManager.RTC,c.getTimeInMillis(),pendingIntent);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void cancelNotification() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
        alarmManager.cancel(pendingIntent);
        textView.setText("No alarm");
    }

}
