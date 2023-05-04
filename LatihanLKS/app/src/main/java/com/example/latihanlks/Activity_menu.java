package com.example.latihanlks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.latihanlks.adapter.customAdapter;
import com.example.latihanlks.backgtoundTasks.BackgroundTask;

public class Activity_menu extends AppCompatActivity {

    String[] listdata = {"data 1", "data 2", "data 3", "data 4", "data 5", "data 6", "data 7"};
    ListView listView;

    Button btnTampil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnTampil = (Button) findViewById(R.id.btnBayarSekarng);
        listView = findViewById(R.id.listviewbarang);
        customAdapter adapter = new customAdapter(getApplicationContext(), listdata);
        listView.setAdapter(adapter);

        btnTampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackgroundTask backgroundTask = new BackgroundTask(getApplicationContext());
                backgroundTask.execute("databarang");
            }
        });
    }
}