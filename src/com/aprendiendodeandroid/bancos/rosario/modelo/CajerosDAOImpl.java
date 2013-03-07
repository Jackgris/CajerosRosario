package com.aprendiendodeandroid.bancos.rosario.modelo;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Esta va a ser nuestra clase DAO donde vamos a implementar el manejo de los datos
 * @author jackgris
 *
 */
public class CajerosDAOImpl implements ConstantesCajeros, CajerosDAO{
		
	/**
	 * Con este metodo simplemente creamos una instancia de la base de datos
	 * @param context
	 */
	public void cajerosAutomaticosBd(Context context) {
	    try {
	        // Instancio la base de datos
	        DataBaseHelper bd = new DataBaseHelper(context, CAJEROSAUTOMATICOS, null, VERSION);
            SQLiteDatabase base = bd.getWritableDatabase();
            base.close();
        }
	    catch(Exception e) {
            Log.v("TaxisOnlineBD", e.getMessage());
        }
    }
    
    /**
     * Esta funcion recibe una sentencia SQL y la ejecuta
     * @param Context context estado de la aplicacion
     * @param String sql sentencia a ejecutar
     */
	public void ejecutarSql(Context context, String sql) {
	        
        try {       
            DataBaseHelper bd = new DataBaseHelper(context, CAJEROSAUTOMATICOS, null, VERSION);
            SQLiteDatabase base = bd.getWritableDatabase();
            base.execSQL(sql);
            base.close();
        } catch(SQLException e) {
            Log.v("ejecutarSql", e.getMessage());
        }
    }
}
