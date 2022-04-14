package com.example.weather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.weather.fragment.FragmentListener;
import com.example.weather.fragment.settings.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements FragmentListener {

    private ViewPager2 mViewPager2;
    private BottomNavigationView bottomNavigationView;
    private NavigationView navigationView;

    private String cityName;
    private String days;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView  = findViewById(R.id.nav_menu);
        mViewPager2 = findViewById(R.id.view_pager_2);
        navigationView = findViewById(R.id.navView);


        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(this);
        mViewPager2.setAdapter(myViewPagerAdapter);




        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.settings:

                        SettingsFragment fragment = new SettingsFragment();
                        fragment.show(getSupportFragmentManager(),"dialog");
                        System.out.println("Settings is clicked");
                        return true;
                    case R.id.about:
                        System.out.println("About is clicked");
                        return true;
                }
                return true;
            }
        });



        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id==R.id.navigation_today){
                   mViewPager2.setCurrentItem(0);
                }else if (id==R.id.navigation_weekly){
                    mViewPager2.setCurrentItem(1);
                }else if (id==R.id.navigation_detail){
                    mViewPager2.setCurrentItem(2);
                }
                return true;
            }
        });

        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.navigation_today).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.navigation_weekly).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.navigation_detail).setChecked(true);
                        break;
                }
            }
        });

    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return "MainActivity{" +
                "cityName='" + cityName + '\'' +
                ", days='" + days + '\'' +
                '}';
    }

    @Override
    public void applyTexts(String cityName, String numberDays) {
        setDays(days);
        setCityName(cityName);
    }
}