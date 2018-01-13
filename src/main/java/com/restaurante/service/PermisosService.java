package com.restaurante.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurante.entity.Permisos;
import com.restaurante.repository.PermisosRepository;

@Service
public class PermisosService {
	@Autowired
	private PermisosRepository pr;
	
	public Permisos save(Permisos permiso) {
		return pr.save(permiso);
	}
}
