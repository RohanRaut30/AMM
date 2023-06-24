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
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment= new Fragment1894();
                break;
            case 1:
                fragment= new Fragment1953();
                break;
            case 2:
                fragment= new Fragment1954();
                break;
            case 3:
                fragment= new Fragment2017();
                break;

        }
        return fragment;

    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
