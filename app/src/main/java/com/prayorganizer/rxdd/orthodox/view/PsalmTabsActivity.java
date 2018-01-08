package com.prayorganizer.rxdd.orthodox.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;

import com.prayorganizer.rxdd.orthodox.R;
import com.prayorganizer.rxdd.orthodox.view.HolyActivity;
import com.prayorganizer.rxdd.orthodox.view.fragments.PsalmCSLFragment;
import com.prayorganizer.rxdd.orthodox.view.fragments.PsalmRUFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danbi on 08.01.2018.
 */

public class PsalmTabsActivity extends HolyActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    FrameLayout  mFrameLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout frameLayout = findViewById(R.id.fragment_container);
        View view = getLayoutInflater().inflate(R.layout.psalm_tab_coordinator, frameLayout);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.hide();

        mViewPager = view.findViewById(R.id.psalm_view_pager);
        setupViewPager(mViewPager);
        mTabLayout = view.findViewById(R.id.tabs_psalm_tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);
        
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PsalmRUFragment(), "ONE");
        adapter.addFragment(new PsalmCSLFragment(), "TWO");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    protected int getNavigationId() {
        return R.id.nav_psalms;
    }
}
