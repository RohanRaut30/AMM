package com.example.amm;

import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FullScreenImageActivity extends AppCompatActivity {

    private ImageView fullScreenImageView;
    private Button previousButton;
    private Button nextButton;
    private ArrayList<String> imageUrls;
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);

        fullScreenImageView = findViewById(R.id.fullScreenImageView);
        previousButton = findViewById(R.id.previousButton);
        nextButton = findViewById(R.id.nextButton);

        Intent intent = getIntent();
        if (intent != null) {
            imageUrls = intent.getStringArrayListExtra("image_urls");
            currentIndex = intent.getIntExtra("current_index", 0);

            if (imageUrls != null && currentIndex >= 0 && currentIndex < imageUrls.size()) {
                Glide.with(this).load(imageUrls.get(currentIndex)).into(fullScreenImageView);
            }
        }

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateImage(-1);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateImage(1);
            }
        });
        Button downloadButton = findViewById(R.id.downloadButton);
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadImage();
            }
        });
        
    }

    private void downloadImage() {
        if (imageUrls != null && currentIndex >= 0 && currentIndex < imageUrls.size()) {
            String imageUrl = imageUrls.get(currentIndex);
            // Implement the image downloading logic here
            // You can use libraries like Android DownloadManager or third-party libraries to download the image
            // For simplicity, I'll show a basic example using Android DownloadManager
            Uri imageUri = Uri.parse(imageUrl);
            DownloadManager.Request request = new DownloadManager.Request(imageUri);
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "image.jpg");

            DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
            if (downloadManager != null) {
                downloadManager.enqueue(request);
            }
        }
    }

    private void navigateImage(int offset) {
        int newIndex = currentIndex + offset;
        if (newIndex >= 0 && newIndex < imageUrls.size()) {
            currentIndex = newIndex;
            Glide.with(this).load(imageUrls.get(currentIndex)).into(fullScreenImageView);
        }
    }
}
