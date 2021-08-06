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


@HiltViewModel
public class PaymentOptionViewModel extends ViewModel {

    private final PaymentOptionsRepository repository;
    private final MutableLiveData<List<Applicable>> paymentOptions = new MutableLiveData<>();
    public LiveData<List<Applicable>> gePaymentOptionList() {
        return paymentOptions;
    }

    @Inject
    PaymentOptionViewModel(PaymentOptionsRepository repository) {
        this.repository = repository;
    }

    @SuppressLint("CheckResult")
    public void getPaymentOptions() {
        repository.getPaymentOptions()
                .subscribeOn(Schedulers.io())
                .map(paymentListResponse -> paymentListResponse.getNetworks().getApplicable())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(paymentOptions::setValue, error -> Log.e("test", "getPaymentMethods: " + error.getMessage()));
    }
}
