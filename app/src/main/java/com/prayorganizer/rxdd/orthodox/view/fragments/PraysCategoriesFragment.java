package com.prayorganizer.rxdd.orthodox.view.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;


import com.prayorganizer.rxdd.orthodox.R;
import com.prayorganizer.rxdd.orthodox.any.MyDividerItemDecoration;
import com.prayorganizer.rxdd.orthodox.any.adapters.FilterAdapter;
import com.prayorganizer.rxdd.orthodox.any.adapters.PraysCategoriesAdapter;
import com.prayorganizer.rxdd.orthodox.content.PraysCategories;
import com.prayorganizer.rxdd.orthodox.database.HolyModel;

import java.util.List;

/**
 * Created by danbi on 03.01.2018.
 */

public class PraysCategoriesFragment extends FilteringListFragment{


    private PraysCategoriesAdapter mAdapter;

    private List<PraysCategories> mPraysCategories;

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
//        setHasOptionsMenu(true);

        mPraysCategories = HolyModel.getInstance().getMasterCategoriesOfPrays();
        mAdapter = new PraysCategoriesAdapter(mPraysCategories);

    }


//
}
