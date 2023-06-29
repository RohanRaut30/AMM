package com.example.amm.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.amm.R;
import com.example.amm.ui.home.HomeFragment;

public class AdminLogin extends Fragment {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_login, container, false);


        // Find views
        usernameEditText = view.findViewById(R.id.usernameEditText);
        passwordEditText = view.findViewById(R.id.passwordEditText);
        loginButton = view.findViewById(R.id.loginButton);

        // Set click listener for login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve entered username and password
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Validate credentials
                if (isValidCredentials(username, password)) {
                    // Login successful, perform desired action (e.g., navigate to admin dashboard)
                    // Navigate to the AboutUsFragment
                    NavHostFragment.findNavController(AdminLogin.this).navigate(R.id.action_admin_to_adminHome);
                    Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                    // TODO: Perform action after successful login
                } else {
                    // Invalid credentials, show error message
                    Toast.makeText(requireContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }


    private boolean isValidCredentials(String username, String password) {
        // Perform your own validation logic here (e.g., check against a database)
        // For demonstration purposes, consider hardcoded valid credentials
        String validUsername = "Admin";
        String validPassword = "Admin@214";

        return username.equals(validUsername) && password.equals(validPassword);
    }
}