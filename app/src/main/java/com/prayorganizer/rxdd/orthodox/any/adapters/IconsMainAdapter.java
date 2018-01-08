package com.prayorganizer.rxdd.orthodox.any.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prayorganizer.rxdd.orthodox.R;
import com.prayorganizer.rxdd.orthodox.content.IconsMain;

import java.util.List;

/**
 * Created by Rexedead on 08.01.2018.
 */

public class IconsMainAdapter extends FilterAdapter<IconsMain, IconsMainAdapter.IconsViewHolder> {

    private IconsMainAdapterItemClick mListener;

    public IconsMainAdapter(List<IconsMain> iconslist) {
        super(iconslist);
    }



    public interface IconsMainAdapterItemClick {
        void onClick(View view, String title);
    }


    static class IconsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
          IconsViewHolderClick mListenerHolder;
        TextView title;
        IconsViewHolder(View view, IconsViewHolderClick listener) {
            super(view);
            view.setOnClickListener(this);

            mListenerHolder = listener;
            title = view.findViewById(R.id.title_img);
        }

        @Override
        public void onClick(View v) {

        }

        public interface IconsViewHolderClick {
            void onClick(View view, String title);
        }
    }

    @Override
    public IconsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.icons_view_single, parent, false);

        return new IconsViewHolder(itemView, new IconsViewHolder.IconsViewHolderClick() {
            @Override
            public void onClick(View view, String title) {
                mListener.onClick(view, title);
            }
        });
    }

    @Override
    public void onBindViewHolder(IconsViewHolder holder, int position) {
        IconsMain iconsMain = mListFiltered.get(position);

    }

    @Override
    protected boolean filter(String str, IconsMain obj) {
        return false;
    }
}
