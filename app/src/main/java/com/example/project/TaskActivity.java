package com.example.project;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class TaskActivity extends AppCompatActivity {
private EditText task;
private TextView date,time;
private Button save;
dataBaseHelper db;
Calendar c;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        save=findViewById(R.id.save);
        task=findViewById(R.id.task);
        date=findViewById(R.id.date);
        time=findViewById(R.id.time);
        db =new dataBaseHelper(this);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();

            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           showTimePickerDialog();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(task.getText().toString().equals(""))
                    task.setError("Please Enter Task");
                        else
                        {
                        String Date=date.getText().toString();
                            String Task=task.getText().toString();
                            String Time=time.getText().toString();
                            db.InsertData(Task,Date,Time);
                            Toast.makeText(TaskActivity.this,"Task Created Successfully",Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(TaskActivity.this,MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);

                        }
            }
        });
    }
private void showDatePickerDialog()
{
    Calendar c=Calendar.getInstance();
    int day=c.get(Calendar.DAY_OF_MONTH);
    int month=c.get(Calendar.MONTH);
    int year=c.get(Calendar.YEAR);
    DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            String dateValue=day+"/"+(month+1)+"/"+year;
            date.setText(dateValue);
        }
    }, year, month, day);
    datePickerDialog.show();
}
private void showTimePickerDialog()
{
    Calendar c=Calendar.getInstance();
    int hour=c.get(Calendar.HOUR_OF_DAY);
    int minute=c.get(Calendar.MINUTE);
    TimePickerDialog timePickerDialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
       if(minute<10)
       {
           String timeValue=hourOfDay+":"+"0"+minute;
           time.setText(timeValue);
       }
       else
       {
           String timeValue=hourOfDay+":"+minute;
           time.setText(timeValue);
       }

        }
    },hour,minute,true );
timePickerDialog.show();
}


}


