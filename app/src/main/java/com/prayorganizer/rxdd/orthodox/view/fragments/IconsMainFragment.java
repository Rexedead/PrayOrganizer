package com.prayorganizer.rxdd.orthodox.view.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prayorganizer.rxdd.orthodox.R;
import com.prayorganizer.rxdd.orthodox.any.GridSpacingItemDecoration;
import com.prayorganizer.rxdd.orthodox.any.adapters.IconsMainAdapter;
import com.prayorganizer.rxdd.orthodox.content.IconsMain;
import com.prayorganizer.rxdd.orthodox.database.HolyModel;

import java.util.List;

/**
 * Created by Rexedead on 08.01.2018.
 * todo открывать иконы по клику
 */

public class IconsMainFragment extends FilteringListFragment {
    private IconsMainAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        List<IconsMain> iconsMain = HolyModel.getInstance().getIconsData();
        mAdapter = new IconsMainAdapter(iconsMain);

    }

    @Override
    protected IconsMainAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    protected int getIdContainer() {
        return R.layout.recycler_view;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view,container, false );
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    protected int getIdRecyclerView() {
        System.out.println(R.id.recycler_view);
        return R.id.recycler_view;
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
