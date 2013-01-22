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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapageneral);
		
		locationManager = (LocationManager)this.getSystemService(getApplicationContext().LOCATION_SERVICE);
		
		mapa = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.mapa)).getMap();
				
		init();
    }
	/** this criteria will settle for less accuracy, high power, and cost */
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
	/** this criteria needs high accuracy, high power, and cost */
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
	  make sure to call this in the main thread, not a background thread
	  make sure to call locMgr.removeUpdates(...) when you are done
	*/
	public void init(){
	  
	  // get low accuracy provider
	  LocationProvider low=
	    locationManager.getProvider(locationManager.getBestProvider(createCoarseCriteria(),false));
	 
	  // get high accuracy provider
	  LocationProvider high=
	    locationManager.getProvider(locationManager.getBestProvider(createFineCriteria(), false));
	 
	  // using low accuracy provider... to listen for updates
	  locationManager.requestLocationUpdates(low.getName(), 0, 0f,
	        new LocationListener() {
	        public void onLocationChanged(Location location) {
	          // do something here to save this new location
	          float lat = (float) (location.getLatitude());
	          float lng = (float) (location.getLongitude());
	          LatLng latLng = new LatLng(lat, lng);
	          actual = mapa.addMarker(new MarkerOptions().position(latLng).title("Rosario"));

	          Log.d("Localizacion", "Valor: " + location.toString() + " latitud: " + lat +
	                  " logitud: " + lng + "Valor al mapa" + latLng);
	          Toast.makeText(getApplicationContext(), location.toString(), Toast.LENGTH_LONG).show();
	          
	        }
	        public void onStatusChanged(String s, int i, Bundle bundle) {
	 
	        }
	        public void onProviderEnabled(String provider) {
	           // try switching to a different provider
	           Log.d("Localizacion", "Esta habilitado el proveedor");
	           Toast.makeText(getApplicationContext(), "Esta  habilitado el proveedor " + provider,
	                    Toast.LENGTH_SHORT).show();
	        }
	        public void onProviderDisabled(String provider) {
	           // try switching to a different provider
	           Log.d("Localizacion", "Esta desabilitado el proveedor");
	           Toast.makeText(getApplicationContext(), "El proveedor de ubicacion " + provider
	                   + " esta desabilitado", Toast.LENGTH_SHORT).show();
	           Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	              startActivity(settingsIntent);
	        }
	      });
	 
	  // using high accuracy provider... to listen for updates
	  locationManager.requestLocationUpdates(high.getName(), 0, 0f,
	        new LocationListener() {
	        public void onLocationChanged(Location location) {
	            // do something here to save this new location
	            float lat = (float) (location.getLatitude());
	            float lng = (float) (location.getLongitude());
	            LatLng latLng = new LatLng(lat, lng);
	            actual = mapa.addMarker(new MarkerOptions().position(latLng).title("Rosario"));
	            Log.d("Localizacion", "Valor: " + location.toString() + " latitud: " + lat +
	                      " logitud: " + lng + "Valor al mapa" + latLng);
	          Toast.makeText(getApplicationContext(), location.toString(), Toast.LENGTH_LONG).show();
	        }
	        public void onStatusChanged(String s, int i, Bundle bundle) {
	 
	        }
	        public void onProviderEnabled(String provider) {
	            // try switching to a different provider
	            Log.d("Localizacion", "Esta habilitado el proveedor");
	            Toast.makeText(getApplicationContext(), "Esta  habilitado el proveedor " + provider,
	                        Toast.LENGTH_SHORT).show();
	        }
	        public void onProviderDisabled(String provider) {
	          // try switching to a different provider
	          Log.d("Localizacion", "Esta desabilitado el proveedor");
	          Toast.makeText(getApplicationContext(), "El proveedor de ubicacion " + provider
	                  + " esta desabilitado", Toast.LENGTH_SHORT).show();
	          Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	          startActivity(settingsIntent);
	        }
	      });
	}
}