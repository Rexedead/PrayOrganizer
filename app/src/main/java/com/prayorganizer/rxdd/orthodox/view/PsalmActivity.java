package com.prayorganizer.rxdd.orthodox.view;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.prayorganizer.rxdd.orthodox.R;
import com.prayorganizer.rxdd.orthodox.view.fragments.PraysCategoriesFragment;
import com.prayorganizer.rxdd.orthodox.view.fragments.PsalmTabsFragment;
import com.prayorganizer.rxdd.orthodox.view.fragments.PsalmsCategoriesFragment;

/**
 * Created by danbi on 05.01.2018.
 */


public class PsalmActivity extends HolyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            init();
        }

    }

    private void init(){
        FragmentManager fm = getSupportFragmentManager();
        PsalmsCategoriesFragment fragment = new PsalmsCategoriesFragment();
        fm.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit();

    }



}
