package com.example.amm;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;

public class EventDataUploadCenter extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText titleEditText, dateEditText, contentEditText;
    private Button chooseImageButton, uploadButton;
    private ImageView imageView;

    private DatabaseReference eventsRef;
    private StorageReference imageStorageRef;

    private Uri imageUri;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_data_upload_center, container, false);

        eventsRef = FirebaseDatabase.getInstance().getReference("events");
        imageStorageRef = FirebaseStorage.getInstance().getReference("event_images");

        titleEditText = view.findViewById(R.id.titleEditText);
        dateEditText = view.findViewById(R.id.dateEditText);
        contentEditText = view.findViewById(R.id.contentEditText);
        chooseImageButton = view.findViewById(R.id.chooseImageButton);
        uploadButton = view.findViewById(R.id.uploadButton);
        imageView = view.findViewById(R.id.imageView);

        chooseImageButton.setOnClickListener(v -> openFileChooser());

        uploadButton.setOnClickListener(v -> uploadEventData());

        return view;
    }

    private void openFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadEventData() {
        String title = titleEditText.getText().toString();
        String date = dateEditText.getText().toString();
        String content = contentEditText.getText().toString();

        String eventId = eventsRef.push().getKey();
        StorageReference imageRef = imageStorageRef.child(eventId + ".jpg");

        if (imageUri != null) {
            imageRef.putFile(imageUri)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            imageRef.getDownloadUrl().addOnCompleteListener(task1 -> {
                                if (task1.isSuccessful()) {
                                    String imageUrl = task1.getResult().toString();
                                    Event event = new Event(eventId, title, date, content, imageUrl);
                                    eventsRef.child(eventId).setValue(event)
                                            .addOnCompleteListener(task2 -> {
                                                if (task2.isSuccessful()) {
                                                    showToast("Event data uploaded successfully");
                                                    clearForm();
                                                } else {
                                                    showToast("Failed to upload event data");
                                                }
                                            });
                                } else {
                                    showToast("Failed to get image download URL");
                                }
                            });
                        } else {
                            showToast("Failed to upload image");
                        }
                    });
        } else {
            Event event = new Event(eventId, title, date, content);
            eventsRef.child(eventId).setValue(event)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            showToast("Event data uploaded successfully");
                            clearForm();
                        } else {
                            showToast("Failed to upload event data");
                        }
                    });
        }
    }

    private void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    private void clearForm() {
        titleEditText.setText("");
        dateEditText.setText("");
        contentEditText.setText("");
        imageView.setImageDrawable(null);
        imageUri = null;
    }


}
