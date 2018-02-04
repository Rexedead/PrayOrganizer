package com.prayorganizer.rxdd.orthodox.any.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.prayorganizer.rxdd.orthodox.AppContext;
import com.prayorganizer.rxdd.orthodox.R;
import com.prayorganizer.rxdd.orthodox.content.Pray;
import com.prayorganizer.rxdd.orthodox.database.HolyModel;

import java.util.List;

/**
 * Created by Rexedead on 07.01.2018.
 * todo https://code.tutsplus.com/ru/tutorials/getting-started-with-recyclerview-and-cardview-on-android--cms-23465
 */

public class PrayAdapter extends RecyclerView.Adapter<PrayAdapter.PrayViewHolder> {

    private List<Pray> prayList;
    private CheckBox mCheckBoxFav;
    private boolean mChecked;

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
                .inflate(R.layout.pray_single_row, parent, false);
        return new PrayViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PrayViewHolder holder, int position) {
        final String mPraysFavTable = "prays_fav";
        holder.text.setText(prayList.get(position).getText());
        holder.header.setText(prayList.get(position).getHead());
        final String currentId = prayList.get(position).getId();

            mChecked = HolyModel.getInstance().checkFav(prayList.get(position).getId(), mPraysFavTable);
            mCheckBoxFav = holder.itemView.findViewById(R.id.pray_fav_img);
            mCheckBoxFav.setChecked(mChecked);

        mCheckBoxFav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    HolyModel.getInstance().removeFav(currentId, mPraysFavTable);
                    Toast.makeText(AppContext.getAppContext(), "Удалено", Toast.LENGTH_SHORT).show();
                } else {
                    HolyModel.getInstance().setFav(currentId, mPraysFavTable);
                    Toast.makeText(AppContext.getAppContext(), "Добавлено в избранное", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return prayList.size();
    }


}
