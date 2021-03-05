package com.moorgan.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.moorgan.IRepositories.IClientRepository;
import com.moorgan.PreferencesController;
import com.moorgan.R;
import com.moorgan.Repositories.ClientRepository;

public class NewClient extends Fragment {

    private EditText fieldName;

    private EditText fieldPhoneNumber;

    private Button buttonSave;

    private IClientRepository clientRepository;

    private PreferencesController preferencesController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_new_client, container, false);

        initComponents(view);



        return view;
    }


    private void initComponents(View view){

        clientRepository = new ClientRepository(getActivity());

        preferencesController = new PreferencesController(getActivity());

        fieldName = view.findViewById(R.id.field_name);
        fieldPhoneNumber = view.findViewById(R.id.field_phone_number);
        buttonSave = view.findViewById(R.id.save_client);


        saveClient();
    }

    private void saveClient(){

        this.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = fieldName.getText().toString().trim();
                String phoneNumber = fieldPhoneNumber.getText().toString().trim();

                if(name.isEmpty() || phoneNumber.isEmpty())
                    Toast.makeText(getActivity(), getResources().getString(R.string.empty_field), Toast.LENGTH_SHORT).show();
                else if(!isNumeric(phoneNumber) || phoneNumber.length() < 5)
                    Toast.makeText(getActivity(), getResources().getString(R.string.string_is_not_numeric), Toast.LENGTH_SHORT).show();
                else{

                    if( !clientRepository.insert(name, phoneNumber, preferencesController.getCurrentUser()) )
                        Toast.makeText(getActivity(), getResources().getString(R.string.error_client_add), Toast.LENGTH_SHORT).show();
                    else{
                        goToAddClientJobFragment();
                        Toast.makeText(getActivity(), getResources().getString(R.string.successful_client_add), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });





    }

    /**
     *
     */
    public void goToAddClientJobFragment(){
        AddClientJobFragment addClientJobFragment = new AddClientJobFragment();
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_layout, addClientJobFragment);
        transaction.commit();
    }

    /**
     *
     * @param str
     * @return
     */
    public boolean isNumeric(String str) {

        boolean result;

        try {
            Long.parseLong(str);
            result = true;
        } catch (NumberFormatException excepcion) {
            result = false;
        }

        return result;
    }

}