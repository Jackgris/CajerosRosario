package com.aprendiendodeandroid.bancos.rosario.modelo;

import android.content.Context;

/**
 * Con esta interfas vamos a espesificar que metos vamos a necesitar para el manejo 
 * de nuestro datos
 * @author jackgris
 *
 */
public interface CajerosDAO {
    
    // Variables necesarias para la creacion de nuestra BD
    public static final String CAJEROSAUTOMATICOS = "CajerosRosario.db";
    public static final int VERSION = 1;
    
    /**
     * Solo vamos a necesitar el contexto para crear una instancia de la bd
     * @param context
     */
    public void cajerosAutomaticosBd(Context context);
    
    /**
     * Este metodo lo vamos a usar para poder ejecutar cualquier consulta sql a la bd
     * @param context
     * @param sql 
     */
    public void ejecutarSql(Context context, String sql);
}
