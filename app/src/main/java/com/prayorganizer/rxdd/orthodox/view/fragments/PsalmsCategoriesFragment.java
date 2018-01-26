package com.prayorganizer.rxdd.orthodox.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.prayorganizer.rxdd.orthodox.R;
import com.prayorganizer.rxdd.orthodox.any.adapters.FilterAdapter;
import com.prayorganizer.rxdd.orthodox.any.adapters.PsalmsCategoriesAdapter;
import com.prayorganizer.rxdd.orthodox.content.PsalmsCategories;
import com.prayorganizer.rxdd.orthodox.database.HolyModel;

import java.util.List;

/**
 * Created by danbi on 03.01.2018.
 */

public class PsalmsCategoriesFragment extends FilteringListFragment {

    private PsalmsCategoriesAdapter mAdapter;
    private List<PsalmsCategories> mPsalmsCategories;
    private OnClickListener mOnClickListener;

    public interface OnClickListener{
        void onClickPsalmCategories(String title);
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPsalmsCategories = HolyModel.getInstance().getMasterCategoriesOfPsalms();
        mAdapter = new PsalmsCategoriesAdapter(mPsalmsCategories, new PsalmsCategoriesAdapter.PsalmCategoriesAdapterItemClick() {
            @Override
            public void onClick(View view, String title) {
                mOnClickListener.onClickPsalmCategories(title);
            }
        });

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
    protected FilterAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    protected int getIdContainer() {
        return R.layout.recycler_view;
    }

    @Override
    protected int getIdRecyclerView() {
        return R.id.recycler_view;
    }

}
