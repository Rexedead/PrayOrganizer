package com.prayorganizer.rxdd.orthodox.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPsalmsCategories = HolyModel.getInstance().getMasterCategoriesOfPsalms();
        mAdapter = new PsalmsCategoriesAdapter(mPsalmsCategories);

    }

    @Override
    protected FilterAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    protected int getIdContainer() {
        return R.layout.fragment_psalms_categories;
    }

    @Override
    protected int getIdRecyclerView() {
        return R.id.psalms_categories_recycler_view;
    }

}
