package com.aprendiendodeandroid.bancos.rosario.modelo;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Esta clase se va a encargar de la creacion de la base de datos
 * @author gabriel
 *
 */
public class DataBaseHelper extends SQLiteOpenHelper implements ConstantesCajeros {

    /*
     * Estas van a ser las consultas con las que se van a crear las tablas e insertar los datos por
     * defecto, que van a estar dentro de nuestra bd
     */
	final static private String tableCajeros = "CREATE TABLE " + TABLE_CAJEROS +
	        " (codigo INTEGER, nombre TEXT)";
	final static private String eliminarTablaCajeros = "DROP TABLE IF EXISTS " + TABLE_CAJEROS;
	 
	
	/**
	 * Constructor por defecto para la clase con la que vamos a crear nuestra base de datos
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 */
	public DataBaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Aca vamos a armar la estructura de la base de datos
	    try{
	        // Creamos la tabla
	        db.execSQL(tableCajeros);
	    }
	    catch(SQLException ex){
	        Log.d("SQLException", "CREATE: " + ex.getMessage());
	    }
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int versionanterior, int nuevaversion) {
		
		// verificamos si es una version nueva de la base de datos, si es asi creamos la tabla
		// nueva
		if(versionanterior < nuevaversion){
		    try{
		        //Se elimina la version anterior de la tabla
	            db.execSQL(eliminarTablaCajeros);
	     
	            //Se crea la nueva version de la tabla
	            db.execSQL(tableCajeros);
	            
	            // FIXME falta rinsertar los datos y demas cosas
		    }
		    catch(SQLException ex){
	            Log.d("SQLException", "UPGRADE: " + ex.getMessage());
	        }

		}
	}

}
