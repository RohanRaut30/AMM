package com.example.amm.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.amm.R;

public class AdminHome extends Fragment {

    private Button adminHomeBtn1,adminHome3;
    private Button adminHomebtn2;


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

                // Navigate to the Upload Image
                NavHostFragment.findNavController(AdminHome.this).navigate(R.id.admin_to_admin_uploadImage);

            }
        });

        adminHomebtn2 = root.findViewById(R.id.adminHomeBtn2);
        adminHomebtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Navigate to the Event Management Center
                NavHostFragment.findNavController(AdminHome.this).navigate(R.id.admin_to_admin_EventManagement_center);

            }
        });

        adminHome3 = root.findViewById(R.id.adminHomeBtn3);
        adminHome3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Navigate to the Notification data management
                NavHostFragment.findNavController(AdminHome.this).navigate(R.id.admin_to_admin_notification_uploade_data);

            }
        });


        return root;
    }
}