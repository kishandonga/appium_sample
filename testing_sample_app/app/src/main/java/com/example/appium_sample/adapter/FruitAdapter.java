package com.example.appium_sample.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appium_sample.R;
import com.example.appium_sample.model.FruitModel;

import java.util.List;
import java.util.Locale;

/**
 * Created by Kishan Donga on 3/10/19
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private List<FruitModel> list;
    private OnItemClickListener listener;
    private OnPriceChangeListener priceChangeListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setOnPriceChangeListener(OnPriceChangeListener listener) {
        this.priceChangeListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_fruit, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setItems(List<FruitModel> list) {
        this.list = list;
        notifyDataSetChanged();

        if (priceChangeListener != null) {
            float amountTotal = 0.0f;
            for (FruitModel model : list) {
                amountTotal += model.getAmount();
            }
            priceChangeListener.onPriceChange(amountTotal);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(FruitModel fruit);

        void onItemLongClick(FruitModel fruit);
    }

    public interface OnPriceChangeListener {
        void onPriceChange(float amount);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        View view;
        ImageView ivFruit;
        TextView tvFruitName;
        TextView tvFruitPrice;

        ViewHolder(View v) {
            super(v);
            view = v;
            initView(v);
        }

        private void initView(View rootView) {
            ivFruit = rootView.findViewById(R.id.ivFruit);
            tvFruitName = rootView.findViewById(R.id.tvFruitName);
            tvFruitPrice = rootView.findViewById(R.id.tvFruitPrice);
        }

        private void bind(final FruitModel fruit, final OnItemClickListener listener) {
            ivFruit.setImageResource(fruit.getPhotoId());
            tvFruitName.setText(fruit.getFruit());
            tvFruitPrice.setText(String.format(Locale.ENGLISH, "$%.2f", fruit.getAmount()));

            view.setOnClickListener(v -> listener.onItemClick(fruit));
            view.setOnLongClickListener(v -> {
                listener.onItemLongClick(fruit);
                return true;
            });
        }
    }
}
