
package com.aprendiendodeandroid.bancos.rosario;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Este es el fragment que va a contener la informacion del cajero
 */
public class CajeroFragment extends Fragment {

    final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;

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
        return inflater.inflate(R.layout.cajero, container, false);
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
        TextView textoCajero = (TextView) getActivity().findViewById(R.id.cajero);
//        article.setText(Ipsum.Articles[position]);
        textoCajero.setText("Cajero numero " + position);
        mCurrentPosition = position;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current article selection in case we need to recreate the fragment
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }
}