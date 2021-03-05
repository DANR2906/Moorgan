package com.moorgan.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.moorgan.MainActivity;
import com.moorgan.R;


public class AddClientJobFragment extends Fragment {

    private RelativeLayout relativeLayoutNewJob;
    private RelativeLayout relativeLayoutNewClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_client_job, container, false);

        relativeLayoutNewJob = view.findViewById(R.id.layout_new_job);

        relativeLayoutNewClient = view.findViewById(R.id.layout_new_client);

        relativeLayoutNewJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewJob();
            }
        });

        relativeLayoutNewClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewClient();
            }
        });

        return view;
    }

    /**
     *
     */
    public void goToNewJob(){
        NewJob newJob = new NewJob();
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_layout, newJob);
        transaction.commit();
    }

    /**
     *
     */
    public void goToNewClient(){
        NewClient newClient = new NewClient();
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_layout, newClient);
        transaction.commit();
    }
}