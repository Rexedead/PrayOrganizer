package com.prayorganizer.rxdd.orthodox.view.main;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.prayorganizer.rxdd.orthodox.R;
import com.prayorganizer.rxdd.orthodox.view.HolyActivity;
import com.prayorganizer.rxdd.orthodox.view.fragments.PraysCategoriesFragment;


//TODO Psalm
public class MainActivity extends HolyActivity implements PraysCategoriesFragment.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            init();
        }

    }

    private void init(){
        FragmentManager fm = getSupportFragmentManager();
        PraysCategoriesFragment fragment = PraysCategoriesFragment.newInstance(false);
        fm.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit();

    }


    private void showPrayCategories(){
        PraysCategoriesFragment fragment = PraysCategoriesFragment.newInstance(false);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    private void showPraySlaveCategories(String masterCategories){
        PraysCategoriesFragment fragment = PraysCategoriesFragment.newInstance(true, masterCategories);


        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

//OnClickListener interface PraysCategoriesFragment
//    @Override
//    public void onClickCategories(String masterCategories) {
//
//    }
//
//    @Override
//    public void onClickSlaveCategories(String slaveCategories) {
//
//    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onClickCategories(String masterCategories) {
        showPraySlaveCategories(masterCategories);
//        Log.i("lolkeklol", masterCategories);
    }

    @Override
    public void onClickSlaveCategories(String slaveCategories) {

    }

    //    private void showPsalmsCategories(){
//        PsalmsCategoriesFragment fragment = new PsalmsCategoriesFragment();
//        FragmentManager fm = getSupportFragmentManager();
//        fm.beginTransaction()
//                .replace(R.id.fragment_container, fragment)
//                .commit();
//
//
//    }




}
