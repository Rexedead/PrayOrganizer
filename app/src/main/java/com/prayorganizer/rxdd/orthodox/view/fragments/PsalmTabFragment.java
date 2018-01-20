package com.prayorganizer.rxdd.orthodox.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.prayorganizer.rxdd.orthodox.R;
import com.prayorganizer.rxdd.orthodox.any.MyDividerItemDecoration;
import com.prayorganizer.rxdd.orthodox.any.adapters.PsalmAdapter;
import com.prayorganizer.rxdd.orthodox.content.Psalm;
import com.prayorganizer.rxdd.orthodox.database.HolyModel;

/**
 * Created by Rexedead on 08.01.2018.
 *
 */

public class PsalmTabFragment extends Fragment {
    private static final String KEY_PSALM_NAME = "key_psalm_name";
    private static final String KEY_LANG = "key_lang";

    private String mPsalmName;
    private String mLang;
    private Psalm mPsalm;
    private PsalmAdapter mPsalmAdapter;

    public static PsalmTabFragment newInstance(String psalmName, String lang){
        PsalmTabFragment fragment = new PsalmTabFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_PSALM_NAME, psalmName);
        bundle.putString(KEY_LANG, lang);
        fragment.setArguments(bundle);
        return fragment;
    }

    public PsalmTabFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLang = getArguments().getString(KEY_LANG);
        mPsalmName = getArguments().getString(KEY_PSALM_NAME);
        mPsalm = HolyModel.getInstance().getSinglePsalm(mPsalmName);
        String[] psalmLines;
        if(mPsalmName.equals("ru")){
            psalmLines = mPsalm.getLinesRU();
        }else{
            psalmLines = mPsalm.getLinesCSL();
        }

        mPsalmAdapter = new PsalmAdapter(psalmLines);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_psalm_single_tag, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.psalm_lines_recycler_view);
        recyclerView.addItemDecoration(new MyDividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL, 16));

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        recyclerView.setAdapter(mPsalmAdapter);
        return view;
    }

}
