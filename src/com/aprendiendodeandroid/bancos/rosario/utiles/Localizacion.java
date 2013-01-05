package com.aprendiendodeandroid.bancos.rosario.utiles;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * Esta clase la vamos a utilizar para manejar nuestra ubicacion y la de diferentes marcadores
 * en nuestros mapas
 * @author jackgris
 *
 */
public class Localizacion extends Activity{
	
	// Obtenemos una referencia al manejadores de ubicaciones que nos provee Android
	private LocationManager locationManager = (LocationManager)this.
			getSystemService(Context.LOCATION_SERVICE);

	
	protected void onCreate(Bundle savedInstanceState) {
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, 
				locationListener);
	};
	
	
	// Este va a ser el listener para el cambio de ubicacion
	private LocationListener locationListener = new LocationListener() {
		
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// FIXME vamos a manejar los diferentes cambios de estados
			
		}
		
		public void onProviderEnabled(String provider) {
			// FIXME cuando esta dispobible el proovedor de ubicacion
			
		}
		
		public void onProviderDisabled(String provider) {
			// FIXME  cuando no esta disponible nuestro proveedor de ubicacion
			
		}
		
		public void onLocationChanged(Location location) {
			// FIXME Este metodo es el que va a manejar los cambios de ubicacion		
						
		}
	};

}
