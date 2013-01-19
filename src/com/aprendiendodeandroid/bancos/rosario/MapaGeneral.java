package com.aprendiendodeandroid.bancos.rosario;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapageneral);
		
        // Recuperamos el estado anterior antes de la rotacion del dispositivo (si existio alguno)
        // if (savedInstanceState != null) {
        //     useFine = savedInstanceState.getBoolean(KEY_FINE);
        //     useBoth = savedInstanceState.getBoolean(KEY_BOTH);
        // } else {
        //     useFine = false;
        //     useBoth = false;
        // }
        // tomamos una referencia a nuestro location manager
        // locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
		mapa = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.mapa))
		        .getMap();
			
        mapa.setOnMapClickListener(new OnMapClickListener() {
            public void onMapClick(LatLng point) {
                Projection proj = mapa.getProjection();
                Point coord = proj.toScreenLocation(point);
                
                Toast.makeText(
                        MapaGeneral.this, 
                        "Click\n" + 
                        "Lat: " + point.latitude + "\n" +
                        "Lng: " + point.longitude + "\n" +
                        "X: " + coord.x + " - Y: " + coord.y,
                        Toast.LENGTH_SHORT).show();
            }
        });
        
        mapa.setOnMapLongClickListener(new OnMapLongClickListener() {
            public void onMapLongClick(LatLng point) {
                Projection proj = mapa.getProjection();
                Point coord = proj.toScreenLocation(point);
                
                Toast.makeText(
                        MapaGeneral.this, 
                        "Click Largo\n" + 
                        "Lat: " + point.latitude + "\n" +
                        "Lng: " + point.longitude + "\n" +
                        "X: " + coord.x + " - Y: " + coord.y,
                        Toast.LENGTH_SHORT).show();
            }
        });
        
        mapa.setOnCameraChangeListener(new OnCameraChangeListener() {
            public void onCameraChange(CameraPosition position) {
                Toast.makeText(
                        MapaGeneral.this, 
                        "Cambio Cámara\n" + 
                        "Lat: " + position.target.latitude + "\n" +
                        "Lng: " + position.target.longitude + "\n" +
                        "Zoom: " + position.zoom + "\n" +
                        "Orientación: " + position.bearing + "\n" +
                        "Ángulo: " + position.tilt,
                        Toast.LENGTH_SHORT).show();
            }
        });
        
        mapa.setOnMarkerClickListener(new OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(
                        MapaGeneral.this, 
                        "Marcador pulsado:\n" + 
                        marker.getTitle(),
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) 
    {   
        switch(item.getItemId())
        {
            case 2:
                mostrarMarcador(40.5, -3.5);
                break;
            case 1:
                mostrarLineas();
                break;
        }
        
        return super.onOptionsItemSelected(item);
    }
    
    private void mostrarMarcador(double lat, double lng)
    {
        mapa.addMarker(new MarkerOptions()
        .position(new LatLng(lat, lng))
        .title("Pais: España"));
    }
    
    private void mostrarLineas()
    {
        //Dibujo con Lineas
        
        PolylineOptions lineas = new PolylineOptions()
            .add(new LatLng(45.0, -12.0))
            .add(new LatLng(45.0, 5.0))
            .add(new LatLng(34.5, 5.0))
            .add(new LatLng(34.5, -12.0))
            .add(new LatLng(45.0, -12.0));

        lineas.width(8);
        lineas.color(Color.RED);

        mapa.addPolyline(lineas);
        
        //Dibujo con polígonos
        
        //PolygonOptions rectangulo = new PolygonOptions()
        //              .add(new LatLng(45.0, -12.0),
        //                 new LatLng(45.0, 5.0),
        //                 new LatLng(34.5, 5.0),
        //                 new LatLng(34.5, -12.0),
        //                 new LatLng(45.0, -12.0));
        //
        //rectangulo.strokeWidth(8);
        //rectangulo.strokeColor(Color.RED);
        //
        //mapa.addPolygon(rectangulo);
    }
}
