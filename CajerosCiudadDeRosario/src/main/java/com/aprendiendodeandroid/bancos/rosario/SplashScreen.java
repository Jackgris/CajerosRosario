package com.aprendiendodeandroid.bancos.rosario;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

public class SplashScreen extends FragmentActivity{
	protected EstadoSalvado data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // le quitamos la barra de titulo a la aplicacion
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splashscreen);


        this.data = (EstadoSalvado) getLastCustomNonConfigurationInstance();
        if (this.data == null) {
            this.data = new EstadoSalvado();
        }
        if (this.data.doInit) {
            doInit();
        }
    }
    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return this.data;
    }
    // y aca configuramos a la vista que vamos a pasar
    protected void startNextActivity() {
        Intent intent = new Intent(this, ManageTabsActivity.class);
        this.startActivity(intent);
        this.finish();
    }
    // Este metodoes el que maneja el delay en la carga del splashscreen
    protected void doInit() {
        this.data.doInit = false;
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                //TODO: Your application init goes here.
				startNextActivity();
            }
        }, 3000);
    }
    private class EstadoSalvado {
        public boolean doInit = true;
    }
}
