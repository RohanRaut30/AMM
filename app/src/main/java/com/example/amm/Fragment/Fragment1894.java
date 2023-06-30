package com.example.amm.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.amm.R;
import com.example.amm.databinding.FragmentSlideshowBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class Fragment1894 extends Fragment {

    private FragmentSlideshowBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        return inflater.inflate(R.layout.fragment_1894, container, false);


    }
}