package com.moorgan.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.moorgan.R;

import java.util.ArrayList;
import java.util.List;


public class NewJob extends Fragment {

    private LinearLayout statusList;

    private List<View> statusViewsList;

    private ImageView selectClients;

    private EditText fieldJobName;

    private EditText fieldEndDate;

    private EditText fieldPayment;

    private ImageButton addStatus;

    private Button saveJob;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_new_job, container, false);

        initComponents(view);



        return view;
    }

    /**
     *
     * @param view
     */
    private void initComponents(View view){

        statusList = view.findViewById(R.id.status_list);

        selectClients = view.findViewById(R.id.add_clients);

        fieldJobName = view.findViewById(R.id.field_name);

        fieldEndDate = view.findViewById(R.id.field_end_date);

        fieldPayment = view.findViewById(R.id.field_payment);

        addStatus = view.findViewById(R.id.add_status);

        addStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStatusView(getResources().getString(R.string.job_status_new));
            }
        });

        saveJob = view.findViewById(R.id.save_job);

        saveJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        statusViewsList = new ArrayList<>();

        addStatusView(getResources().getString(R.string.job_status_inicial));
        addStatusView(getResources().getString(R.string.job_status_medium));
        addStatusView(getResources().getString(R.string.job_status_final));

        statusViewsList.get(0).findViewById(R.id.delete_status).setVisibility(View.INVISIBLE);

    }

    /**
     *
     * @param hint
     */
    public void addStatusView(String hint){

        View statusView = getLayoutInflater().inflate(R.layout.status_list_view, null, false);

        EditText statusName = statusView.findViewById(R.id.status_name);
        ImageView deleteStatus = statusView.findViewById(R.id.delete_status);

        statusName.setHint(hint);

        deleteStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statusViewsList.remove(statusView);
                removeStatusView(statusView);
            }
        });

        statusViewsList.add(statusView);
        statusList.addView(statusView);


    }

    /**
     *
     * @param view
     */
    public void removeStatusView(View view){

        this.statusList.removeView(view);

    }


}