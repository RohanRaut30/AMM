package com.example.amm;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.Timer;
import java.util.TimerTask;


public class WeAreEcoFriendly extends Fragment {

    private ViewPager viewPager;
    private ImageSliderAdapter imageSliderAdapter;
    private Timer slideTimer;
    private Handler handler;
    private Runnable slideRunnable;
    private int currentPage = 0;
    private int NUM_PAGES = 0;
    private final long SLIDE_DELAY = 3000; // Delay between slides in milliseconds

    private int[] imageIds = {
            R.drawable.mandaiganpati1,
            R.drawable.eco2,
            R.drawable.eco1
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_we_are_eco_friendly, container, false);

        viewPager = view.findViewById(R.id.viewPager);
        imageSliderAdapter = new ImageSliderAdapter(getContext(), imageIds);
        viewPager.setAdapter(imageSliderAdapter);

        NUM_PAGES = imageIds.length;

        // Set up automatic sliding
        handler = new Handler();
        slideRunnable = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        startSlideTimer();

        return view;
    }

    private void startSlideTimer() {
        slideTimer = new Timer();
        slideTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(slideRunnable);
            }
        }, SLIDE_DELAY, SLIDE_DELAY);
    }

    private void stopSlideTimer() {
        if (slideTimer != null) {
            slideTimer.cancel();
            slideTimer = null;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        stopSlideTimer();
    }

    @Override
    public void onResume() {
        super.onResume();
        startSlideTimer();
    }
}
