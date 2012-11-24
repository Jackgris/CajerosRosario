package com.aprendiendodeandroid.bancos.rosario;




import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class ManageTabsActivity extends TabActivity {

	  /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        TabHost tabHost = getTabHost();
        
        // Tab for cajeros en general
        TabSpec generalCajeros = tabHost.newTabSpec("Todos los cajeros");
        generalCajeros.setIndicator("Cajeros", getResources().getDrawable(R.drawable.icon_photos_tab));
        Intent generalIntent = new Intent(this, AllCajerosActivity.class);
        generalCajeros.setContent(generalIntent);
        
        // Tab for cajeros Banelco
        TabSpec cajerosBanelco = tabHost.newTabSpec("Banelco");
        // setting Title and Icon for the Tab
        cajerosBanelco.setIndicator("Banelco", getResources().getDrawable(R.drawable.icon_songs_tab));
        Intent banelcoIntent = new Intent(this, CajerosBanelcoActivity.class);
        cajerosBanelco.setContent(banelcoIntent);
        
        // Tab for cajeros Red Link
        TabSpec cajerosLink = tabHost.newTabSpec("Red Link");
        cajerosLink.setIndicator("Red Link", getResources().getDrawable(R.drawable.icon_videos_tab));
        Intent redLinkIntent = new Intent(this, CajerosLinkActivity.class);
        cajerosLink.setContent(redLinkIntent);
        
     // Tab for CreditosActivity
        TabSpec creditos = tabHost.newTabSpec("CreditosActivity");
        creditos.setIndicator("CreditosActivity", getResources().getDrawable(R.drawable.icon_photos_tab));
        Intent creditosIntent = new Intent(this, CreditosActivity.class);
        creditos.setContent(creditosIntent);
        
     // Tab for Mapa General
        TabSpec mapageneral = tabHost.newTabSpec("MapaGeneral");
        mapageneral.setIndicator("MapaGeneral", getResources().getDrawable(R.drawable.icon_photos_tab));
        Intent mapaIntent = new Intent(this, MapaGeneral.class);
        mapageneral.setContent(mapaIntent);
        
        // Adding all TabSpec to TabHost
        tabHost.addTab(generalCajeros); // Adding general tab
        tabHost.addTab(cajerosBanelco); // Adding banelco tab
        tabHost.addTab(cajerosLink); // Adding link tab
        tabHost.addTab(creditos); // Adding creditos tab
        tabHost.addTab(mapageneral); // Adding mapa general
    }
    

/*    @Override
>>>>>>> primerosCambios
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
<<<<<<< HEAD

=======
*/

}
