package com.prayorganizer.rxdd.orthodox.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.prayorganizer.rxdd.orthodox.R;
import com.prayorganizer.rxdd.orthodox.any.adapters.IconsMainAdapter;
import com.prayorganizer.rxdd.orthodox.content.IconsMain;
import com.prayorganizer.rxdd.orthodox.database.HolyModel;

import java.util.List;

/**
 * Created by Rexedead on 08.01.2018.
 */

public class IconsMainFragment extends FilteringListFragment {
    private IconsMainAdapter mAdapter;
    private List<IconsMain> mIconsMain;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mIconsMain = HolyModel.getInstance().getIconsData();
        mAdapter = new IconsMainAdapter(mIconsMain);

    }

    @Override
    protected IconsMainAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    protected int getIdContainer() {
        return R.layout.icons_recycle;
    }

    @Override
    protected int getIdRecyclerView() {
        return R.id.icons_recycle;
    }
}
