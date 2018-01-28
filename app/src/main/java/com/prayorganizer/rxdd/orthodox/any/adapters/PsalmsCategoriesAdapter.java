package com.prayorganizer.rxdd.orthodox.any.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.prayorganizer.rxdd.orthodox.R;
import com.prayorganizer.rxdd.orthodox.content.PsalmsCategories;

import java.util.List;

/**
 * Created by danbi on 03.01.2018.
 * todo grid view instead of list
 */

public class PsalmsCategoriesAdapter extends FilterAdapter<PsalmsCategories, PsalmsCategoriesAdapter.PsalmsViewHolder> {
    private PsalmCategoriesAdapterItemClick mListener;
    public interface PsalmCategoriesAdapterItemClick {
        void onClick(View view, String title);
    }

    public PsalmsCategoriesAdapter(List<PsalmsCategories> praysList, PsalmCategoriesAdapterItemClick listener) {
        super(praysList);
        mListener = listener;

    }

    @Override
    protected boolean filter(String str, PsalmsCategories obj) {
        return obj.getTitle().toLowerCase().contains(str.toLowerCase());
    }

    @Override
    public PsalmsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.psalms_categories_row, parent, false);
        return new PsalmsViewHolder(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(PsalmsViewHolder holder, int position) {
        PsalmsCategories psalmsCategories = mListFiltered.get(position);
        holder.title.setText(psalmsCategories.getTitle());
    }

    static class PsalmsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title;
        PsalmCategoriesAdapterItemClick mListener;

        public PsalmsViewHolder(View itemView, PsalmCategoriesAdapterItemClick listener) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = itemView.findViewById(R.id.title_psalm);
            mListener = listener;
        }


        @Override
        public void onClick(View v) {
            mListener.onClick(v, title.getText().toString());
        }
    }
}
