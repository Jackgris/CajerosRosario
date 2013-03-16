package com.aprendiendodeandroid.bancos.rosario;

import com.aprendiendodeandroid.bancos.rosario.utiles.LocationListenerGPS;
import com.aprendiendodeandroid.bancos.rosario.utiles.LocationListenerNetwork;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;

/**
 * Esta clase la vamos a utilizar para manejar nuestra ubicacion y la de diferentes marcadores
 * en nuestros mapas, y tambien vamos a realizar los provesos de actualizacion de la ubicacion.
 * Como tambien convertir la latitud y la longitud a direccion entendibles por nosotros.
 * @author jackgris
 *
 */
public class MapaGeneral extends android.support.v4.app.FragmentActivity {

	public static GoogleMap mapa;
	//private static final LatLng ROSARIO = new LatLng(-32.962, -780.662);
	//final private float posicionzoomgeneral = 17;
	private LocationManager locationManager;
	private LocationListenerNetwork locationListenerNET = new LocationListenerNetwork();
	private LocationListenerGPS locationListenerGPS = new LocationListenerGPS();
	public static Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapageneral);
		context = getApplicationContext();
		
		getApplicationContext();
        locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        
		
		mapa = (((SupportMapFragment)getSupportFragmentManager().
		        findFragmentById(R.id.mapa)).getMap());	
		mapa.setMyLocationEnabled(true);
		
		iniciamosLaEscucha();
    }
	/** 
	 hay que asegurarse de usar esta funcion en el hilo principal, y no en un hilo en background 
	 hay que asegurarse de remover las actualizaciones cuando no se use mas
	*/
	public void iniciamosLaEscucha(){
	  // baja precision
	  LocationProvider low=
	    locationManager.getProvider(locationManager.getBestProvider(LocationListenerNetwork.
	            crearCriteriaRed(),false));
	 
	  // alta precision
	  LocationProvider high=
	    locationManager.getProvider(locationManager.getBestProvider(LocationListenerGPS.
	            crearCriteriaGPS(), false));
	 
	  // usamos el proveedor de baja precision... y le ponemos el listener para que lo actualice
	  locationManager.requestLocationUpdates(low.getName(), 0, 0f, locationListenerNET);

	  // usamos el proveedor que tiene mayor precision... le agregamos el listener para actualizar
	  locationManager.requestLocationUpdates(high.getName(), 0, 0f, locationListenerGPS);
	  
	  // TODO hay que verificar que esto funcione correctamente
	  if (locationListenerGPS.getMapa() != null) {
	      mapa = locationListenerGPS.getMapa();
	      
	      String locationProvider = LocationManager.GPS_PROVIDER;
	      
	      Location ultimaUbicacion = locationManager.getLastKnownLocation(locationProvider);
	      

	      mapa.addMarker(new MarkerOptions()
	        .position(new LatLng(ultimaUbicacion.getLatitude(), ultimaUbicacion.getLongitude()))
	        .title("Aquí estas ;)"));
	  }
	  else if (locationListenerNET.getMapa() != null) {
	      mapa = locationListenerNET.getMapa();
			
	      String locationProvider = LocationManager.NETWORK_PROVIDER;
	      
	      Location ultimaUbicacion = locationManager.getLastKnownLocation(locationProvider);
	      
	      mapa.addMarker(new MarkerOptions()
	        .position(new LatLng(ultimaUbicacion.getLatitude(), ultimaUbicacion.getLongitude()))
	        .title("Aquí estas ;)"));
	  }
	}
	/**
	 * Vamos a detener la escucha de nuestro listener y todo tipo de actualizacion	
	 */
	@Override
	protected void onStop() {
		super.onStop();
		locationManager.removeUpdates(locationListenerGPS);
		locationManager.removeUpdates(locationListenerNET);
	}
	/**
	 * Vamos a detener la escucha de nuestro listener y todo tipo de actualizacion 
	 */
	@Override
	protected void onPause() {
		super.onPause();
		locationManager.removeUpdates(locationListenerGPS);
		locationManager.removeUpdates(locationListenerNET);
	}
	/**
	 * Volvemos a arrancar las actualizaciones
	 */
	@Override
	protected void onResume() {
		super.onResume();
		iniciamosLaEscucha();
	}  
}