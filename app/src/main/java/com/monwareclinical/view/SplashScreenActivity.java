package com.monwareclinical.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.monwareclinical.R;


import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;


public class SplashScreenActivity extends AppCompatActivity {

    Activity fa;

    final long MILLS = 500L;

    public static final Object lock = new Object();
    public static int loading = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        fa = this;
    }

    void continueToLogin() {
        startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    void continueToApp() {
        startActivity(new Intent(SplashScreenActivity.this, MenuActivity.class));
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void onStart() {
        super.onStart();
        new Handler().postDelayed(() -> {
            Thread t = new Thread() {
                public void run() {
                    synchronized (lock) {
                        while (loading == 0)
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        // User has been checked and the app is now running

                        switch (loading) {
                            // User is logged
                            case 1:
                                continueToApp();
                                break;
                            // User is not logged
                            case 2:
                                continueToLogin();
                                break;
                        }
                    }
                }
            };
            t.start();

           checkUser();

            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, MILLS);
    }

    void checkUser() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            synchronized (SplashScreenActivity.lock) {
                SplashScreenActivity.loading = 1;
                SplashScreenActivity.lock.notify();
            }
        } else {
            synchronized (SplashScreenActivity.lock) {
                SplashScreenActivity.loading = 2;
                SplashScreenActivity.lock.notify();
            }
        }
    }
}