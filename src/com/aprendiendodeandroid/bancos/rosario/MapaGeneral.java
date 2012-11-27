package com.aprendiendodeandroid.bancos.rosario;

import android.os.Bundle;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

public class MapaGeneral extends MapActivity {

	private MapView mapageneral;
	
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.mapageneral);
		
		// tomo referencia del mapa
		mapageneral = (MapView)findViewById(R.id.mapageneral);
		
		mapageneral.setBuiltInZoomControls(true);
		
	}
	
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
