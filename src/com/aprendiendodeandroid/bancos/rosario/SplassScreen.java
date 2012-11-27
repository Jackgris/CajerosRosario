package com.aprendiendodeandroid.bancos.rosario;

import com.aprendiendodeandroid.bancos.rosario.ManageTabsActivity;
import com.aprendiendodeandroid.bancos.rosario.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;


public class SplassScreen extends Activity{


	// Set the display time, in milliseconds (or extract it out as a configurable parameter)
    private final int SPLASH_DISPLAY_LENGTH = 3000;
 
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
    }
 
    @Override
    protected void onResume()
    {
        super.onResume();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        // Obtain the sharedPreference, default to true if not available
        boolean isSplashEnabled = sp.getBoolean("isSplashEnabled", true);
 
        if (isSplashEnabled)
        {
            new Handler().postDelayed(new Runnable()
            {
                public void run()
                {
                    //Finish the splash activity so it can't be returned to.
                    SplassScreen.this.finish();
                    // Create an Intent that will start the main activity.
                    Intent mainIntent = new Intent(SplassScreen.this, ManageTabsActivity.class);
                    SplassScreen.this.startActivity(mainIntent);
                }
            }, SPLASH_DISPLAY_LENGTH);
        }
        else
        {
            // if the splash is not enabled, then finish the activity immediately and go to main.
            finish();
            Intent mainIntent = new Intent(SplassScreen.this, ManageTabsActivity.class);
            SplassScreen.this.startActivity(mainIntent);
        }
    }

}