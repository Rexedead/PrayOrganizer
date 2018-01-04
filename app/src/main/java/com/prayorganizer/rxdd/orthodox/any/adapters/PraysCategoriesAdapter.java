package com.prayorganizer.rxdd.orthodox.any.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.prayorganizer.rxdd.orthodox.R;
import com.prayorganizer.rxdd.orthodox.content.PraysCategories;

import java.util.ArrayList;
import java.util.List;

public class PraysCategoriesAdapter extends FilterAdapter<PraysCategories, PraysCategoriesAdapter.PraysViewHolder> {

    class PraysViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        PraysViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);

        }
    }

    @Override
    public PraysViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.categories_list_row, parent, false);

        return new PraysViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PraysViewHolder holder, int position) {
        PraysCategories praysCategories = mListFiltered.get(position);
            holder.title.setText(praysCategories.getTitle());
    }



    public PraysCategoriesAdapter(List<PraysCategories> praysCategoriesList){
        super(praysCategoriesList);
    }

    @Override
    protected boolean filter(String str, PraysCategories obj) {
        return obj.getTitle().toLowerCase().contains(str.toLowerCase());
    }


}




