package com.example.amm.Fragment;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.amm.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ContactUs extends Fragment {

    private TextView contactText1,text;
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
        contactText1 = root.findViewById(R.id.contactText1);
        text = root.findViewById(R.id.text1);

        animatedTextTwo();

        animatexTextone();

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
                                    Toast.makeText(getContext(), "Thank you for reaching out! We'll get back to you soon.", Toast.LENGTH_SHORT).show();
                                    // Clear input fields
                                    nameEditText.getText().clear();
                                    emailEditText.getText().clear();
                                    mobileEditText.getText().clear();
                                    subjectEditText.getText().clear();
                                    commentEditText.getText().clear();

                                } else {
                                    // Error occurred while saving data
                                    Log.e("Firebase", "Failed to submit form data", task.getException());
                                    Toast.makeText(getContext(), "Oops! Something went wrong. Please try again.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });





            }
        });

        
        return root;
    }

    private void animatedTextTwo() {
        String textToAnimate = getString(R.string.conatctText_first); // Get text from resources

        ValueAnimator animator = ValueAnimator.ofInt(0, textToAnimate.length());
        animator.setDuration(textToAnimate.length() * 800); // Adjust the duration as needed
        animator.addUpdateListener(animation -> {
            int animatedValue = (int) animation.getAnimatedValue();
            text.setText(textToAnimate.substring(0, animatedValue));
        });
        animator.start();
    }

    private void animatexTextone() {

        String textToAnimate = getString(R.string.contact); // Get text from resources

        ValueAnimator animator = ValueAnimator.ofInt(0, textToAnimate.length());
        animator.setDuration(textToAnimate.length() * 650); // Adjust the duration as needed
        animator.addUpdateListener(animation -> {
            int animatedValue = (int) animation.getAnimatedValue();
            contactText1.setText(textToAnimate.substring(0, animatedValue));
        });
        animator.start();
    }


}