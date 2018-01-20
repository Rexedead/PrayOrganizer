package com.prayorganizer.rxdd.orthodox.any.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prayorganizer.rxdd.orthodox.R;

/**
 * Created by danbi on 14.01.2018.
 */

public class PsalmAdapter extends RecyclerView.Adapter<PsalmAdapter.PsalmViewHolder> {

    private String[] mPsalmLines;

    public PsalmAdapter(String[] psalmLines){
        mPsalmLines = psalmLines;
    }




    @Override
    public PsalmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.psalm_single_row, parent, false);
        return new PsalmAdapter.PsalmViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(PsalmViewHolder holder, int position) {
        holder.mNumTextView.setText(String.valueOf(position));
        holder.mLineTextView.setText( mPsalmLines[position]);
    }

    @Override
    public int getItemCount() {
        return mPsalmLines.length;
    }



    class PsalmViewHolder extends RecyclerView.ViewHolder {
        TextView mNumTextView;
        TextView mLineTextView;

        public PsalmViewHolder(View itemView) {
            super(itemView);
            mLineTextView = itemView.findViewById(R.id.ps_line_text);
            mNumTextView = itemView.findViewById(R.id.ps_line_num);
        }
    }
}
