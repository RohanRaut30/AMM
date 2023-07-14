package com.example.amm;

import android.annotation.SuppressLint;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class EventDataUploadCenter extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText titleEditText, dateEditText, contentEditText;
    private Button chooseImageButton, uploadButton;
    private ImageView imageView;

    private DatabaseReference eventsRef;
    private StorageReference imageStorageRef;

    private Uri imageUri;

    public EventDataUploadCenter() {
        // Required empty public constructor
    }

    public static EventDataUploadCenter newInstance() {
        return new EventDataUploadCenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_data_upload_center, container, false);

        // Initialize Firebase references
        eventsRef = FirebaseDatabase.getInstance().getReference("events");
        imageStorageRef = FirebaseStorage.getInstance().getReference("event_images");

        // Get references to UI elements
        titleEditText = view.findViewById(R.id.titleEditText);
        dateEditText = view.findViewById(R.id.dateEditText);
        contentEditText = view.findViewById(R.id.contentEditText);
        chooseImageButton = view.findViewById(R.id.chooseImageButton);
        uploadButton = view.findViewById(R.id.uploadButton);
        imageView = view.findViewById(R.id.imageView);

        chooseImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadEventData();
            }
        });

        return view;
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
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
        final String title = titleEditText.getText().toString();
        final String date = dateEditText.getText().toString();
        final String content = contentEditText.getText().toString();

        // Generate a unique key for the event
        final String eventId = eventsRef.push().getKey();

        if (imageUri != null) {
            // Create a storage reference for the image file
            final StorageReference imageRef = imageStorageRef.child(eventId + ".jpg");

            // Upload the image file to Firebase Storage
            imageRef.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()) {
                        imageRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                if (task.isSuccessful()) {
                                    Uri downloadUri = task.getResult();

                                    Event event = new Event(eventId, title, date, content, downloadUri.toString());

                                    // Upload event data to Firebase database
                                    eventsRef.child(eventId).setValue(event).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(getActivity(), "Event data uploaded successfully", Toast.LENGTH_SHORT).show();
                                                clearForm();
                                            } else {
                                                Toast.makeText(getActivity(), "Failed to upload event data", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                } else {
                                    Toast.makeText(getActivity(), "Failed to get image download URL", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(getActivity(), "Failed to upload image", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Event event = new Event(eventId, title, date, content);

            // Upload event data to Firebase database
            eventsRef.child(eventId).setValue(event).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getActivity(), "Event data uploaded successfully", Toast.LENGTH_SHORT).show();
                        clearForm();
                    } else {
                        Toast.makeText(getActivity(), "Failed to upload event data", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void clearForm() {
        titleEditText.setText("");
        dateEditText.setText("");
        contentEditText.setText("");
        imageView.setImageDrawable(null);
        imageUri = null;
    }

    // ConcreteEvent subclass
    static class Event {
        private String eventId;
        private String title;
        private String date;
        private String content;
        private String imageUrl;

        public Event(String eventId, String title, String date, String content) {
            this.eventId = eventId;
            this.title = title;
            this.date = date;
            this.content = content;
        }

        public Event(String eventId, String title, String date, String content, String imageUrl) {
            this.eventId = eventId;
            this.title = title;
            this.date = date;
            this.content = content;
            this.imageUrl = imageUrl;
        }

        public String getEventId() {
            return eventId;
        }

        public String getTitle() {
            return title;
        }

        public String getDate() {
            return date;
        }

        public String getContent() {
            return content;
        }

        public String getImageUrl() {
            return imageUrl;
        }
    }
}
