package com.zalocoders.payoneer.data.network;

import com.zalocoders.payoneer.data.models.payment_options.PaymentListResponse;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;


public interface ApiService {
    @GET("listresult.json")
    Observable<PaymentListResponse> getPaymentOptions();

    @POST
    Observable<Response<ResponseBody>> postPaymentDetails(@Url String url, @Body String body);
}
