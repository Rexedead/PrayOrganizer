package com.prayorganizer.rxdd.orthodox.any.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.prayorganizer.rxdd.orthodox.AppContext;
import com.prayorganizer.rxdd.orthodox.R;
import com.prayorganizer.rxdd.orthodox.any.GlideApp;
import com.prayorganizer.rxdd.orthodox.any.MyDividerItemDecoration;
import com.prayorganizer.rxdd.orthodox.content.IconsMain;

import java.util.List;

import jp.wasabeef.glide.transformations.CropTransformation;

import static com.prayorganizer.rxdd.orthodox.any.GlideOptions.bitmapTransform;

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
        TextView icon_title;
        ImageView icon_img;

        IconsViewHolder(View view, IconsViewHolderClick listener) {
            super(view);
            view.setOnClickListener(this);

            mListenerHolder = listener;
            icon_title = view.findViewById(R.id.title_img);
            icon_img = view.findViewById(R.id.icon_img);
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
        holder.icon_title.setText(iconsMain.getIcon_name());
        GlideApp.with(AppContext.getAppContext())
                .load(iconsMain.getIcon_link())
                .placeholder(R.drawable.fav_button)
//                .apply(bitmapTransform(new SketchFilterTransformation()))
//                .apply(bitmapTransform(new BrightnessFilterTransformation(5.5f)).dontAnimate())
                .apply(bitmapTransform(
                        new CropTransformation(MyDividerItemDecoration.dip2px(AppContext.getAppContext(), 200), MyDividerItemDecoration.dip2px(AppContext.getAppContext(), 200),
                                CropTransformation.CropType.TOP)))
                .into(holder.icon_img);
    }

    @Override
    protected boolean filter(String str, IconsMain obj) {
        return obj.getIcon_name().toLowerCase().contains(str.toLowerCase());
    }
}
