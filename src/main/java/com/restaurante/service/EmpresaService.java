package com.restaurante.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurante.entity.Empresa;
import com.restaurante.repository.EmpresaRepository;

@Service
public class EmpresaService {
	@Autowired
	private EmpresaRepository er;
	
	public Empresa find() {
		return er.findAll().get(0);
	}
	
	public Empresa save(Empresa empresa) {
		return er.save(empresa);
	}
}
