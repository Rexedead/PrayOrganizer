package com.prayorganizer.rxdd.orthodox.any.adapters;

import android.support.v7.widget.RecyclerView;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danbi on 03.01.2018.
 */

public abstract class FilterAdapter<T, THolder extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<THolder> implements Filterable {
    protected List<T> mList;
    protected List<T> mListFiltered;

    public FilterAdapter(List<T> praysList) {
        this.mList = praysList;
        this.mListFiltered = praysList;
    }

    @Override
    public int getItemCount() {
        return mListFiltered.size();
    }

    protected abstract boolean filter(String str, T obj);

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mListFiltered = mList;
                } else {
                    List<T> filteredList = new ArrayList<>();
                    for (T row : mList) {

                        if (FilterAdapter.this.filter(charString, row)) {
                            filteredList.add(row);
                        }
                    }

                    mListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mListFiltered = (ArrayList<T>) filterResults.values;
                notifyDataSetChanged();
            }
        };
}
}
