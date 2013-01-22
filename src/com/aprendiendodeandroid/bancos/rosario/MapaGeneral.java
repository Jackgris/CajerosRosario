package com.aprendiendodeandroid.bancos.rosario;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

/**
 * Esta clase la vamos a utilizar para manejar nuestra ubicacion y la de diferentes marcadores
 * en nuestros mapas, y tambien vamos a realizar los provesos de actualizacion de la ubicacion.
 * Como tambien convertir la latitud y la longitud a direccion entendibles por nosotros.
 * @author jackgris
 *
 */
public class MapaGeneral extends android.support.v4.app.FragmentActivity {

	private GoogleMap mapa;
	static final LatLng ROSARIO = new LatLng(-32.962, -780.662);
	final float posicionzoomgeneral = 17;
	LocationManager locationManager;
	Marker actual;
	LocationListener locationListenerNET;
	LocationListener locationListenerGPS;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapageneral);
		
		locationManager = (LocationManager)this.getSystemService(getApplicationContext().LOCATION_SERVICE);
		locationListenerGPS =
		        new LocationListener() {
		        public void onLocationChanged(Location location) {
		            // aca vamos a realizar una accion al tomar un nuevo valor
		            float lat = (float) (location.getLatitude());
		            float lng = (float) (location.getLongitude());
		            LatLng latLng = new LatLng(lat, lng);
		            actual = mapa.addMarker(new MarkerOptions().position(latLng).title("Rosario"));
		            Log.d("Localizacion", "Valor: " + location.toString() + " latitud: " + lat +
		                      " logitud: " + lng + "Valor al mapa" + latLng);
		        }
		        public void onStatusChanged(String s, int i, Bundle bundle) {
		 
		        }
		        public void onProviderEnabled(String provider) {
		            // vamos a intentar cambiar con otros proveedores
		            Log.d("Localizacion", "Esta habilitado el proveedor " + provider);
		        }
		        public void onProviderDisabled(String provider) {
		          // vamos a intentar cambiar con otros proveedores
		          Log.d("Localizacion", "Esta desabilitado el proveedor");
		          Toast.makeText(getApplicationContext(), "El proveedor de ubicacion " + provider
		                  + " esta desabilitado", Toast.LENGTH_SHORT).show();
		          Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		          startActivity(settingsIntent);
		        }
		      };
        locationListenerNET = new LocationListener() {
		        public void onLocationChanged(Location location) {
		          // aca vamos a realizar una accion al tomar un nuevo valor
		          float lat = (float) (location.getLatitude());
		          float lng = (float) (location.getLongitude());
		          LatLng latLng = new LatLng(lat, lng);
		          actual = mapa.addMarker(new MarkerOptions().position(latLng).title("Rosario"));

		          Log.d("Localizacion", "Valor: " + location.toString() + " latitud: " + lat +
		                  " logitud: " + lng + "Valor al mapa" + latLng);		          
		        }
		        public void onStatusChanged(String s, int i, Bundle bundle) {
		 
		        }
		        public void onProviderEnabled(String provider) {
		           // vamos a intentar cambiar con otros proveedores
		           Log.d("Localizacion", "Esta habilitado el proveedor");
		        }
		        public void onProviderDisabled(String provider) {
		           // vamos a intentar cambiar con otros proveedores
		           Log.d("Localizacion", "Esta desabilitado el proveedor");
		           Toast.makeText(getApplicationContext(), "El proveedor de ubicacion " + provider
		                   + " esta desabilitado", Toast.LENGTH_SHORT).show();
		           Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		              startActivity(settingsIntent);
		        }
		      };
		
		mapa = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.mapa)).getMap();
				
		init();
    }
	/** esta criteria va a tener menos precision, consumir menos energia, y un menor costo */
	public static Criteria createCoarseCriteria() {
	 
	  Criteria c = new Criteria();
	  c.setAccuracy(Criteria.ACCURACY_COARSE);
	  c.setAltitudeRequired(false);
	  c.setBearingRequired(false);
	  c.setSpeedRequired(false);
	  c.setCostAllowed(true);
	  c.setPowerRequirement(Criteria.POWER_HIGH);
	  return c;
	}
	/** esta criteria va a tener una mayor precision, consumir mas energia y un costo mayor*/
	public static Criteria createFineCriteria() {
	 
	  Criteria c = new Criteria();
	  c.setAccuracy(Criteria.ACCURACY_FINE);
	  c.setAltitudeRequired(false);
	  c.setBearingRequired(false);
	  c.setSpeedRequired(false);
	  c.setCostAllowed(true);
	  c.setPowerRequirement(Criteria.POWER_HIGH);
	  return c;
	}
	/** 
	 hay que asegurarse de usar esta funcion en el hilo principal, y no en un hilo en background 
	 hay que asegurarse de remover las actualizaciones cuando no se use mas
	*/
	public void init(){
	  // baja precision
	  LocationProvider low=
	    locationManager.getProvider(locationManager.getBestProvider(createCoarseCriteria(),false));
	 
	  // alta precision
	  LocationProvider high=
	    locationManager.getProvider(locationManager.getBestProvider(createFineCriteria(), false));
	 
	  // usamos el proveedor de baja precision... y le ponemos el listener para que lo actualice
	  locationManager.requestLocationUpdates(low.getName(), 0, 0f, locationListenerNET);

	  // usamos el proveedor que tiene mayor precision... le agregamos el listener para actualizar
	  locationManager.requestLocationUpdates(high.getName(), 0, 0f, locationListenerGPS);			  

	}
		
	@Override
	protected void onStop() {
		// aca vamos a remover todas las actualizaciones
		super.onStop();
		locationManager.removeUpdates(locationListenerGPS);
		locationManager.removeUpdates(locationListenerNET);
	}
	
	@Override
	protected void onPause() {
		// aca vamos a remover todas las actualizaciones
		super.onPause();
		locationManager.removeUpdates(locationListenerGPS);
		locationManager.removeUpdates(locationListenerNET);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		init();
	}
	
}