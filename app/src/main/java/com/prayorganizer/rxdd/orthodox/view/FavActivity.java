package com.prayorganizer.rxdd.orthodox.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.prayorganizer.rxdd.orthodox.R;
import com.prayorganizer.rxdd.orthodox.view.fragments.FavoriteIconsFragment;
import com.prayorganizer.rxdd.orthodox.view.fragments.FavoritePrayFragment;
import com.prayorganizer.rxdd.orthodox.view.fragments.FavoritePsalmFragment;

/**
 * Created by Rexedead on 14.01.2018.
 *
 */

public class FavActivity extends HolyActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = findViewById(R.id.fragment_container);
        getLayoutInflater().inflate(R.layout.fav_main, frameLayout);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.hide();



        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_fav);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_pray:
                                selectedFragment = FavoritePrayFragment.newInstance();
                                break;
                            case R.id.action_psalm:
                                selectedFragment = FavoritePsalmFragment.newInstance();
                                break;
                            case R.id.action_icon:
                                selectedFragment = FavoriteIconsFragment.newInstance();
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.fav_frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fav_frame_layout, FavoritePrayFragment.newInstance());
        transaction.commit();
    }

    @Override
    protected int getNavigationId() {
        return R.id.nav_favorites;
    }




    //Used to select an item programmatically
    //bottomNavigationView.getMenu().getItem(2).setChecked(true);



}
