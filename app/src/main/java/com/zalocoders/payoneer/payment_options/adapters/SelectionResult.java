package com.zalocoders.payoneer.payment_options.adapters;

import com.zalocoders.payoneer.data.models.payment_options.Applicable;

public interface SelectionResult{
    void paymentMethodSelected(Applicable applicable);
}
