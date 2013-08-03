package com.aprendiendodeandroid.bancos.rosario.modelo;

import android.content.Context;

import java.util.List;

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
     * @param context contexto actual de la aplicacion necesario para acceder a la base de datos
     */
    public void cajerosAutomaticosBd(Context context);
    
    /**
     * Este metodo lo vamos a usar para poder ejecutar cualquier consulta sql a la bd
     * @param context contexto actual de la aplicacion necesario para acceder a la base de datos
     * @param sql 
     */
    public void ejecutarSql(Context context, String sql);
    
    /**
     * Este metodo se encargara de devolvernos la totalidad de los datos de los cajeros de la 
     * red Banelco
     * @param context contexto actual de la aplicacion necesario para acceder a la base de datos
     */
    public List<Cajero> consultaCajerosBanelco(Context context);
    
    /**
     * Este metodo se encargara de devolvernos la totalidad de los datos de los cajeros 
     * de la red Link
     * @param context contexto actual de la aplicacion necesario para acceder a la base de datos
     */
    public List<Cajero> consultaCajerosLink(Context context);
    
    /**
     * Esta consulta se encargara de devolvernos simplemente uno solo de los cajeros que elijamos
     *
     * @param context contexto actual de la aplicacion necesario para acceder a la base de datos
     * @param red este es el identificador del tipo de red de cajeros
     * @param idCajero este es el identificador
     */
    public Cajero consultaUnCajero(Context context, int red, int idCajero);
}
