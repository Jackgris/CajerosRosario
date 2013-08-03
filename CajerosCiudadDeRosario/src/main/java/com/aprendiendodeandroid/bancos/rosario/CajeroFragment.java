
package com.aprendiendodeandroid.bancos.rosario;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Este es el fragment que va a contener la informacion del cajero
 */
public class CajeroFragment extends Fragment {

    final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;
    private Button botonVerMapa;

    // Estas son las variables a mostrar en el detalle
    private String nombre;
    private String direccion;
    private String telefono;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {

        // Si la activity es recreada, como al girar la pantalla, restauramos la seleccion
        // previa de cajeros configurada en el metodo onSaveInstanceState().
        // Esto es principalmente necesario, cuando tenemos los dos panerles en el layout
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
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
            updateCajeroView(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            // Set article based on saved instance state defined during onCreateView
            updateCajeroView(mCurrentPosition);
        }
    }

    public void updateCajeroView(int position) {
        // FIXME debo agregar el codigo para obtener los datos a mostrar de el cajero en particular
        TextView textoNombre = (TextView) getActivity().findViewById(R.id.cajeroNombre);
        textoNombre.setText("hola");
        TextView textoDireccion = (TextView) getActivity().findViewById(R.id.cajeroDireccion);
        textoDireccion.setText("hola");
        TextView textoTelefono = (TextView) getActivity().findViewById(R.id.cajeroTelefono);
        textoTelefono.setText("hola");

        mCurrentPosition = position;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current article selection in case we need to recreate the fragment
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }

    private void verEnElMapa(){
        botonVerMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity().getApplicationContext(), "Hola", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        botonVerMapa = (Button)getActivity().findViewById(R.id.buttonIrMapa);
        verEnElMapa();
    }
}