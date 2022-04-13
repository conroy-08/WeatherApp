package com.example.weather;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.weather.fragment.detail.DetailFragment;
import com.example.weather.fragment.today.TodayFragment;
import com.example.weather.fragment.weekly.WeeklyFragment;

public class MyViewPagerAdapter extends FragmentStateAdapter {


    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new TodayFragment();
            case 1:
                return new WeeklyFragment();
            case 2:
                return new DetailFragment();
            default:
                return new TodayFragment();
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
