package com.restaurante.entity;

import java.util.List;

public class Menu
{
	private String Accion;
	private String Texto;
	private String DataTarget;
	private String Funcion;
	private List<Menu> Submenu;

	public Menu() {
	}

	public String getAccion() {
		return Accion;
	}

	public void setAccion(String accion) {
		Accion = accion;
	}

	public String getTexto() {
		return Texto;
	}

	public void setTexto(String texto) {
		Texto = texto;
	}

	public String getDataTarget() {
		return DataTarget;
	}

	public void setDataTarget(String dataTarget) {
		DataTarget = dataTarget;
	}

	public String getFuncion() {
		return Funcion;
	}

	public void setFuncion(String funcion) {
		Funcion = funcion;
	}

	public List<Menu> getSubmenu() {
		return Submenu;
	}

	public void setSubmenu(List<Menu> submenu) {
		Submenu = submenu;
	}
}