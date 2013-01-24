package com.aprendiendodeandroid.bancos.rosario.utiles;

import com.aprendiendodeandroid.bancos.rosario.MapaGeneral;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

public class LocationListenerGPS implements LocationListener{

    private Marker actual;
    private GoogleMap mapa;
    
    public void onLocationChanged(Location location) {
        
      // aca vamos a realizar una accion al tomar un nuevo valor
      float lat = (float) (location.getLatitude());
      float lng = (float) (location.getLongitude());
      LatLng latLng = new LatLng(lat, lng);
      mapa = MapaGeneral.mapa;
      actual = mapa.addMarker(new MarkerOptions().position(latLng).title("Rosario"));
      Log.d("Localizacion", "Valor: " + location.toString() + " latitud: " + lat +
                " logitud: " + lng + "Valor al mapa" + latLng);
    }           

    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    public void onProviderDisabled(String provider) {
        // vamos a intentar cambiar con otros proveedores
        Log.d("Localizacion", "Esta desabilitado el proveedor");
    }
    public void onProviderEnabled(String provider) {
        // vamos a intentar cambiar con otros proveedores
        Log.d("Localizacion", "Esta habilitado el proveedor " + provider);
    }

    /** esta criteria va a tener una mayor precision, consumir mas energia y un costo mayor*/
    public static Criteria crearCriteriaGPS() {
     
      Criteria c = new Criteria();
      c.setAccuracy(Criteria.ACCURACY_FINE);
      c.setAltitudeRequired(false);
      c.setBearingRequired(false);
      c.setSpeedRequired(false);
      c.setCostAllowed(true);
      c.setPowerRequirement(Criteria.POWER_HIGH);
      return c;
    }

    public Marker getActual() {
        return actual;
    }

    public void setActual(Marker actual) {
        this.actual = actual;
    }

    public GoogleMap getMapa() {
        return mapa;
    }

    public void setMapa(GoogleMap mapa) {
        this.mapa = mapa;
    }    
}
