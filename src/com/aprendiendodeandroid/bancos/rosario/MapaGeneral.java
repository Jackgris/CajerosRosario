package com.aprendiendodeandroid.bancos.rosario;

import android.os.Bundle;

import com.google.android.maps.MapActivity;

public class MapaGeneral extends MapActivity {

	@Override
	protected void onCreate(Bundle icicle) {
		// TODO Auto-generated method stub
		super.onCreate(icicle);
		setContentView(R.layout.mapageneral);
		
	}
	
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
