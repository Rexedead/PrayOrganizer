package com.prayorganizer.rxdd.orthodox.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.prayorganizer.rxdd.orthodox.R;
import com.prayorganizer.rxdd.orthodox.any.MyDividerItemDecoration;
import com.prayorganizer.rxdd.orthodox.any.adapters.PrayAdapter;
import com.prayorganizer.rxdd.orthodox.any.adapters.PsalmAdapter;
import com.prayorganizer.rxdd.orthodox.content.Pray;
import com.prayorganizer.rxdd.orthodox.database.HolyModel;

import java.util.List;

/**
 * Created by danbi on 08.01.2018.
 */

public class PraysFragment extends Fragment {

    private List<Pray> mPrayList;
    private PrayAdapter mPrayAdapter;

    public static final String KEY_SLAVE = "key_slave_categories";

    public static PraysFragment newInstance(String slaveCategories){
        PraysFragment fragment = new PraysFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_SLAVE, slaveCategories);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String slaveCategories =  getArguments().getString(KEY_SLAVE);
        mPrayList = HolyModel.getInstance().getPrays(slaveCategories);
        mPrayAdapter = new PrayAdapter(mPrayList);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pray_single,container, false );
        RecyclerView recyclerView = view.findViewById(R.id.pray_single_list_recycler_view);

        recyclerView.addItemDecoration(new MyDividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL, 16));

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mPrayAdapter);

        return view;
    }
}
