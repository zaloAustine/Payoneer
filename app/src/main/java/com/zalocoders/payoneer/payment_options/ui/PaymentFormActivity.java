package com.zalocoders.payoneer.payment_options.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.zalocoders.payoneer.R;
import com.zalocoders.payoneer.data.models.payment_options.Applicable;
import com.zalocoders.payoneer.data.models.payment_options.InputElement;
import com.zalocoders.payoneer.databinding.ActivityPaymentFormBinding;
import com.zalocoders.payoneer.payment_options.viewmodel.PaymentOptionViewModel;
import com.zalocoders.payoneer.utils.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import okhttp3.ResponseBody;
import retrofit2.Response;

@AndroidEntryPoint
public class PaymentFormActivity extends AppCompatActivity {

    private ActivityPaymentFormBinding binding;

    private PaymentOptionViewModel paymentOptionViewModel;

    private Applicable applicable;

    private List<String> editTextTags = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        paymentOptionViewModel = new ViewModelProvider(this).get(PaymentOptionViewModel.class);
        generateInputFields();

        postPaymentDetails();
        observeViewModels();

    }

    public void postPaymentDetails(){
        binding.materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getApplicableIntent() != null){
                    paymentOptionViewModel.postPaymentDetails(getApplicableIntent().getLinks().getOperation(),getUserInput());
                }
            }
        });
    }

    public void observeViewModels(){
        paymentOptionViewModel.getPostPaymentLiveData().observe(this, new Observer<Response<ResponseBody>>() {
            @Override
            public void onChanged(Response<ResponseBody> responseBodyResponse) {
                if(responseBodyResponse.code() == 200){

                }else {

                }

            }
        });


        paymentOptionViewModel.geLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

            }
        });

    }

    private void generateInputFields(){

        LinearLayout linearLayout = binding.inputLinearL;

        if(getApplicableIntent().getInputElements() != null) {
            for (InputElement inputElement : getApplicableIntent().getInputElements()) {
                EditText editText = new EditText(this);
                editText.setHint(inputElement.getName());
                editText.setTag(inputElement.getName());
                editTextTags.add(inputElement.getName());
                linearLayout.addView(editText);
            }
        }
    }

    private String getUserInput()  {
        JSONObject jsonObject = new JSONObject();

        try {
            EditText editText;
            for (String tag:editTextTags) {
                editText = binding.inputLinearL.findViewWithTag(tag);
                jsonObject.putOpt(tag,editText.getText().toString());
            }

        }catch (JSONException e) {
        e.printStackTrace();
    }

        return jsonObject.toString();
    }


    private Applicable getApplicableIntent(){
        Applicable applicable = null;
        if(getIntent().hasExtra(Constants.APPLICABLE_INTENT)){
            Log.e("Paypal",getIntent().getStringExtra(Constants.APPLICABLE_INTENT));
            applicable = new Gson().fromJson(getIntent().getStringExtra(Constants.APPLICABLE_INTENT),Applicable.class);
        }
        return applicable;
    }
}