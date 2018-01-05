package com.prayorganizer.rxdd.orthodox.view;

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

    public static final String EXTRA_KEY_ITEM_ID = "item_id";
    protected int mCurrentItemId;

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

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent argsIntent = getIntent();
        if(argsIntent != null ){
            mCurrentItemId = argsIntent.getIntExtra(EXTRA_KEY_ITEM_ID, R.id.nav_prays);
        }else{
            mCurrentItemId = R.id.nav_prays;
        }
        navigationView.setCheckedItem(mCurrentItemId);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
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



    @SuppressWarnings("StatementWithEmptyBody")
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        log("item id = " + id);

        if(id == mCurrentItemId){
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }

        if (id == R.id.nav_prays) {

            log("R.id.nav_prays = " + id);
            start(MainActivity.class, id);
        } else if (id == R.id.nav_psalms) {
            log("R.id.nav_psalms = " + id);
            start(PsalmActivity.class, id);

        } else if (id == R.id.nav_icons) {
            log("R.id.nav_icons = " + id);

        } else if (id == R.id.nav_pray_of_day) {
            log("R.id.nav_pray_of_day = " + id);

        } else if (id == R.id.nav_recepts) {
            log("R.id.nav_recepts = " + id);

        } else if (id == R.id.nav_favorites) {
            log("R.id.nav_favorites = " + id);

        } else if (id == R.id.nav_map) {
            log("R.id.nav_map = " + id);

        }

        //костыль
        if(mCurrentItemId != R.id.nav_prays){
            finish();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void start(Class clazz, int id){
        Intent intent = new Intent(this , clazz);
        intent.putExtra(EXTRA_KEY_ITEM_ID, id);
        startActivity(intent);

    }

    private void log(String msg){
        Log.i(TAG, msg);
    }
}
