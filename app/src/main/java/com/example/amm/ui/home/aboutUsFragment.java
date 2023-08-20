package com.example.amm.ui.home;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.amm.R;


public class aboutUsFragment extends Fragment {

    private TextView animatedText;
    private View horizontalLine;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);

        animatedText = view.findViewById(R.id.animatedText);

        animatedText.setText(""); // Clear the text initially
        animateTextView();



        return view;

    }
    private void animateTextView() {
        String textToAnimate = getString(R.string.about_mandal); // Get text from resources

        ValueAnimator animator = ValueAnimator.ofInt(0, textToAnimate.length());
        animator.setDuration(textToAnimate.length() * 150); // Adjust the duration as needed
        animator.addUpdateListener(animation -> {
            int animatedValue = (int) animation.getAnimatedValue();
            animatedText.setText(textToAnimate.substring(0, animatedValue));
        });
        animator.start();
    }
}