package com.aprendiendodeandroid.bancos.rosario;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.TabHost;

/**
 * Esta clase va a ser la encargada de manejar la vista de los cajeros Banelco
 */
public class CajerosBanelcoActivity extends FragmentActivity implements
	                                                            Cajeros.OnCajeroSeleccionadoListener{

    private final static String TAG = "CajerosBanelcoActivity";
    private Cajeros cajeros;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cajerosbanelco);

        // Verificamos que la activity este usando el layout con el fragment
        // conteinerbanelco FrameLayout. Si es asi, deberiamos agregar el fragment de tipo cajero
        if (findViewById(R.id.conteinerbanelco) != null) {

            // Sin enbargo, si vamos a restaurar desde un estado anterior,
            // entonces no necesitamos realizar nada, asi que simplemente retornamos
            // o podriamos cambiar y actualizar algunas cosas del fragment.
            if (savedInstanceState != null) {
                return;
            }

            // Creamos una instancia del fragment
            cajeros = new Cajeros(Cajeros.BANELCO);

            // En caso de que la activity comenzo con algunas instrucciones en el intent,
            // podemos tomarlos y pasarcelo a nuestro fragment
            cajeros.setArguments(getIntent().getExtras());

            // agregamos el fragment a nuestro conteiner
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.conteinerbanelco, cajeros).commit();
        }
    }

    @Override
    public void onCajeroSeleccionado(int position, int idCajero, int eleccion) {

        // Cuando el usuario elige uno de los elementos realizaremos algunas accion
        // Capturamos la seleccion que se realizo sobre nuestro layout
        CajeroFragment cajeroFragment = (CajeroFragment)
                getSupportFragmentManager().findFragmentById(R.id.cajero);

        if (cajeroFragment != null) {
            // Si el cajero se puede ver en un segundo fragment en nuestra vista, realizamos
            // una llamada para actualizar el contenido de la vista
            cajeroFragment.updateCajeroView(position, idCajero, eleccion);

        } else {
            // Si no es posible verlo en un segundo fragment, entonces actulizamos nuestra vista
            // actual para que intercambien el fragment, por el que deseamos observar

            // Creamos el fragment, y le pasamos los argunmentos sobre el cajero seleccionado
            CajeroFragment nuevoCajeroFragment = new CajeroFragment();
            Bundle argumentos = new Bundle();
            argumentos.putInt(CajeroFragment.ARG_POSITION, position);
            argumentos.putInt(CajeroFragment.TIPO_CAJERO, eleccion);
            argumentos.putInt(CajeroFragment.ID_CAJERO, idCajero);

            Log.d(TAG, "id cajero " +  idCajero + " posicion " + position +  " tipo cajero " + eleccion);


            nuevoCajeroFragment.setArguments(argumentos);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // Reemplazamos cualquier fragment seleccionado anteriormente por el este,
            // y lo agregamos a la transaccion para que el usuario pueda navegar hacia atras
            transaction.replace(R.id.conteinerbanelco, nuevoCajeroFragment);
            transaction.addToBackStack(null);

            // Realizamos la transaccion
            transaction.commit();
        }
    }
}

