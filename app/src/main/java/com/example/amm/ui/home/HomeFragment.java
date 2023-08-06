package com.example.amm.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.amm.R;
import com.example.amm.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private TextView textView;
    private TextView textView3;
    private TextView textview4;
    private String textToAnimate = "Akhil Mandai \nMandal";
    private int index = 0;
    private NavController navController;
    private CardView cardStudent;
    private CardView ecoFriendly;
    private Button btnTrust;


    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        textView = root.findViewById(R.id.text2);

        textView();

        //for Student adoptation card view
        cardStudent = root.findViewById(R.id.cardStudent);
        cardStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Navigate to the Student Adoptation
                NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_mainFragment_to_studentAdaptation);

            }
        });

        //for We Are Eco Friendly

        ecoFriendly = root.findViewById(R.id.ecoFriendly);
        ecoFriendly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Navigate to the Eco Friendly
                NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_mainFragment_to_EcoFriendly);

            }
        });

        btnTrust = root.findViewById(R.id.btnTrust);
        btnTrust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the AboutUsFragment
                NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_home_to_trustPage);

            }
        });




        Button btn = root.findViewById(R.id.btnhome1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the AboutUsFragment
                NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_mainFragment_to_aboutUsFragment);
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