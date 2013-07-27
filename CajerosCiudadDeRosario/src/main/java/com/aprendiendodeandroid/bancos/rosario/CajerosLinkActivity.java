package com.aprendiendodeandroid.bancos.rosario;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

//import com.aprendiendodeandroid.bancos.rosario.R;
import com.aprendiendodeandroid.bancos.rosario.modelo.Cajero;
import com.aprendiendodeandroid.bancos.rosario.modelo.CajerosDAOImpl;

import java.util.List;

public class CajerosLinkActivity extends Activity {

	private Button buttonTest;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cajeroslinks);
		
		buttonTest = (Button)findViewById(R.id.buttonCajrosLinks);
		
		obtenerCajerosTest();
	}
	
	private void obtenerCajerosTest(){
		buttonTest.setOnClickListener(new OnClickListener() {
			CajerosDAOImpl cajerosDAOImpl = new CajerosDAOImpl();
			
			@Override
			public void onClick(View v) {
				// FIXME Este metodo es simplemente para realizar un test
				List<Cajero> mostrar = cajerosDAOImpl.consultaCajerosLink(getApplicationContext());
				String mostrarString = "";
				
				for (Cajero cajero : mostrar) {
					mostrarString += cajero.getNombreBanco() + " " + cajero.getDireccion() + "\n";
				}
				
				Toast.makeText(getApplicationContext(), 
						"Estos son los cajeros de la red Link: " + 
								mostrarString, Toast.LENGTH_SHORT).show();
				
			}
		});
	}
	
}
