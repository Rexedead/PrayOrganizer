package com.prayorganizer.rxdd.orthodox.view.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.prayorganizer.rxdd.orthodox.R;
import com.prayorganizer.rxdd.orthodox.any.adapters.FilterAdapter;
import com.prayorganizer.rxdd.orthodox.any.adapters.PraysCategoriesAdapter;
import com.prayorganizer.rxdd.orthodox.content.PraysCategories;
import com.prayorganizer.rxdd.orthodox.database.HolyModel;

import java.util.List;

/**
 * Created by danbi on 03.01.2018.
 */

public class PraysCategoriesFragment extends FilteringListFragment
        implements PraysCategoriesAdapter.PraysCategoriesAdapterItemClick{


    public interface OnClickListener {
        void onClickCategories(String masterCategories);
        void onClickSlaveCategories(String slaveCategories);
    }

    private OnClickListener mOnClickListener;
    private boolean mIsSlaveCategories;



private @Nullable String mMasterCategory;

    private PraysCategoriesAdapter mAdapter;

    private List<PraysCategories> mPraysCategories;

    public static PraysCategoriesFragment newInstance(boolean isSlave){
        return newInstance(isSlave, null);
    }


    public static PraysCategoriesFragment newInstance(boolean isSlave, @Nullable String masterCategory){
        PraysCategoriesFragment fragment = new PraysCategoriesFragment();
        Bundle args = new Bundle();
        args.putBoolean("isSlave", isSlave);
        fragment.setArguments(args);
        if(isSlave){
            args.putString("masterCategory", masterCategory);
        }

        return fragment;
    }

    @Override
    protected FilterAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    protected int getIdContainer() {
        return R.layout.fragment_prays_categories;
    }

    @Override
    protected int getIdRecyclerView() {
        return R.id.prays_categories_recycler_view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();

        if (args != null) {
            mIsSlaveCategories = args.getBoolean("isSlave");
            mMasterCategory = args.getString("masterCategory");
        }
        if(mIsSlaveCategories){
            mPraysCategories = HolyModel.getInstance().getSlaveCategoriesOfPrays(mMasterCategory);
        }else{
            mPraysCategories = HolyModel.getInstance().getMasterCategoriesOfPrays();
        }
        mAdapter = new PraysCategoriesAdapter(mPraysCategories, this);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mOnClickListener = (OnClickListener)context;
        }catch (ClassCastException  e){
            throw new ClassCastException(context.toString() + " must implement PraysCategoriesFragment.OnClickListener");
        }
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onClick(View view, String title) {
        if(!mIsSlaveCategories){
            mOnClickListener.onClickCategories(title);
        }else{
            mOnClickListener.onClickSlaveCategories(title);
        }
    }
}
