package com.aprendiendodeandroid.bancos.rosario.modelo;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.aprendiendodeandroid.bancos.rosario.CajeroFragment;
import com.aprendiendodeandroid.bancos.rosario.Cajeros;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta va a ser nuestra clase DAO donde vamos a implementar el manejo de los datos
 * @author jackgris
 *
 */
public class CajerosDAOImpl implements ConstantesCajeros, CajerosDAO{

    private final static String TAG = "CajerosDAOImpl";
	/**
	 * Con este metodo simplemente creamos una instancia de la base de datos
	 * @param context contexto actual de la aplicacion necesario para acceder a la base de datos
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
	public List<Cajero> consultaCajerosBanelco(Context context) {
		List<Cajero> resultado = new ArrayList<Cajero>();
		Cajero cajero;
		
		final String SQL_CAJEROS_LINK = "SELECT * FROM " + ConstantesCajeros.TABLE_CAJEROS +
				" WHERE " + ConstantesCajeros.COLUMN_RED + " IN ('Banelco')" ;
		
        try {       
            DataBaseHelper bd = new DataBaseHelper(context, CAJEROSAUTOMATICOS, null, VERSION);
            SQLiteDatabase base = bd.getWritableDatabase();
            Cursor cursor = base.rawQuery(SQL_CAJEROS_LINK, null);
            
            Log.d("CONSULTA", "Resultado: " + cursor.toString());
            //Nos aseguramos de que existe al menos un registro
            if (cursor.moveToFirst()) {
                 //Recorremos el cursor hasta que no haya más registros
                 do {
                	  cajero = new Cajero();
                      int idCajero = cursor.getInt(0);
                      String banco = cursor.getString(1);
                      String calle = cursor.getString(3);
                      Integer altura = cursor.getInt(4);
                      Integer latitud = cursor.getInt(5);
                      Integer longitud = cursor.getInt(6);
                      
                      cajero.setNombreBanco(banco);
                      cajero.setDireccion(calle + " " + altura);
                      cajero.setLatitud(latitud);
                      cajero.setLongitud(longitud);
                      cajero.setIdCajero(idCajero);
                      
                      resultado.add(cajero);
                      
                 } while(cursor.moveToNext());
            }

            cursor.close();
            base.close();
            bd.close();
        } catch(SQLException e) {
            Log.v("ejecutarSql", e.getMessage());
        }
        
        return resultado;
		
		
	}

	@Override
	public List<Cajero> consultaCajerosLink(Context context) {
		
		List<Cajero> resultado = new ArrayList<Cajero>();
		Cajero cajero = null;
		
		final String SQL_CAJEROS_LINK = "SELECT * FROM " + ConstantesCajeros.TABLE_CAJEROS +
				" WHERE " + ConstantesCajeros.COLUMN_RED + " IN ('Link')" ;
		
        try {       
            DataBaseHelper bd = new DataBaseHelper(context, CAJEROSAUTOMATICOS, null, VERSION);
            SQLiteDatabase base = bd.getWritableDatabase();
            Cursor cursor = base.rawQuery(SQL_CAJEROS_LINK, null);

            Log.d("CONSULTA", "Resultado: " + cursor.toString());
            //Nos aseguramos de que existe al menos un registro
            if (cursor.moveToFirst()) {
                 //Recorremos el cursor hasta que no haya más registros
                 do {
                	  cajero = new Cajero();
                      int idCajero = cursor.getInt(0);
                      String banco = cursor.getString(1);
                      String calle = cursor.getString(3);
                      Integer altura = cursor.getInt(4);
                      Integer latitud = cursor.getInt(5);
                      Integer longitud = cursor.getInt(6);

                      cajero.setIdCajero(idCajero);
                      cajero.setNombreBanco(banco);
                      cajero.setDireccion(calle + " " + altura);
                      cajero.setLatitud(latitud);
                      cajero.setLongitud(longitud);
                      
                      resultado.add(cajero);
                      
                 } while(cursor.moveToNext());
            }

            cursor.close();
            base.close();
            bd.close();
        } catch(SQLException e) {
            Log.v("ejecutarSql", e.getMessage());
        }
        
        return resultado;
		
	}

    @Override
    public Cajero consultaUnCajero(Context context, int red, int idCajero) {

        Cajero cajero =  new Cajero();

        final String consulta = "SELECT * FROM " + ConstantesCajeros.TABLE_CAJEROS +
                " WHERE " + ConstantesCajeros.COLUMN_ID + "='" + idCajero + "'";

        try {
            DataBaseHelper bd = new DataBaseHelper(context, CAJEROSAUTOMATICOS, null, VERSION);
            SQLiteDatabase base = bd.getWritableDatabase();
            Cursor cursor = base.rawQuery(consulta, null);

            //Nos aseguramos de que existe al menos un registro
            if (cursor.moveToFirst()) {
                //Recorremos el cursor hasta que no haya más registros
                do {
                    cajero = new Cajero();
                    String banco = cursor.getString(1);
                    String calle = cursor.getString(3);
                    Integer altura = cursor.getInt(4);
                    Integer latitud = cursor.getInt(5);
                    Integer longitud = cursor.getInt(6);
                    String telefono = cursor.getString(7);

                    cajero.setIdCajero(idCajero);
                    cajero.setNombreBanco(banco);
                    cajero.setDireccion(calle + " " + altura);
                    cajero.setLatitud(latitud);
                    cajero.setLongitud(longitud);
                    cajero.setTelefonoBanco(telefono);

                } while(cursor.moveToNext());
            }

            cursor.close();
            base.close();
            bd.close();
        } catch(SQLException e) {
            Log.e(TAG, "Funcion consultaUnCajero " +  e);
        }

        return cajero;
    }

}
