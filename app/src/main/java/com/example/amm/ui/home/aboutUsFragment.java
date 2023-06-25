package com.example.amm.ui.home;

import android.animation.ObjectAnimator;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

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


        // Set up the animation
        Animation animation = new TranslateAnimation(-1000, 0, 0, 0);
        animation.setDuration(1000); // Animation duration in milliseconds

        // Start the animation
        animatedText.startAnimation(animation);





        return view;

    }
}