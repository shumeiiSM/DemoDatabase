package com.example.a17010233.demodatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnGetTasks;
    TextView tvResults;

    ListView lv;
    ArrayList<Task> al;
    ArrayAdapter aa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);

        lv = findViewById(R.id.lv);
        al = new ArrayList<Task>();


        DBHelper db = new DBHelper(MainActivity.this);

        // Insert a task
        ArrayList<Task> allData = db.getTasks();
        db.close();

        for (int i = 0; i < allData.size(); i++) {
            Task task1 = new Task(allData.get(i).getId(), allData.get(i).getDescription(), allData.get(i).getDate());
            al.add(task1);
            aa = new CustomAdapter(this, R.layout.row, al);
            lv.setAdapter(aa);
        }


        // DBHelper db = new DBHelper(this);
        // db.getWritableDatabase();
        // db.close();

        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                db.insertTask("Submit RJ", "25 Apr 2016");
                db.close();
            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<String> data = db.getTaskContent();
                db.close();


                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i +". "+data.get(i));
                    txt += i + ". " + data.get(i) + "\n";




                }
                tvResults.setText(txt);
            }
        });


    }
}
