package com.devworms.ejemplo.splash;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by DevWorms S.A. de C.V. on 12/10/15.
 */
public class MainActivity extends FragmentActivity {

    private static final long SPLASH_SCREEN_DELAY = 3000;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hide action bar whereas the splash screen is visible
        getActionBar().hide();

        if (savedInstanceState == null) {
            // Show the splash screen at the beginning
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new SplashScreenFragment()).commit();
        }

        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                // Replace the splash screen fragment to main fragment and
                // specific animation resources to run for the fragments that
                // are entering and exiting in this transaction.
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new MainFragment()).commit();

                // Show action bar when the main fragment is visible
                runOnUiThread(new Runnable() {
                    public void run() {
                        getActionBar().show();
                    }
                });

            }

        };

        // Simulate a long loading process on application startup.
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }
}
