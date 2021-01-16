package com.moorgan.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.moorgan.R;

public class Register1 extends Fragment {

    public EditText name;
    public EditText lastName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_register1, container, false);

        name = view.findViewById(R.id.field_name);
        lastName = view.findViewById(R.id.field_last_name);

        return view;
    }

    /**
     *
     */
    public void sendData(){

        Bundle bundle = new Bundle();
        bundle.putString("name", name.getText().toString().trim());
        bundle.putString("lastName", lastName.getText().toString().trim());
        getParentFragmentManager().setFragmentResult("register1", bundle);

    }
}