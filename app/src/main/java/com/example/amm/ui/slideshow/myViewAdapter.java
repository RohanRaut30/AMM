package com.example.amm.ui.slideshow;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.amm.Fragment.Fragment1894;
import com.example.amm.Fragment.Fragment1953;
import com.example.amm.Fragment.Fragment1954;
import com.example.amm.Fragment.Fragment2017;

public class myViewAdapter extends FragmentStateAdapter {
    public myViewAdapter(@NonNull SlideshowFragment fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new Fragment1894();
            case 1:
                return new Fragment1953();
            case 2:
                return new Fragment1954();
            case 3:
                return new Fragment2017();
            default:
                return new Fragment1894();

        }

    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
