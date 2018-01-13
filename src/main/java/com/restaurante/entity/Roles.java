package com.restaurante.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Roles {
	@Id
	@GeneratedValue(
		    strategy= GenerationType.AUTO, 
		    generator="native"
		)
		@GenericGenerator(
		    name = "native", 
		    strategy = "native"
		)
	@Column(name="id_rol")
	private Long id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="agregar_usuarios")
	private boolean agregar_usuarios;
	
	@Column(name="agregar_platillos")
	private boolean agregar_platillos;
	
	@Column(name="agregar_inventario")
	private boolean agregar_inventario;
	
	@Column(name="visualizar_reportes")
	private boolean visualizar_reportes;
	
	@Column(name="eliminar_usuarios")
	private boolean eliminar_usuarios;
	
	@Column(name="eliminar_platillos")
	private boolean eliminar_platillos;
	
	@Column(name="eliminar_del_inventario")
	private boolean eliminar_del_inventario;
	
	@Column(name="modificar_usuarios")
	private boolean modificar_usuarios;
	
	@Column(name="modificar_platillos")
	private boolean modificar_platillos;
	
	@Column(name="modificar_inventario")
	private boolean modificar_inventario;
	
	@Column(name="ver_usuarios")
	private boolean ver_usuarios;
	
	@Column(name="ver_inventario")
	private boolean ver_inventario;
	
	@Column(name="agregar_paquetes")
	private boolean agregar_paquetes;
	
	@Column(name="modificar_paquetes")
	private boolean modificar_paquetes;
	
	@Column(name="eliminar_paquetes")
	private boolean eliminar_paquetes;
	
	@Column(name="realizar_orden")
	private boolean realizar_orden;
	
	@Column(name="ver_mesas")
	private boolean ver_mesas;
	
	@Column(name="ver_ordenes")
	private boolean ver_ordenes;
	
	@Column(name="cobrar")
	private boolean cobrar;
	
	@Column(name="agregar_mesas")
	private boolean agregar_mesas;
	
	@Column(name="visualizar_estadisticas")
	private boolean visualizar_estadisticas;
	
	@Column(name="ver_compras")
	private boolean ver_compras;
	
	@Column(name="ver_gastos")
	private boolean ver_gastos;
	
	@Column(name="ver_platillos")
	private boolean ver_platillos;
	
	@Column(name="ver_paquetes")
	private boolean ver_paquetes;
	
	@Column(name = "ver_barra")
	private boolean ver_barra;
	
	@Column(name="empresa")
	private boolean empresa;
	
	public Roles(Long id) {
		this.setId(id);
	}
	
	public Roles() {
		
	}
	
	public Roles(String nombre,String descripcion,boolean agregar_usuarios,boolean agregar_platillos,boolean agregar_inventario,boolean visualizar_reportes,boolean eliminar_usuarios,boolean eliminar_platillos,boolean eliminar_del_inventario,boolean modificar_usuarios,boolean modificar_platillos,boolean modificar_inventario,boolean ver_usuarios,boolean ver_inventario,boolean agregar_paquetes,boolean modificar_paquetes,boolean eliminar_paquetes,boolean realizar_orden,boolean ver_mesas,boolean cobrar,boolean agregar_mesas,boolean visualizar_estadisticas,boolean ver_compras,boolean ver_gastos,boolean ver_platillos,boolean ver_paquetes) {
		this.setAgregar_usuarios(agregar_usuarios);
		this.setAgregar_platillos(agregar_platillos);
		this.setAgregar_inventario(agregar_inventario);
		this.setVisualizar_reportes(visualizar_reportes);
		this.setEliminar_usuarios(eliminar_usuarios);
		this.setEliminar_platillos(eliminar_platillos);
		this.setEliminar_del_inventario(eliminar_del_inventario);
		this.setModificar_usuarios(modificar_usuarios);
		this.setModificar_platillos(modificar_platillos);
		this.setModificar_inventario(modificar_inventario);
		this.setVer_usuarios(ver_usuarios);
		this.setVer_inventario(ver_inventario);
		this.setAgregar_paquetes(agregar_paquetes);
		this.setModificar_paquetes(modificar_paquetes);
		this.setEliminar_paquetes(eliminar_paquetes);
		this.setRealizar_orden(realizar_orden);
		this.setVer_mesas(ver_mesas);
		this.setCobrar(cobrar);
		this.setAgregar_mesas(agregar_mesas);
		this.setVisualizar_estadisticas(visualizar_estadisticas);
		this.setVer_compras(ver_compras);
		this.setVer_gastos(ver_gastos);
		this.setVer_platillos(ver_platillos);
		this.setVer_paquetes(ver_paquetes);
		this.setNombre(nombre);
		this.setDescripcion(descripcion);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isAgregar_usuarios() {
		return agregar_usuarios;
	}

	public void setAgregar_usuarios(boolean agregar_usuarios) {
		this.agregar_usuarios = agregar_usuarios;
	}

	public boolean isAgregar_platillos() {
		return agregar_platillos;
	}

	public void setAgregar_platillos(boolean agregar_platillos) {
		this.agregar_platillos = agregar_platillos;
	}

	public boolean isAgregar_inventario() {
		return agregar_inventario;
	}

	public void setAgregar_inventario(boolean agregar_inventario) {
		this.agregar_inventario = agregar_inventario;
	}

	public boolean isVisualizar_reportes() {
		return visualizar_reportes;
	}

	public void setVisualizar_reportes(boolean visualizar_reportes) {
		this.visualizar_reportes = visualizar_reportes;
	}

	public boolean isEliminar_usuarios() {
		return eliminar_usuarios;
	}

	public void setEliminar_usuarios(boolean eliminar_usuarios) {
		this.eliminar_usuarios = eliminar_usuarios;
	}

	public boolean isEliminar_platillos() {
		return eliminar_platillos;
	}

	public void setEliminar_platillos(boolean eliminar_platillos) {
		this.eliminar_platillos = eliminar_platillos;
	}

	public boolean isEliminar_del_inventario() {
		return eliminar_del_inventario;
	}

	public void setEliminar_del_inventario(boolean eliminar_del_inventario) {
		this.eliminar_del_inventario = eliminar_del_inventario;
	}

	public boolean isModificar_usuarios() {
		return modificar_usuarios;
	}

	public void setModificar_usuarios(boolean modificar_usuarios) {
		this.modificar_usuarios = modificar_usuarios;
	}

	public boolean isModificar_inventario() {
		return modificar_inventario;
	}

	public void setModificar_inventario(boolean modificar_inventario) {
		this.modificar_inventario = modificar_inventario;
	}

	public boolean isVer_usuarios() {
		return ver_usuarios;
	}

	public void setVer_usuarios(boolean ver_usuarios) {
		this.ver_usuarios = ver_usuarios;
	}

	public boolean isVer_inventario() {
		return ver_inventario;
	}

	public void setVer_inventario(boolean ver_inventario) {
		this.ver_inventario = ver_inventario;
	}

	public boolean isAgregar_paquetes() {
		return agregar_paquetes;
	}

	public void setAgregar_paquetes(boolean agregar_paquetes) {
		this.agregar_paquetes = agregar_paquetes;
	}

	public boolean isModificar_paquetes() {
		return modificar_paquetes;
	}

	public void setModificar_paquetes(boolean modificar_paquetes) {
		this.modificar_paquetes = modificar_paquetes;
	}

	public boolean isEliminar_paquetes() {
		return eliminar_paquetes;
	}

	public void setEliminar_paquetes(boolean eliminar_paquetes) {
		this.eliminar_paquetes = eliminar_paquetes;
	}

	public boolean isRealizar_orden() {
		return realizar_orden;
	}

	public void setRealizar_orden(boolean realizar_orden) {
		this.realizar_orden = realizar_orden;
	}

	public boolean isVer_mesas() {
		return ver_mesas;
	}

	public void setVer_mesas(boolean ver_mesas) {
		this.ver_mesas = ver_mesas;
	}

	public boolean isVer_ordenes() {
		return ver_ordenes;
	}

	public void setVer_ordenes(boolean ver_ordenes) {
		this.ver_ordenes = ver_ordenes;
	}

	public boolean isCobrar() {
		return cobrar;
	}

	public void setCobrar(boolean cobrar) {
		this.cobrar = cobrar;
	}

	public boolean isAgregar_mesas() {
		return agregar_mesas;
	}

	public void setAgregar_mesas(boolean agregar_mesas) {
		this.agregar_mesas = agregar_mesas;
	}

	public boolean isVisualizar_estadisticas() {
		return visualizar_estadisticas;
	}

	public void setVisualizar_estadisticas(boolean visualizar_estadisticas) {
		this.visualizar_estadisticas = visualizar_estadisticas;
	}

	public boolean isVer_compras() {
		return ver_compras;
	}

	public void setVer_compras(boolean ver_compras) {
		this.ver_compras = ver_compras;
	}

	public boolean isVer_gastos() {
		return ver_gastos;
	}

	public void setVer_gastos(boolean ver_gastos) {
		this.ver_gastos = ver_gastos;
	}

	public boolean isVer_platillos() {
		return ver_platillos;
	}

	public void setVer_platillos(boolean ver_platillos) {
		this.ver_platillos = ver_platillos;
	}

	public boolean isVer_paquetes() {
		return ver_paquetes;
	}

	public void setVer_paquetes(boolean ver_paquetes) {
		this.ver_paquetes = ver_paquetes;
	}

	public boolean isModificar_platillos() {
		return modificar_platillos;
	}

	public void setModificar_platillos(boolean modificar_platillos) {
		this.modificar_platillos = modificar_platillos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isVer_barra() {
		return ver_barra;
	}

	public void setVer_barra(boolean ver_barra) {
		this.ver_barra = ver_barra;
	}

	public boolean isEmpresa() {
		return empresa;
	}

	public void setEmpresa(boolean empresa) {
		this.empresa = empresa;
	}

}
