package com.zalocoders.payoneer.payment_options.repository;

import com.zalocoders.payoneer.data.models.payment_options.PaymentListResponse;
import com.zalocoders.payoneer.data.network.ApiService;
import javax.inject.Inject;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;


public class PaymentOptionsRepository{

   private final ApiService apiService;

    @Inject
    public PaymentOptionsRepository(ApiService apiService){
        this.apiService = apiService;
    }
    public Observable<PaymentListResponse> getPaymentOptions() {
        return apiService.getPaymentOptions();
    }

    public Observable<Response<ResponseBody>> postPaymentDetails(String url,String body){
        return apiService.postPaymentDetails(url,body);
    }

}
