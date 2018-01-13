package com.restaurante.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurante.repository.TonosRepository;
import com.restaurante.entity.Tonos;

import java.util.List;

@Service
public class TonosService {
	@Autowired
	private TonosRepository tr;
	
	public List<Tonos> findAll(){
		return tr.findAll();
	}
	
	public Tonos getEstablecido() {
		return tr.findByEstablecido(true);
	}
	
	public Tonos findByID(Long id) {
		return tr.findById(id).get();
	}
	
	public void reset() {
		List<Tonos> tonos = tr.findAll();
		for(int i = 0; i<tonos.size();i++) {
			tonos.get(i).setEstablecido(false);
		}
		tr.saveAll(tonos);
	}
	
	public Tonos save(Tonos t) {
		return tr.save(t);
	}
}
