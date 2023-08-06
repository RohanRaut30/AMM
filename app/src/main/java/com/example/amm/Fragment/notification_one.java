package com.example.amm.Fragment;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import com.example.amm.DataModel;
import com.example.amm.R;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class notification_one extends Fragment {

    private EditText editText1, editText2, editText3;
    private Button submitButton;

    // Firebase database reference
    private DatabaseReference databaseReference;

    public notification_one() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification_one, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize Firebase database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("Notification Data");

        editText1 = view.findViewById(R.id.editText1);
        editText2 = view.findViewById(R.id.editText2);
        editText3 = view.findViewById(R.id.editText3);
        submitButton = view.findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get data from EditText fields
                String title = editText1.getText().toString();
                String dateAndTime = editText2.getText().toString();
                String information = editText3.getText().toString();

                // Store the data in Firebase
                storeDataInFirebase(title, dateAndTime, information);
            }
        });
    }

    private void storeDataInFirebase(String title, String dateAndTime, String information) {
        // Create a new node with a unique key and set the values
        String key = databaseReference.push().getKey();
        DataModel dataModel = new DataModel(title, dateAndTime, information);

        // Add the completion listener to handle success/failure
        databaseReference.child(key).setValue(dataModel, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if (error == null) {
                    // Data uploaded successfully
                    showNotification("Data Upload Successful", "Data has been uploaded to Firebase.");
                } else {
                    // Data upload failed
                    showNotification("Data Upload Failed", "Failed to upload data to Firebase.");
                }
            }
        });
    }

    private void showNotification(String title, String message) {
        // Create a notification channel (required for Android Oreo and above)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("channel_id", "Channel Name", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = requireContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(), "channel_id")
                .setSmallIcon(R.drawable.notification_vector)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Show the notification
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(requireContext());
        notificationManagerCompat.notify(1, builder.build());
    }
}
