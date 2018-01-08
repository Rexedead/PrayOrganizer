package com.prayorganizer.rxdd.orthodox.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.prayorganizer.rxdd.orthodox.R;
import com.prayorganizer.rxdd.orthodox.settings.Settings;
import com.prayorganizer.rxdd.orthodox.view.main.MainActivity;

/**
 * Created by danbi on 04.01.2018.
 */

public abstract class HolyActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "HolyActivity";

//    public static final String EXTRA_KEY_ITEM_ID = "item_id";
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);

        
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

    }

    protected abstract int getNavigationId();

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

//            if(this.getClass() == MainActivity.class){
//                log("onBackPressed()" + "MainActivity");
//            }
//
//            if(this.getClass() == PsalmTabsActivity.class){
//                log("onBackPressed() " + "PsalmTabsActivity" );
//            }

            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent settingsActivity = new Intent(getBaseContext(), Settings.class);
            startActivity(settingsActivity);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mNavigationView.setCheckedItem(getNavigationId());

    }

    @SuppressWarnings("StatementWithEmptyBody")
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        log("item id = " + id);



        if (id == R.id.nav_prays) {

            log("R.id.nav_prays = " + id);
            start(MainActivity.class, id);
        } else if (id == R.id.nav_psalms) {
            log("R.id.nav_psalms = " + id);
            start(PsalmActivity.class, id);

        } else if (id == R.id.nav_icons) {
            log("R.id.nav_icons = " + id);
            start(IconActivity.class, id);
        } else if (id == R.id.nav_pray_of_day) {
            log("R.id.nav_pray_of_day = " + id);

        } else if (id == R.id.nav_recepts) {
            log("R.id.nav_recepts = " + id);

        } else if (id == R.id.nav_favorites) {
            log("R.id.nav_favorites = " + id);

        } else if (id == R.id.nav_map) {
            log("R.id.nav_map = " + id);

        }

        if(this.getClass() != MainActivity.class){
            finish();
        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void start(Class clazz, int id){
        Intent intent = new Intent(this , clazz);
//        intent.putExtra(EXTRA_KEY_ITEM_ID, id);

        startActivity(intent);
    }

    private void log(String msg){
        Log.i(TAG, msg);
    }

}
