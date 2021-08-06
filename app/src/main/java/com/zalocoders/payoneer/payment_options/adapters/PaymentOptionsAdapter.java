package com.zalocoders.payoneer.payment_options.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zalocoders.payoneer.data.models.payment_options.Applicable;
import com.zalocoders.payoneer.databinding.PaymentOptionItemBinding;

import java.util.ArrayList;
import java.util.Random;

public class PaymentOptionsAdapter extends RecyclerView.Adapter<PaymentOptionsAdapter.PaymentViewHolder> {

    private final ArrayList<Applicable> data;
    private int lastPosition = -1;
    private int mSelectedItem = -1;
    SelectionResult selectionResult;


    public PaymentOptionsAdapter(ArrayList<Applicable> data, SelectionResult selectionResult) {
        this.data = data;
        this.selectionResult = selectionResult;
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

        setAnimation(holder.itemView, position);
        holder.optionName.setText(applicable.getLabel());
        Glide.with(holder.imageView.getContext()).load(applicable.getLinks().getLogo()).into(holder.imageView);

        holder.radioButton.setChecked(position == mSelectedItem);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            ScaleAnimation anim =
                    new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setDuration(new Random().nextInt(1001));
            viewToAnimate.startAnimation(anim);
            lastPosition = position;
        }
    }

    class PaymentViewHolder extends RecyclerView.ViewHolder{
        private final TextView optionName;
        private final ImageView imageView;
        private final ConstraintLayout constraintLayout;
        private final RadioButton radioButton;

        public PaymentViewHolder(@NonNull @io.reactivex.annotations.NonNull PaymentOptionItemBinding binding) {
            super(binding.getRoot());
            optionName = binding.optionName;
            imageView = binding.optionImage;
            constraintLayout = binding.constraintLayout;
            radioButton = binding.radioButton;

            View.OnClickListener clickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelectedItem = getBindingAdapterPosition();
                    notifyDataSetChanged();
                    selectionResult.paymentMethodSelected(data.get(mSelectedItem));
                }
            };

            binding.getRoot().setOnClickListener(clickListener);
            radioButton.setOnClickListener(clickListener);

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

