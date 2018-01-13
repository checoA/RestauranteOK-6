package com.restaurante.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Ventas {
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
	private Tipos_pago tipo_pago;
	
	@Column(name="total_venta")
	private float total;
	
	public Ventas() {
		
	}
	
	public Ventas(float total) {
		this.setTotal(total);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public Tipos_pago getTipo_pago() {
		return tipo_pago;
	}

	public void setTipo_pago(Tipos_pago tipo_pago) {
		this.tipo_pago = tipo_pago;
	}
}
