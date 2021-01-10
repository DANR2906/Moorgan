package com.moorgan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.moorgan.Adapter.JobAdapter;
import com.moorgan.model.Job;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private JobAdapter jobAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Prueba
        recyclerView = findViewById(R.id.recycler_view);
        List<Job> jobs = new ArrayList<>();
        jobs.add(new Job());
        jobs.add(new Job());

        jobAdapter = new JobAdapter(this, jobs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(jobAdapter);

    }
}