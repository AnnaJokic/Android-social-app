package com.example.project2.adapter;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.example.project2.Fragments.Fragment1;
import com.example.project2.Fragments.Fragment2;
import com.example.project2.Fragments.Fragment3;
import com.example.project2.R;

import java.util.ArrayList;
import java.util.List;

public class SimplePagerAdapter extends FragmentPagerAdapter {

    private static final int FRAGMENT_COUNT = 3;


    private List<String> mTitles;

    public SimplePagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        initTitles(context);

    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return Fragment1.newInstance();
            case 1:
                return Fragment2.newInstance();
            case 2:
                return Fragment3.newInstance();

        }

        return null;
    }

    @Override
    public int getCount() {
        return FRAGMENT_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    private void initTitles(Context context) {
        mTitles = new ArrayList<>();

        mTitles.add(context.getString(R.string.first_fragment));
        mTitles.add(context.getString(R.string.second_fragment));
        mTitles.add(context.getString(R.string.thrid_fragment));



    }
}
