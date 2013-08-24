package com.aprendiendodeandroid.bancos.rosario;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class ManageTabsActivity extends TabActivity {

	  /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // le quitamos la barra de titulo a la aplicacion
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
        TabHost tabHost = getTabHost();
        
        // Tab for cajeros en general
        TabSpec generalCajeros = tabHost.newTabSpec("Todos los cajeros");
        generalCajeros.setIndicator("", getResources().getDrawable(R.drawable.descripccion));
        Intent generalIntent = new Intent(this, AllCajerosActivity.class);
        generalCajeros.setContent(generalIntent);
        
        // Tab for cajeros Banelco
        TabSpec cajerosBanelco = tabHost.newTabSpec("Banelco");
        // setting Title and Icon for the Tab
        cajerosBanelco.setIndicator("", getResources().getDrawable(R.drawable.logo_banelco));
        Intent banelcoIntent = new Intent(this, CajerosBanelcoActivity.class);
        cajerosBanelco.setContent(banelcoIntent);
        
        // Tab for cajeros Red Link
        TabSpec cajerosLink = tabHost.newTabSpec("Red Link");
        cajerosLink.setIndicator("", getResources().getDrawable(R.drawable.pagos_link));
        Intent redLinkIntent = new Intent(this, CajerosLinkActivity.class);
        cajerosLink.setContent(redLinkIntent);
        
        // Tab for CreditosActivity
        TabSpec creditos = tabHost.newTabSpec("Creditos");
        creditos.setIndicator("", getResources().getDrawable(R.drawable.desarrollador));
        Intent creditosIntent = new Intent(this, CreditosActivity.class);
        creditos.setContent(creditosIntent);
        
        // Tab for Mapa General
        TabSpec mapageneral = tabHost.newTabSpec("MapaGeneral");
        mapageneral.setIndicator("", getResources().getDrawable(R.drawable.google_maps));
        Intent mapaIntent = new Intent(this, MapaGeneral.class);
        mapageneral.setContent(mapaIntent);
        
        // Adding all TabSpec to TabHost
        tabHost.addTab(mapageneral); // Adding mapa general
        tabHost.addTab(cajerosBanelco); // Adding banelco tab
        tabHost.addTab(cajerosLink); // Adding link tab
        tabHost.addTab(generalCajeros); // Adding general tab
        tabHost.addTab(creditos); // Adding creditos tab

    }
    

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.menu.options_menu:
                startActivity(new Intent(this, EditPreferences.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
*/

}
