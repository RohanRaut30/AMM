package com.example.amm.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.amm.Fragment.Fragment1894;
import com.example.amm.Fragment.Fragment1953;
import com.example.amm.Fragment.Fragment1954;
import com.example.amm.Fragment.Fragment2017;
import com.example.amm.R;
import com.example.amm.databinding.FragmentSlideshowBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class SlideshowFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;


    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        tabLayout = root.findViewById(R.id.tablayout);
        viewPager = root.findViewById(R.id.view);

        // Create a list of tab names
        final String[] tabNames = {
                getString(R.string.histoname1),
                getString(R.string.histoname2),
                getString(R.string.histoname3),
                getString(R.string.histoname4)
        };

        // Set up the ViewPager adapter
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager(), getLifecycle());
        viewPager.setAdapter(adapter);

        // Connect the TabLayout and ViewPager
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(tabNames[position])).attach();

        return root;
    }

    private static class ViewPagerAdapter extends FragmentStateAdapter {
        public ViewPagerAdapter(FragmentManager fragmentManager, Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }


        @Override
        public Fragment createFragment(int position) {
            // Create and return the fragment for the corresponding tab position
            // Replace PlaceholderFragment with your actual fragment class
            switch (position) {
                case 0:
                    return new Fragment1894();
                case 1:
                    return new Fragment1953();
                case 2:
                    return new Fragment1954();
                case 3:
                    return new Fragment2017();
                default:
                    return null;
            }


        }

        @Override
        public int getItemCount() {
            return 4;
        }
    }
}
