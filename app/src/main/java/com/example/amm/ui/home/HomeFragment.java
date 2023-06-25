package com.example.amm.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.amm.Fragment.Fragment1894;
import com.example.amm.R;
import com.example.amm.databinding.FragmentHomeBinding;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import org.w3c.dom.Text;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private TextView textView;
    private TextView textView3;
    private TextView textview4;
    private String textToAnimate = "Akhil Mandai Mandal";
    private int index = 0;
    private NavController navController;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);



        View root = inflater.inflate(R.layout.fragment_home, container, false);




        textView = root.findViewById(R.id.text2);

        textView();

        Button btn = root.findViewById(R.id.btnhome1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the AboutUsFragment
                NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_homeFragment_to_anotherFragment);
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}