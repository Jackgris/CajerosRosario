package com.aprendiendodeandroid.bancos.rosario.utiles;

import com.aprendiendodeandroid.bancos.rosario.modelo.Cajero;

import java.util.List;

public interface LocalizacionHelper {

	public void posicionActual();
	public List<Cajero> posicionCajerosBanelco();
	public List<Cajero> posicionCajerosRedLink();
	public List<Cajero> posicionTodosLosCajeros();
}
