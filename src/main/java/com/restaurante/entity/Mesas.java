package com.restaurante.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Mesas {
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
	
	@Column(name="num_mesa")
	private int numMesa;

	@ManyToOne
	private Estados estado;
	
	public Mesas() {
		
	}

	public Mesas(Long num_mesa) {
		this.setNum_mesa(num_mesa.intValue());
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public int getNum_mesa() {
		return numMesa;
	}

	public void setNum_mesa(int num_mesa) {
		this.numMesa = num_mesa;
	}

	public Estados getEstado() {
		return estado;
	}

	public void setEstado(Estados estado) {
		this.estado = estado;
	}

}
