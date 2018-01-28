package com.prayorganizer.rxdd.orthodox.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.prayorganizer.rxdd.orthodox.R;
import com.prayorganizer.rxdd.orthodox.any.MyDividerItemDecoration;
import com.prayorganizer.rxdd.orthodox.any.adapters.PsalmAdapter;
import com.prayorganizer.rxdd.orthodox.content.Psalm;
import com.prayorganizer.rxdd.orthodox.database.HolyModel;


/**
 * Created by Rexedead on 08.01.2018.
 */

public class PsalmTabFragment extends Fragment {
    private static final String KEY_PSALM_NAME = "key_psalm_name";
    private static final String KEY_LANG = "key_lang";

    private String mPsalmName;
    private String mLang;
    private Psalm mPsalm;
    private PsalmAdapter mPsalmAdapter;
    private TextView mHeaderTextView;
    private CheckBox mCheckBoxFav;
    private boolean mChecked;

    public static PsalmTabFragment newInstance(String psalmName, String lang){
        PsalmTabFragment fragment = new PsalmTabFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_PSALM_NAME, psalmName.split(" ")[1]);
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
        if(mLang.equals("ru")){
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
        View view = inflater.inflate(R.layout.psalm_single, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.psalm_lines_recycler_view);
        recyclerView.addItemDecoration(new MyDividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL, 0));
        mHeaderTextView = view.findViewById(R.id.psalm_header);

        if(mLang.equals("ru")){
            mHeaderTextView.setText(mPsalm.getHeadRU());
        }else{
            mHeaderTextView.setText(mPsalm.getHeadCSL());
        }

        final String mPsalmFavTable = "psalt_fav";
        final String mPID = getArguments().getString(KEY_PSALM_NAME);
        mChecked = HolyModel.getInstance().checkFav(mPID,mPsalmFavTable);
        mCheckBoxFav = view.findViewById(R.id.psalm_fav_img);
        mCheckBoxFav.setChecked(mChecked);
        mCheckBoxFav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked){
                    HolyModel.getInstance().removeFav(mPID,mPsalmFavTable);
                    Toast.makeText(getContext(), "Удалено из избранного", Toast.LENGTH_SHORT).show();
                }else {HolyModel.getInstance().setFav(mPID,mPsalmFavTable);
                Toast.makeText(getContext(), "Добавлено в избранное", Toast.LENGTH_SHORT).show();}
            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        recyclerView.setAdapter(mPsalmAdapter);
        return view;
    }

}
