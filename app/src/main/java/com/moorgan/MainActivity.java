package com.moorgan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.moorgan.Adapter.JobAdapter;
import com.moorgan.IRepositories.IClientRepository;
import com.moorgan.Interfaces.BarLayoutController;
import com.moorgan.Model.Client;
import com.moorgan.Model.Job;
import com.moorgan.Repositories.ClientRepository;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener, BarLayoutController {

    //Components
    private TextView clientsCount;


    //Repositories
    private IClientRepository clientRepository;

    private PreferencesController preferencesController;

    private RecyclerView recyclerView;

    private JobAdapter jobAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initComponents();


    }


    /**
     *
     */
    private void initComponents(){

        this.clientRepository = new ClientRepository(this);

        preferencesController = new PreferencesController(this);

        this.clientsCount = findViewById(R.id.clients_count);


        this.clientsCount.setText(String.valueOf(getClientsCount()));


        this.recyclerView = findViewById(R.id.recycler_view);
        //Borrar
        List<Job> jobs = new ArrayList<>();
        for (int x = 0; x < 10; x++) {
            jobs.add(new Job());
            jobs.get(x).setName("Trabajo " + x);
        }
        //Borrar

        this.jobAdapter = new JobAdapter(this, jobs);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.setAdapter(jobAdapter);

    }

    private int getClientsCount(){

        int clients = 0;

        for (Client client : clientRepository.findAll()){
            if(client.getUser() == preferencesController.getCurrentUser())
                clients++;
        }

        return clients;

    }



    /**
     *
     * @param view
     */
    public void showPopup(View view){
        PopupMenu popupMenu = new PopupMenu(this, view);

        popupMenu.setOnMenuItemClickListener(this);

        popupMenu.inflate(R.menu.popup_menu_main);

        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        switch (item.getItemId()){

            case R.id.item1:
                Toast.makeText(this, "Ver clientes", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.item2:
                Toast.makeText(this, "Ajustes", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return false;

        }
    }

    @Override
    public void goToAdd(View view) {
        Intent intent = new Intent(this, AddClientJob.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void goToMain(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void goToWallet(View view) {

    }
}