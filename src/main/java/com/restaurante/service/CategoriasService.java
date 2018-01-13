package com.restaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurante.entity.Categorias;
import com.restaurante.repository.CategoriasRepository;

@Service
public class CategoriasService {
	@Autowired
	private CategoriasRepository
	cr;
	
	public List<Categorias> findAll(){
		return cr.findAll();
	}
	
	public Categorias save(Categorias c) {
		return cr.save(c);
	}
	
	public boolean delete(Categorias c) {
		try {
			cr.delete(c);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
}
