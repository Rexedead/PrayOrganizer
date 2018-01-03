package com.prayorganizer.rxdd.orthodox.view;

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

public class PraysCategoriesAdapter extends RecyclerView.Adapter<PraysCategoriesAdapter.MyViewHolder> implements Filterable {

    private List<PraysCategories> praysList;
    private List<PraysCategories> praysListFiltered;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, genre;

        MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            genre = view.findViewById(R.id.desc);


        }
    }


    PraysCategoriesAdapter(List<PraysCategories> praysList) {
        this.praysList = praysList;
        this.praysListFiltered = praysList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.prays_categories_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PraysCategories movie = praysListFiltered.get(position);
        holder.title.setText(movie.getTitle());
        holder.genre.setText(movie.getDesc());
    }

    @Override
    public int getItemCount() {
        return praysListFiltered.size();
    }




    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    praysListFiltered = praysList;
                } else {
                    List<PraysCategories> filteredList = new ArrayList<>();
                    for (PraysCategories row : praysList) {


                        if (row.getTitle().toLowerCase().contains(charString.toLowerCase()) || row.getDesc().contains(charSequence)) {
                            filteredList.add(row);
                            System.out.println(row.getTitle());
                        }
                    }

                    praysListFiltered = filteredList;
                    System.out.println(praysListFiltered.size());
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = praysListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                praysListFiltered = (ArrayList<PraysCategories>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }









}