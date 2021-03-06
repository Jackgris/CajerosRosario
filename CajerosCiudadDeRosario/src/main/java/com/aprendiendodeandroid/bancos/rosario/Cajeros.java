package com.aprendiendodeandroid.bancos.rosario;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.aprendiendodeandroid.bancos.rosario.modelo.Cajero;
import com.aprendiendodeandroid.bancos.rosario.modelo.CajerosDAOImpl;

import java.util.List;

public class Cajeros extends ListFragment {

    private final static  String TAG = "Cajeros";
    private CajerosDAOImpl cajerosDAOImpl = new CajerosDAOImpl();
    public final static int BANELCO = 1;
    public final static int LINK = 2;
    private int eleccion;
    OnCajeroSeleccionadoListener mCallback;

    /**
     * La activity que contenga este fragment deberia implementar esta interfaz para poder
     * enviarle mensajes
     */
    public interface OnCajeroSeleccionadoListener {
        /** Es llamado cuando un item de la lista es seleccionado */
        public void onCajeroSeleccionado(int position, int idCajero, int tipo);
    }

    /**
     * Debemos mantener un constructor vacio
     */
    public Cajeros(){

    }

    /**
     * Este es el constructor de la clase cajeros, donde deberemos pasarle como parametro el numero
     * que se corresponde a una de las redes de cajeros, para que nos muestre la lista completa
     * de ellos
     *
     * @param eleccion este es un numero entero que se corresponde a una de las redes de cajeros
     */
    public Cajeros(int eleccion){
        this.eleccion = eleccion;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Nosotros necesitamos usar diferentes lista de item en el layout, para versiones mas
        //  viejas que Honeycomb
        int layout = android.R.layout.simple_list_item_1;

        // Creamos un array adapter para ver la vista de la lista, vamos a usar los nombres de
        // los cajeros para eso
        setListAdapter(new ArrayAdapter<String>(getActivity(), layout,
                nombreCajeros(getActivity().getApplicationContext(), eleccion)));
    }

    @Override
    public void onStart() {
        super.onStart();

        // Cuando mostramos en el layout los dos fragment, configuramos el listview para que marque
        // el item seleccionado
        // (Realizamos esto durante el onStart porque en este punto el listview esta listo.)
        if(eleccion == BANELCO){
            if (getFragmentManager().findFragmentById(R.id.cajero_fragmentBanelco) != null) {
                getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            }
        }else {
            if (getFragmentManager().findFragmentById(R.id.cajero_fragmentLink) != null) {
                getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            }
        }

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Con esto nos aseguramos de que la actividad que lo contenga tendra que implementar
        // la interfaz callback. Si no, nos lanzara una excepcion.
        try {
            mCallback = (OnCajeroSeleccionadoListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " deberia implementar OnCajeroSeleccionadoListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        // Vamos a tomar el ID y el tipo de cajero seleccionado
        List<Cajero> mostrar;

        if(eleccion == BANELCO){
            mostrar = cajerosDAOImpl.consultaCajerosBanelco(getActivity().getApplicationContext());
        }else {
            mostrar = cajerosDAOImpl.consultaCajerosLink(getActivity().getApplicationContext());
        }

        Cajero cajero = mostrar.get(position);
        int idCajero = cajero.getIdCajero();

        Log.d(TAG, "id cajero " +  idCajero + " posicion " + position);

        // Avisamos a la activity padre del item seleccionado
        mCallback.onCajeroSeleccionado(position, idCajero, eleccion);

        // Configuramos para que cuando el item es seleccionado se marque, cuando tenemos los dos
        // fragments en el mismo layout
        getListView().setItemChecked(position, true);
    }

    private String[] nombreCajeros(Context context, int eleccion){

        List<Cajero> mostrar;

        if(eleccion == BANELCO){
            mostrar = cajerosDAOImpl.consultaCajerosBanelco(context);
        }else {
            mostrar = cajerosDAOImpl.consultaCajerosLink(context);
        }

        String[] lista = new String[mostrar.size()];
        String mostrarString;

        int i = 0;
        for (Cajero cajero : mostrar) {
            mostrarString = cajero.getNombreBanco() + "\n";
            mostrarString += cajero.getDireccion();

            lista[i] = mostrarString;
            i++;
        }

        return lista;
    }
}
