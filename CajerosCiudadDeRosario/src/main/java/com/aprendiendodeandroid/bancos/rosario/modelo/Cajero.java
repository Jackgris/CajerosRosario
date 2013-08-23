package com.aprendiendodeandroid.bancos.rosario.modelo;

public class Cajero {

	private String direccion;
	private String telefonoBanco;
	private double longitud;
	private double latitud;
	private String nombreBanco;
	private String tipoCajero;
    private int idCajero;
    private String calle;
    private int altura;

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public String getTipoCajero() {
        return tipoCajero;
    }

    public int getIdCajero() {
        return idCajero;
    }

    public void setIdCajero(int idCajero) {
        this.idCajero = idCajero;
    }

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
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	public String getNombreBanco() {
		return nombreBanco;
	}
	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}
	public String isTipoCajero() {
		return tipoCajero;
	}
	public void setTipoCajero(String tipoCajero) {
		this.tipoCajero = tipoCajero;
	}
}
