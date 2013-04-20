package com.aprendiendodeandroid.bancos.rosario.utiles;
import java.util.List;

import com.aprendiendodeandroid.bancos.rosario.modelo.Cajero;

public interface LocalizacionHelper {

	public void posicionActual();
	public List<Cajero> posicionCajerosBanelco();
	public List<Cajero> posicionCajerosRedLink();
	public List<Cajero> posicionTodosLosCajeros();
}
