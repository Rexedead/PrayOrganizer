package com.prayorganizer.rxdd.orthodox.view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import com.prayorganizer.rxdd.orthodox.R;
import com.prayorganizer.rxdd.orthodox.view.fragments.IconsMainFragment;

/**
 * Created by Rexedead on 08.01.2018.
 */

public class IconActivity extends HolyActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            init();
        }

    }

    @Override
    protected int getNavigationId() {
        return R.id.nav_icons;
    }

    private void init(){
        FragmentManager fm = getSupportFragmentManager();
        IconsMainFragment fragment = new IconsMainFragment();
        fm.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit();

    }
}
