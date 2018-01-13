package com.restaurante.entity;

import java.util.List;

public class Serializer {
	private boolean login;
	private boolean created;
	private boolean mesaCreated;
	private boolean deleted;
	private boolean updated;
	private boolean entregar;
	private boolean entregado;
	private boolean exist;
	private Usuario usuario;
	private List<Menu> permisos;
	private String mensaje;
	
	public Serializer() {

	}
	
	public Serializer(boolean login,Usuario user,String mensaje) {
		this.setLogin(login);
		this.setUsuario(user);
		this.setMensaje(mensaje);
	}

	public boolean isLogin() {
		return login;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public boolean isCreated() {
		return created;
	}

	public void setCreated(boolean created) {
		this.created = created;
	}

	public boolean isExist() {
		return exist;
	}

	public void setExist(boolean exist) {
		this.exist = exist;
	}

	public List<Menu> getPermisos() {
		return permisos;
	}

	public void setPermisos(List<Menu> permisos) {
		this.permisos = permisos;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public boolean isUpdated() {
		return updated;
	}

	public void setUpdated(boolean updated) {
		this.updated = updated;
	}

	public boolean isEntregar() {
		return entregar;
	}

	public void setEntregar(boolean entregar) {
		this.entregar = entregar;
	}

	public boolean isEntregado() {
		return entregado;
	}

	public void setEntregado(boolean entregado) {
		this.entregado = entregado;
	}

	public boolean isMesaCreated() {
		return mesaCreated;
	}

	public void setMesaCreated(boolean mesaCreated) {
		this.mesaCreated = mesaCreated;
	}
}
