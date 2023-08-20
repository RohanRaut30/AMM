package com.example.amm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;


public class UploadData extends Fragment {

    private CardView uploadData,uploadImages,uploadNotification,goLive;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_upload_data, container, false);
        uploadData = root.findViewById(R.id.uploadevent);

        uploadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(UploadData.this).navigate(R.id.admin_to_admin_EventManagement_center);
            }
        });
        uploadImages = root.findViewById(R.id.UploadImages);
        uploadImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(UploadData.this).navigate(R.id.admin_to_admin_uploadImage);
            }
        });
        uploadNotification = root.findViewById(R.id.uploadNotification);
        uploadNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(UploadData.this).navigate(R.id.admin_to_admin_notification_uploade_data);
            }
        });

        goLive = root.findViewById(R.id.goLive);
        goLive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(UploadData.this).navigate(R.id.admin_to_admin_go_live);
            }
        });



        return root;
    }
}