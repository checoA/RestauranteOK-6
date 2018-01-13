package com.restaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurante.repository.TiposPagoRepository;
import com.restaurante.entity.Tipos_pago;

@Service
public class TiposPagoService {
	@Autowired
	private TiposPagoRepository tpr;
	
	public List<Tipos_pago> findAll(){
		return tpr.findAll();
	}
	
}
