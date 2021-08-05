package com.zalocoders.payoneer.features.payment_options.repository;

import com.zalocoders.payoneer.BuildConfig;
import com.zalocoders.payoneer.data.models.payment_options.PaymentListResponse;
import com.zalocoders.payoneer.data.network.ApiService;
import com.zalocoders.payoneer.utils.Constants;

import javax.inject.Inject;
import io.reactivex.Observable;



public class PaymentOptionsRepository{

   private final ApiService apiService;

    @Inject
    public PaymentOptionsRepository(ApiService apiService){
        this.apiService = apiService;
    }
    public Observable<PaymentListResponse> getPaymentOptions() {
        return apiService.getPaymentOptions();
    }

}
