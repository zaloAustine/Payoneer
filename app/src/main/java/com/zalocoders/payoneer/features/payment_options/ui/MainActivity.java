package com.zalocoders.payoneer.features.payment_options.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.zalocoders.payoneer.data.models.payment_options.Applicable;
import com.zalocoders.payoneer.databinding.ActivityMainBinding;
import com.zalocoders.payoneer.features.payment_options.viewmodel.PaymentOptionViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private PaymentOptionViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(PaymentOptionViewModel.class);
        viewModel.getPaymentOptions();
        observePaymentOptions();
    }

    private void observePaymentOptions() {
        viewModel.gePaymentOptionList().observe(this, new Observer<List<Applicable>>() {
            @Override
            public void onChanged(List<Applicable> applicableList) {
                Toast.makeText(MainActivity.this,applicableList.get(0).getLabel(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}