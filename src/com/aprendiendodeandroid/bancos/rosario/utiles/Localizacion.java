package com.aprendiendodeandroid.bancos.rosario.utiles;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import com.aprendiendodeandroid.bancos.rosario.R;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Esta clase la vamos a utilizar para manejar nuestra ubicacion y la de diferentes marcadores
 * en nuestros mapas, y tambien vamos a realizar los provesos de actualizacion de la ubicacion.
 * Como tambien convertir la latitud y la longitud a direccion entendibles por nosotros.
 * @author jackgris
 *
 */
public class Localizacion extends FragmentActivity{
	
    private LocationManager locationManager;
    private Handler handler;
    private boolean geocoderAvailable;
    private boolean useFine;
    private boolean useBoth;

    // Con estas String vamos a mantener la UI despues de rotar la pantalla
    private static final String KEY_FINE = "use_fine";
    private static final String KEY_BOTH = "use_both";
    // Codigos para los handlers de la UI
    private static final int UPDATE_ADDRESS = 1;
    private static final int UPDATE_LATLNG = 2;
    // Tiempo que vamos a utilizar
    private static final int DIEZ_SEGUNDOS = 10000;
    private static final int DIEZ_METROS = 10;
    private static final int DOS_MINUTOS = 1000 * 60 * 2;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapageneral);

        // Recuperamos el estado anterior antes de la rotacion del dispositivo (si existio alguno)
        if (savedInstanceState != null) {
            useFine = savedInstanceState.getBoolean(KEY_FINE);
            useBoth = savedInstanceState.getBoolean(KEY_BOTH);
        } else {
            useFine = false;
            useBoth = false;
        }
        // tomamos una referencia a nuestro location manager
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }
    // Restauramos la UI despues de la rotacion.
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_FINE, useFine);
        outState.putBoolean(KEY_BOTH, useBoth);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setup();
    }
    
    @Override
    protected void onStart() {
        super.onStart();

        // Verificamos si el GPS esta habilitado en nuestro dispositivo.
        // Esta verificacion es conveniente realizarla en el metodo onStart porque el sistema va a 
        // llamar a este metodo, cuando el usuario vuelva a la activity, asi vamos a asegurarnos 
        // de que nuestro location provider esta habilitado cada vez que se vuelva a la misma.
        LocationManager locationManager =
                (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        final boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (!gpsEnabled) {
            // Si no llega a estar habilitado, lanzaremos una alerta para que el usuario lo habilite
            // presionando el boton OK, ya que se llamara al metodo enableLocationSettings()
            new EnableGpsDialogFragment().show(getSupportFragmentManager(), "enableGpsDialog");
        }
    }

    // Va a lanzar nuestra configuracion
    private void enableLocationSettings() {
        Intent configuracionIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(configuracionIntent);
    }

    // En el metodo onStop vamos a desabilitar las actulizaciones, para que no consuma recursos, 
    // mientras que nuestra activity no este a la vista
    @Override
    protected void onStop() {
        super.onStop();
        locationManager.removeUpdates(listener);
    }

    // Vamos a setear el location provider fine y/o coarse 
    private void setup() {
        Location gpsLocation = null;
        Location networkLocation = null;
        locationManager.removeUpdates(listener);
        // Tomamos solamente las actualizaciones del location fine 
        if (useFine) {
            // Pedimos actualizacion del provedor fine (GPS)
            gpsLocation = requestUpdatesFromProvider(
                    LocationManager.GPS_PROVIDER, R.string.not_support_gps);
            // Si obtenemos las actualizaciones tambien lo hacemos con la UI
            if (gpsLocation != null) updateUILocation(gpsLocation);
        } else if (useBoth) {
            // Pedimos las actualizaciones de ambos, fine (GPS) y coarse (la red o network)
            gpsLocation = requestUpdatesFromProvider(
                    LocationManager.GPS_PROVIDER, R.string.not_support_gps);
            networkLocation = requestUpdatesFromProvider(
                    LocationManager.NETWORK_PROVIDER, R.string.not_support_network);

            // Si los dos proveedores devuelven la ultima localizacion conocida, las comparamos 
            // y usamos la que creemos mejor. Si solo uno devuelve la ubicacion, usamos esa.
            if (gpsLocation != null && networkLocation != null) {
                updateUILocation(getBetterLocation(gpsLocation, networkLocation));
            } else if (gpsLocation != null) {
                updateUILocation(gpsLocation);
            } else if (networkLocation != null) {
                updateUILocation(networkLocation);
            }
        }
    }
    /**
     * Con este metodo vamos a registrar las actualizaciones de la ubicacion con el proveedor que
     * deseemos. Si este proveedor no llega a estar disponible, vamos a lanzar un toast con un 
     * mensaje.
     *
     * @param provider nombre del proveedor que vamos a pedir usar
     * @param errorResId Es el id de la string del mensaje que vamos a desplegar si el proveedor 
     * 					 no existe en nuestro dispositivo
     * @return retornamos {@link android.location.Location} desde el proveedor que pedimos, si existe
     */
    private Location requestUpdatesFromProvider(final String provider, final int errorResId) {
        Location location = null;
        if (locationManager.isProviderEnabled(provider)) {
            locationManager.requestLocationUpdates(provider, DIEZ_SEGUNDOS, DIEZ_METROS, listener);
            location = locationManager.getLastKnownLocation(provider);
        } else {
            Toast.makeText(this, errorResId, Toast.LENGTH_LONG).show();
        }
        return location;
    }
    // Devolvemos la llamada del boton del proovedor fine 
    public void useFineProvider(View v) {
        useFine = true;
        useBoth = false;
        setup();
    }
    // Devolvemos la llamada del boton ambos proveedores
    public void useCoarseFineProviders(View v) {
        useFine = false;
        useBoth = true;
        setup();
    }
    private void doReverseGeocoding(Location location) {
        // Devido a que la API de geocoding es sincronica, nos puede llegar a bloquear nuestra 
    	// aplicacion, ya que puede tomar algun tiempo. Y como nosotros no queremos que pase 
    	// eso en el hilo de la UI, por eso invocamos a ReverseGeocodingTask que es una AsyncTask
        (new ReverseGeocodingTask(this)).execute(new Location[] {location});
    }
    private void updateUILocation(Location location) {
        // Vamos a enviar la actualizacion a un handler para actualizar los datos de la UI con 
    	// la nueva ubicacion
        Message.obtain(handler,
                UPDATE_LATLNG,
                location.getLatitude() + ", " + location.getLongitude()).sendToTarget();

        // Realizacion el reverseGeocoding solo, si el geocoder esta disponible
        if (geocoderAvailable) doReverseGeocoding(location);
    }
    private final LocationListener listener = new LocationListener() {

        public void onLocationChanged(Location location) {
        	// Recibimos la actualizacion de la ubicacion, donde prodremos usarla en nuestra app
            updateUILocation(location);
        }

        public void onProviderDisabled(String provider) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    };

     /**
      * Vamos a determinar cuando una ubicacion nueva es mejor que la actual 
      *
      * @param nuevaUbicacion  La nueva ubicacion que vamos a evaluar
      * @param actualMejorUbicacion La actual ubicacion que podria mejorar,comparandola con la nueva
      * @return Retornamos el mejor objeto Location resultado de nuestra comparacion
      */
    protected Location getBetterLocation(Location nuevaUbicacion, Location actualMejorUbicacion) {
        if (actualMejorUbicacion == null) {
            // Una nueva ubicacion es mejor siempre que no alla ninguna
            return nuevaUbicacion;
        }

        // Verificamos si la nueva ubicacion es mas reciente que la actual
        long timeDelta = nuevaUbicacion.getTime() - actualMejorUbicacion.getTime();
        boolean isSignificantlyNewer = timeDelta > DOS_MINUTOS;
        boolean isSignificantlyOlder = timeDelta < -DOS_MINUTOS;
        boolean isNewer = timeDelta > 0;

        // Si ya pasaron mas de dos minutos de la ubicacion actual, vamos a utilizar la nueva, 
        // ya que provablemente el usuario en ese tiempo se cambio de ubicacion
        if (isSignificantlyNewer) {
            return nuevaUbicacion;
        // Si la nueva ubicacion tiene ya mas de dos minustos, debe ser peor
        } else if (isSignificantlyOlder) {
            return actualMejorUbicacion;
        }

        // Comprobamos si la nueva ubicacion es mas o menos precisa
        int accuracyDelta = (int) (nuevaUbicacion.getAccuracy() - actualMejorUbicacion.getAccuracy());
        boolean isLessAccurate = accuracyDelta > 0;
        boolean isMoreAccurate = accuracyDelta < 0;
        boolean isSignificantlyLessAccurate = accuracyDelta > 200;

        // Comprobamos que ambas ubicacion son del mismo proveedor
        boolean isFromSameProvider = isSameProvider(nuevaUbicacion.getProvider(),
                actualMejorUbicacion.getProvider());

        // Determinamos la calidad de la ubicacion usando una convinacion de puntualidad y precision
        if (isMoreAccurate) {
            return nuevaUbicacion;
        } else if (isNewer && !isLessAccurate) {
            return nuevaUbicacion;
        } else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
            return nuevaUbicacion;
        }
        return actualMejorUbicacion;
    }

    /** Comprovamos si los proveedores son los mismos*/
    private boolean isSameProvider(String provider1, String provider2) {
        if (provider1 == null) {
          return provider2 == null;
        }
        return provider1.equals(provider2);
    }

    // AsyncTask encapsula al API de geocodificion-inversa. Asi que como esta bloqueada la API de 
    // geocodificacion, no queremos volver a invocarla desde un subproceso llamado desde la UI
    private class ReverseGeocodingTask extends AsyncTask<Location, Void, Void> {
        Context mContext;

        public ReverseGeocodingTask(Context context) {
            super();
            mContext = context;
        }

        @Override
        protected Void doInBackground(Location... params) {
            Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());

            Location loc = params[0];
            List<Address> addresses = null;
            try {
                addresses = geocoder.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
            } catch (IOException e) {
                e.printStackTrace();
                // Actualizamos el campo de la direccion con la excepcion capturada
                Message.obtain(handler, UPDATE_ADDRESS, e.toString()).sendToTarget();
            }
            if (addresses != null && addresses.size() > 0) {
                Address address = addresses.get(0);
                // Damos formato primero a la direccion si esta disponible, luego a la ciudad
                // y por ultimo al nombre del pais
                String addressText = String.format("%s, %s, %s",
                        address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",
                        address.getLocality(),
                        address.getCountryName());
                // Actualizamos el campo de direccion de la UI.
                Message.obtain(handler, UPDATE_ADDRESS, addressText).sendToTarget();
            }
            return null;
        }
    }

    /**
     * Dialog para pedirle al usuario que active el GPS.
     */
    @SuppressLint("ValidFragment")
	private class EnableGpsDialogFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity())
                    .setTitle(R.string.enable_gps)
                    .setMessage(R.string.enable_gps_dialog)
                    .setPositiveButton(R.string.enable_gps, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            enableLocationSettings();
                        }
                    })
                    .create();
        }
    }
						
		

}
