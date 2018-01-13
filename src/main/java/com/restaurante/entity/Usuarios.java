package com.restaurante.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Usuarios {
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
	
	@Column(name="usuario")
	private String user;
	
	@Column(name="password")
	private String password;
	
	private transient String confirmarPassword;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="apepat")
	private String apepat;
	
	@Column(name="apemat")
	private String apemat;
	
	@Column(name="correo")
	private String correo;
	
	@ManyToOne
	private Permisos permiso;

	public Usuarios() {
		
	}
	
	public Usuarios(String user) {
		this.setUser(user);
	}
	
	public Usuarios(Long id) {
		this.setId(id);
	}
	
	public Usuarios(String user,String password,String nombre,String apepat,String apemat,String correo) {
		this.setUser(user);
		this.setPassword(password);
		this.setNombre(nombre);
		this.setApepat(apepat);
		this.setApemat(apemat);
		this.setCorreo(correo);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmarPassword() {
		return confirmarPassword;
	}

	public void setConfirmarPassword(String confirmarPassword) {
		this.confirmarPassword = confirmarPassword;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApepat() {
		return apepat;
	}

	public void setApepat(String apepat) {
		this.apepat = apepat;
	}

	public String getApemat() {
		return apemat;
	}

	public void setApemat(String apemat) {
		this.apemat = apemat;
	}

	public Permisos getPermiso() {
		return permiso;
	}

	public void setPermiso(Permisos permiso) {
		this.permiso = permiso;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
}
