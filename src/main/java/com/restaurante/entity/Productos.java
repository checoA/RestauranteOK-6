package com.restaurante.entity;

//import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;


@Entity
public class Productos {
	@GeneratedValue(
		    strategy= GenerationType.AUTO, 
		    generator="native"
		)
		@GenericGenerator(
		    name = "native", 
		    strategy = "native"
		)
	private Long id;
	
	
	private Set<Productos_Inventario> productos_inventario;
	
	
	private String nombre;
	
	@Column(name="precio")
	private float precio;
	
	private Categorias categoria;
	
	public Productos() {
		
	}
	
	public Productos(String nombre, float precio) {
		this.setNombre(nombre);
		this.setPrecio(precio);
		//productos_inventario = new HashSet<>();
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
	@Column(name="id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="nombre")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	@ManyToOne
	public Categorias getCategoria() {
		return categoria;
	}

	public void setCategoria(Categorias categoria) {
		this.categoria = categoria;
	}

	//@JsonIgnore
	@OneToMany(mappedBy = "productos")
	public Set<Productos_Inventario> getProducto() {
		return productos_inventario;
	}

	public void setProducto(Set<Productos_Inventario> producto) {
		this.productos_inventario = producto;
	}
}
