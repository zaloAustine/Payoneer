package com.zalocoders.payoneer.features.payment_options.viewmodel;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.zalocoders.payoneer.data.models.payment_options.Applicable;
import com.zalocoders.payoneer.features.payment_options.repository.PaymentOptionsRepository;

import java.util.List;
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.schedulers.Schedulers;


@HiltViewModel
public class PaymentOptionViewModel extends ViewModel {

    private final PaymentOptionsRepository repository;

    private final MutableLiveData<List<Applicable>> paymentOptions = new MutableLiveData<>();
    public MutableLiveData<List<Applicable>> gePaymentOptionList() {
        return paymentOptions;
    }

    @Inject
    PaymentOptionViewModel(PaymentOptionsRepository repository) {
        this.repository = repository;
    }

    @SuppressLint("CheckResult")
    public void getPaymentOptions() {
        MutableLiveData<List<Applicable>> data = new MutableLiveData<>();
        repository.getPaymentOptions().subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(paymentListResponse -> paymentListResponse.getNetworks().getApplicable())
                .subscribe(data::setValue, error -> Log.e("test", "getPaymentMethods: " + error.getMessage()));
    }
}
