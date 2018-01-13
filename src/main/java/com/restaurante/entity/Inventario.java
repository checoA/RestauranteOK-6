package com.restaurante.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Inventario {
	@GeneratedValue(
		    strategy= GenerationType.AUTO, 
		    generator="native"
		)
		@GenericGenerator(
		    name = "native", 
		    strategy = "native"
		)
	private Long codigo;
	
	private Set<Productos_Inventario> inventarios_productos;
	
	@Column(name="nombre")
	private String nombre;
	
	private float precio;
	
	@Column(name="existencia")
	private float existencia;
	
	@Column(name="stock")
	private float stock;
	
	public Inventario() {
		
	}

	@Id
	@GeneratedValue(
		    strategy= GenerationType.AUTO, 
		    generator="native"
		)
		@GenericGenerator(
		    name = "native", 
		    strategy = "native"
		)
	@Column(name="codigo")
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name="precio")
	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio_compra) {
		this.precio = precio_compra;
	}

	public float getExistencia() {
		return existencia;
	}

	public void setExistencia(float existencia) {
		this.existencia = existencia;
	}

	public float getStock() {
		return stock;
	}

	public void setStock(float stock) {
		this.stock = stock;
	}

	@JsonIgnore
	@OneToMany(mappedBy= "inventario")
	public Set<Productos_Inventario> getInventarios_productos() {
		return inventarios_productos;
	}

	public void setInventarios_productos(Set<Productos_Inventario> inventarios_productos) {
		this.inventarios_productos = inventarios_productos;
	}
	
}
