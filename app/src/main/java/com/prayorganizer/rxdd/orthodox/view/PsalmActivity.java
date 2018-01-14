package com.prayorganizer.rxdd.orthodox.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.prayorganizer.rxdd.orthodox.R;
import com.prayorganizer.rxdd.orthodox.view.fragments.PsalmsCategoriesFragment;

/**
 * Created by danbi on 05.01.2018.
 */


public class PsalmActivity extends HolyActivity implements PsalmsCategoriesFragment.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            init();
        }

    }

    @Override
    protected int getNavigationId() {
        return R.id.nav_psalms;
    }

    private void init(){
        FragmentManager fm = getSupportFragmentManager();
        PsalmsCategoriesFragment fragment = new PsalmsCategoriesFragment();
        fm.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit();

    }


    private void showPsalm(String psalmCategories){
        Intent intent = PsalmTabsActivity.newIntent(psalmCategories, this);
        startActivity(intent);
    }


    @Override
    public void onClickPsalmCategories(String title) {
        showPsalm(title);
    }
}
