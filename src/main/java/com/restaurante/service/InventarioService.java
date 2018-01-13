package com.restaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurante.repository.InventarioRepository;
import com.restaurante.entity.Inventario;

@Service
public class InventarioService {
	@Autowired
	private InventarioRepository ir;

	public List<Inventario> findAll() {
		return ir.findAll();
	}

	public Inventario save(Inventario i) {
		return ir.save(i);
	}

	public boolean delete(Inventario i) {
		try {
			ir.delete(i);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
