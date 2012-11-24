package com.aprendiendodeandroid.bancos.rosario;

import com.aprendiendodeandroid.bancos.rosario.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import roboguice.fragment.RoboFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SplashScreenFragment extends RoboFragment {

	 @SuppressWarnings("unused")
	private static final Logger log = LoggerFactory
	            .getLogger(SplashScreenFragment.class);
	    
	 
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	        View view = inflater.inflate(R.layout.splashscreen, container,
	                false);
	        return view;
	    }
}
