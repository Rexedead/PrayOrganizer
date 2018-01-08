package com.prayorganizer.rxdd.orthodox.any.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prayorganizer.rxdd.orthodox.R;
import com.prayorganizer.rxdd.orthodox.content.PsalmsCategories;

import java.util.List;

/**
 * Created by danbi on 03.01.2018.
 */

public class PsalmsCategoriesAdapter extends FilterAdapter<PsalmsCategories, PsalmsCategoriesAdapter.PsalmsViewHolder> {

    public PsalmsCategoriesAdapter(List<PsalmsCategories> praysList) {
        super(praysList);

    }

    @Override
    protected boolean filter(String str, PsalmsCategories obj) {
        return obj.getTitle().toLowerCase().contains(str.toLowerCase());
    }

    @Override
    public PsalmsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.categories_psalms_row, parent, false);
        return new PsalmsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PsalmsViewHolder holder, int position) {
        PsalmsCategories psalmsCategories = mListFiltered.get(position);
        holder.title.setText(psalmsCategories.getTitle());
    }

    class PsalmsViewHolder extends RecyclerView.ViewHolder{
        TextView title;

        public PsalmsViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_psalm);
        }

    }
}
