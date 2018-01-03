package com.prayorganizer.rxdd.orthodox.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.prayorganizer.rxdd.orthodox.R;
import com.prayorganizer.rxdd.orthodox.content.PsalmsCategories;

import java.util.ArrayList;
import java.util.List;

public class PsalmsCategoriesAdapter extends RecyclerView.Adapter<PsalmsCategoriesAdapter.MyViewHolder> implements Filterable {

    private List<PsalmsCategories> mPsalmsCats;
    private List<PsalmsCategories> mPsalmsCatsFiltered;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mCategoryName;

        MyViewHolder(View view) {
            super(view);
            mCategoryName = view.findViewById(R.id.title);

        }
    }


    PsalmsCategoriesAdapter(List<PsalmsCategories> psalmsCats) {
        this.mPsalmsCats = psalmsCats;
        this.mPsalmsCatsFiltered = psalmsCats;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.categories_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PsalmsCategories pslist = mPsalmsCatsFiltered.get(position);
        holder.mCategoryName.setText(pslist.getTitle());
    }

    @Override
    public int getItemCount() {
        return mPsalmsCatsFiltered.size();
    }




    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mPsalmsCatsFiltered = mPsalmsCats;
                } else {
                    List<PsalmsCategories> filteredList = new ArrayList<>();
                    for (PsalmsCategories row : mPsalmsCats) {


                        if (row.getTitle().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                            System.out.println(row.getTitle());
                        }
                    }

                    mPsalmsCatsFiltered = filteredList;
                    System.out.println(mPsalmsCatsFiltered.size());
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mPsalmsCatsFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mPsalmsCatsFiltered = (ArrayList<PsalmsCategories>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }









}