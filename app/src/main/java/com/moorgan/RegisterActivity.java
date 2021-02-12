package com.moorgan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringDef;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.moorgan.Adapter.JobAdapter;
import com.moorgan.Fragments.Register1;
import com.moorgan.Fragments.Register2;
import com.moorgan.Fragments.Register3;
import com.moorgan.IRepositories.IWalletRepository;
import com.moorgan.Model.Wallet;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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

        //Revisar -------------------------------------------------------------------------------
        if(currentPage == NUM_PAGES-1){

            Intent intent = new Intent(this, MainActivity.class);

            startActivity(intent);
            /*
            IWalletRepository walletRepository = new WalletRepository(this);
            walletRepository.insert(1,100);
            Wallet w = walletRepository.findByID(1);

            if(w != null) {
                String s = w.getId() + " / " + w.getBalance();
                Toast.makeText(this, "" + s, Toast.LENGTH_SHORT).show();
            }

            Toast.makeText(this, "Total: " + walletRepository.findAll().size(), Toast.LENGTH_SHORT).show();


             */
        } else if(currentPage > NUM_PAGES-1)
            Toast.makeText(this, getString(R.string.toast_no_more_pages), Toast.LENGTH_SHORT).show();
        else {
            viewPager.setCurrentItem(currentPage + 1);

            if((currentPage + 1 ) == 1)
                ((Register1) fragments.get(0)).sendData();

            if((currentPage + 1 ) == 2)
                ((Register2) fragments.get(1)).sendData();

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