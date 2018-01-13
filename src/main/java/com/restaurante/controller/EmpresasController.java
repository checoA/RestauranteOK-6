package com.restaurante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.restaurante.entity.Empresa;
import com.restaurante.entity.Serializer;
import com.restaurante.service.EmpresaService;

@RestController
public class EmpresasController {
	@Autowired
	private EmpresaService es;
	
	@RequestMapping(value="/empresa",method=RequestMethod.GET)
	@ResponseBody
	public Empresa obtenerEmpresa() {
		return es.find();
	}
	
	@RequestMapping(value="/saveEmpresa",method=RequestMethod.PUT)
	@ResponseBody
	public Serializer guardarEmpresa(@RequestBody Empresa e) {
		System.out.println(e.getId());
		Serializer result = new Serializer();
		if(es.save(e) != null) {
			result.setCreated(true);
		}
		else {
			result.setCreated(false);
		}
		return result;
	}
	
}
