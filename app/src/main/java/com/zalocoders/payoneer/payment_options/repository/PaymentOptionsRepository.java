package com.zalocoders.payoneer.payment_options.repository;

import com.zalocoders.payoneer.data.models.payment_options.PaymentListResponse;
import com.zalocoders.payoneer.data.network.ApiService;

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
