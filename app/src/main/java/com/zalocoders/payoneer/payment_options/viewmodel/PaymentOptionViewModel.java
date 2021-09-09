package com.zalocoders.payoneer.payment_options.viewmodel;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.zalocoders.payoneer.data.models.payment_options.Applicable;
import com.zalocoders.payoneer.payment_options.repository.PaymentOptionsRepository;

import java.util.List;
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;


@HiltViewModel
public class PaymentOptionViewModel extends ViewModel {

    private final PaymentOptionsRepository repository;

    private final MutableLiveData<List<Applicable>> paymentOptions = new MutableLiveData<>();
    public LiveData<List<Applicable>> gePaymentOptionList() {
        return paymentOptions;
    }

    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();
    public LiveData<Boolean> geLoading() {
        return loading;
    }

    private final MutableLiveData<Response<ResponseBody>> postPaymentDetailLiveData = new MutableLiveData<>();
    public LiveData<Response<ResponseBody>> getPostPaymentLiveData(){
        return postPaymentDetailLiveData;
    }


    @Inject
    PaymentOptionViewModel(PaymentOptionsRepository repository) {
        this.repository = repository;
    }

    @SuppressLint("CheckResult")
    public void getPaymentOptions() {
        repository.getPaymentOptions()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(consumer -> {
                    loading.postValue(true);
                })
                .map(paymentListResponse -> paymentListResponse.getNetworks().getApplicable())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    paymentOptions.setValue(response);
                    loading.setValue(false);
                }, error -> Log.e("test", "getPaymentMethods: " + error.getMessage()));
    }

    @SuppressLint("CheckResult")
    public void postPaymentDetails(String url,String body){
        repository.postPaymentDetails(url,body)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(it -> {
                    loading.postValue(true);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseBodyResponse -> {
                    postPaymentDetailLiveData.postValue(responseBodyResponse);
                });

    }
}
