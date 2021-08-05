package com.zalocoders.payoneer.data.network;

import com.zalocoders.payoneer.data.models.payment_options.PaymentListResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;


public interface ApiService {
    @GET("https://raw.githubusercontent.com/optile/checkout-android/develop/shared-test/lists/listresult.json")
    Observable<PaymentListResponse> getPaymentOptions();
}
