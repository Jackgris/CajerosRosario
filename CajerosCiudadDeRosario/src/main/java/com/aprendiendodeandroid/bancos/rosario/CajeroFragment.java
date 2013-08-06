
package com.aprendiendodeandroid.bancos.rosario;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.aprendiendodeandroid.bancos.rosario.modelo.Cajero;
import com.aprendiendodeandroid.bancos.rosario.modelo.CajerosDAO;
import com.aprendiendodeandroid.bancos.rosario.modelo.CajerosDAOImpl;

/**
 * Este es el fragment que va a contener la informacion del cajero
 */
public class CajeroFragment extends Fragment {

    // variables para el pasaje de datos
    final static String ARG_POSITION = "position";
    final static String ID_CAJERO = "id_cajero";
    final static String TIPO_CAJERO = "tipo_cajero";
    private final static String TAG = "CajeroFragment";

    int mCurrentPosition = -1;
    int idCajero = 0;
    int tipoCajero = 1;
    private Button botonVerMapa;
    TabHost tabHost;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {

        // Si la activity es recreada, como al girar la pantalla, restauramos la seleccion
        // previa de cajeros configurada en el metodo onSaveInstanceState().
        // Esto es principalmente necesario, cuando tenemos los dos panerles en el layout
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
            idCajero = savedInstanceState.getInt(ID_CAJERO);
            tipoCajero = savedInstanceState.getInt(TIPO_CAJERO);
        }

        // Inflamos el  layout para este fragment
        return inflater.inflate(R.layout.cajero2, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        // Durante el arranque, verificamos si se le pasaron parametros al fragment.
        // onStart es un buen lugar para realizar esto, porque el layout esta listo para que se le
        // coloque el fragment en este punto, es seguro llamar a nuestro metodos, como el configurar
        // el texto a mostrar en el mismo.
        Bundle args = getArguments();
        if (args != null) {
            // le seteamos los argunmentos pasados
            updateCajeroView(args.getInt(ARG_POSITION), args.getInt(ID_CAJERO), args.getInt(TIPO_CAJERO));
        } else if (mCurrentPosition != -1) {
            // Set article based on saved instance state defined during onCreateView
            updateCajeroView(mCurrentPosition, args.getInt(ID_CAJERO), args.getInt(TIPO_CAJERO));
        }
    }

    public void updateCajeroView(int position, int idCajero, int tipoCajero) {
        // FIXME debo agregar el codigo para obtener los datos a mostrar de el cajero en particular
        CajerosDAO cajerosDAO = new CajerosDAOImpl();

        Cajero cajero = cajerosDAO.consultaUnCajero(getActivity().getApplicationContext(),
                tipoCajero, idCajero);

        tabHost =  (TabHost) getActivity().getParent().findViewById(android.R.id.tabhost);

        TextView textoNombre = (TextView) getActivity().findViewById(R.id.cajeroNombre);
        textoNombre.setText(cajero.getNombreBanco());
        TextView textoDireccion = (TextView) getActivity().findViewById(R.id.cajeroDireccion);
        textoDireccion.setText(cajero.getDireccion());
        TextView textoTelefono = (TextView) getActivity().findViewById(R.id.cajeroTelefono);
        textoTelefono.setText(cajero.getTelefonoBanco());

        verEnElMapa(cajero);

        mCurrentPosition = position;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current article selection in case we need to recreate the fragment
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }

    private void verEnElMapa(final Cajero cajero){
        botonVerMapa.setOnClickListener(new View.OnClickListener() {
            final Context context = getActivity().getApplicationContext();

            @Override
            public void onClick(View view) {

                String mensaje  = "Vamos a ver el cajero en el Mapa";

                tabHost.setCurrentTab(0);

                if(context != null){
                    Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        botonVerMapa = (Button)getActivity().findViewById(R.id.buttonIrMapa);

    }
}