package com.prayorganizer.rxdd.orthodox.view;

import android.content.Context;
import android.content.Intent;
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
import com.prayorganizer.rxdd.orthodox.view.fragments.PsalmTabFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danbi on 08.01.2018.
 */

public class PsalmTabsActivity extends HolyActivity {

    private static final String EXTRA_KEY_PSALM_NAME = "extra_key_psalm_name";

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    FrameLayout  mFrameLayout;
    private String mPsalmName;

    public static Intent newIntent(String psalmName, Context context){
        Intent intent = new Intent(context, PsalmTabsActivity.class);

        return intent.putExtra(EXTRA_KEY_PSALM_NAME, psalmName);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPsalmName =  getIntent().getStringExtra(EXTRA_KEY_PSALM_NAME);
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
        adapter.addFragment(PsalmTabFragment.newInstance(mPsalmName, "ru"), "кек");
        adapter.addFragment(PsalmTabFragment.newInstance(mPsalmName, "cls"), "мда");
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
