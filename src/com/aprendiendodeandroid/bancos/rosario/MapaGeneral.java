package com.aprendiendodeandroid.bancos.rosario;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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
	
		// con este objeto podemos nanejar la ubicacion actual
		LocationListener locationListener = new LocationListener() {
			
			public void onStatusChanged(String provider, int status, Bundle extras) {
				// FIXME tenemos que manejar el cambio de estados
				
			}
			
			public void onProviderEnabled(String provider) {
				// FIXME esto es cuando tenemos habilitado el proveer los datos
				
			}
			
			public void onProviderDisabled(String provider) {
				// FIXME esto es por si tenemos desabilitado los datos
				
			}
			
			public void onLocationChanged(Location location) {
				// FIXME este es el método pricipal a modificar
				
				
			}
		};
		
		// con esto deberiamos manejar las posiciones 
		LocationManager locationManager =(LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
		
		// Aca tomamos la latitud y longitud
		CameraPosition posiscioncamara = mapa.getCameraPosition();
		LatLng coordenada = posiscioncamara.target;
		
		// establecemos los parametros para nuestra camara : ubicacion posicion angulo etc etc
		CameraPosition lugaractual = new CameraPosition.Builder().target(coordenada).build();
				
		CameraUpdate posicionactual = CameraUpdateFactory.newCameraPosition(lugaractual);
	
		mapa.moveCamera(posicionactual);
	}
}
