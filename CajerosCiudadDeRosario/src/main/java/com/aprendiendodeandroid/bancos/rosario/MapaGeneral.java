package com.aprendiendodeandroid.bancos.rosario;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.aprendiendodeandroid.bancos.rosario.modelo.Cajero;
import com.aprendiendodeandroid.bancos.rosario.modelo.CajerosDAO;
import com.aprendiendodeandroid.bancos.rosario.modelo.CajerosDAOImpl;
import com.aprendiendodeandroid.bancos.rosario.utiles.LocationListenerGPS;
import com.aprendiendodeandroid.bancos.rosario.utiles.LocationListenerNetwork;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Esta clase la vamos a utilizar para manejar nuestra ubicacion y la de diferentes marcadores
 * en nuestros mapas, y tambien vamos a realizar los provesos de actualizacion de la ubicacion.
 * Como tambien convertir la latitud y la longitud a direccion entendibles por nosotros.
 * @author jackgris
 *
 */
public class MapaGeneral extends android.support.v4.app.FragmentActivity {

    private static final String TAG = "MapaGeneral";
    private static final int BANELCO = 1;
    private static final int LINK = 2;
	public static GoogleMap mapa;
	private LocationManager locationManager;
	private LocationListenerNetwork locationListenerNET = new LocationListenerNetwork();
	private LocationListenerGPS locationListenerGPS = new LocationListenerGPS();
	public static Context context;
    private SharedPreferences settings;
    private static Location location = null;
    private Button botonCentrarMapa;
    private Button botonCajerosBanelco;
    private Button botonCajerosLink;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapageneral);
		context = getApplicationContext();
		
        locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);

		// obtenemos el mapa desde el fragment
		mapa = (((SupportMapFragment)getSupportFragmentManager().
		        findFragmentById(R.id.mapa)).getMap());

        botonCajerosBanelco = (Button)findViewById(R.id.buttonCajerosBanelco);
        botonCajerosLink = (Button)findViewById(R.id.buttonCajerosLink);
        botonCentrarMapa = (Button)findViewById(R.id.buttonCentrarMapa);
		
		// seteamos para que este el boton de buscar nuestra ubicacion activado
//        if(mapa != null){
//            mapa.setMyLocationEnabled(true);
//        }

        centrarMapa();
        mostrarCajerosBanelco();
        mostrarCajerosRedLink();
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

    @Override
    protected void onStart() {
        super.onStart();

   }

    /**
	 * Vamos a detener la escucha de nuestro listener y todo tipo de actualizacion 
	 */
	@Override
	protected void onPause() {
		super.onPause();
		locationManager.removeUpdates(locationListenerGPS);
		locationManager.removeUpdates(locationListenerNET);

        // limpiamos nuestro mapa al salir del mismo
        mapa.clear();
	}
	
	/**
	 * Volvemos a arrancar las actualizaciones
	 */
	@Override
	protected void onResume() {
		super.onResume();
		iniciamosLaEscucha();

        settings = getSharedPreferences("Cajeros", 0);
        if(settings.getInt("cajero", 0) != 0){
            agregarUnMarcador();
        }
	}

    /**
     * Con este metodo vamos a agregar un marcador al mapa, en este caso almacenado en nuestro
     * {@link SharedPreferences}
     */
    private void agregarUnMarcador(){

        CajerosDAO cajerosDAO = new CajerosDAOImpl();
        Cajero cajero = cajerosDAO.consultaUnCajero(getApplicationContext(),
                settings.getInt("cajero", 0));

        String nombre = cajero.getNombreBanco();

        // armamos la ubicacion del marcador
        LatLng ubicacion  = new LatLng(cajero.getLatitud(), cajero.getLongitud());

        // agregamos el marcador a nuestro mapa
        mapa.addMarker(new MarkerOptions().position(ubicacion).title(nombre));

        if(location != null){
            LatLng miUbicacion = new LatLng(location.getLatitude(), location.getLongitude());

            PolylineOptions dibujoLinea = new PolylineOptions();
            dibujoLinea.add(ubicacion);
            dibujoLinea.add(miUbicacion);

            mapa.addPolyline(dibujoLinea);
        }
    }

    private void agregarListaMarcadore(int tipoCajero){
        List<Cajero> cajeros;
        CajerosDAO cajerosDAO = new CajerosDAOImpl();

        if(tipoCajero == BANELCO){
            cajeros = cajerosDAO.consultaCajerosBanelco(getApplicationContext());

            for(Cajero cajero : cajeros){
                String nombre = cajero.getNombreBanco();

                // armamos la ubicacion del marcador
                LatLng ubicacion  = new LatLng(cajero.getLatitud(), cajero.getLongitud());

                // agregamos el marcador a nuestro mapa
                mapa.addMarker(new MarkerOptions().position(ubicacion).title(nombre));
            }

        }

        if(tipoCajero == LINK){
            cajeros = cajerosDAO.consultaCajerosLink(getApplicationContext());

            for(Cajero cajero : cajeros){
                String nombre = cajero.getNombreBanco();

                // armamos la ubicacion del marcador
                LatLng ubicacion  = new LatLng(cajero.getLatitud(), cajero.getLongitud());

                // agregamos el marcador a nuestro mapa
                mapa.addMarker(new MarkerOptions().position(ubicacion).title(nombre));
            }
        }
    }

    /**
     * Accion boton centrar mapa
     */
    private void centrarMapa(){
        botonCentrarMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moverMapa();
            }
        });
    }

    /**
     * Accion boton mostrar cajeros banelco
     */
    private void mostrarCajerosBanelco(){
        botonCajerosBanelco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarListaMarcadore(BANELCO);
            }
        });
    }

    /**
     * Accion boton mostrar cajeros red link
     */
    private void mostrarCajerosRedLink(){
        botonCajerosLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarListaMarcadore(LINK);
            }
        });
    }

    /**
     * Vamos a centrar el mapa, en la ultima ubicacion que alla tomado el GPS
     */
    private void moverMapa(){

        if(location != null){
            LatLng miUbicacion = new LatLng(location.getLatitude(), location.getLongitude());
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(miUbicacion)      // seteamos el centro del mapa en la posicion actual
                    .zoom(14)                   // configuramos el zoom
                    .bearing(90)                // seteamos la orientacion hacia el este
                    .tilt(30)                   // configuramos el angulo de la camara a 30 grados
                    .build();                   // Una vez seteado los parametros, construimos el objetos

            // ubicamos nuestra posicion en el mapa, para que sea centrado ahi, con un angulo y vista
            mapa.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
    }

    public static void setLocation(Location location) {
        MapaGeneral.location = location;
    }
}