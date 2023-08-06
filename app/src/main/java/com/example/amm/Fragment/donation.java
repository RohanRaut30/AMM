package com.example.amm.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.fragment.app.Fragment;

import com.example.amm.R;
import com.example.amm.WebViewController;


public class donation extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_donation, container, false);

        WebView webView = root.findViewById(R.id.webview);
        webView.loadUrl("https://akhilmandaimandal.org/");
        webView.setWebViewClient(new WebViewController());


        return root;
    }
}