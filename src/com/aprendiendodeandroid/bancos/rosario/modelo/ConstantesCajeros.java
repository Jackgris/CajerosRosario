package com.aprendiendodeandroid.bancos.rosario.modelo;

import android.provider.BaseColumns;

/**
 * Con esta interfas vamos a espesificar las columnas de la tabla cajeros
 * @author jackgris
 *
 */
public interface ConstantesCajeros extends BaseColumns{
	
	// Nombre de la tabla
	public static final String TABLE_CAJEROS = "cajeros";
	
	// Nombre de las columnas
	public static final String COLUMN_RED = "red_cajeros";
	public static final String COLUMN_LATITUD = "latitud";
	public static final String COLUMN_LONGITUD = "longitud";
	public static final String COLUMN_CALLE = "calle";
	public static final String COLUMN_ALTURA = "altura";
	public static final String COLUMN_BANCO = "nombre_banco";

}
