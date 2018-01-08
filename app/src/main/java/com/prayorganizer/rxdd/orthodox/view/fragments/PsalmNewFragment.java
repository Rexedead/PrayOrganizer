package com.prayorganizer.rxdd.orthodox.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.prayorganizer.rxdd.orthodox.R;
/**
 * Created by Rexedead on 08.01.2018.
 *
 */

public class PsalmNewFragment extends Fragment {

    public PsalmNewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_psalm_single_new, container, false);
    }

}
