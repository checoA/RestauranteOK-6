package com.restaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurante.entity.Tipos_gasto;
import com.restaurante.repository.TiposGastoRepository;

@Service
public class TiposGastoService {
	@Autowired
	private TiposGastoRepository tgr;
	
	public Tipos_gasto create(Tipos_gasto t) {
		return tgr.save(t);
	}
	
	public List<Tipos_gasto> retrieve(){
		return tgr.findAll();
	}
	
	public boolean delete(Tipos_gasto t) {
		try {
			tgr.delete(t);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
}
