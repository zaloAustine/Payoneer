package com.zalocoders.payoneer.payment_options.adapters;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.zalocoders.payoneer.data.models.payment_options.Applicable;

import java.util.ArrayList;
import java.util.List;

public class PaymentDiffCallBack  extends DiffUtil.Callback {

    ArrayList<Applicable> oldPayments;
    ArrayList<Applicable> newPayments;

    public PaymentDiffCallBack(ArrayList<Applicable> newList, ArrayList<Applicable> oldList) {
        this.newPayments = newList;
        this.oldPayments = oldList;
    }

    @Override
    public int getOldListSize() {
        return oldPayments.size();
    }

    @Override
    public int getNewListSize() {
        return newPayments.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldPayments.get(oldItemPosition).getLabel() == newPayments.get(newItemPosition).getLabel();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldPayments.get(oldItemPosition).equals(newPayments.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
