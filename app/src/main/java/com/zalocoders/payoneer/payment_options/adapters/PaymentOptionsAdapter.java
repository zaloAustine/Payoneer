package com.zalocoders.payoneer.payment_options.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;
import com.zalocoders.payoneer.data.models.payment_options.Applicable;
import com.zalocoders.payoneer.databinding.PaymentOptionItemBinding;

import java.util.ArrayList;

public class PaymentOptionsAdapter extends RecyclerView.Adapter<PaymentOptionsAdapter.PaymentViewHolder> {

    private final ArrayList<Applicable> data;

    public PaymentOptionsAdapter(ArrayList<Applicable> data) {
        this.data = data;
    }

    @NonNull
    @io.reactivex.annotations.NonNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull @io.reactivex.annotations.NonNull ViewGroup parent, int viewType) {
        PaymentOptionItemBinding binding = PaymentOptionItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new PaymentViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @io.reactivex.annotations.NonNull PaymentViewHolder holder, int position) {
        Applicable applicable = data.get(position);

        holder.optionName.setText(applicable.getLabel());
        Glide.with(holder.imageView.getContext()).load(applicable.getLinks().getLogo()).into(holder.imageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class PaymentViewHolder extends RecyclerView.ViewHolder{
        private final TextView optionName;
        private final ImageView imageView;
        private final ConstraintLayout cardView;

        public PaymentViewHolder(@NonNull @io.reactivex.annotations.NonNull PaymentOptionItemBinding binding) {
            super(binding.getRoot());
            optionName = binding.optionName;
            imageView = binding.optionImage;
            cardView = binding.constraintLayout;
        }
    }

    public void updateList(ArrayList<Applicable> newList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new PaymentDiffCallBack(newList,data));
        diffResult.dispatchUpdatesTo(this);
        notifyDataSetChanged();
        data.clear();
        this.data.addAll(newList);
    }

}
