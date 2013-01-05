package com.aprendiendodeandroid.bancos.rosario.utiles;
import java.util.List;

import com.aprendiendodeandroid.bancos.rosario.modelo.Cajeros;

public interface LocalizacionHelper {

	public void posicionActual();
	public List<Cajeros> posicionCajerosBanelco();
	public List<Cajeros> posicionCajerosRedLink();
	public List<Cajeros> posicionTodosLosCajeros();
}
