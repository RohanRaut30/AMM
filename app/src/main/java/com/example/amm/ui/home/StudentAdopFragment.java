package com.example.amm.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amm.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;


public class StudentAdopFragment extends Fragment {


    private TextView textView;
    private String textToAnimate = "Student Adaptation";
    private int index = 0;
    private Button btnfrom;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_student_adop, container, false);

        textView = root.findViewById(R.id.adp1);

        textView();

        btnfrom = root.findViewById(R.id.btnForm1);

        btnfrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the Student Adoptation
                NavHostFragment.findNavController(StudentAdopFragment.this).navigate(R.id.action_mainFragment_to_StudentForm);


            }
        });



        return root;
    }

    private void textView() {

        if (index < textToAnimate.length()) {
            textView.setText(textToAnimate.substring(0, index + 1));
            index++;
            Handler handler = new Handler();
            handler.postDelayed(this::textView, 150); // Delay between each character typing
        }
    }


}