package com.aprendiendodeandroid.bancos.rosario;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import android.os.Bundle;

public class MapaGeneral extends android.support.v4.app.FragmentActivity {

	final float posicionzoomgeneral = 10;
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.mapageneral);
		
		// Obtenemos la referencia al mapa
		GoogleMap mapa = ((SupportMapFragment)getSupportFragmentManager()
				.findFragmentById(R.id.mapa)).getMap();
		// configuramos el tipo de mapa
		mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		
		// Aca tomamos la latitud y longitud
		CameraPosition posiscioncamara = mapa.getCameraPosition();
		LatLng coordenada = posiscioncamara.target;
		
		// establecemos los parametros para nuestra camara : ubicacion posicion angulo etc etc
		CameraPosition lugaractual = new CameraPosition.Builder().target(coordenada).build();
				
		CameraUpdate posicionactual = CameraUpdateFactory.newCameraPosition(lugaractual);
	
		mapa.moveCamera(posicionactual);
	}
}
