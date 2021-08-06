package com.zalocoders.payoneer.payment_options.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;

import com.zalocoders.payoneer.data.models.payment_options.Applicable;
import com.zalocoders.payoneer.databinding.ActivityMainBinding;
import com.zalocoders.payoneer.payment_options.adapters.PaymentOptionsAdapter;
import com.zalocoders.payoneer.payment_options.viewmodel.PaymentOptionViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private PaymentOptionViewModel viewModel;
    private PaymentOptionsAdapter adapter;
    private ArrayList<Applicable> paymentOptionList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(PaymentOptionViewModel.class);

        paymentOptionList  = new ArrayList<>();


        viewModel.getPaymentOptions();
        observePaymentOptions();

        setUpRecyclerView();
    }

    private void observePaymentOptions() {
        viewModel.gePaymentOptionList().observe(this, new Observer<List<Applicable>>() {
            @Override
            public void onChanged(List<Applicable> applicableList) {
                adapter.updateList((ArrayList<Applicable>) applicableList);
            }
        });
    }

    private void setUpRecyclerView(){
        binding.paymentOptionRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PaymentOptionsAdapter(paymentOptionList);
        binding.paymentOptionRecyclerview.setAdapter(adapter);
    }
}