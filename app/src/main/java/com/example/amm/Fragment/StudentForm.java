package com.example.amm.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amm.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**

 */
public class StudentForm extends Fragment {

    private EditText editTextFullName;
    private EditText editTextAddress;
    private EditText editTextContactNumber;
    private EditText editTextAge;
    private EditText editText10thMarks;
    private EditText editText12thMarks;
    private EditText editTextGraduationMarks;
    private EditText editTextAmount;
    private EditText editTextComments;

    private Button btnSubmit;
    private DatabaseReference databaseReference;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_student_form, container, false);

        editTextFullName = root.findViewById(R.id.editTextFullName);
        editTextAddress = root.findViewById(R.id.editTextAddress);
        editTextContactNumber = root.findViewById(R.id.editTextContactNumber);
        editTextAge = root.findViewById(R.id.editTextAge);
        editText10thMarks = root.findViewById(R.id.editText10thMarks);
        editText12thMarks = root.findViewById(R.id.editText12thMarks);
        editTextGraduationMarks = root.findViewById(R.id.editTextGraduationMarks);
        editTextAmount = root.findViewById(R.id.editTextAmount);
        editTextComments = root.findViewById(R.id.editTextComments);

        // Initialize the database reference
        databaseReference = FirebaseDatabase.getInstance().getReference();

        btnSubmit = root.findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve form data from XML fields
                String fullName = ((TextInputEditText) root.findViewById(R.id.editTextFullName)).getText().toString();
                String address = ((TextInputEditText) root.findViewById(R.id.editTextAddress)).getText().toString();
                String contactNumber = ((TextInputEditText) root.findViewById(R.id.editTextContactNumber)).getText().toString();
                int age = Integer.parseInt(((TextInputEditText) root.findViewById(R.id.editTextAge)).getText().toString());
                float tenthMarks = Float.parseFloat(((TextInputEditText) root.findViewById(R.id.editText10thMarks)).getText().toString());
                float twelfthMarks = Float.parseFloat(((TextInputEditText) root.findViewById(R.id.editText12thMarks)).getText().toString());
                float graduationMarks = Float.parseFloat(((TextInputEditText) root.findViewById(R.id.editTextGraduationMarks)).getText().toString());
                float amountForEducation = Float.parseFloat(((TextInputEditText) root.findViewById(R.id.editTextAmount)).getText().toString());
                String comments = ((TextInputEditText) root.findViewById(R.id.editTextComments)).getText().toString();

                // Create a FormData object with the retrieved data
                StudentAdapData formData = new StudentAdapData();
                formData.setFullName(fullName);
                formData.setAddress(address);
                formData.setContactNumber(contactNumber);
                formData.setAge(age);
                formData.setTenthMarks(tenthMarks);
                formData.setTwelfthMarks(twelfthMarks);
                formData.setGraduationMarks(graduationMarks);
                formData.setAmountForEducation(amountForEducation);
                formData.setComments(comments);

                // Get the database reference from the URL
                String firebaseUrl = "https://akhil-mandai-default-rtdb.firebaseio.com/"; // Replace with your Firebase database URL
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl(firebaseUrl);

                // Save the FormData object to the database
                String key = databaseReference.child("forms").push().getKey();
                databaseReference.child("Student Adaptation Form Data").child(key).setValue(formData)
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



