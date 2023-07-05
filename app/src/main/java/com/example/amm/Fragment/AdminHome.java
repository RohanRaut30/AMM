package com.example.amm.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.amm.R;
import com.example.amm.ui.home.HomeFragment;

public class AdminHome extends Fragment {

    private Button adminHomeBtn1,adminShowImg;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_admin_home, container, false);
        adminHomeBtn1 = root.findViewById(R.id.adminHomeBtn1);

        adminHomeBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the AboutUsFragment
                NavHostFragment.findNavController(AdminHome.this).navigate(R.id.admin_to_admin_uploadImage);

            }
        });
        adminShowImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AdminHome.this).navigate(R.id.action_admin_home_to_gallery);
            }
        });


        return root;
    }
}