package com.moorgan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.moorgan.Adapter.JobAdapter;
import com.moorgan.Model.Job;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private JobAdapter jobAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recycler_view);
        List<Job> jobs = new ArrayList<>();
        for (int x = 0; x < 20; x++)
            jobs.add(new Job());


        jobAdapter = new JobAdapter(this, jobs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(jobAdapter);



    }

    /**
     *
     * @param view
     */
    public void goToAdd(View view){
        Toast.makeText(this, "Añadir", Toast.LENGTH_SHORT).show();
    }

    /**
     *
     * @param view
     */
    public void goToMain(View view){

        Toast.makeText(this, "Main", Toast.LENGTH_SHORT).show();
    }

    /**
     *
     * @param view
     */
    public void goToWallet(View view){

        Toast.makeText(this, "Wallet", Toast.LENGTH_SHORT).show();

    }
}