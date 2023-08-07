package com.example.amm;

import android.annotation.SuppressLint;
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
    EditText etPaymentAmount,mobile,email;



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment, container, false);



        paytext = view.findViewById(R.id.paytext);
        paybtn = view.findViewById(R.id.btnPay);
        etPaymentAmount = view.findViewById(R.id.etPaymentAmount);
        mobile = view.findViewById(R.id.etMobileNumber);
        email = view.findViewById(R.id.etEmail);


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
        String paymentMobile = mobile.getText().toString();
        if (TextUtils.isEmpty(paymentAmount)) {
            // Handle the case when the user has not entered any amount
            Toast.makeText(requireContext(), "Please enter a valid Mobile Number", Toast.LENGTH_SHORT).show();
            return;
        }
        String paymentEmail = email.getText().toString();
        if (TextUtils.isEmpty(paymentAmount)) {
            // Handle the case when the user has not entered any amount
            Toast.makeText(requireContext(), "Please enter a valid Email", Toast.LENGTH_SHORT).show();
            return;
        }
        int amountInPaise = Integer.parseInt(paymentAmount) * 100;
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_oC9Zw79UDfq0Hf");

        checkout.setImage(R.drawable.baggremove);
        final Activity activity = requireActivity();

        try {
            JSONObject options = new JSONObject();

            options.put("name", "Akhil Mandai Mandal");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            // options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#FF6200EE");
            options.put("currency", "INR");
            options.put("amount", amountInPaise);//300 X 100
            options.put("prefill.email", paymentEmail);
            JSONObject put = options.put("prefill.contact", paymentMobile);
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

    private void clearForm() {
        etPaymentAmount.setText("");
        mobile.setText("");
        email.setText("");
    }
}
