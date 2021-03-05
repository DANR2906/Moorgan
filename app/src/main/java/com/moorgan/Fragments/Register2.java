package com.moorgan.Fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import com.moorgan.R;
import com.moorgan.RegisterActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class Register2 extends Fragment {

    private EditText birthDate;

    private EditText career;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register2, container, false);

        birthDate = view.findViewById(R.id.field_birthdate);
        career = view.findViewById(R.id.field_career);

        birthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });


        return view;
    }


    /**
     *
     * @return
     */
    public boolean sendData(){

        if(birthDate.getText().toString().trim().isEmpty() || career.getText().toString().trim().isEmpty())
            return false;

        else {
            Bundle bundle = new Bundle();

            bundle.putString("birthDate", birthDate.getText().toString().trim());
            bundle.putString("career", career.getText().toString().trim());

            getParentFragmentManager().setFragmentResult("register2", bundle);



            return true;
        }

    }

    /**
     *
     */
    public void showDatePickerDialog() {
        DialogFragment newFragment = new DatePickerFragment(this.birthDate);
        newFragment.show(getParentFragmentManager(), "datePicker");

    }

    /**
     *
     */
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        private EditText field_date;
        public DatePickerFragment(EditText field_date){
            this.field_date = field_date;
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        @NonNull
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            setDate(year, (month+1), day);
        }

        public void setDate(int year,int month,int day){
            String date_aux = day + "/" + month + "/" + year;
            field_date.setText(date_aux);
        }


    }
}