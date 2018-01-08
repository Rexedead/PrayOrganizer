package com.prayorganizer.rxdd.orthodox.any.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.prayorganizer.rxdd.orthodox.R;
import com.prayorganizer.rxdd.orthodox.content.Pray;

import java.util.List;

/**
 * Created by Rexedead on 07.01.2018.
 */

public class PrayAdapter extends RecyclerView.Adapter<PrayAdapter.PrayViewHolder> {

    private List <Pray> prayList;

    public PrayAdapter(List<Pray> prayList) {
        this.prayList = prayList;
    }

    public class PrayViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        TextView header;
        ImageView fav;

        public PrayViewHolder(View itemView) {
            super(itemView);
            header = itemView.findViewById(R.id.pray_header);
            text = itemView.findViewById(R.id.pray_text);
            fav = itemView.findViewById(R.id.imagePrayRow);
        }
    }

    @Override
    public PrayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_prays_categories, parent, false);
        return new PrayAdapter.PrayViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PrayViewHolder holder, int position) {
        holder.text.setText(prayList.get(position).getText());
        holder.header.setText(prayList.get(position).getHead());
    }

    @Override
    public int getItemCount() {
        return prayList.size();
    }


}
