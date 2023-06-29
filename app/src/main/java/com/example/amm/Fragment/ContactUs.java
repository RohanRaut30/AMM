package com.example.amm.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.amm.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ContactUs extends Fragment {

    private EditText contactName;
    private EditText contactEmail;
    private EditText contactMobile;
    private EditText contactSubject;
    private EditText contactComment;
    private Button contactBtn;
    private DatabaseReference databaseReference;
    



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_contact_us, container, false);
        contactName = root.findViewById(R.id.contactName);
        contactEmail = root.findViewById(R.id.contactEmail);
        contactMobile = root.findViewById(R.id.contactMobile);
        contactSubject = root.findViewById(R.id.contactSubject);
        contactComment = root.findViewById(R.id.editTextComments);

        // Initialize the database reference
        databaseReference = FirebaseDatabase.getInstance().getReference();


        contactBtn = root.findViewById(R.id.contactBtn);

        contactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve form values
                TextInputEditText nameEditText = root.findViewById(R.id.contactName);
                TextInputEditText emailEditText = root.findViewById(R.id.contactEmail);
                TextInputEditText mobileEditText = root.findViewById(R.id.contactMobile);
                TextInputEditText subjectEditText = root.findViewById(R.id.contactSubject);
                TextInputEditText commentEditText = root.findViewById(R.id.contactComment);

                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String mobile = mobileEditText.getText().toString();
                String subject = subjectEditText.getText().toString();
                String comment = commentEditText.getText().toString();

                ContactUsData contactUsData = new ContactUsData();
                contactUsData.setName(name);
                contactUsData.setEmail(email);
                contactUsData.setMobile(mobile);
                contactUsData.setSubject(subject);
                contactUsData.setComments(comment);



                // Get the database reference from the URL
                String firebaseUrl = "https://akhil-mandai-default-rtdb.firebaseio.com/"; // Replace with your Firebase database URL
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl(firebaseUrl);

                // Save the FormData object to the database
                String key = databaseReference.child("contacts").push().getKey();
                databaseReference.child("Contact Us Data").child(key).setValue(contactUsData)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    // Data successfully saved
                                    Log.d("Firebase", "Form data submitted successfully");
                                    Toast.makeText(getContext(), "Form data submitted successfully", Toast.LENGTH_SHORT).show();
                                } else {
                                    // Error occurred while saving data
                                    Log.e("Firebase", "Failed to submit form data", task.getException());
                                    Toast.makeText(getContext(), "Failed to submit form data", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });





            }
        });

        
        return root;
    }


}