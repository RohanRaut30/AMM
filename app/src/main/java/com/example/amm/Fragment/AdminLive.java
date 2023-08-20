package com.example.amm.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.amm.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminLive extends Fragment {

    private EditText urlEditText;
    private Button goLiveButton;
    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_admin_live, container, false);

        urlEditText = rootView.findViewById(R.id.two);
        goLiveButton = rootView.findViewById(R.id.webBTN);

        // Initialize Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("webview_url");

        goLiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = urlEditText.getText().toString().trim();
                if (!url.isEmpty()) {
                    // Delete old data first
                    databaseReference.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // Store the new URL in Firebase
                            databaseReference.setValue(url);

                            // Show toast message and clear EditText
                            Toast.makeText(getContext(), "You are live!", Toast.LENGTH_SHORT).show();
                            urlEditText.getText().clear();
                        }
                    });
                } else {
                    Toast.makeText(getContext(), "Please enter a URL.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }
}
