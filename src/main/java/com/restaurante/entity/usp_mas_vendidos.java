package com.restaurante.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

public class usp_mas_vendidos {
	private BigDecimal cantidad;
	private String nombre;
	private BigInteger categoria;
	
	public usp_mas_vendidos(String nombre,BigDecimal cantidad,BigInteger categoria) {
		this.setCantidad(cantidad);
		this.setNombre(nombre);
		this.setCategoria(categoria);
	}
	
	public BigDecimal getCantidad() {
		return cantidad;
	}
	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigInteger getCategoria() {
		return categoria;
	}

	public void setCategoria(BigInteger categoria) {
		this.categoria = categoria;
	}
	
}
