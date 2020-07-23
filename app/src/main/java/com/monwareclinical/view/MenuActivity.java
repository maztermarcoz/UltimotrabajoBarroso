package com.monwareclinical.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.monwareclinical.R;
import com.monwareclinical.util.SetUpToolBar;

public class MenuActivity extends AppCompatActivity {
    Activity fa;

    SetUpToolBar toolBar;
    BottomNavigationView bottomNavigationView;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        initComps();
        initActions();
        showToolbar();
        setUpBottomNav();
    }

    void initComps() {
        fa = this;
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
    }

    void initActions() {
    }

    void showToolbar() {
        toolBar = new SetUpToolBar(fa, false, "Menú", mUser.getPhotoUrl());
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    void setUpBottomNav() {
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        toolBar.setTitle(getString(R.string.menu_home));
        loadFragment(new HomeFragment());

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    toolBar.setTitle(getString(R.string.menu_home));
                    fragment = HomeFragment.newInstance();
                    break;

                case R.id.navigation_clinic:
                    toolBar.setTitle(getString(R.string.menu_building));
                    fragment = ClinicFragment.newInstance();
                    break;
                case R.id.navigation_medicines:
                    toolBar.setTitle(getString(R.string.menu_medicines));
                    fragment = ClinicFragment.newInstance();
                    break;
            }

            return loadFragment(fragment);
        });
    }

    boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}