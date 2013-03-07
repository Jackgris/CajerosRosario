package com.aprendiendodeandroid.bancos.rosario.modelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

	final private String tableCajeros = "CREATE TABLE cajeros (codigo INTEGER, nombre TEXT)";
	 
	
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
		// Aca vamos a crear la base de datos
		db.execSQL(tableCajeros);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int versionanterior, int nuevaversion) {
		
		// verificamos si es una version nueva de la base de datos, si es asi creamos la tabla
		// nueva
		if(versionanterior < nuevaversion){
		//Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Usuarios");
 
        //Se crea la nueva versión de la tabla
        db.execSQL(tableCajeros);
		}
	}

}
