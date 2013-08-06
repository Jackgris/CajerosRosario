package com.aprendiendodeandroid.bancos.rosario;

import android.content.Context;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;

import com.aprendiendodeandroid.bancos.rosario.utiles.LocationListenerGPS;
import com.aprendiendodeandroid.bancos.rosario.utiles.LocationListenerNetwork;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

/**
 * Esta clase la vamos a utilizar para manejar nuestra ubicacion y la de diferentes marcadores
 * en nuestros mapas, y tambien vamos a realizar los provesos de actualizacion de la ubicacion.
 * Como tambien convertir la latitud y la longitud a direccion entendibles por nosotros.
 * @author jackgris
 *
 */
public class MapaGeneral extends android.support.v4.app.FragmentActivity {

    public final static String MARCADOR = "marcador";
    public final static String MARCADORES = "marcadores";
	public static GoogleMap mapa;
	private LocationManager locationManager;
	private LocationListenerNetwork locationListenerNET = new LocationListenerNetwork();
	private LocationListenerGPS locationListenerGPS = new LocationListenerGPS();
	public static Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapageneral);
		context = getApplicationContext();
		
        locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        
		// obtenemos el mapa desde el fragment
		mapa = (((SupportMapFragment)getSupportFragmentManager().
		        findFragmentById(R.id.mapa)).getMap());
		
		// seteamos para que este el boton de buscar nuestra ubicacion activado
        if(mapa != null){
            mapa.setMyLocationEnabled(true);
        }
				
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

	  // FIXME tengo que comprobar en realidad cada cierto tiempo si el GPS tomo ubicacion
	  if(locationListenerGPS.getActual() != null){
		  // usamos el proveedor que tiene mayor precision... le agregamos el listener para actualizar
		  locationManager.requestLocationUpdates(high.getName(), 0, 0f, locationListenerGPS);
	  }
	  else{
		  // usamos el proveedor de baja precision... y le ponemos el listener para que lo actualice
		  locationManager.requestLocationUpdates(low.getName(), 0, 0f, locationListenerNET);
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

        mapa.clear();
	}
	
	/**
	 * Volvemos a arrancar las actualizaciones
	 */
	@Override
	protected void onResume() {
		super.onResume();
		iniciamosLaEscucha();
	}

    private void agregarMarcador(){
        //FIXME con este metodo vamos a agregar un marcador al mapa
    }

    private void agregarListaMarcadore(){
        // FIXME con este metodo vamos a agregar una lista de marcadores
    }
}