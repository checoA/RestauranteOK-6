package com.restaurante.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurante.repository.ConfiguracionesRepository;

import java.util.List;

import com.restaurante.entity.Configuraciones;

@Service
public class ConfiguracionesService {
	@Autowired
	private ConfiguracionesRepository cr;
	
	public Configuraciones setConfiguracion(Configuraciones c) {
		return cr.save(c);
	}
	
	public Configuraciones getConfiguraciones(String nombre) {
		switch(nombre) {
		case "Efectivo":
			nombre = "Aumento a efectivo"; 
			break;
		case "Tarjeta":
			nombre = "Aumento a tarjeta";
			break;
		case "Cheque":
			nombre = "Aumento a cheque";
			break;
		case "Transferencia":
			nombre = "Aumento a transferencia";
			break;
		case "Otros":
			nombre = "Aumento a otros";
			break;
		}
		return cr.findByNombre(nombre);
	}
	
	public List<Configuraciones> getConfiguraciones(){
		return cr.findAll();
	}
	
}
