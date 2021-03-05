package com.moorgan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringDef;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.moorgan.Adapter.JobAdapter;
import com.moorgan.Fragments.Register1;
import com.moorgan.Fragments.Register2;
import com.moorgan.Fragments.Register3;
import com.moorgan.IRepositories.IUserRepository;
import com.moorgan.IRepositories.IWalletRepository;
import com.moorgan.Model.Wallet;
import com.moorgan.Repositories.UserRepository;
import com.moorgan.Repositories.WalletRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {

    private static final int NUM_PAGES = 3;

    private SwipeDisableViewPager viewPager;

    private PagerAdapter pagerAdapter;

    private List<Fragment> fragments;

    private PreferencesController preferencesController;

    //Repositories
    private IUserRepository userRepository;

    private IWalletRepository walletRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initComponents();

    }

    /**
     *
     */
    private void initComponents(){

        userRepository = new UserRepository(this);
        walletRepository = new WalletRepository(this);

        preferencesController = new PreferencesController(this);

        viewPager = findViewById(R.id.view_pager);

        fragments = new ArrayList<>();

        addFragments(fragments);

        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), fragments);

        viewPager.setAdapter(pagerAdapter);
        viewPager.setEnabled(false);

    }



    /**
     * This method fill the list whit the fragments
     * @param fragments
     */
    private void addFragments(List<Fragment> fragments){

        fragments.add(new Register1());
        fragments.add(new Register2());
        fragments.add(new Register3());

    }



    public void goToRightPage(View view){

        int currentPage = viewPager.getCurrentItem();

        if(currentPage == NUM_PAGES-1){

            preferencesController.uploadRegiterInformation();

            String name = ((Register3) fragments.get(2)).getName();
            String lastName = ((Register3) fragments.get(2)).getLastName();
            String birthDate = ((Register3) fragments.get(2)).getBirthDate();
            String career = ((Register3) fragments.get(2)).getCareer();

            if(preferencesController.getCurrentUser() == 0){
                //Create first user

                if(walletRepository.insert(1, 0) &&
                        userRepository.insert(name, lastName, birthDate, career,1))
                    preferencesController.setCurrentUser("1");


            }else{

                int currentUserID = preferencesController.getCurrentUser();

                currentUserID += 1;

                if(walletRepository.insert(currentUserID, 0) &&
                        userRepository.insert(name, lastName, birthDate, career,currentUserID))
                    preferencesController.setCurrentUser(String.valueOf(currentUserID));

            }



            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();


        } else if(currentPage > NUM_PAGES-1)
            Toast.makeText(this, getString(R.string.toast_no_more_pages), Toast.LENGTH_SHORT).show();
        else {


            if((currentPage + 1 ) == 1)
                if(((Register1) fragments.get(0)).sendData())
                    viewPager.setCurrentItem(currentPage + 1);
                else
                    Toast.makeText(this, getResources().getString(R.string.empty_field), Toast.LENGTH_SHORT).show();

            if((currentPage + 1 ) == 2)
                if(((Register2) fragments.get(1)).sendData())
                    viewPager.setCurrentItem(currentPage + 1);
                else
                    Toast.makeText(this, getResources().getString(R.string.empty_field), Toast.LENGTH_SHORT).show();

        }


    }

    public void goToLeftPage(View view){

        int currentPage = viewPager.getCurrentItem();

        if(currentPage <= 0)
            Toast.makeText(this, getString(R.string.toast_no_more_pages), Toast.LENGTH_SHORT).show();
        else
            viewPager.setCurrentItem(currentPage - 1);

    }


    /**
     * Class PagerAdapter
     */
    private class PagerAdapter extends FragmentStatePagerAdapter {

        List<Fragment> fragments;

        public PagerAdapter(FragmentManager supportFragmentManager, List<Fragment> fragments) {
            super(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

            this.fragments = fragments;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }


}