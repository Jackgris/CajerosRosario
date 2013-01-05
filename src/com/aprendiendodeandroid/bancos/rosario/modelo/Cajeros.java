package com.aprendiendodeandroid.bancos.rosario.modelo;

public class Cajeros {

	private String direccion;
	private String telefonoBanco;
	private int longitud;
	private int latitud;
	private String nombreBanco;
	private boolean tipoCajero;
		
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefonoBanco() {
		return telefonoBanco;
	}
	public void setTelefonoBanco(String telefonoBanco) {
		this.telefonoBanco = telefonoBanco;
	}
	public int getLongitud() {
		return longitud;
	}
	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}
	public int getLatitud() {
		return latitud;
	}
	public void setLatitud(int latitud) {
		this.latitud = latitud;
	}
	public String getNombreBanco() {
		return nombreBanco;
	}
	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}
	public boolean isTipoCajero() {
		return tipoCajero;
	}
	public void setTipoCajero(boolean tipoCajero) {
		this.tipoCajero = tipoCajero;
	}
}
