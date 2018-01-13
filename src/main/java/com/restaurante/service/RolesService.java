package com.restaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurante.entity.Roles;
import com.restaurante.repository.RolesRepository;

@Service
public class RolesService {
	@Autowired
	private RolesRepository rr;
	
	public List<Roles> findAll(){
		List<Roles> completa = rr.findAll();
		return completa.subList(1, completa.size());
	}
	
}
