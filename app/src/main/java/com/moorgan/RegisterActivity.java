package com.moorgan;

import androidx.annotation.NonNull;
import androidx.annotation.StringDef;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.moorgan.Fragments.Register1;
import com.moorgan.Fragments.Register2;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private static final int NUM_PAGES = 2;

    private ViewPager viewPager;

    private PagerAdapter pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        viewPager = findViewById(R.id.view_pager);

        List<Fragment> fragments = new ArrayList<>();
        addFragments(fragments);

        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), fragments);

        viewPager.setAdapter(pagerAdapter);
    }

    /**
     * This method fill the list whit the fragments
     * @param fragments
     */
    private void addFragments(List<Fragment> fragments){

        fragments.add(new Register1());
        fragments.add(new Register2());

    }

    public void goToRightPage(View view){

        int currentPage = viewPager.getCurrentItem();
        
        if(currentPage >= NUM_PAGES-1)
            Toast.makeText(this, getString(R.string.toast_no_more_pages), Toast.LENGTH_SHORT).show();
        else
            viewPager.setCurrentItem(currentPage + 1);
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