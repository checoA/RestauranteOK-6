package com.restaurante.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@IdClass(ProductosInventarioPK.class)
public class Productos_Inventario implements Serializable{
	
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
	
	@Id
	@Column(name="productos_id",insertable=false,updatable=false)
	private Long productos_id;
	
	@Id
	@Column(name="inventario_codigo",insertable=false, updatable=false)
	private Long inventario_codigo;
	
	@ManyToOne
	@JoinColumn(name="productos_id")
	private Productos productos;
	
	@ManyToOne
	@JoinColumn(name="inventario_codigo")
	private Inventario inventario;
	
	private float cantidad;

	
	@JsonIgnore
	//@ManyToOne
	//@JoinColumn(name="productos_id")
	public Productos getProductos() {
		return productos;
	}

	public void setProductos(Productos productos) {
		this.productos = productos;
	}

	
	//@JsonIgnore
	//@ManyToOne
	//@JoinColumn(name="inventario_codigo")
	public Inventario getInventario() {
		return inventario;
	}

	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}

	//@Column(name="cantidad")
	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
