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

    public String name;
    public String lastName;
    public String career;
    public String birthDate;

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

                setBirthDate(result.getString("birthDate"));
                setCareer(result.getString("career"));

                tvBirthDate.setText(getBirthDate());
                tvCareer.setText(getCareer());

            }
        });


        getParentFragmentManager().setFragmentResultListener("register1", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                setName(result.getString("name"));
                setLastName(result.getString("lastName"));

                tvName.setText(getName() + " " + getLastName());

            }
        });




        return view;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}