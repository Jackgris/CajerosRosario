package com.aprendiendodeandroid.bancos.rosario;

import android.os.Bundle;
import android.preference.PreferenceActivity;

//import com.aprendiendodeandroid.bancos.rosario.R;


public class EditPreferences extends PreferenceActivity {
	
	  @Override
	    public void onCreate(Bundle savedInstanceState)
	    {
	        super.onCreate(savedInstanceState);
	    }
	 
	    @SuppressWarnings("deprecation")
		@Override
	    protected void onResume()
	    {
	        super.onResume();
	        addPreferencesFromResource(R.xml.preferences);
	    }
}
