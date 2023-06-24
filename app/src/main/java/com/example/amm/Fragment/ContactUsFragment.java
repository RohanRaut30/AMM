package com.example.amm.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amm.R;
import com.example.amm.databinding.FragmentSlideshowBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
//test
public class ContactUsFragment extends Fragment {

    private FragmentSlideshowBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        return root;
    }
}