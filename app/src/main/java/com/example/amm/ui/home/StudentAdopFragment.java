package com.example.amm.ui.home;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.amm.R;


public class StudentAdopFragment extends Fragment {


    private TextView textView;
    private String textToAnimate;
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

        String textToAnimate = getString(R.string.student_adaptation); // Get text from resources

        ValueAnimator animator = ValueAnimator.ofInt(0, textToAnimate.length());
        animator.setDuration(textToAnimate.length() * 150); // Adjust the duration as needed
        animator.addUpdateListener(animation -> {
            int animatedValue = (int) animation.getAnimatedValue();
            textView.setText(textToAnimate.substring(0, animatedValue));
        });
        animator.start();
    }


}