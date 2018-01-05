package com.prayorganizer.rxdd.orthodox.any.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.prayorganizer.rxdd.orthodox.AppContext;
import com.prayorganizer.rxdd.orthodox.R;
import com.prayorganizer.rxdd.orthodox.content.PraysCategories;
import com.prayorganizer.rxdd.orthodox.view.fragments.PraysCategoriesFragment;

import java.util.List;

public class PraysCategoriesAdapter extends FilterAdapter<PraysCategories, PraysCategoriesAdapter.PraysViewHolder> {

    private PraysCategoriesAdapterItemClick mListener;
    public interface PraysCategoriesAdapterItemClick {
        void onClick(View view, String title);
    }

    static class PraysViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView img;
        PraysViewHolderClick mListenerHolder;
        PraysViewHolder(View view, PraysViewHolderClick listener) {
            super(view);
            view.setOnClickListener(this);
            title = view.findViewById(R.id.title);
            img = view.findViewById(R.id.imageView);
            mListenerHolder = listener;
        }

        @Override
        public void onClick(View v){
            mListenerHolder.onClick(v, title.getText().toString());
        }

        public interface PraysViewHolderClick{
            void onClick(View view, String title);
        }
    }

    @Override
    public PraysViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.categories_list_row, parent, false);

        return new PraysViewHolder(itemView, new PraysViewHolder.PraysViewHolderClick() {
            @Override
            public void onClick(View view, String title) {
                mListener.onClick(view, title);
            }
        });
    }       

    @Override
    public void onBindViewHolder(PraysViewHolder holder, int position) {
        PraysCategories praysCategories = mListFiltered.get(position);
            holder.title.setText(praysCategories.getTitle());
            int imgName = AppContext.getAppContext().getResources().getIdentifier(praysCategories.getImg(), "drawable", AppContext.getAppContext().getPackageName());
            holder.img.setImageResource(imgName);
    }



    public PraysCategoriesAdapter(List<PraysCategories> praysCategoriesList,PraysCategoriesAdapterItemClick listener){
        super(praysCategoriesList);
        mListener = listener;
    }

    @Override
    protected boolean filter(String str, PraysCategories obj) {
        return obj.getTitle().toLowerCase().contains(str.toLowerCase());
    }


}




