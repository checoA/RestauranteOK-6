package com.restaurante.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;


@Entity
public class Ordenes {
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
	
	@Column(name="total")
	private float total;
	
	@Column(name="subtotal")
	private float subtotal;
	
	@Column(name="fecha")
	private String fecha;

	@Column(name="num_mesa")
	private int numMesa;
	
	@Column(name="num_para_llevar")
	private int numParaLlevar;
	
	@ManyToOne
	private Estados estado;
	
	@ManyToOne
	private Tipos_pago tipoPago;
	
	@Column(name="numPersonas")
	private int numPersonas;
	
	@Column(name="aumento")
	private float aumento;
	
	@Column(name="pago")
	private float pago;
	
	@Column(name="visto")
	private boolean visto;
	
	public Ordenes() {
		
	}
	
	public Ordenes(float total,float subtotal) {
		this.setTotal(total);
		this.setSubtotal(subtotal);
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

	public float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}

	public String getFecha() {
		return fecha;
	}

	public void setDate(String fecha) {
		this.fecha = fecha;
	}
	
	public Estados getEstado() {
		return estado;
	}

	public void setEstado(Estados estado) {
		this.estado = estado;
	}

	public int getNumMesa() {
		return numMesa;
	}

	public void setNumMesa(int num_mesa) {
		this.numMesa = num_mesa;
	}

	public Tipos_pago getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(Tipos_pago tipoPago) {
		this.tipoPago = tipoPago;
	}

	public int getNumPersonas() {
		return numPersonas;
	}

	public void setNumPersonas(int numPersonas) {
		this.numPersonas = numPersonas;
	}

	public float getAumento() {
		return aumento;
	}

	public void setAumento(float aumento) {
		this.aumento = aumento;
	}

	public int getNumParaLlevar() {
		return numParaLlevar;
	}

	public void setNumParaLlevar(int numParaLlevar) {
		this.numParaLlevar = numParaLlevar;
	}

	public float getPago() {
		return pago;
	}

	public void setPago(float pago) {
		this.pago = pago;
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
