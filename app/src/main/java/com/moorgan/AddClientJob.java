package com.moorgan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.moorgan.Fragments.AddClientJobFragment;
import com.moorgan.Interfaces.BarLayoutController;

public class AddClientJob extends AppCompatActivity implements BarLayoutController {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client_job);

        AddClientJobFragment addClientJobFragment = new AddClientJobFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().add(R.id.fragment_layout, addClientJobFragment).commit();
    }


    @Override
    public void goToAdd(View view){
        Intent intent = new Intent(this, AddClientJob.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void goToMain(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void goToWallet(View view){

        Toast.makeText(this, "Wallet", Toast.LENGTH_SHORT).show();

    }
}