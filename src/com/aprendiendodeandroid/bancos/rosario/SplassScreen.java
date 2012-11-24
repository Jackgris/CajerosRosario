package com.aprendiendodeandroid.bancos.rosario;

<<<<<<< HEAD
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
=======
//import org.michenux.android.init.AppInit;

import com.aprendiendodeandroid.bancos.rosario.R;
>>>>>>> primerosCambios

import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.ContentView;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


@ContentView(R.layout.fragment)
public class SplassScreen extends RoboFragmentActivity{

	 protected MyStateSaver data;

	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);

	        this.data = (MyStateSaver) getLastCustomNonConfigurationInstance();
	        if (this.data == null) {
	            this.data = new MyStateSaver();
	        }
	        if (this.data.doInit) {
	            doInit();
	        }
	    }

	    @Override
	    public Object onRetainCustomNonConfigurationInstance() {
	        return this.data;
	    }

	    protected void startNextActivity() {
	        Intent intent = new Intent(SplassScreen.this, ManageTabsActivity.class);
	        this.startActivity(intent);
	        this.finish();
	    }

	    protected void doInit() {
	        this.data.doInit = false;
	        final Handler handler = new Handler();
	        handler.postDelayed(new Runnable() {
	            public void run() {
	                try {
	                    //TODO: Your application init goes here.
	                    startNextActivity();
	                } catch( Exception e ) {
	                    throw new RuntimeException(e);
	                }
	            }
	        }, 2000);
	    }

	    private class MyStateSaver {
	        public boolean doInit = true;
	    }
}
