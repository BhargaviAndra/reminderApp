package com.example.project;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    dataBaseHelper db;
    ArrayList<ObtainData> data = new ArrayList<ObtainData>();
    recycleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        db = new dataBaseHelper(this);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TaskActivity.class);
                startActivity(intent);
            }
        });


        Cursor cursor = db.getTaskData();
        if (cursor.getCount() == 0)
            Toast.makeText(MainActivity.this, "No Tasks to Show", Toast.LENGTH_LONG).show();
        else {
            if (cursor.moveToFirst()) {
                do {
                    ObtainData item = new ObtainData();
                    item.setTask(cursor.getString(1));
                    item.setDate(cursor.getString(2));
                    item.setTime(cursor.getString(3));
                    data.add(item);
                    //to set layout grid,staggered
                    adapter = new recycleAdapter(this, data);
                    recyclerView.setAdapter(adapter);
                } while (cursor.moveToNext());
            }
        }





    }
}







