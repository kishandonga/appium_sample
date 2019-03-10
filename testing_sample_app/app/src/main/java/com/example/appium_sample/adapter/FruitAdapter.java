package com.example.appium_sample.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appium_sample.R;

import java.util.List;

/**
 * Created by Kishan Donga on 3/10/19
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private List<String> list;
    private OnItemClickListener listener;

    public FruitAdapter(List<String> sections) {
        this.list = sections;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_section_textview, parent, false);
        return new ViewHolder(textView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnItemClickListener {
        void onItemClick(String fruit);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;

        ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }

        private void bind(final String fruit, final OnItemClickListener listener) {
            mTextView.setText(fruit);
            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(fruit);
                }
            });
        }
    }
}
