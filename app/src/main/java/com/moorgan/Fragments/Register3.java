package com.moorgan.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moorgan.R;

import org.w3c.dom.Text;

public class Register3 extends Fragment {

    public TextView tvName;
    public TextView tvBirthDate;
    public TextView tvCareer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register3, container, false);

        tvName = view.findViewById(R.id.name);
        tvBirthDate = view.findViewById(R.id.birthdate);
        tvCareer = view.findViewById(R.id.career);

        getParentFragmentManager().setFragmentResultListener("register2", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String name = result.getString("name");
                String lastName = result.getString("lastName");
                String birthDate = result.getString("birthDate");
                String career = result.getString("career");

                tvName.setText(name + " " + lastName);
                tvBirthDate.setText(birthDate);
                tvCareer.setText(career);
            }
        });

        return view;
    }
}