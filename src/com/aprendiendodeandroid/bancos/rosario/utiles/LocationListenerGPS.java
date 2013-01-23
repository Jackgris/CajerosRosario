package com.aprendiendodeandroid.bancos.rosario.utiles;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

public class LocationListenerGPS implements LocationListener{
    long fecha_hora_anterior = 0;
    private String latitud = "";
    private String longitud = "";
    
    public void onLocationChanged(Location location) {
        // Obtengo la fecha y hora actual
        java.util.Date fecha_hora_actual = new java.util.Date();
        
        // Si es la primera vez que entro no tengo la fecha y hora anterior
        // por eso le doy un valor inicial
        if(fecha_hora_anterior == 0) {
            fecha_hora_anterior = fecha_hora_actual.getTime();
        } else {
            // Resto la fecha y hora actual con la anterior
            // paso el resultado a segundos
            float dif_tiempo = (fecha_hora_actual.getTime() - fecha_hora_anterior) / 1000;
            // ahora paso el la diferencia de tiempo en horas
            dif_tiempo = dif_tiempo / 3600;
            
            // Llamo a la función obtener_velocidad y guardo su resultado en
            // la variable pública velocidad
//            posicion.velocidad = posicion.obtener_velocidad(location.getLatitude(),
//                    location.getLongitude(), posicion.latitud_movil, posicion.longitud_movil,
//                    dif_tiempo);
        }           
        // Actualizo la latitud y longitud para enviar al servidor
        latitud = String.valueOf(location.getLatitude());
        longitud = String.valueOf(location.getLongitude());
        
        // Si tengo la posición anterior del movil obtengo su rumbo
//        if(posicion.latitud_movil != 0 && posicion.longitud_movil != 0) {
//            // Obtengo el rumbo actual
//            posicion.rumbo = posicion.obtener_angulo_entre_dos_puntos( posicion.latitud_movil,
//                    posicion.longitud_movil, location.getLatitude(), location.getLongitude());
        }           
        // Guardo la latitud y longitud en el singleton Posicion
//        posicion.latitud_movil = location.getLatitude();
//        posicion.longitud_movil = location.getLongitude();
//        
        // obtengo la presicion del GPS
//        posicion.presicion = location.getAccuracy();
        
        // Actualizo el valor de la fecha y hora anterior
//        fecha_hora_anterior = fecha_hora_actual.getTime();
//    }
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    public void onProviderDisabled(String s) {
        // Si pierdo senial de GPS seteo la latitud y la longitud a vaci�o
        latitud = "";
        longitud = "";
        // Seteo en 0.0 la posicion del movil
//        posicion.latitud_movil = 0.0;
//        posicion.longitud_movil = 0.0;
    }
    public void onProviderEnabled(String s) {
    }
    // getter and setter para actualizar los valores
    public String getLatitud() {
        return latitud;
    }
    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }
    public String getLongitud() {
        return longitud;
    }
    public void setLongitud(String longitud) {
        this.longitud = longitud;
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
}
