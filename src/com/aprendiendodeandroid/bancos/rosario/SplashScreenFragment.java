package com.aprendiendodeandroid.bancos.rosario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import roboguice.fragment.RoboFragment;

public class SplashScreenFragment extends RoboFragment{
	
	// Este objeto nos servira para tomar el log de errores
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(SplashScreenFragment.class);
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Aqui estamos realizando un inflate a splasscreen_fragment
		View view = inflater.inflate(R.layout.splashscreen_fragment, container, false);
		return view;
	}
}
