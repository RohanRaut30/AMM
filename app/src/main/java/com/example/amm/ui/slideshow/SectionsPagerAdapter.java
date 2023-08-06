package com.example.amm.ui.slideshow;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.amm.Fragment.Fragment1894;
import com.example.amm.Fragment.Fragment1953;
import com.example.amm.Fragment.Fragment1954;
import com.example.amm.Fragment.Fragment2017;
import com.example.amm.R;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    @StringRes
    private static final int[] TAB_TITLES = new int[]{
            R.string.histoname1,
            R.string.histoname2,
            R.string.histoname3,
            R.string.histoname4

    };
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position)
        {
            case 0:fragment=new Fragment1894();
                break;
            case 1:fragment=new Fragment1953();
                break;
            case 2:fragment=new Fragment1954();
                break;
            case 3:fragment=new Fragment2017();
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {

        return 4;
    }
}
