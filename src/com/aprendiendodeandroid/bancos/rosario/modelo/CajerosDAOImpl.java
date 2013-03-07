package com.aprendiendodeandroid.bancos.rosario.modelo;

import android.app.Activity;

/**
 * Esta va a ser nuestra clase DAO donde vamos a implementar el manejo de los datos
 * @author jackgris
 *
 */
public class CajerosDAOImpl extends Activity implements ConstantesCajeros, CajerosDAO{
	
	private final String CAJEROSAUTOMATICOS = "CajerosRosario.db";
	private final int VERSION = 1;
	final private DataBaseHelper dataBase = new DataBaseHelper( getApplicationContext(),
			CAJEROSAUTOMATICOS, null, VERSION);
	

}
