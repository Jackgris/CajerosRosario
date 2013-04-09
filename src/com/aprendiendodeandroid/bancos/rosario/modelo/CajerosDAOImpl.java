package com.aprendiendodeandroid.bancos.rosario.modelo;

import android.content.Context;
import android.database.Cursor;
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
	
	/**
	 * Esta funcion tiene  mayormente propositos de testing, para corroborar que los datos estan
	 * correctamente accesibles
	 * @param context solo es el contexto desde donde se va a llamar a la funcion
	 * @return String un simple string donde se contendra el total de los cajeros
	 */
	public String totalCajeros(Context context) {
		
		final String SQLTOTALCAJEROS = "SELECT * FROM " + ConstantesCajeros.TABLE_CAJEROS;
		String total = "";
		
        try {       
            DataBaseHelper bd = new DataBaseHelper(context, CAJEROSAUTOMATICOS, null, VERSION);
            SQLiteDatabase base = bd.getWritableDatabase();
            Cursor cursor = base.rawQuery(SQLTOTALCAJEROS, null);

            //Nos aseguramos de que existe al menos un registro
            if (cursor.moveToFirst()) {
                 //Recorremos el cursor hasta que no haya más registros
                 do {
                      String cajero = cursor.getString(0);
                      
                      total += " " + cajero;
                 } while(cursor.moveToNext());
            }
                       
            base.close();
        } catch(SQLException e) {
            Log.v("ejecutarSql", e.getMessage());
        }
        
        return total;
    }

	@Override
	public void consultaCajerosBanelco(Context context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void consultaCajerosLink(Context context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void consultaUnCajero(Context context, String red, String direccion) {
		// TODO Auto-generated method stub
		
	}
}
