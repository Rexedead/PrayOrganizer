package com.prayorganizer.rxdd.orthodox.view.fragments;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.prayorganizer.rxdd.orthodox.R;

/**
 * Created by Rexedead on 14.01.2018.
 *
 */

public class FavoritePrayFragment extends Fragment {

    public static FavoritePrayFragment newInstance() {
        return new FavoritePrayFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        
        return inflater.inflate(R.layout.recycler_view, container, false);
    }
}