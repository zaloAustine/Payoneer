package com.zalocoders.payoneer.payment_options.viewmodel;

import androidx.lifecycle.Observer;

import com.zalocoders.payoneer.RxImmediateSchedulerRule;
import com.zalocoders.payoneer.data.models.payment_options.Applicable;
import com.zalocoders.payoneer.payment_options.repository.PaymentOptionsRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;


@RunWith(MockitoJUnitRunner.class)
public class PaymentOptionViewModelTest {

    @Mock
    PaymentOptionsRepository paymentOptionsRepository;

    @Mock
    public Observer<List<Applicable>> observer;

    public PaymentOptionViewModel viewModel;

    @Rule
    RxImmediateSchedulerRule rxImmediateSchedulerRule = new RxImmediateSchedulerRule();

    @Before
    public void setUp(){
    viewModel = new PaymentOptionViewModel(paymentOptionsRepository);
    }

    @Test
    public void gePaymentOptionList() {
        List<Applicable> applicable = new ArrayList<>();
        applicable.add(new Applicable());
        Mockito.when(paymentOptionsRepository.getPaymentOptions().map(it -> it.getNetworks().getApplicable())).thenReturn(Observable.just(applicable));
        viewModel.gePaymentOptionList().observeForever(observer);
        assert(viewModel.gePaymentOptionList().getValue() == null);
    }
}

