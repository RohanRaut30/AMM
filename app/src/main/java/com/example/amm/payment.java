package com.example.amm;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class payment extends Fragment implements PaymentResultListener {

    Button paybtn;
    TextView paytext;
    EditText etPaymentAmount;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment, container, false);



        paytext = view.findViewById(R.id.paytext);
        paybtn = view.findViewById(R.id.btnPay);
        etPaymentAmount = view.findViewById(R.id.etPaymentAmount);


        paybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makePayment();
            }
        });

        return view;
    }

    private void makePayment() {

        String paymentAmount = etPaymentAmount.getText().toString();
        if (TextUtils.isEmpty(paymentAmount)) {
            // Handle the case when the user has not entered any amount
            Toast.makeText(requireContext(), "Please enter a valid amount", Toast.LENGTH_SHORT).show();
            return;
        }
        int amountInPaise = Integer.parseInt(paymentAmount) * 100;
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_oC9Zw79UDfq0Hf");

        checkout.setImage(R.drawable.lgog);
        final Activity activity = requireActivity();

        try {
            JSONObject options = new JSONObject();

            options.put("name", "Akhil Mandai Mandal");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            // options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", amountInPaise);//300 X 100
            options.put("prefill.email", "suryawanshiomkar2019@gmail.com");
            JSONObject put = options.put("prefill.contact", "7666318076");
            checkout.open(activity, options);
        } catch(Exception e) {
            Log.e("TAG", "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        paytext.setText("Successful payment ID: " + s);
    }

    @Override
    public void onPaymentError(int i, String s) {
        paytext.setText("Failed and cause is: " + s);
    }
}
