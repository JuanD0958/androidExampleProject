package com.example.juanherrera.myfirstapp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.juanherrera.myfirstapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    //Store the binding
    private ActivityMainBinding activityMainBinding;
    private BottomSheetBehavior mBottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        BottomNavigationView bottomNavigationView = activityMainBinding.bottomNavigation;
        BottomNavigationView bottomNavigationViewSecondary = activityMainBinding.bottomNavigationSecondary;
        menuActions(bottomNavigationView);
        menuActions(bottomNavigationViewSecondary);
    }

    private void menuActions(BottomNavigationView bottomNavigationView){
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
        new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()){
                    case R.id.nav_home:
                        selectedFragment = new HomeFragment();
                        mBottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.bottom_navigation_secondary));
                        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                        break;

                    case R.id.nav_profile:
                        selectedFragment = new ProfileFragment();
                        mBottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.bottom_navigation_secondary));
                        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                        break;

                    case R.id.more_options:
                        mBottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.bottom_navigation_secondary));
                        if(mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED){
                            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        }else{
                            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        }
                        break;
                }
                return true;
            }
        };
}