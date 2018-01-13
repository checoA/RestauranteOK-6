package com.restaurante.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Detalle_ordenes {
	@Id
	@GeneratedValue(
		    strategy= GenerationType.AUTO, 
		    generator="native"
		)
		@GenericGenerator(
		    name = "native", 
		    strategy = "native"
		)
	@Column(name="id")
	private Long id;
	
	@ManyToOne
	private Ordenes orden;
	
	@ManyToOne
	private Productos producto;
	
	@Column(name="cantidad")
	private float cantidad;
	
	@Column(name="precio")
	private float precio;
	
	@ManyToOne
	private Estados estado;
	
	@Column(name="usuario")
	private String usuario;
	
	@Column(name="visto")
	private boolean visto;
	
	public Detalle_ordenes() {
		
	}
	
	public Detalle_ordenes(int cantidad,int precio) {
		this.setCantidad(cantidad);
		this.setPrecio(precio);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Productos getProducto() {
		return producto;
	}

	public void setProducto(Productos producto) {
		this.producto = producto;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public Estados getEstado() {
		return estado;
	}

	public void setEstado(Estados estado) {
		this.estado = estado;
	}

	public Ordenes getOrden() {
		return orden;
	}

	public void setOrden(Ordenes orden) {
		this.orden = orden;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the visto
	 */
	public boolean isVisto() {
		return visto;
	}

	/**
	 * @param visto the visto to set
	 */
	public void setVisto(boolean visto) {
		this.visto = visto;
	}
}
