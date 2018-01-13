package com.restaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurante.entity.Gastos;
import com.restaurante.repository.GastosRepository;

@Service
public class GastosService {
	@Autowired
	private GastosRepository gr;
	
	public Gastos create(Gastos g) {
		return gr.save(g);
	}
	
	public List<Gastos> get(String fecha_inicio,String fecha_fin) {
		return gr.findByFechaGreaterThanAndFechaLessThan(fecha_inicio, fecha_fin);
	}
	
	public List<Gastos> get(String fecha_inicio,String fecha_fin,String nombre){
		return gr.findByFechaGreaterThanAndFechaLessThanAndNombre(fecha_inicio, fecha_fin, nombre);
	}
}
